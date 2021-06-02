package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Foto;

public interface IFotoService {
	public Foto crearFoto();
	public List<Foto> obtenerTodosFotos();
	public void guardarFoto(Foto unaFoto);
	public Foto encontrarUnaFoto(int idf) throws Exception;
}
