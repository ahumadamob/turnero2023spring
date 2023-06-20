package imb3.turnero.paciente.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import imb3.turnero.paciente.entity.Paciente;
import imb3.turnero.paciente.repository.PacienteRepository;
import imb3.turnero.paciente.service.IPacienteServicio;

@Service
@Primary
public class PacienteServicioMysql implements IPacienteServicio {
	
	@Autowired
	PacienteRepository repo;

	@Override
	public List<Paciente> buscarPacientes() {
		return repo.findAll();
	}

	@Override
	public Paciente buscarPacientePorId(Integer id) {
		Optional<Paciente> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
	}

	@Override
	public void guardarPaciente(Paciente paciente) {
		repo.save(paciente);
		
	}

	@Override
	public void eliminarPaciente(Integer id) {
		repo.deleteById(id);
		
	}
	
}
