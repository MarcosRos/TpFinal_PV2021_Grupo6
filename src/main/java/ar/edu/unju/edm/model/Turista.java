package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Table (name="TURISTAS")
@Component
public class Turista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idTurista;
	
	@Column
	@NotBlank(message="Incluir email")
	private String email;
	
	@Column
	@NotBlank
	private String password;
	
	@Column
	@NotBlank(message="Incluir almenos un nombre")
	private String nombres;
	
	@Column
	@NotBlank(message="Incluir almenos un apellido")
	private String apellidos;
	
	@Column
	@Min(100000) 
	@Max(99999999)
	@NotNull (message="Incluir documento")
	private long documento;
	
	@Column
	@NotBlank
	private String pais;
	
	@Column
	private int puntos; //son los puntos para canjear
	
	@Column
	private String tipo;
	
	public Turista() {
		// TODO Auto-generated constructor stub
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdTurista() {
		return idTurista;
	}

	public void setIdTurista(Integer idTurista) {
		this.idTurista = idTurista;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public long getDocumento() {
		return documento;
	}

	public void setDocumento(long documento) {
		this.documento = documento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
