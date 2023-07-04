package imb.turnero.service;

import java.util.List;

import imb.turnero.entity.EspecialidadEntity;

public interface IEspecialidadService {
	
	public List<EspecialidadEntity> ObtenerEspecialidades();
	public EspecialidadEntity ObtenerEspecialidadesPorId(Integer id);
	public void guardarTipoEspecialidad(EspecialidadEntity especialidad);
	public void eliminarTipoEspecialidad(Integer id);

}
