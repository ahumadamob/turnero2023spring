package imb3.turnero.service;

import java.util.List;

import imb3.turnero.entity.EspecialidadesEntity;

public interface IEspecialidadesService {
	
	public List<EspecialidadesEntity> ObtenerEspecialidades();
	public EspecialidadesEntity ObtenerEspecialidadesPorId(Integer id);
	public void guardarTipoEspecialidad(EspecialidadesEntity especialidad);
	public void eliminarTipoEspecialidad(Integer id);

}
