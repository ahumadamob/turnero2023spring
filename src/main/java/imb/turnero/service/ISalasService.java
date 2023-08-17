package imb.turnero.service;

import java.util.List;

import imb.turnero.entity.Salas;

public interface ISalasService {
	
	public List<Salas> buscarSalas ();
	public Salas buscarSalasPorId (Integer id);
	public void guardarSalas (Salas salas);
	public void eliminarSalas (Integer id);
	
}
