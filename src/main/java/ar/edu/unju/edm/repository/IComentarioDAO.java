package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Comentario;

@Repository
public interface IComentarioDAO extends CrudRepository<Comentario, Integer>{
	
	@Query("from Comentario c order by c.idComentario")
	public List<Comentario> obtenerComentarios();
	public Optional<Comentario> findByIdComentario(int idc);
}
