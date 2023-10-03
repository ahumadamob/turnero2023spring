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
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/paciente")
public class PacienteController {
	
	@Autowired
	IPacienteService pacienteServicio;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Paciente>>> mostrarTodos() {		
		APIResponse<List<Paciente>> response = new APIResponse<List<Paciente>>(200, null, pacienteServicio.buscarPacientes());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Paciente>> mostrarPacientePorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Paciente paciente = pacienteServicio.buscarPacientePorId(id);
			APIResponse<Paciente> response = new APIResponse<Paciente>(HttpStatus.OK.value(), null, paciente);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Paciente con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Paciente> response = new APIResponse<Paciente>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Paciente>> crearPaciente(@RequestBody Paciente paciente) {
		if(this.existe(paciente.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una paciente con el ID = " + paciente.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Paciente> response = new APIResponse<Paciente>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			pacienteServicio.guardarPaciente(paciente);
			APIResponse<Paciente> response = new APIResponse<Paciente>(HttpStatus.CREATED.value(), null, paciente);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Paciente>> modificarPaciente(@RequestBody Paciente paciente) {
		if(this.existe(paciente.getId())) {
			pacienteServicio.guardarPaciente(paciente);
			APIResponse<Paciente> response = new APIResponse<Paciente>(HttpStatus.OK.value(), null, paciente);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un paciente con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponse<Paciente> response = new APIResponse<Paciente>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Paciente>> eliminarPaciente(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			pacienteServicio.eliminarPaciente(id);
			List<String> messages = new ArrayList<>();
			messages.add("El paciente que figura en el cuerpo ha sido eliminada") ;			
			APIResponse<Paciente> response = new APIResponse<Paciente>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un paciente con el ID = " + id.toString());
			APIResponse<Paciente> response = new APIResponse<Paciente>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Paciente paciente = pacienteServicio.buscarPacientePorId(id);
			if(paciente == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Paciente>> handleConstrainViolationException(ConstraintViolationException ex){
		List<String> errors = new ArrayList<>();
		for(ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		APIResponse<Paciente> response = new APIResponse<Paciente>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}
}
