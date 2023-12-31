package imb.pr2.turnero.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.turnero.entity.Paciente;
import imb.pr2.turnero.repository.PacienteRepository;
import imb.pr2.turnero.service.IPacienteService;

@Service
@Primary
public class PacienteServiceImplJpa implements IPacienteService {
	
	@Autowired
	PacienteRepository repo;

	@Override
	public List<Paciente> buscarPacientes() {
		return repo.findAll();
	}

    @Override
    public Paciente buscarPacientePorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

	@Override
	public Paciente guardarPaciente(Paciente paciente) {
		return repo.save(paciente);
		
	}

	@Override
	public void eliminarPaciente(Integer id) {
		repo.deleteById(id);
		
	}
	
	public boolean exists(Integer id) {
		return (id == null)? false: repo.existsById(id);
		
	}
}
