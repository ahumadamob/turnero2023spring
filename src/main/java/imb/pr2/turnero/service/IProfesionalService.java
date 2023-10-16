package imb.pr2.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Profesional;

public interface IProfesionalService {
	public List<Profesional> buscar();
	public Profesional buscarPorId(Integer id);
	public Profesional guardar(Profesional profesional);
	public void eliminar(Integer id);
	public boolean exists(Integer id);
}

