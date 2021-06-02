package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.POI;


public interface IPOIService {
	public POI crearPOI();
	public List<POI> obtenerTodosPOIs();
	public void guardarPOI(POI unPOI);
	public POI encontrarUnPOI(int idp) throws Exception;
}
