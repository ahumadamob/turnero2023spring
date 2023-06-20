package imb3.turnero.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb3.turnero.entity.Mutual;
import imb3.turnero.repository.MutualRepository;
import imb3.turnero.service.IMutualService;

@Service
@Primary
public class MutualServiceImplMysql implements IMutualService {

	@Autowired
	MutualRepository repo;
	
	@Override
	public List<Mutual> buscarMutual() {
		return repo.findAll();
	}

	@Override
	public Mutual buscarMutualPorId(Integer idMutual) {
		Optional<Mutual> optional = repo.findById(idMutual);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public void guardarMutual(Mutual mutual) {
		repo.save(mutual);
		
	}

	@Override
	public void eliminarMutual(Integer idMutual) {
		repo.deleteById(idMutual);
		
	}
	
}
