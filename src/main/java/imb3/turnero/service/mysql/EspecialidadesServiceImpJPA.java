package imb3.turnero.service.mysql;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import imb3.turnero.entity.EspecialidadesEntity;
import imb3.turnero.repository.EspecialidadesRepository;
import imb3.turnero.service.IEspecialidadesService;

@Service
@Primary
public class EspecialidadesServiceImpJPA implements IEspecialidadesService {
	
	@Autowired
	EspecialidadesRepository repo;

	@Override
	public List<EspecialidadesEntity> ObtenerEspecialidades() {
		
		return repo.findAll();
	}
	
	@Override
	public void guardarTipoEspecialidad(EspecialidadesEntity especialidad) {
		repo.save(especialidad);
		
	}

	@Override
	public void eliminarTipoEspecialidad(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public EspecialidadesEntity ObtenerEspecialidadesPorId(Integer id) {
		Optional<EspecialidadesEntity> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
	}

}
