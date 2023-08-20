package imb.pr2.turnero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Mutual {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idMutual;
	@NotBlank(message = "El nombre de la mutual no puede estar vacío.")
	@Size(max = 100, message = "El nombre de la mutual no debe exceder los 100 caracteres.")
	private String nombreMutual;
	private String beneficios;
	
	public Integer getIdMutual() {
		return idMutual;
	}
	public void setIdMutual(Integer idMutual) {
		this.idMutual = idMutual;
	}
	public String getNombreMutual() {
		return nombreMutual;
	}
	public void setNombreMutual(String nombreMutual) {
		this.nombreMutual = nombreMutual;
	}
	public String beneficios() {
		return beneficios;
	}
	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}
	
}
