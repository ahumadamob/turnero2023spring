package imb.pr2.turnero.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.turnero.entity.Turno;
import imb.pr2.turnero.repository.TurnoRepository;
import imb.pr2.turnero.service.ITurnoService;

@Service
@Primary
public class TurnoServiceImplJpa implements ITurnoService{

	@Autowired
	TurnoRepository repo;

	@Override
	public List<Turno> mostrarTodo() {		
		return repo.findAll();
	}

	@Override
	public void guardar(Turno turno) {
		repo.save(turno);
		
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public Turno mostrarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }	
	

	@Override
	public boolean exists(Integer id) {
	    return id != null && repo.existsById(id);
	}

}
