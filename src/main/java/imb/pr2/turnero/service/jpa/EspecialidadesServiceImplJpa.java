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
public class EspecialidadesServiceImplJpa implements IEspecialidadService {
	
	@Autowired
	EspecialidadRepository repo;

	@Override
	public List<Especialidad> ObtenerEspecialidades() {
		
		return repo.findAll();
	}
	
	@Override
	public void guardarTipoEspecialidad(Especialidad especialidad) {
		repo.save(especialidad);
		
	}

	@Override
	public void eliminarTipoEspecialidad(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public Especialidad ObtenerEspecialidadesPorId(Integer id) {
		Optional<Especialidad> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
	}

}
