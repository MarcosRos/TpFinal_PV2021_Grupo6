package ar.edu.unju.edm.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Comentario;
import ar.edu.unju.edm.repository.IComentarioDAO;
import ar.edu.unju.edm.service.IComentarioService;

@Service
public class ComentarioServiceMySQL implements IComentarioService{
	
	@Autowired
	Comentario unComentario;
	
	@Autowired
	IComentarioDAO comentarioDAO;

	@Override
	public void guardarComentario(Comentario unComentario) {
		// TODO Auto-generated method stub
		comentarioDAO.save(unComentario);
	}

	@Override
	public Comentario crearComentario() {
		// TODO Auto-generated method stub
		return unComentario;
	}

	@Override
	public List<Comentario> obtenerTodosComentario() {
		// TODO Auto-generated method stub
		return (List<Comentario>) comentarioDAO.findAll();
	}

	@Override
	public Comentario encontrarUnComentario(int idc) throws Exception {
		// TODO Auto-generated method stub
		return comentarioDAO.findByIdComentario(idc).orElseThrow(()->new Exception ("El comentario NO existe"));
	}

	@Override
	public void modificarComentario(Comentario comentarioModificado) throws Exception {
		// TODO Auto-generated method stub
		Comentario comentarioAModificar = comentarioDAO.findByIdComentario(comentarioModificado.getIdComentario()).orElseThrow(()->new Exception("El Coomentario no fue encontrado"));
		cambiarComentario(comentarioModificado, comentarioAModificar);
		
		comentarioDAO.save(comentarioAModificar);
	}
	
	private void cambiarComentario (Comentario desde, Comentario hacia) {
		hacia.setFechaHora(desde.getFechaHora());
		hacia.setTexto(desde.getTexto());
		//hacia.setIdComentario(desde.getIdComentario());
	}
	
	@Override
	public void eliminarComentario(int idc) throws Exception {
		// TODO Auto-generated method stub
		Comentario comentarioEliminar = comentarioDAO.findByIdComentario(idc).orElseThrow(()->new Exception("El comentario no fue encontrado"));
		comentarioDAO.delete(comentarioEliminar);
	}
}
