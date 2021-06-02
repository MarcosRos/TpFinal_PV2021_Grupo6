package ar.edu.unju.edm.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.repository.ITuristaDAO;
import ar.edu.unju.edm.service.ITuristaService;

@Service
public class TuristaServiceMySQL implements ITuristaService{
	
	@Autowired
	Turista unTurista;
	
	@Autowired
	ITuristaDAO turistaDAO;
	
	@Override
	public Turista crearTurista() {
		// TODO Auto-generated method stub
		return unTurista;
	}

	@Override
	public List<Turista> obtenerTodosTuristas() {
		// TODO Auto-generated method stub
		return (List<Turista>) turistaDAO.findAll();
	}

	@Override
	public void guardarTurista(Turista unTurista) {
		// TODO Auto-generated method stub
		turistaDAO.save(unTurista);
	}

	@Override
	public Turista encontrarUnTurista(int ema) throws Exception {
		// TODO Auto-generated method stub
		return turistaDAO.findByEmail(ema).orElseThrow(()->new Exception ("El turista NO existe"));
	}
	
}
