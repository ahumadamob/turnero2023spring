package imb3.turnero.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb3.turnero.entity.Turno;
import imb3.turnero.repository.TurnoRepository;
import imb3.turnero.service.ITurnoService;

@Service
@Primary
public class TurnoServiceImplMysql implements ITurnoService{

	@Autowired
	TurnoRepository repo;

	@Override
	public List<Turno> buscarTurno() {		
		return repo.findAll();
	}

	@Override
	public void guardarTurno(Turno turno) {
		repo.save(turno);
		
	}

	@Override
	public void eliminarTurno(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public Turno buscarTurnoPorId(Integer id) {
		Optional<Turno> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else{
			return null;
		}		
	}

}