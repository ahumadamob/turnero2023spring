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

import imb.pr2.turnero.entity.Sala;
import imb.pr2.turnero.service.ISalaService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping("/api/v1/sala")
public class SalaController {
	
	@Autowired
	ISalaService salaService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Sala>>> mostrarTodasLasSalas() {		
		APIResponse<List<Sala>> response = new APIResponse<List<Sala>>(200, null, salaService.mostrarTodos());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Sala>> mostrarSalaPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Sala sala = salaService.mostrarPorId(id);
			APIResponse<Sala> response = new APIResponse<Sala>(HttpStatus.OK.value(), null, sala);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Sala con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Sala> response = new APIResponse<Sala>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Sala>> crearSala(@RequestBody Sala sala) {
		if(this.existe(sala.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una sala con el ID = " + sala.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Sala> response = new APIResponse<Sala>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			salaService.guardar(sala);
			APIResponse<Sala> response = new APIResponse<Sala>(HttpStatus.CREATED.value(), null, sala);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Sala>> modificarSala(@RequestBody Sala sala) {
		if(this.existe(sala.getId())) {
			salaService.guardar(sala);
			APIResponse<Sala> response = new APIResponse<Sala>(HttpStatus.OK.value(), null, sala);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una sala con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponse<Sala> response = new APIResponse<Sala>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Sala>> eliminarSala(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			salaService.eliminar(id);
			List<String> messages = new ArrayList<>();
			messages.add("La Sala que figura en el cuerpo ha sido eliminada") ;			
			APIResponse<Sala> response = new APIResponse<Sala>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una sala con el ID = " + id.toString());
			APIResponse<Sala> response = new APIResponse<Sala>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Sala sala = salaService.mostrarPorId(id);
			if(sala == null) {
				return false;				
			}else {
				return true;
		}
		}
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleConstrainViolationException(ConstraintViolationException ex){
		List<String> errors = new ArrayList<>();
		for(ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}

		
		APIResponse<Sala> response = new APIResponse<Sala>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}
}

	
	
