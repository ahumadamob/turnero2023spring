package imb.turnero.controller;

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

import imb.turnero.entity.Turno;
import imb.turnero.service.ITurnoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/turno")
public class TurnoController {
	
	@Autowired
	ITurnoService turnoService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Turno>>> mostrarTodos() {		
		APIResponse<List<Turno>> response = new APIResponse<List<Turno>>(200, null, turnoService.buscarTurno());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Turno>> mostrarTurnoPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Turno turno = turnoService.buscarTurnoPorId(id);
			APIResponse<Turno> response = new APIResponse<Turno>(HttpStatus.OK.value(), null, turno);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el turno con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro.");
			APIResponse<Turno> response = new APIResponse<Turno>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Turno>> crearTurno(@RequestBody Turno turno) {
		if(this.existe(turno.getIdTurno())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe un turno con el id = " + turno.getIdTurno().toString());
			messages.add("Para actualizar utilice el verbo PUT.");
			APIResponse<Turno> response = new APIResponse<Turno>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			turnoService.guardarTurno(turno);
			APIResponse<Turno> response = new APIResponse<Turno>(HttpStatus.CREATED.value(), null, turno);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Turno>> modificarTurno(@RequestBody Turno turno) {
		if(this.existe(turno.getIdTurno())) {
			turnoService.guardarTurno(turno);
			APIResponse<Turno> response = new APIResponse<Turno>(HttpStatus.OK.value(), null, turno);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un turno con el id especificado.");
			messages.add("Para crear uno nuevo utilice el verbo POST.");
			APIResponse<Turno> response = new APIResponse<Turno>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Turno>> eliminarTurno(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			turnoService.eliminarTurno(id);
			List<String> messages = new ArrayList<>();
			messages.add("El turno que figura en el cuerpo ha sido eliminado.") ;			
			APIResponse<Turno> response = new APIResponse<Turno>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un turno con el id = " + id.toString());
			APIResponse<Turno> response = new APIResponse<Turno>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	}
	
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Turno turno = turnoService.buscarTurnoPorId(id);
			if(turno == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleConstraintViolationException(ConstraintViolationException ex){
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		APIResponse<Turno> response = new APIResponse<Turno>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}
}
