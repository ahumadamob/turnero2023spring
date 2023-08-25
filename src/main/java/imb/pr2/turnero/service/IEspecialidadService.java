package imb.pr2.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Especialidad;

public interface IEspecialidadService {
	
	public List<Especialidad> obtenerTodasLasEspecialidades();
	public Especialidad obtenerEspecialidadPorId(Integer id);
	public void guardarEspecialidad(Especialidad especialidad);
	public void eliminarEspecialidad(Integer id);

}
