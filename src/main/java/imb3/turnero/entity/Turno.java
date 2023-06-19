package imb3.turnero.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Turno {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer idTurno;
		private Integer numeroTurno;
		@NotNull(message = "La fecha y hora no pueden estar vacías.")
		@Future(message = "La fecha y hora ingresadas ya sucedieron.")
		private LocalDateTime fechaYHora;
		@NotNull(message = "El id del paciente no puede estar vacío.")
		@Min(value=1, message="El id del paciente debe ser mayor que 1.")
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
		public LocalDateTime getFechaYHora() {
			return fechaYHora;
		}
		public void setFechaYHora(LocalDateTime fechaYHora) {
			this.fechaYHora = fechaYHora;
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


