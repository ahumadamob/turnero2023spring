package imb.turnero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Salas {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idSalas;
	@NotBlank(message = "El nombre no puede estar vacío.")
	@Size(max = 20, message = "El nombre no debe superar los 20 caracteres.")
	private	String nombreSalas;
	private boolean disponible;
	@NotBlank(message = "La ubicación no puede estar vacía.")
	@Size(max = 30, message = "La ubicación no debe superar los 30 caracteres.")
	private String ubicacion;
	
	public Integer getIdSalas() {
		return idSalas;
	}
	public void setIdSalas(Integer idSalas) {
		this.idSalas = idSalas;
	}
	public String getNombreSalas() {
		return nombreSalas;
	}
	public void setNombreSalas(String nombreSalas) {
		this.nombreSalas = nombreSalas;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
