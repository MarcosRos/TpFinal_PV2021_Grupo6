package ar.edu.unju.edm.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.POI;
import ar.edu.unju.edm.repository.IPOIDAO;
import ar.edu.unju.edm.service.IPOIService;

@Service
public class POIServiceMySQL implements IPOIService{
	
	@Autowired
	POI unPOI;
	
	@Autowired
	IPOIDAO POIDAO;
	
	@Override
	public POI crearPOI() {
		// TODO Auto-generated method stub
		return unPOI;
	}

	@Override
	public List<POI> obtenerTodosPOIs() {
		// TODO Auto-generated method stub
		
		
		/*return (List<POI>) POIDAO.findAll();;*/
		return null;
	}

	@Override
	public void guardarPOI(POI unPOI) {
		// TODO Auto-generated method stub
		POIDAO.save(unPOI);
	}

	@Override
	public POI encontrarUnPOI(int idp) throws Exception {
		// TODO Auto-generated method stub
		
		/*return POIDAO.findBbyIdPOI(idp).orElseThrow(()->new Exception ("El POI no se encontro"));*/
	    return null;
	}
	
}
