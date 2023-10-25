package imb.pr2.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Turno;

public interface ITurnoService {
	
	public List<Turno> buscarTodos ();
	public Turno buscarPorId (Integer id);
	public Turno guardar (Turno turno);
	public void eliminar (Integer id);
	public boolean existe (Integer id);

}
