 package ar.edu.unju.edm.model;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
//import javax.persistence.Lob;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Table (name="POIS")
@Component
public class POI {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idPOI;
	
	@Column
	@NotBlank
	private String nombre;
	
	@Column
	private String descripcion;
	
	@Column
	private String etiqueta;
	
	@Column
	private String sitioWeb; //es opcional y se elige una sola (cultura, naturaleza, aventura, deportivo, gastronomía) o sitio web
	
	@Column
	@NotBlank
	private String calle;
	
	@Column
	@NotNull
	private int numero;
	
	@Column
	@NotBlank
	private String barrio;
	
	@Column
	@NotBlank
	private String localidad;
	
	@Column
	@NotNull
	private Double latitud;
	
	@Column
	@NotNull
	private Double longitud;
	
	@Column
	private double valoracionTotal=0;
	
	@Column
	private int cantValoraciones=0;
	
	@Column
	@NotNull
	private String turistaCreador;
	
	@Lob
	@Column (name = "prod_imagen", columnDefinition = "LONGBLOB")
	private String imagen; //Esto es de foto
	
	@Lob
	@Column (name = "prod_imagen2", columnDefinition = "LONGBLOB")
	private String imagen2; //Esto es de foto
	
	@Lob
	@Column (name = "prod_imagen3", columnDefinition = "LONGBLOB")
	private String imagen3; //Esto es de foto
	
	public POI() {
		// TODO Auto-generated constructor stub
	}

	public String getImagen2() {
		return imagen2;
	}

	public void setImagen2(String imagen2) {
		this.imagen2 = imagen2;
	}

	public String getImagen3() {
		return imagen3;
	}

	public void setImagen3(String imagen3) {
		this.imagen3 = imagen3;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Integer getIdPOI() {
		return idPOI;
	}

	public void setIdPOI(Integer idPOI) {
		this.idPOI = idPOI;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public String getTuristaCreador() {
		return turistaCreador;
	}

	public void setTuristaCreador(String usuario) {
		this.turistaCreador = usuario;
	}

	public double getValoracionTotal() {
		return valoracionTotal;
	}

	public void setValoracionTotal(double valoracionTotal) {
		this.valoracionTotal = valoracionTotal;
	}

	public double getCantValoraciones() {
		return cantValoraciones;
	}

	public void setCantValoraciones(int cantValoraciones) {
		this.cantValoraciones = cantValoraciones;
	}

	/*public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}*/
	
	
}
