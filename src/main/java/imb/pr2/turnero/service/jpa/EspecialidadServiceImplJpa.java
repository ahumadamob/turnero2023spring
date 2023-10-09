package imb.pr2.turnero.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.turnero.entity.Especialidad;
import imb.pr2.turnero.repository.EspecialidadRepository;
import imb.pr2.turnero.service.IEspecialidadService;

@Service
@Primary
public class EspecialidadServiceImplJpa implements IEspecialidadService {
	
	@Autowired
	EspecialidadRepository repo;

	@Override
	public List<Especialidad> obtenerTodas() {
		
		return repo.findAll();
	}
	
	@Override
	public void guardar(Especialidad especialidad) {
		repo.save(especialidad);
		
	}

	 @Override
	    public Especialidad obtenerPorId(Integer id) {
	        Optional<Especialidad> optional = repo.findById(id);
	        return optional.orElse(null);
	    }

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);				
	}
	
	public boolean exists (Integer id) {
		return repo.existsById(id);
	}

}
