package imb.pr2.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Salas;

public interface ISalasService {
	public Salas buscarSalasPorId(Integer id);
	public void guardarSalas(Salas salas);
	public void eliminarSalas(Integer id);
	public List<Salas> buscarSalas();
	
	
}
