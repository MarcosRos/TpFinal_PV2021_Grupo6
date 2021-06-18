package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TURISTAPOIS")
public class TuristaPOI {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idTuristaPOI;
	
	@Column
	@NotNull
	private String turistaCreador;
	
	@Column
	@NotNull
	private Integer idPOI;
	
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

	public String getTuristaCreador() {
		return turistaCreador;
	}

	public void setTuristaCreador(String turistaCreador) {
		this.turistaCreador = turistaCreador;
	}

	public Integer getIdPOI() {
		return idPOI;
	}

	public void setIdPOI(Integer idPOI) {
		this.idPOI = idPOI;
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
