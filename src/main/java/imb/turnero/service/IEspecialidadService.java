package imb.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Especialidad;

public interface IEspecialidadService {
	
	public List<Especialidad> ObtenerEspecialidades();
	public Especialidad ObtenerEspecialidadesPorId(Integer id);
	public void guardarTipoEspecialidad(Especialidad especialidad);
	public void eliminarTipoEspecialidad(Integer id);

}
