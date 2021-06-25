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
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class TuristaPOIController {
	private static final Log LOGGER = LogFactory.getLog(TuristaPOIController.class);
	@Autowired
	@Qualifier("impturistapoi")
	ITuristaPOIService turistaPoiService;
	
	@Qualifier("imppoi")
	IPOIService poiService;
	
	@Autowired
	@Qualifier("impturista")
	ITuristaService turistaService;
	 
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
			int idp=nuevoTuristaPoi.getIdPOI();
			LOGGER.info("POI EN EL QUE SE COMENTA: "+idp);
			turistaPoiService.guardarTuristaPOI(nuevoTuristaPoi,auth.getName(),id,idp);
			LOGGER.info("Tamaño del Listado: "+ turistaPoiService.obtenerTodosTuristaPOIs().size());
			model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
			return ("redirect:/poi/mostrar/{idPOI}");
		}
	}
	
	@GetMapping("/turistaPoi/editar/{idTuristaPOI}")		//recibe la petición desde el html o vista, enviandole idPOI
	public String editarTuristaPoi(Model model, @PathVariable(name="idTuristaPOI") Integer id) throws Exception {
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
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return("valorarYComentar");
	}
	
	@PostMapping("/turistaPoi/modificar")
    public String modificarTuristaPoi(@ModelAttribute("unTuristaPoi") TuristaPOI turistaPoiModificado, Model model,Authentication auth){
        try {
        	turistaPoiService.modificarTuristaPOI(turistaPoiModificado, auth.getName());
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
        return ("redirect:/poi/verpoi");
    }
	
	@GetMapping("/turistaPoi/eliminarTuristaPoi/{idTuristaPOI}")
	public String eliminarTurista(Model model, @PathVariable(name="idTuristaPOI") int id) {		
		try {			
			turistaPoiService.eliminarTuristaPOI(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/poi/verpoi";
	}
	
	@GetMapping("/turistaPoi/cancelar")
	public String cancelar() {
		return "redirect:/poi/verpoi";
	}

}
