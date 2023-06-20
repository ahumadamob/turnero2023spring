package imb3.turnero.service;

import java.util.List;

import imb3.turnero.entity.Mutual;

public interface IMutualService {

	public List<Mutual> buscarMutual();
	public Mutual buscarMutualPorId(Integer idMutual);
	public void guardarMutual(Mutual mutual);
	public void eliminarMutual(Integer idMutual);
	
}
