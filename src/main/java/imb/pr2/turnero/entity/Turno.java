package imb.pr2.turnero.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	    @ManyToOne
	    @JoinColumn(name = "id_paciente")
	    private Paciente paciente;
	    
	    @ManyToOne
	    @JoinColumn(name = "id_salas")
	    private Salas salas;
	    
	    @ManyToOne
	    @JoinColumn(name = "id_profesional")
	    private Profesional profesional;
		
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
		public Paciente getPaciente() {
			return paciente;
		}
		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}
		public Salas getSalas() {
			return salas;
		}
		public void setSalas(Salas salas) {
			this.salas = salas;
		}
		public Profesional getProfesional() {
			return profesional;
		}
		public void setProfesional(Profesional profesional) {
			this.profesional = profesional;
		}
			
	}


