package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.TuristaPOI;

public interface ITuristaPOIService {
	public TuristaPOI crearTuristaPOI();
	public List<TuristaPOI> obtenerTodosTuristaPOIs();
	//public void guardarTuristaPOIValoracion(TuristaPOI unTuristaPOI, String usuario, Integer poi) throws Exception ;
	//public void guardarTuristaPOIComentario(TuristaPOI unTuristaPOI, String usuario, Integer poi) throws Exception;
	public void guardarTuristaPOI(TuristaPOI nuevoTuristaPoi, String usuario, int id) throws Exception;
	public TuristaPOI encontrarUnTuristaPOI(int idtp) throws Exception;
	public void modificarTuristaPOI(TuristaPOI turistaPOIModificado) throws Exception;
	public void eliminarTuristaPOI(int idtp) throws Exception;
	
	
}
