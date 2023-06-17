package imb3.turnero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb3.turnero.entity.TipoEspecialidad;

public interface EspecialidadesRepository extends JpaRepository<TipoEspecialidad, Integer> {

}
