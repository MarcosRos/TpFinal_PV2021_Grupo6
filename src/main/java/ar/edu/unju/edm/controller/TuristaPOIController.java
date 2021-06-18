package ar.edu.unju.edm.controller;

import java.util.Base64;

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
import ar.edu.unju.edm.model.TuristaPOI;
import ar.edu.unju.edm.service.IPOIService;
import ar.edu.unju.edm.service.ITuristaPOIService;

@Controller
public class TuristaPOIController {
	private static final Log LOGGER = LogFactory.getLog(TuristaPOIController.class);
	@Autowired
	@Qualifier("impturistapoi")
	ITuristaPOIService turistaPoiService;
	
	@Qualifier("imppoi")
	IPOIService poiService;
	
	/*@GetMapping("/turistaPoi/mostrar/{idPOI}")
	public String cargarTuristaPoi(Model model, @PathVariable(name="idPOI") int idp) throws Exception {
		/*model.addAttribute("unTuristaPoi", turistaPoiService.crearTuristaPOI());
		model.addAttribute("turistaPois", turistaPoiService.obtenerTodosTuristaPOIs());
		//model.addAttribute("pois", poiService.obtenerTodosPOIs());
		LOGGER.info("METHOD: ingresando el metodo Mostrar3");
		try {
			POI poiEncontrado = poiService.encontrarUnPOI(idp);
			model.addAttribute("unPoi", poiEncontrado);
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unPoi", turistaPoiService.crearTuristaPOI());
		}
		model.addAttribute("pois", poiService.obtenerTodosPOIs());
		return("valorarYComentar");
	}*/

	@PostMapping("/turistaPoi/guardar/{idPOI}")
	public String guardarUnNuevoTuristarPoi(@ModelAttribute("unTuristaPoi") TuristaPOI nuevoTuristaPoi,BindingResult resultado,Model model, Authentication auth, @PathVariable(name="idPOI") int id) throws Exception {
		if(resultado.hasErrors()) {
			model.addAttribute("unTuristaPoi", nuevoTuristaPoi);
			model.addAttribute("turistaPois", turistaPoiService.obtenerTodosTuristaPOIs());
			return("allPoI");
		}else {
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			LOGGER.info("Usuario: "+auth.getName());
			//String email= auth.getName();
			turistaPoiService.guardarTuristaPOI(nuevoTuristaPoi,auth.getName(),id);
			LOGGER.info("Tamaño del Listado: "+ turistaPoiService.obtenerTodosTuristaPOIs().size());
			return ("redirect:/poi/verpoi");
		}
	}
		/*try {
			POI poiEncontrado = poiService.encontrarUnPOI(id);
			model.addAttribute("unPoi", poiEncontrado);
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unPoi", poiService.crearPOI());
		}
		model.addAttribute("pois", poiService.obtenerTodosPOIs());
		return("valorarYComentar");
	}*/
	
	
	
	/*@PostMapping("/turistaPoi/guardar/valoracion/{idPOI}")
	public String guardarNuevoTuristaPoi(@ModelAttribute("unTuristaPoi") TuristaPOI nuevoTuristaPoi, BindingResult resultado, Model model, Authentication auth,@PathVariable(name="idPOI") Integer id) throws Exception{
		if(resultado.hasErrors()) {
			model.addAttribute("unTuristaPoi", nuevoTuristaPoi);
			model.addAttribute("turistaPois", turistaPoiService.obtenerTodosTuristaPOIs());
			return("allPoI");
		}
		else {
			LOGGER.info("METHOD: ingresando el metodo Guardar");
			LOGGER.info("Usuario: "+auth.getName());
			//String email= auth.getName();
			turistaPoiService.guardarTuristaPOIValoracion(nuevoTuristaPoi,auth.getName(),id);
			LOGGER.info("Tamaño del Listado: "+ turistaPoiService.obtenerTodosTuristaPOIs().size());
			return ("redirect:/poi/verpoi");
		}
	}*/
	
	
	@GetMapping("/turistaPoi/editar/{idPOI}")		//recibe la petición desde el html o vista, enviandole idPOI
	public String editarTuristaPoi(Model model, @PathVariable(name="idPOI") Integer id) throws Exception {
		try {
			TuristaPOI turistaPoiEncontrado = turistaPoiService.encontrarUnTuristaPOI(id);
			model.addAttribute("unTuristaPoi", turistaPoiEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unTuristaPoi", turistaPoiService.crearTuristaPOI());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("pois", turistaPoiService.obtenerTodosTuristaPOIs());
		return("poi");
	}
	
	@PostMapping("/turistaPoi/modificar")
    public String modificarTuristaPoi(@ModelAttribute("unTuristaPoi") TuristaPOI turistaPoiModificado, Model model){
        try {
        	turistaPoiService.modificarTuristaPOI(turistaPoiModificado);
            model.addAttribute("unTuristaPoi", new POI());
            model.addAttribute("editMode", "false");
        }
        catch (Exception e) {
            model.addAttribute("formUsuarioErrorMessage",e.getMessage());
            model.addAttribute("unTuristaPoi", turistaPoiModificado);
            model.addAttribute("turistaPoi", turistaPoiService.obtenerTodosTuristaPOIs());
            model.addAttribute("editMode", "true");
        }
        model.addAttribute("turistaPoi", turistaPoiService.obtenerTodosTuristaPOIs());
        return ("redirect:/poi/mostrar");
    }
	
	@GetMapping("/turistaPoi/cancelar")
	public String cancelar() {
		return "redirect:/poi/mostrar";
	}

}
