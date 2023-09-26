package imb.pr2.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Turno;

public interface ITurnoService {
	
	public List<Turno> mostrarTodo ();
	public Turno mostrarPorId (Integer id);
	public void guardar (Turno turno);
	public void eliminar (Integer id);
	public boolean exists(Integer id);

}
