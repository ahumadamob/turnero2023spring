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
public class Profesional {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProfesional;
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 60, message = "El nombre no debe superar los 60 caracteres")
	private String nombreProfesional;
	private String apellidoProfesional;
	@NotBlank(message = "El nombre no puede estar vacío")
	@ManyToOne
	@JoinColumn(name="especialidadId")
	private Especialidad especialidad;
	
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
	public Especialidad getEspecialidadId() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad idEspecialidad) {
		this.especialidad = idEspecialidad;
	}
	
}
