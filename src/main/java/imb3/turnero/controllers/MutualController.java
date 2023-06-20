package imb3.turnero.controllers;

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

import imb3.turnero.entity.Mutual;
import imb3.turnero.service.IMutualService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/mutual")
public class MutualController {

	@Autowired
	IMutualService mutualService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Mutual>>> mostrarTodos() {
		APIResponse<List<Mutual>> response = new APIResponse<List<Mutual>>(200, null, mutualService.buscarMutual());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{idMutual}")
	public ResponseEntity<APIResponse<Mutual>> mostrarMutualPorId(@PathVariable("idMutual") Integer idMutual) {
		if(this.existe(idMutual)) {
			Mutual mutual = mutualService.buscarMutualPorId(idMutual);
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.OK.value(), null, mutual);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Mutual con id = " + idMutual.toString());
			messages.add("Modifique el parámetro ingresado.");
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Mutual>> crearMutual(@RequestBody Mutual mutual) {
		if(this.existe(mutual.getIdMutual())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una mutual con el ID = " + mutual.getIdMutual().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			mutualService.guardarMutual(mutual);
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.CREATED.value(), null, mutual);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Mutual>> modificarMutual(@RequestBody Mutual mutual) {
		if(this.existe(mutual.getIdMutual())) {
			mutualService.guardarMutual(mutual);
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.OK.value(), null, mutual);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una mutual con el ID especificado.");
			messages.add("Para crear una nueva utilice el verbo POST.");
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@DeleteMapping("/{idMutual}")
	public ResponseEntity<APIResponse<Mutual>> eliminarMutual(@PathVariable("idMutual") Integer idMutual) {
		if(this.existe(idMutual)) {
			mutualService.eliminarMutual(idMutual);
			List<String> messages = new ArrayList<>();
			messages.add("La mutual ha sido eliminada.");
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una mutual con el ID = " + idMutual.toString());
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}
	
	
	private boolean existe(Integer idMutual) {
		if(idMutual == null) {
			return false;
		}else{
			Mutual mutual = mutualService.buscarMutualPorId(idMutual);
			if(mutual == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
		public ResponseEntity<APIResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);		
	}
	
}
