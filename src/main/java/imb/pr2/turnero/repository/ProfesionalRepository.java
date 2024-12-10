package imb.pr2.turnero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.turnero.entity.Profesional;

public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {
    List<Profesional> findByNumeroLicencia(Integer numeroLicencia);
}

