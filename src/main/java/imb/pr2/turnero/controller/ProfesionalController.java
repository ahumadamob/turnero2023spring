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

import imb.pr2.turnero.entity.Profesional;
import imb.pr2.turnero.service.IProfesionalService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping("/api/v1/profesional")
public class ProfesionalController {
	
	@Autowired
	IProfesionalService profesionalService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Profesional>>> mostrarTodosLosProfesionales() {
		List<Profesional> profesional = profesionalService.buscar();
		if(profesional.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron Profesionales");
		}else {
			return ResponseUtil.success(profesional);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Profesional>> buscarProfesionalPorId(@PathVariable("id") Integer id) {
		return (profesionalService.exists(id) ? ResponseUtil.badRequest("No se encontro la especialidad")
				: ResponseUtil.success(profesionalService.buscarPorId(id)));
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Profesional>> crearProfesional(@RequestBody Profesional profesional) {
		return (profesionalService.exists(profesional.getId()))? ResponseUtil.badRequest("Ya Existe un Profesor") : ResponseUtil.created(profesionalService.guardar(profesional));			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Profesional>> modificarProfesional(@RequestBody Profesional profesional) {
		return (profesionalService.exists(profesional.getId()))? ResponseUtil.badRequest("No existe un profesional con el ID especificado"): ResponseUtil.created(profesionalService.guardar(profesional));

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Profesional>> eliminarProfesional(@PathVariable("id") Integer id) {
	    if(profesionalService.exists(id)){
	    	profesionalService.eliminar(id);
	        return ResponseUtil.badRequest("El profesional fue eliminado con exito"); 
	    }else {
	        return ResponseUtil.badRequest("No existe una Profesional con el Id especificado");
	    }
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}

}
