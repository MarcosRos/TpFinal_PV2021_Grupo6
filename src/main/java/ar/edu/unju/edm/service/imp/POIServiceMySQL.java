package ar.edu.unju.edm.service.imp;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.edm.model.POI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.repository.IPOIDAO;
import ar.edu.unju.edm.repository.ITuristaDAO;
import ar.edu.unju.edm.service.IPOIService;

@Service
@Qualifier("imppoi")
public class POIServiceMySQL implements IPOIService{
	
	@Autowired
	POI unPOI;
	
	@Autowired
	IPOIDAO POIDAO;
	
	@Autowired
	Turista unTurista;
	
	@Autowired
	ITuristaDAO turistaDAO;
	
	@Override
	public POI crearPOI() {
		// TODO Auto-generated method stub
		return unPOI;
	}

	@Override
	public List<POI> obtenerTodosPOIs() {
		// TODO Auto-generated method stub
		
		return (List<POI>) POIDAO.findAll();
	}

	@Override
	public POI encontrarUnPOI(int idp) throws Exception {
		// TODO Auto-generated method stub
		
		return POIDAO.findByIdPOI(idp).orElseThrow(()->new Exception ("El POI no se encontro"));
	}

	@Override
	public void modificarPOI(@RequestParam("file") MultipartFile file, POI POIModificado) throws Exception {
		// TODO Auto-generated method stub
		POI POIAModificar = POIDAO.findByIdPOI(POIModificado.getIdPOI()).orElseThrow(()->new Exception("El POI no fue encontrado"));
		cambiarPOI(POIModificado, POIAModificar);
		byte[] content = file.getBytes();
        String base64 = Base64.getEncoder().encodeToString(content);
        POIAModificar.setImagen(base64);
		POIDAO.save(POIAModificar);
	}

	private void cambiarPOI(POI desde, POI hacia) {
		hacia.setBarrio(desde.getBarrio());
		hacia.setCalle(desde.getCalle());
		hacia.setDescripcion(desde.getDescripcion());
		hacia.setEtiqueta(desde.getEtiqueta());
		hacia.setLatitud(desde.getLatitud());
		hacia.setLocalidad(desde.getLocalidad());
		hacia.setLongitud(desde.getLongitud());
		hacia.setNombre(desde.getNombre());
		hacia.setNumero(desde.getNumero());
		hacia.setSitioWeb(desde.getSitioWeb());
		//colocar foto
	}
	
	@Override
	public void eliminarPOI(int idp) throws Exception {
		// TODO Auto-generated method stub
		POI POIEliminar = POIDAO.findByIdPOI(idp).orElseThrow(()->new Exception("El POI no fue encontrado"));
		Turista unTurista = turistaDAO.findByEmail(POIEliminar.getTuristaCreador()).orElseThrow(()->new Exception("El turista no se encontro"));
		int resultadoPuntos=unTurista.getPuntos()-5;
		unTurista.setPuntos(resultadoPuntos);
		POIDAO.delete(POIEliminar);
	}

	@Override
	public void guardarPOI(POI unPOI, String usuario) throws Exception {
		// TODO Auto-generated method stub
		unPOI.setTuristaCreador(usuario);
		Turista unTurista = turistaDAO.findByEmail(usuario).orElseThrow(()->new Exception("El turista no se encontro"));
		int resultadoPuntos=unTurista.getPuntos()+5;
		unTurista.setPuntos(resultadoPuntos);
		POIDAO.save(unPOI);
	}
	
	/*@Override
	public List<POI> obtenerMisPOIs(String usuario) {
		// TODO Auto-generated method stub
			return (List<POI>) POIDAO.findByCreador(usuario);
			
			
	}*/

	
	
}
