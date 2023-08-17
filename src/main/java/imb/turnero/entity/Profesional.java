package imb.turnero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Profesional {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProfesional;
	@NotBlank(message = "El nombre no puede estar vacío.")
	@Size(max = 30, message = "El nombre no debe superar los 30 caracteres.") 
	private String nombreProfesional;
	@NotBlank(message = "El apellido no puede estar vacío.")
	@Size(max = 30, message = "El apellido no debe superar los 30 caracteres.") 
	private String apellidoProfesional;
	private Integer idEspecialidad;
	
	public Integer getIdProfesional() {
		return idProfesional;
	}
	public void setIdProfesional(Integer idProfesional) {
		this.idProfesional = idProfesional;
	}
	public String getNombreProfesional() {
		return nombreProfesional;
	}
	public void setNombreProfesional(String nombreProfesional) {
		this.nombreProfesional = nombreProfesional;
	}
	public String getApellidoProfesional() {
		return apellidoProfesional;
	}
	public void setApellidoProfesional(String apellidoProfesional) {
		this.apellidoProfesional = apellidoProfesional;
	}
	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
}
