package imb.pr2.turnero.service;

import java.util.List;

import imb.pr2.turnero.entity.Mutual;

public interface IMutualService {

	public List<Mutual> obtenerTodasLasMutuales();
	public Mutual obtenerMutualPorId(Integer id);
	public void guardarMutual(Mutual mutual);
	public void eliminarMutual(Integer id);
	
}
