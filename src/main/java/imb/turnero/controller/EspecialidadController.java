package imb.turnero.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

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

import imb.turnero.entity.EspecialidadEntity;
import imb.turnero.service.IEspecialidadService;

@RestController
@RequestMapping("/api/v1/especialidades")
public class EspecialidadController {
	
	@Autowired
	IEspecialidadService servicioEspecialidades;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<EspecialidadEntity>>> mostrarTodos() {		
		APIResponse<List<EspecialidadEntity>> response = new APIResponse<List<EspecialidadEntity>>(200, null, servicioEspecialidades.ObtenerEspecialidades());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<EspecialidadEntity>> mostrarTipoEspecialidadPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			EspecialidadEntity especialidad = servicioEspecialidades.ObtenerEspecialidadesPorId(id);
			APIResponse<EspecialidadEntity> response = new APIResponse<EspecialidadEntity>(HttpStatus.OK.value(), null, especialidad);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la especialidad con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<EspecialidadEntity> response = new APIResponse<EspecialidadEntity>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<EspecialidadEntity>> crearCategoria(@RequestBody EspecialidadEntity especialidad) {
		if(this.existe(especialidad.getIdEspecialidad())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una categoria con el ID = " + especialidad.getIdEspecialidad().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<EspecialidadEntity> response = new APIResponse<EspecialidadEntity>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			servicioEspecialidades.guardarTipoEspecialidad(especialidad);
			APIResponse<EspecialidadEntity> response = new APIResponse<EspecialidadEntity>(HttpStatus.CREATED.value(), null, especialidad);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<EspecialidadEntity>> modificarCategoria(@RequestBody EspecialidadEntity especialidad) {
		if(this.existe(especialidad.getIdEspecialidad())) {
			servicioEspecialidades.guardarTipoEspecialidad(especialidad);
			APIResponse<EspecialidadEntity> response = new APIResponse<EspecialidadEntity>(HttpStatus.OK.value(), null, especialidad);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una especialidad con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponse<EspecialidadEntity> response = new APIResponse<EspecialidadEntity>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<EspecialidadEntity>> eliminarTipoEspecialidad(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			servicioEspecialidades.eliminarTipoEspecialidad(id);
			List<String> messages = new ArrayList<>();
			messages.add("La especialidad que figura en el cuerpo ha sido eliminada") ;			
			APIResponse<EspecialidadEntity> response = new APIResponse<EspecialidadEntity>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una especialidad con el ID = " + id.toString());
			APIResponse<EspecialidadEntity> response = new APIResponse<EspecialidadEntity>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			EspecialidadEntity especialidad = servicioEspecialidades.ObtenerEspecialidadesPorId(id);
			if(especialidad == null) {
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
		APIResponse<EspecialidadEntity> response = new APIResponse<EspecialidadEntity>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}
	
	
	}


