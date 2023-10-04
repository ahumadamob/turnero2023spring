package imb.pr2.turnero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Mutual {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idMutual;
	@NotBlank(message = "El nombre de la mutual no puede estar vacío.")
	@Size(max = 20, message = "El nombre de la mutual no debe exceder los 20 caracteres.")
	private String nombre;
	private String beneficios;
	
	public Integer getId() {
		return idMutual;
	}
	public void setId(Integer id) {
		this.idMutual = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getBeneficios() {
		return beneficios;
	}
	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}
	
}
