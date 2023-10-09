package imb.pr2.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Especialidad;

public interface IEspecialidadService {
	
	public List<Especialidad> obtenerTodas();
	public Especialidad obtenerPorId(Integer id);
	public void guardar(Especialidad especialidad);
	public void eliminar(Integer id);
	public boolean exists (Integer id);
	

}
