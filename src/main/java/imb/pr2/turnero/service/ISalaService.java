package imb.pr2.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Sala;

public interface ISalaService {
	public Sala mostrarPorId(Integer id);
	public void guardar(Sala sala);
	public void eliminar(Integer id);
	public List<Sala> mostrarTodos();
	public boolean exist(Integer id);
	
}
