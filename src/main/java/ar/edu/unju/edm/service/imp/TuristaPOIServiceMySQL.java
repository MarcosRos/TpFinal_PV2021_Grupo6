package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.POI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.TuristaPOI;
import ar.edu.unju.edm.repository.IPOIDAO;
import ar.edu.unju.edm.repository.ITuristaDAO;
import ar.edu.unju.edm.repository.ITuristaPOIDAO;
import ar.edu.unju.edm.service.ITuristaPOIService;


@Service
@Qualifier("impturistapoi")
public class TuristaPOIServiceMySQL implements ITuristaPOIService{
	
	@Autowired
	TuristaPOI unTuristaPOI;
	
	@Autowired
	ITuristaPOIDAO turistaPOIDAO;
	
	@Autowired
	POI unPOI;
	
	@Autowired
	IPOIDAO POIDAO;
	
	@Autowired
	Turista unTurista;
	
	@Autowired
	ITuristaDAO turistaDAO;
	
	@Override
	public TuristaPOI crearTuristaPOI() {
		// TODO Auto-generated method stub
		return unTuristaPOI;
	}

	@Override
	public List<TuristaPOI> obtenerTodosTuristaPOIs() {
		// TODO Auto-generated method stub
		return (List<TuristaPOI>) turistaPOIDAO.findAll();
	}

	@Override
	public void guardarTuristaPOI(TuristaPOI nuevoTuristaPoi, String usuario, int id) throws Exception {
		// TODO Auto-generated method stub
		nuevoTuristaPoi.setIdPOI(id);
		nuevoTuristaPoi.setTuristaCreador(usuario);
		Turista unTurista = turistaDAO.findByEmail(usuario).orElseThrow(()->new Exception("El turista no se encontro"));
		int resultadoPuntos=unTurista.getPuntos()+13;
		unTurista.setPuntos(resultadoPuntos);
		turistaPOIDAO.save(nuevoTuristaPoi);
	}
	
	@Override
	public TuristaPOI encontrarUnTuristaPOI(int idtp) throws Exception {
		// TODO Auto-generated method stub
		return turistaPOIDAO.findByIdTuristaPOI(idtp).orElseThrow(()->new Exception ("El turistaPOI NO existe"));
	}
	
	@Override
	public void modificarTuristaPOI(TuristaPOI turistaPOIModificado) throws Exception {
		// TODO Auto-generated method stub
		TuristaPOI turistaPOIAModificar = turistaPOIDAO.findByIdTuristaPOI(turistaPOIModificado.getIdTuristaPOI()).orElseThrow(()->new Exception("El TuristaPOI no fue encontrado"));
		cambiarTuristaPOI(turistaPOIModificado, turistaPOIAModificar);
		
		turistaPOIDAO.save(turistaPOIAModificar);
	}
	
	public void cambiarTuristaPOI(TuristaPOI desde, TuristaPOI hacia) {
		hacia.setComentario(desde.getComentario());
		hacia.setValoracion(desde.getValoracion());
		//hacia.setPoi(desde.getPoi());
		//hacia.setTurista(desde.getTurista());
		//hacia.setIdTuristaPOI(desde.getIdTuristaPOI());
		
	}
	
	@Override
	public void eliminarTuristaPOI(int idtp) throws Exception {
		// TODO Auto-generated method stub
		TuristaPOI turistaPOIEliminar = turistaPOIDAO.findByIdTuristaPOI(idtp).orElseThrow(()->new Exception("El TuristaPOI no fue encontrado"));
		turistaPOIDAO.delete(turistaPOIEliminar);
	}

	

}
