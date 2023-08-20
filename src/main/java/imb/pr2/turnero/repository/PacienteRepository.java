package imb.pr2.turnero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.turnero.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
	
}
