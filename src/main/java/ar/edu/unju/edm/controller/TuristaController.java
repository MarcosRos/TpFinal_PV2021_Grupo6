package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class TuristaController {
	private static final Log LOGGER = LogFactory.getLog(TuristaController.class);
	
	@Autowired
	//@Qualifier("impmysql")
	ITuristaService turistaService;
	
	@GetMapping("/turista/mostrar")
	public String cargarTurista(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return("turista");
	}

	@PostMapping("/turista/guardar")
	public String guardarNuevoTurista(@Valid @ModelAttribute("unTurista") Turista nuevoTurista, BindingResult resultado,Model model) {		
		LOGGER.info("METHOD: ingresando el metodo Guardar");
		if (resultado.hasErrors())
		{
			model.addAttribute("unTurista", nuevoTurista);
			model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
			return "turista";
		}else {
			turistaService.guardarTurista(nuevoTurista);		
			LOGGER.info("Tamaño del Listado: "+ turistaService.obtenerTodosTuristas().size());
			return "redirect:/turista/mostrar";
		}
	}
}
