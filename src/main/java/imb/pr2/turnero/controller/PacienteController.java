package imb.pr2.turnero.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr2.turnero.entity.Paciente;
import imb.pr2.turnero.service.IPacienteService;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/paciente")
public class PacienteController {
	
	@Autowired
	IPacienteService pacienteServicio;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Paciente>>> mostrarTodos() {		
		List<Paciente> pacientes = pacienteServicio.buscarPacientes();
		if(pacientes.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron pacientes.");
		} else {
			return ResponseUtil.success(pacientes);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Paciente>> mostrarPacientePorId(@PathVariable("id") Integer id) {
		return (pacienteServicio.exists(id))
				? ResponseUtil.success(pacienteServicio.buscarPacientePorId(id))
				: ResponseUtil.notFound("No se encontró el paciente con id = " + id.toString() + ".");
	
	}
	

	// Con la notación @PostMapping indicamos que el siguiente método recibirá solicitudes HTTP POST.
	@PostMapping
	
	// El método llamado 'crearPaciente' recibe un objeto 'Paciente', el cual se incluirá en el cuerpo de la solicitud HTTP.
	// Además, el método devuelve un 'ResponseEntity' (que contiene un objeto de tipo 'APIResponse') parametrizado con 'Paciente'.
	public ResponseEntity<APIResponse<Paciente>> crearPaciente(@RequestBody Paciente paciente) {
		
		// Verificamos si existe un paciente en función de su ID, en caso de existir, devolvemos una respuesta de error,
		// si aún no existe, lo guardamos en el método 'guardarPaciente' y devolvemos una respuesta existosa mediante
		// el 'ResponseUtil.created(...)'.
		return (pacienteServicio.exists(paciente.getId())) ?  ResponseUtil.badRequest("Ya existe un paciente.") : 
			ResponseUtil.created(pacienteServicio.guardarPaciente(paciente));	
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Paciente>> modificarPaciente(@RequestBody Paciente paciente) {
		if(pacienteServicio.exists(paciente.getId())) {
			return ResponseUtil.created(pacienteServicio.guardarPaciente(paciente));
		} else if (paciente.getId() == null) {
			return ResponseUtil.badRequest("No ingresaste id de paciente para modificarlo.");
		} else {
			return ResponseUtil.badRequest("No existe un paciente con el id = " + paciente.getId().toString() + ".");
		}
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<String>> eliminarPaciente(@PathVariable("id") Integer id) {
		if(pacienteServicio.exists(id)) {
			pacienteServicio.eliminarPaciente(id);
			return ResponseUtil.success("El paciente con id = " + id.toString() + " ha sido eliminado.");
		} else {
			return ResponseUtil.badRequest("No existe un paciente con el id = " + id.toString() + ".");
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstrainViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}
}
