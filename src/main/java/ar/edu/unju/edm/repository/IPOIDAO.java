package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.POI;

@Repository
public interface IPOIDAO extends CrudRepository<POI, Integer>{
	
	 @Query("from POI p order by p.idPOI")
	 public List<POI> obtenerPOIs();
	 
	 public Optional<POI> findByIdPOI(int idp);
}
