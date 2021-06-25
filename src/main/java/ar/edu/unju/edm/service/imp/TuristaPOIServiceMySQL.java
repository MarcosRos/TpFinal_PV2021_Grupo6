package ar.edu.unju.edm.service.imp;

import java.util.List;
import java.util.Optional;

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
	public void guardarTuristaPOI(TuristaPOI nuevoTuristaPoi, String usuario, int id, int idp) throws Exception {
		// TODO Auto-generated method stub
		nuevoTuristaPoi.setIdPOI(id);
		nuevoTuristaPoi.setTuristaCreador(usuario);
		Turista unTurista = turistaDAO.findByEmail(usuario).orElseThrow(()->new Exception("El turista no se encontro"));
		POI unPoi= POIDAO.findByIdPOI(idp).orElseThrow(()->new Exception("El turista no se encontro"));
		if (nuevoTuristaPoi.getValoracion()==null)
		{
			if (nuevoTuristaPoi.getComentario().isEmpty())
			{
				int resultadoPuntos=unTurista.getPuntos();
				unTurista.setPuntos(resultadoPuntos);
			}
			else
			{
				int resultadoPuntos=unTurista.getPuntos()+5;
				unTurista.setPuntos(resultadoPuntos);
			}
		}
		else
		{
			//unPoi.setCantValoraciones(unPoi.getCantValoraciones()+1);
			float valor=0;
			int valoraciones=0;
			List<TuristaPOI> lista = obtenerTodosTuristaPOIs();
			for (int i=0;i<lista.size();i++)
			{
				if (lista.get(i).getIdPOI()==idp)
				{
					valoraciones=valoraciones+1;
				}
			}
			if (valoraciones==0)
			{
				switch (nuevoTuristaPoi.getValoracion())
				{
				case "★★★★★":
					valor=5;
					break;
				case "★★★★":
					valor=4;
					break;
				case "★★★":
					valor=3;
					break;
				case "★★":
					valor=2;
					break;
				case "★":
					valor=1;
					break;
				}
				valoraciones=1;
			}
			else
			{
				for (int i=0;i<lista.size();i++)
				{
					if (lista.get(i).getIdPOI()==idp)
					{
						switch (lista.get(i).getValoracion())
						{
						case "★★★★★":
							valor=valor+5;
							break;
						case "★★★★":
							valor=valor+4;
							break;
						case "★★★":
							valor=valor+3;
							break;
						case "★★":
							valor=valor+2;
							break;
						case "★":
							valor=valor+1;
							break;
						}
						System.out.println(valor);
					}
				}
				switch (nuevoTuristaPoi.getValoracion())
				{
				case "★★★★★":
					valor=valor+5;
					break;
				case "★★★★":
					valor=valor+4;
					break;
				case "★★★":
					valor=valor+3;
					break;
				case "★★":
					valor=valor+2;
					break;
				case "★":
					valor=valor+1;
					break;
				}
				valoraciones=valoraciones+1;
			}
				unPoi.setValoracionTotal(valor/valoraciones);
				unPoi.setCantValoraciones(valoraciones);
			if (nuevoTuristaPoi.getComentario().isEmpty())
			{
				int resultadoPuntos=unTurista.getPuntos()+8;
				unTurista.setPuntos(resultadoPuntos);
			}
			else
			{
				int resultadoPuntos=unTurista.getPuntos()+13;
				unTurista.setPuntos(resultadoPuntos);
			}
		}
		turistaPOIDAO.save(nuevoTuristaPoi);
	}

	
	@Override
	public TuristaPOI encontrarUnTuristaPOI(int idtp) throws Exception {
		// TODO Auto-generated method stub
		return turistaPOIDAO.findByIdTuristaPOI(idtp).orElseThrow(()->new Exception ("El turistaPOI NO existe"));
	}
	
	@Override
	public void modificarTuristaPOI(TuristaPOI turistaPOIModificado, String usuario) throws Exception {
		// TODO Auto-generated method stub
		int valor=0;
		int valoraciones=0;
		TuristaPOI turistaPOIAModificar = turistaPOIDAO.findByIdTuristaPOI(turistaPOIModificado.getIdTuristaPOI()).orElseThrow(()->new Exception("El TuristaPOI no fue encontrado"));
		Turista unTurista = turistaDAO.findByEmail(usuario).orElseThrow(()->new Exception("El turista no se encontro"));
		POI unPoi= POIDAO.findByIdPOI(turistaPOIAModificar.getIdPOI()).orElseThrow(()->new Exception("El POI no se encontro"));
		System.out.println(unPoi.getIdPOI());
		if(turistaPOIAModificar.getComentario().isEmpty())
		{
			cambiarTuristaPOI(turistaPOIModificado, turistaPOIAModificar);
			if(turistaPOIModificado.getComentario().isEmpty()==false)
			{
				int resultadoPuntos=unTurista.getPuntos()+5;
				unTurista.setPuntos(resultadoPuntos);
			}
		}
		else if (turistaPOIAModificar.getValoracion()==null)
		{
			cambiarTuristaPOI(turistaPOIModificado, turistaPOIAModificar);
			if (turistaPOIModificado.getValoracion()!=null)
			{
				int resultadoPuntos=unTurista.getPuntos()+8;
				unTurista.setPuntos(resultadoPuntos);
			}
		}
		else
		{
		cambiarTuristaPOI(turistaPOIModificado, turistaPOIAModificar);
		}
		turistaPOIDAO.save(turistaPOIAModificar);
		List<TuristaPOI> lista = obtenerTodosTuristaPOIs();
		for (int i=0;i<lista.size();i++)
		{
			if (lista.get(i).getIdPOI()==turistaPOIAModificar.getIdPOI())
			{
				valoraciones=valoraciones+1;
				switch (lista.get(i).getValoracion())
				{
				case "★★★★★":
					valor=valor+5;
					break;
				case "★★★★":
					valor=valor+4;
					break;
				case "★★★":
					valor=valor+3;
					break;
				case "★★":
					valor=valor+2;
					break;
				case "★":
					valor=valor+1;
					break;
				}
			}
		}
		unPoi.setValoracionTotal(valor/valoraciones);
		unPoi.setCantValoraciones(valoraciones);
		POIDAO.save(unPoi);
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
		int valor=0, valorABorrar=0, valoraciones=0;
		POI unPoi= POIDAO.findByIdPOI(turistaPOIEliminar.getIdPOI()).orElseThrow(()->new Exception("El POI no se encontro"));
		switch (turistaPOIEliminar.getValoracion())
		{
		case "★★★★★":
			valorABorrar=5;
			break;
		case "★★★★":
			valorABorrar=4;
			break;
		case "★★★":
			valorABorrar=3;
			break;
		case "★★":
			valorABorrar=2;
			break;
		case "★":
			valorABorrar=1;
			break;
		}
		List<TuristaPOI> lista = obtenerTodosTuristaPOIs();
		for (int i=0;i<lista.size();i++)
		{
			if (lista.get(i).getIdPOI()==turistaPOIEliminar.getIdPOI())
			{
				valoraciones=valoraciones+1;
				switch (lista.get(i).getValoracion())
				{
				case "★★★★★":
					valor=valor+5;
					break;
				case "★★★★":
					valor=valor+4;
					break;
				case "★★★":
					valor=valor+3;
					break;
				case "★★":
					valor=valor+2;
					break;
				case "★":
					valor=valor+1;
					break;
				}
			}
		}
		
		valor=valor-valorABorrar;
		valoraciones=valoraciones-1;
		
		unPoi.setCantValoraciones(valoraciones);
		unPoi.setValoracionTotal(valor/valoraciones);
		
		
		turistaPOIDAO.delete(turistaPOIEliminar);
	}

	

}
