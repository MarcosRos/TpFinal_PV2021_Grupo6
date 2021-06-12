package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.Base64;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/producto/editar/{idPOI}")		//recibe la petición desde el html o vista, enviandole idPOI
	public String editarPoi(Model model, @PathVariable(name="idPOI") int id) throws Exception {
		try {
			POI poiEncontrado = poiService.encontrarUnPOI(id);
			model.addAttribute("unPoi", poiEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unPoi", poiService.crearPOI());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("productos", poiService.obtenerTodosPOIs());
		return("poi");
	}
	
	@PostMapping(value="/poi/guardar", consumes = "multipart/form-data"/*esto tambien es de la foto*/)
	public String guardarNuevoPoi(@Valid @RequestParam("file") MultipartFile file/* Esto tambien es de la foto*/, @ModelAttribute("unPoi") POI nuevoPoi, BindingResult resultado, Model model) throws IOException{
		if(resultado.hasErrors()) {
			model.addAttribute("unPoi", nuevoPoi);
			model.addAttribute("pois", poiService.obtenerTodosPOIs());
			return("poi");
		}
		else {
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			byte[] content = file.getBytes();//desde aca
			String base64 = Base64.getEncoder().encodeToString(content);
			nuevoPoi.setImagen(base64);//hasta aca es lo de la foto
			poiService.guardarPOI(nuevoPoi);
			LOGGER.info("Tamaño del Listado: "+ poiService.obtenerTodosPOIs().size());
			return ("redirect:/poi/mostrar");
		}
	}
	
	//postmapping modificar
	
	//cancelar
	
	//eliminarpoi
}
