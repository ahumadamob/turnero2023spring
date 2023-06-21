package imb3.turnero.service;

import java.util.List;

import imb3.turnero.entity.Profesional;

public interface IProfesionalService {
	public List<Profesional> buscarProfesional();
	public Profesional buscarProfesionalPorId(Integer id);
	public void guardarProfesional(Profesional profesional);
	public void eliminarProfesional(Integer id);
	
}

