package imb.turnero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.turnero.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
	
}
