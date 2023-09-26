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

import imb.pr2.turnero.entity.Mutual;
import imb.pr2.turnero.service.IMutualService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/mutual")
public class MutualController {

	@Autowired
	IMutualService mutualService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Mutual>>> mostrarTodasLasMutuales() {
		APIResponse<List<Mutual>> response = new APIResponse<List<Mutual>>(200, null, mutualService.obtenerTodas());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Mutual>> mostrarMutualPorId(@PathVariable("id") Integer id) {
		if(mutualService.exists(id)) {
			Mutual mutual = mutualService.obtenerPorId(id);
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.OK.value(), null, mutual);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la mutual con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro.");
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Mutual>> crearMutual(@RequestBody Mutual mutual) {
		if(mutualService.exists(mutual.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una mutual con el id = " + mutual.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT.");
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			mutualService.guardar(mutual);
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.CREATED.value(), null, mutual);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Mutual>> modificarMutual(@RequestBody Mutual mutual) {
		if(mutualService.exists(mutual.getId())) {
			mutualService.guardar(mutual);
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.OK.value(), null, mutual);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una mutual con el id especificado.");
			messages.add("Para crear una nueva utilice el verbo POST.");
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Mutual>> eliminarMutual(@PathVariable("id") Integer id) {
		if(mutualService.exists(id)) {
			mutualService.eliminar(id);
			List<String> messages = new ArrayList<>();
			messages.add("La mutual que figura en el cuerpo ha sido eliminada.");
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una mutual con el id = " + id.toString());
			APIResponse<Mutual> response = new APIResponse<Mutual>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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
