package imb3.turnero.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb3.turnero.entity.Salas;
import imb3.turnero.repository.SalasRepository;
import imb3.turnero.service.ISalasService;

@Service
@Primary
public class SalasServiceImplMysql implements ISalasService {
	
	@Autowired
	SalasRepository repo;

	@Override
	public List<Salas> buscarSalas() {		
		return repo.findAll();

	}

	@Override
	public void guardarSalas(Salas salas) {
		repo.save(salas);
		
	}

	@Override
	public void eliminarSalas(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public Salas buscarSalasPorId(Integer id) {
		Optional<Salas> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
	}

}
