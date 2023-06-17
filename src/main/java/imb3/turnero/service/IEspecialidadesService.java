package imb3.turnero.service;

import java.util.List;

import imb3.turnero.entity.TipoEspecialidad;

public interface IEspecialidadesService {
	
	public List<TipoEspecialidad> ObtenerEspecialidades();
	public TipoEspecialidad ObtenerEspecialidadesPorId(Integer id);
	public void guardarTipoEspecialidad(TipoEspecialidad especialidad);
	public void eliminarTipoEspecialidad(Integer id);

}
