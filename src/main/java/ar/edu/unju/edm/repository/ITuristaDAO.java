package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Turista;

@Repository
public interface ITuristaDAO extends CrudRepository<Turista, Integer>{
	
	@Query("from Turista t order by t.idTurista")
	public List<Turista> obtenerTuristas();
	
	/*public List<Turista> findByOrderByPuntos();] "posible forma de ordenar la lista" */
	
	public Optional<Turista> findByIdTurista(int idt);
	
	public Optional<Turista> findByEmail(String emailt);
}
