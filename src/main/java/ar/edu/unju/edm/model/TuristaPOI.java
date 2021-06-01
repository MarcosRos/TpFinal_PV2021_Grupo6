package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TURISTAPOIS")
public class TuristaPOI {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idTuristaPOI;
	
	@ManyToOne
	@JoinColumn(name = "email")
	private Turista turista;
	
	@ManyToOne
	@JoinColumn(name = "idPOI")
	private POI poi;
	
	@Column
	private int valoracion;
	
	@Column
	private String comentario;

	public Integer getIdTuristaPOI() {
		return idTuristaPOI;
	}

	public void setIdTuristaPOI(Integer idTuristaPOI) {
		this.idTuristaPOI = idTuristaPOI;
	}

	public Turista getTurista() {
		return turista;
	}

	public void setTurista(Turista turista) {
		this.turista = turista;
	}

	public POI getPoi() {
		return poi;
	}

	public void setPoi(POI poi) {
		this.poi = poi;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
