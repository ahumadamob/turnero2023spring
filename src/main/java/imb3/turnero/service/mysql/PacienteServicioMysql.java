package imb3.turnero.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb3.turnero.entity.Paciente;
import imb3.turnero.repository.PacienteRepository;
import imb3.turnero.service.IPacienteServicio;

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
