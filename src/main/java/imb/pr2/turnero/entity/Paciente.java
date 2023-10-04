package imb.pr2.turnero.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Paciente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "El nombre no puede estar vacío.")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres.")
	private String nombre;
	@NotBlank(message = "El apellido no puede estar vacío.")
	@Size(max = 40, message = "El apellido no debe superar los 40 caracteres.")
	private String apellido;
	@NotBlank(message = "El dni no puede estar vacío.")
	@Size(max = 12, message = "El dni no debe superar los 12 caracteres.")
	private String dni;
	@NotBlank(message = "El domicilio no puede estar vacío.")
	@Size(max = 60, message = "El domicilio no debe superar los 60 caracteres.")
	private String domicilio;
	@NotNull(message = "La fecha y hora no pueden estar vacías.")
	@Past(message = "La fecha y hora ingresadas ya sucedieron.")
	private LocalDate fechaNacimiento;
	@Min(value=1, message="El id del paciente debe ser mayor que 1.")
	private Integer idMutual;
	
	@ManyToOne
	@JoinColumn(name = "idTurno")
	private Turno turno;
	
	@ManyToOne
	@JoinColumn(name = "mutualId")
	private Mutual mutual;
	
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
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Integer getIdMutual() {
		return idMutual;
	}
	public void setIdMutual(Integer idMutual) {
		this.idMutual = idMutual;
	}
	
	
}
