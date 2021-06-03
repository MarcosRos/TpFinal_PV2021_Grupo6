package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Comentario;

public interface IComentarioService {
	public void guardarComentario(Comentario unComentario);
	public Comentario crearComentario();
	public List<Comentario> obtenerTodosComentario();
	public Comentario encontrarUnComentario(int idc) throws Exception;
	public void modificarComentario(Comentario comentarioModificado) throws Exception;
	public void eliminarComentario(int idc) throws Exception;
}
