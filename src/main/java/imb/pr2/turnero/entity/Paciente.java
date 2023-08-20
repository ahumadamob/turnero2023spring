package imb.pr2.turnero.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Paciente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
	private String nombre;
	@NotBlank(message = "El apellido no puede estar vacío")
	@Size(max = 40, message = "El apellido no debe superar los 40 caracteres")
	private String apellido;
	private String dni;
	private String domicilio;
	private Date fechaNacimiento;
	private Integer idMutual;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer idPaciente) {
		this.id = idPaciente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombrePaciente) {
		this.nombre = nombrePaciente;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellidoPaciente) {
		this.apellido = apellidoPaciente;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Integer getIdMutual() {
		return idMutual;
	}
	public void setIdMutual(Integer idMutual) {
		this.idMutual = idMutual;
	}
	
	
}
