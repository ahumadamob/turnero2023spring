package imb3.turnero.paciente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb3.turnero.paciente.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
	
}
