package imb.turnero.service;

import java.util.List;

import imb.turnero.entity.Especialidad;

public interface IEspecialidadService {
	
	public List<Especialidad> buscarEspecialidad ();
	public Especialidad buscarEspecialidadPorId (Integer id);
	public void guardarEspecialidad (Especialidad especialidad);
	public void eliminarEspecialidad (Integer id);

}
