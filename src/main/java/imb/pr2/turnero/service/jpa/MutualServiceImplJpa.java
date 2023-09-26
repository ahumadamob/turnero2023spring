package imb.pr2.turnero.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr2.turnero.entity.Mutual;
import imb.pr2.turnero.repository.MutualRepository;
import imb.pr2.turnero.service.IMutualService;

@Service
public class MutualServiceImplJpa implements IMutualService {

	@Autowired
	MutualRepository repo;
	
	@Override
	public List<Mutual> obtenerTodas() {
		return repo.findAll();
	}

	@Override
    public Mutual obtenerPorId(Integer id) {
        Optional<Mutual> optional = repo.findById(id);
        return optional.orElse(null);
    }

	@Override
	public void guardar(Mutual mutual) {
		repo.save(mutual);
	}

	
	@Override
	public boolean exists(Integer id) {
	return repo.existsById(id);
	}

	@Override
	public void eliminar(Integer id) {
	repo.deleteById(id);
	}


}
