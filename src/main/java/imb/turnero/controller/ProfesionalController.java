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

import imb.pr2.turnero.entity.Profesional;
import imb.turnero.service.IProfesionalService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping("/api/v1/profesional")
public class ProfesionalController {
	
	@Autowired
	IProfesionalService profesionalService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Profesional>>> mostrarTodos() {		
		APIResponse<List<Profesional>> response = new APIResponse<List<Profesional>>(200, null, profesionalService.buscarProfesional());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Profesional>> mostrarProfesionalPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Profesional profesional = profesionalService.buscarProfesionalPorId(id);
			APIResponse<Profesional> response = new APIResponse<Profesional>(HttpStatus.OK.value(), null, profesional);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el Profesional con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Profesional> response = new APIResponse<Profesional>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Profesional>> crearProfesional(@RequestBody Profesional profesional) {
		if(this.existe(profesional.getIdProfesional())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe un Profesional con el ID = " + profesional.getIdProfesional().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Profesional> response = new APIResponse<Profesional>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			profesionalService.guardarProfesional(profesional);
			APIResponse<Profesional> response = new APIResponse<Profesional>(HttpStatus.CREATED.value(), null, profesional);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Profesional>> modificarProfesional(@RequestBody Profesional profesional) {
		if(this.existe(profesional.getIdProfesional())) {
			profesionalService.guardarProfesional(profesional);
			APIResponse<Profesional> response = new APIResponse<Profesional>(HttpStatus.OK.value(), null, profesional);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un profesional con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponse<Profesional> response = new APIResponse<Profesional>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Profesional>> eliminarProfesional(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			profesionalService.eliminarProfesional(id);
			List<String> messages = new ArrayList<>();
			messages.add("El Profesional que figura en el cuerpo ha sido eliminada") ;			
			APIResponse<Profesional> response = new APIResponse<Profesional>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un Profesional con el ID = " + id.toString());
			APIResponse<Profesional> response = new APIResponse<Profesional>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Profesional profesional = profesionalService.buscarProfesionalPorId(id);
			if(profesional == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleConstraintViolationException(ConstraintViolationException ex){
		List<String> errors = new ArrayList<>();
		for(ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		APIResponse<Profesional> response = new APIResponse<Profesional>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}

}
