package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Foto;

@Repository
public interface IFotoDAO extends CrudRepository<Foto, Integer>{
	@Query ("from Foto f order by f.idFoto")
	public List<Foto> obtenerFotos();
	public Optional <Foto> findByIdFoto(int idf);
}
