package imb.pr2.turnero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.turnero.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer> {
	public List<Sala> findByDisponibles(boolean disponibles);
	//select * from sala where disponible = ?
}
