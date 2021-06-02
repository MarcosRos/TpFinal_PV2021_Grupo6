package ar.edu.unju.edm.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Foto;
import ar.edu.unju.edm.repository.IFotoDAO;
import ar.edu.unju.edm.service.IFotoService;

@Service
public class FotoServiceMySQL implements IFotoService{
	
	@Autowired
	Foto unaFoto;
	
	@Autowired
	IFotoDAO fotoDAO;
	
	@Override
	public Foto crearFoto() {
		// TODO Auto-generated method stub
		return unaFoto;
	}

	@Override
	public List<Foto> obtenerTodosFotos() {
		// TODO Auto-generated method stub
		return (List<Foto>) fotoDAO.findAll();
	}

	@Override
	public void guardarFoto(Foto unaFoto) {
		// TODO Auto-generated method stub
		fotoDAO.save(unaFoto);
	}

	@Override
	public Foto encontrarUnaFoto(int idf) throws Exception {
		// TODO Auto-generated method stub
		return fotoDAO.findByIdFoto(idf).orElseThrow(()->new Exception ("La foto NO existe"));
	}

	@Override
	public void modificarFoto(Foto FotoModificado) throws Exception {
		// TODO Auto-generated method stub
		Foto fotoAModificar = fotoDAO.findByIdFoto(FotoModificado.getIdFoto()).orElseThrow(()->new Exception ("La foto no fue encontrada"));
		cambiarFoto(FotoModificado, fotoAModificar);
		
		fotoDAO.save(fotoAModificar);
	}
	
	private void cambiarFoto (Foto desde, Foto hacia) {
		//hacia.setIdFoto(desde.getIdFoto());
		hacia.setLinkFoto(desde.getLinkFoto());
		//hacia.setPoi(desde.getPoi());
	}
	
	@Override
	public void eliminarFoto(int idf) throws Exception {
		// TODO Auto-generated method stub
		Foto fotoEliminar = fotoDAO.findByIdFoto(idf).orElseThrow(()->new Exception("La foto no fue encontrado"));
		fotoDAO.delete(fotoEliminar);
	}

}
