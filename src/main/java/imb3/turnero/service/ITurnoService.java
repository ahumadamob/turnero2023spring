package imb3.turnero.service;

import java.util.List;

import imb3.turnero.entity.Turno;

public interface ITurnoService {
	
	public List<Turno> buscarTurno ();
	public Turno buscarTurnoPorId (Integer idTurno);
	public void guardarTurno (Turno turno);
	public void eliminarTurno (Integer id);

}
