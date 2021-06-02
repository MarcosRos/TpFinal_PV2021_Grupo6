package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.edm.model.TuristaPOI;

public interface ITuristaPOIDAO extends CrudRepository<TuristaPOI, Integer>{
	
	@Query("from TuristaPOI tp order by tp.idTuristaPOI")
	public List<TuristaPOI> obtenerTuristaPOIs();
	public Optional<TuristaPOI> findByIdTuristaPOI(int idtp);
}
