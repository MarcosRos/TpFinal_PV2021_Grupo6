package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.repository.ITuristaDAO;
import ar.edu.unju.edm.service.ITuristaService;

@Service
@Qualifier("impturista")
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
		unTurista.setTipo("comun");
		String pw = unTurista.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		unTurista.setPassword(bCryptPasswordEncoder.encode(pw));
		turistaDAO.save(unTurista);
	}

	@Override
	public void guardarTuristaRoot(Turista unTurista) {
		String pw = unTurista.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		unTurista.setPassword(bCryptPasswordEncoder.encode(pw));
		turistaDAO.save(unTurista);
	}
	
	@Override
	public Turista encontrarUnTurista(int idt) throws Exception {
		// TODO Auto-generated method stub
		return turistaDAO.findByIdTurista(idt).orElseThrow(()->new Exception ("El turista NO existe"));
	}

	@Override
	public void modificarTurista(Turista turistaModificado) throws Exception {
		// TODO Auto-generated method stub
		Turista turistaAModificar = turistaDAO.findByIdTurista(turistaModificado.getIdTurista()).orElseThrow(()->new Exception("El Turista no fue encontrado"));
		cambiarTurista(turistaModificado, turistaAModificar);
		
		turistaDAO.save(turistaAModificar);
	}

	private void cambiarTurista(Turista desde, Turista hacia){
		// TODO Auto-generated method stub
		String pw = desde.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		hacia.setPassword(bCryptPasswordEncoder.encode(pw));
		
		hacia.setEmail(desde.getEmail());
		hacia.setApellidos(desde.getApellidos());
		hacia.setDocumento(desde.getDocumento());
		hacia.setNombres(desde.getNombres());
		hacia.setPais(desde.getPais());
		//hacia.setPassword(desde.getPassword());
		hacia.setTipo(desde.getTipo());
		//hacia.setIdTurista(desde.getIdTurista());
	}
	
	@Override
	public void eliminarTurista(int idt) throws Exception {
		// TODO Auto-generated method stub
		Turista turistaEliminar = turistaDAO.findByIdTurista(idt).orElseThrow(()->new Exception("El turista no fue encontrado"));
	    turistaDAO.delete(turistaEliminar);
	}

	@Override
	public Turista encontrarPorEmail(String emailt) throws Exception {
		// TODO Auto-generated method stub
		return turistaDAO.findByEmail(emailt).orElseThrow(()->new Exception ("El turista NO existe"));
	}
	
	
}
