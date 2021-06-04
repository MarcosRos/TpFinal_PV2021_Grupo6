package ar.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristaService;


@SpringBootApplication
public class TpFinalPv2021Grupo6Application implements CommandLineRunner{

	@Autowired
	@Qualifier("impturista")
	ITuristaService turistaService;
	@Autowired
	Turista turista;
	
	public static void main(String[] args) {
		SpringApplication.run(TpFinalPv2021Grupo6Application.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		//inicializo Usuario ROOT
				/*turista.setEmail("root@gmail.com");
				turista.setNombres("root");
				turista.setApellidos("admin");
				turista.setDocumento(12345678);
				turista.setPais("Argentina");
				turista.setPassword("root");	
				turista.setPuntos(0);
				turista.setTipo("root");							
				turistaService.guardarTurista(turista);*/
//					
}
}