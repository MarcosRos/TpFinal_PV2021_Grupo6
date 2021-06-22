package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.Base64;
//import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
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
import ar.edu.unju.edm.service.ITuristaPOIService;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class POIController {
	private static final Log LOGGER = LogFactory.getLog(POIController.class);
	
	@Autowired
	@Qualifier("imppoi")
	IPOIService poiService;
	
  @Autowired
	@Qualifier("impturistapoi")
	ITuristaPOIService turistaPoiService;
  
  @Autowired
	@Qualifier("impturista")
	ITuristaService turistaService;
  
	@GetMapping("/poi/root/mostrar")
	public String cargarPoiRoot(Model model) {
		model.addAttribute("unPoi", poiService.crearPOI());
		model.addAttribute("pois", poiService.obtenerTodosPOIs());
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return("poi");
	}
	
	@GetMapping("/poi/mostrar")
	public String cargarPoi(Model model) {
		model.addAttribute("unPoi", poiService.crearPOI());
		model.addAttribute("pois", poiService.obtenerTodosPOIs());
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return("poi2");
	}
	
	@GetMapping("/poi/editar/root/{idPOI}")		//recibe la petición desde el html o vista, enviandole idPOI
	public String editarPoiRoot(Model model, @PathVariable(name="idPOI") int id) throws Exception {
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
		model.addAttribute("pois", poiService.obtenerTodosPOIs());
		return("poi");
	}
	
	@GetMapping("/poi/editar/{idPOI}")		//recibe la petición desde el html o vista, enviandole idPOI
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
		model.addAttribute("pois", poiService.obtenerTodosPOIs());
		return("poi2");
	}
	
	@PostMapping(value="/poi/guardar", consumes = "multipart/form-data"/*esto tambien es de la foto*/)

	public String guardarNuevoPoi(@Valid @RequestParam("file") MultipartFile file, MultipartFile file2, MultipartFile file3/* Esto tambien es de la foto*/, @ModelAttribute("unPoi") POI nuevoPoi, BindingResult resultado, Model model, Authentication auth) throws Exception{
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
			
			byte[] content2 = file2.getBytes();//desde aca
			String base642 = Base64.getEncoder().encodeToString(content2);
			nuevoPoi.setImagen2(base642);//hasta aca es lo de la foto
			
			byte[] content3 = file3.getBytes();//desde aca
			String base643 = Base64.getEncoder().encodeToString(content3);
			nuevoPoi.setImagen3(base643);//hasta aca es lo de la foto
      LOGGER.info("Usuario: "+auth.getName());
			//String email= auth.getName();
			poiService.guardarPOI(nuevoPoi,auth.getName());
			LOGGER.info("Tamaño del Listado: "+ poiService.obtenerTodosPOIs().size());
			return ("redirect:/poi/misPois");
		}
	}
	
	@PostMapping("/poi/modificar")
    public String modificarPoi(@RequestParam("file") MultipartFile file, MultipartFile file2, MultipartFile file3, @ModelAttribute("unPoi") POI poiModificado, Model model){
        try {
        	poiService.modificarPOI(file, file2, file3, poiModificado);
            model.addAttribute("unPoi", new POI());
            model.addAttribute("editMode", "false");
        }
        catch (Exception e) {
            model.addAttribute("formUsuarioErrorMessage",e.getMessage());
            model.addAttribute("unPoi", poiModificado);
            model.addAttribute("poi", poiService.obtenerTodosPOIs());
            model.addAttribute("editMode", "true");
        }
        model.addAttribute("poi", poiService.obtenerTodosPOIs());
        return ("redirect:/poi/misPois");
    }
	
	@GetMapping("/poi/cancelar")
	public String cancelar() {
		return "redirect:/poi/misPois";
	}
	
	@GetMapping("/poi/eliminarPoi/root/{id}")
	public String eliminarPOIRoot(Model model, @PathVariable(name="id") int id) {
		try {
			LOGGER.info("METHOD: ingresando el metodo Eliminar");
			poiService.eliminarPOI(id);
			LOGGER.info("METHOD: saliendo el metodo Guardar");
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/poi/mostrar";
	}
	
	@GetMapping("/poi/eliminarPoi/{id}")
	public String eliminarPOI(Model model, @PathVariable(name="id") int id) {
		try {
			LOGGER.info("METHOD: ingresando el metodo Eliminar");
			poiService.eliminarPOI(id);
			LOGGER.info("METHOD: saliendo el metodo Guardar");
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/poi/misPois";
	}
	
	@GetMapping("/poi/verpoi")
	public String verpoi(Model model) {
			model.addAttribute("unPoi", poiService.crearPOI());
			model.addAttribute("pois", poiService.obtenerTodosPOIs());
			model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		
		return("allPoI");
	}
	
	@GetMapping("/poi/misPois")
	public String mispoi(Model model) {
			model.addAttribute("unPoi", poiService.crearPOI());
			model.addAttribute("pois", poiService.obtenerTodosPOIs());
			model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		
		return("misPois");
	}
	
	@GetMapping("/poi/mostrar/{idPOI}")		//recibe la petición desde el html o vista, enviandole idPOI
	public String mostarUnPoi(Model model, @PathVariable(name="idPOI") int id) throws Exception {
		try {
			POI poiEncontrado = poiService.encontrarUnPOI(id);
			model.addAttribute("unPoi", poiEncontrado);
			model.addAttribute("unTuristaPoi", turistaPoiService.crearTuristaPOI());
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unPoi", poiService.crearPOI());
			model.addAttribute("unTuristaPoi", turistaPoiService.crearTuristaPOI());
		}
		model.addAttribute("turPois", turistaPoiService.obtenerTodosTuristaPOIs());
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return("valorarYComentar");
	}
	
	
}
