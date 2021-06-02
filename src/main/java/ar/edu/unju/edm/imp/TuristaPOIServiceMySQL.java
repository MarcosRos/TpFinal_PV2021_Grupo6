package ar.edu.unju.edm.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.TuristaPOI;
import ar.edu.unju.edm.repository.ITuristaPOIDAO;
import ar.edu.unju.edm.service.ITuristaPOIService;


@Service
public class TuristaPOIServiceMySQL implements ITuristaPOIService{
	
	@Autowired
	TuristaPOI unTuristaPOI;
	
	@Autowired
	ITuristaPOIDAO turistaPOIDAO;
	
	@Override
	public TuristaPOI crearTuristaPOI() {
		// TODO Auto-generated method stub
		return unTuristaPOI;
	}

	@Override
	public List<TuristaPOI> obtenerTodosTuristaPOIs() {
		// TODO Auto-generated method stub
		//return (List<TuristaPOI>) turistaPOIDAO.findAll();
		return null;
	}

	@Override
	public void guardarTuristaPOI(TuristaPOI unTuristaPOI) {
		// TODO Auto-generated method stub
		turistaPOIDAO.save(unTuristaPOI);
	}

	@Override
	public TuristaPOI encontrarUnTuristaPOI(int idtp) throws Exception {
		// TODO Auto-generated method stub
		//return turistaPOIDAO.findByIdTuristaPOI(idtp).orElseThrow(()->new Exception ("El turistaPOI NO existe"));
		return null;
	}

}
