package imb3.turnero.paciente.service;

import java.util.List;
import imb3.turnero.paciente.entity.Paciente;

public interface IPacienteServicio {
	
	public List<Paciente> buscarPacientes();
	public Paciente buscarPacientePorId(Integer id);
	public void guardarPaciente(Paciente categoria);
	public void eliminarPaciente(Integer id);
	
}
