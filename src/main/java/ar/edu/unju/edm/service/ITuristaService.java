package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Turista;

public interface ITuristaService {
	public Turista crearTurista();
	public void guardarTurista(Turista unTurista);
	public List<Turista> obtenerTodosTuristas();
	public Turista encontrarUnTurista(int idt) throws Exception;
	public void modificarTurista(Turista turistaModificado) throws Exception;
	public void eliminarTurista(int idt) throws Exception;
	public Turista encontrarPorEmail(String emailt) throws Exception;
}
