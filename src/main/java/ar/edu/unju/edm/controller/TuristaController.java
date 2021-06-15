package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class TuristaController {
	private static final Log LOGGER = LogFactory.getLog(TuristaController.class);
	
	@Autowired
	@Qualifier("impturista")
	ITuristaService turistaService;
	
	@GetMapping("/turista/mostrar")
	public String cargarTurista(Model model) {
		LOGGER.info("Hola"+turistaService.crearTurista().getApellidos());
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
			return "/";
		}else {
			turistaService.guardarTurista(nuevoTurista);		
			LOGGER.info("Tamaño del Listado: "+ turistaService.obtenerTodosTuristas().size());
			return "redirect:/";
		}
	}
	
	@PostMapping("/turistaRoot/guardar")
	public String guardarNuevoTuristaRoot(@Valid @ModelAttribute("unTurista") Turista nuevoTurista, BindingResult resultado,Model model) {		
		LOGGER.info("METHOD: ingresando el metodo Guardar");
		if (resultado.hasErrors())
		{
			model.addAttribute("unTurista", nuevoTurista);
			model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
			return "/turista/mostrar";
		}else {
			turistaService.guardarTuristaRoot(nuevoTurista);		
			LOGGER.info("Tamaño del Listado: "+ turistaService.obtenerTodosTuristas().size());
			return "redirect:/turista/mostrar";
		}
	}
	
	@GetMapping("/turista/editar/{idTurista}")
	public String editarTurista(Model model, @PathVariable(name="idTurista") int id) throws Exception {		
		try {
			LOGGER.info("METHOD: ingresando editar modificar, antes de traer el Turista");
			Turista turistaEncontrado = turistaService.encontrarUnTurista(id);
			LOGGER.info("METHOD: ingresando editar modificar, traje el Turista");
			model.addAttribute("unTurista", turistaEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unCliente", turistaService.crearTurista());
			model.addAttribute("editMode", "false");
		}				
		model.addAttribute("clientes", turistaService.obtenerTodosTuristas());		
		return "turista";
	}
	
	@PostMapping("/turista/modificar")
	public String modificarTurista(@ModelAttribute("unTurista") Turista turistaModificado, Model model) {
		//try permite realizar una accion, pero si ocurre un error no se caera el programa
			try {
				turistaService.modificarTurista(turistaModificado);
				model.addAttribute("unTurista", new Turista());				
				model.addAttribute("editMode", "false");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// pasar las excepciones al html
				model.addAttribute("formUsuarioErrorMessage",e.getMessage());
				model.addAttribute("unTurista", turistaModificado);			
				model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
				model.addAttribute("editMode", "true");
			}		
			model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return "turista";
	}
	
	
	@GetMapping("/turista/eliminarTurista/{id}")
	public String eliminarTurista(Model model, @PathVariable(name="id") int id) {		
		try {			
			turistaService.eliminarTurista(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/turista/mostrar";
	}
	
	@GetMapping("/cancelar")
	public String cancelar() {
		return "redirect:/turista/mostrar";
	}
	
	@GetMapping("/turista/cancelar")
	public String turistaCancelar() {
		return "redirect:/";
	}
	
	@GetMapping("/turista/registrar")
	public String registrarTurista(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
		return("registroTurista");
	}
	
}
