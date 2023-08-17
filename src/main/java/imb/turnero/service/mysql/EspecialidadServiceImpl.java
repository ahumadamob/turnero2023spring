package imb.turnero.service.mysql;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.turnero.entity.Especialidad;
import imb.turnero.repository.EspecialidadRepository;
import imb.turnero.service.IEspecialidadService;

@Service
@Primary
public class EspecialidadServiceImpl implements IEspecialidadService {
	
	@Autowired
	EspecialidadRepository repo;

	@Override
	public List<Especialidad> buscarEspecialidad() {
		return repo.findAll();
	}
	
	@Override
	public void guardarEspecialidad(Especialidad especialidad) {
		repo.save(especialidad);
	}

	@Override
	public void eliminarEspecialidad(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public Especialidad buscarEspecialidadPorId(Integer id) {
		Optional<Especialidad> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
	}
}
