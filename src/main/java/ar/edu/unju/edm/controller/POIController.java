package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.POI;
import ar.edu.unju.edm.service.IPOIService;

@Controller
public class POIController {
	private static final Log LOGGER = LogFactory.getLog(POIController.class);
	
	@Autowired
	@Qualifier("imppoi")
	IPOIService poiService;
	
	@GetMapping("/poi/mostrar")
	public String cargarPoi(Model model) {
		model.addAttribute("unPoi", poiService.crearPOI());
		model.addAttribute("pois", poiService.obtenerTodosPOIs());
		return("poi");
	}
	
	//getmapping editar poi
	
	@PostMapping("/poi/guardar")
	public String guardarNuevoPoi(@ModelAttribute("unPoi") POI nuevoPoi, BindingResult resultado, Model model) {
		if(resultado.hasErrors()) {
			model.addAttribute("unPoi", nuevoPoi);
			model.addAttribute("pois", poiService.obtenerTodosPOIs());
			return("poi");
		}
		else {
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			poiService.guardarPOI(nuevoPoi);
			LOGGER.info("Tamaño del Listado: "+ poiService.obtenerTodosPOIs().size());
			return "redirect:/poi/mostrar";
		}
	}
	
	//postmapping modificar
	
	//cancelar
	
	//eliminarpoi
}
