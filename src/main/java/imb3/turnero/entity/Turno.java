package imb3.turnero.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Turno {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer idTurno;
		private Integer numeroTurno;
		@NotBlank(message = "La fecha no puede estar vacía")
		@Size(max = 10, message = "La fecha no debe superar los 10 caracteres") 
		private String fecha;
		@NotBlank(message = "La hora no puede estar vacía")
		@Size(max = 5, message = "La hora no debe superar los 5 caracteres") 
		private String hora;
		private Integer idPaciente;
		private Integer idSalas;
		private Integer idProfesional;
		public Integer getIdTurno() {
			return idTurno;
		}
		public void setIdTurno(Integer idTurno) {
			this.idTurno = idTurno;
		}
		public Integer getNumeroTurno() {
			return numeroTurno;
		}
		public void setNumeroTurno(Integer numeroTurno) {
			this.numeroTurno = numeroTurno;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getHora() {
			return hora;
		}
		public void setHora(String hora) {
			this.hora = hora;
		}
		public Integer getIdPaciente() {
			return idPaciente;
		}
		public void setIdPaciente(Integer idPaciente) {
			this.idPaciente = idPaciente;
		}
		public Integer getIdSalas() {
			return idSalas;
		}
		public void setIdSalas(Integer idSalas) {
			this.idSalas = idSalas;
		}
		public Integer getIdProfesional() {
			return idProfesional;
		}
		public void setIdProfesional(Integer idProfesional) {
			this.idProfesional = idProfesional;
		}
		
		
			
	}


