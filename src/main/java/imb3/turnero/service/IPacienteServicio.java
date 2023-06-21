package imb3.turnero.service;

import java.util.List;

import imb3.turnero.entity.Paciente;

public interface IPacienteServicio {
	
	public List<Paciente> buscarPacientes();
	public Paciente buscarPacientePorId(Integer id);
	public void guardarPaciente(Paciente paciente);
	public void eliminarPaciente(Integer id);
	
}
