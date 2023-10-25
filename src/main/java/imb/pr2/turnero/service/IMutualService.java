package imb.pr2.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Mutual;

public interface IMutualService {

	public List<Mutual> obtenerTodas();
	public Mutual obtenerPorId(Integer id);
	public Mutual guardar(Mutual mutual);
	public boolean exists(Integer id);
	public void eliminar(Integer id);

	
}
