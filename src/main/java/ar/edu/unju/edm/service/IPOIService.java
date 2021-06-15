package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.edm.model.POI;


public interface IPOIService {
	public POI crearPOI();
	public List<POI> obtenerTodosPOIs();
	public void guardarPOI(POI unPOI);
	public POI encontrarUnPOI(int idp) throws Exception;
	public void modificarPOI(@RequestParam("file") MultipartFile file, MultipartFile file2, MultipartFile file3, POI poiModificado) throws Exception;
	public void eliminarPOI(int idp) throws Exception;
}
