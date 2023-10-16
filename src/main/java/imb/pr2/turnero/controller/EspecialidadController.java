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

import imb.pr2.turnero.entity.Especialidad;
import imb.pr2.turnero.service.IEspecialidadService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/especialidad")
public class EspecialidadController {
	 
	@Autowired
	IEspecialidadService especialidadService;
	
	
	//método GET para obtener el listado de todas las especialidades.
	@GetMapping
	public ResponseEntity<APIResponse<List<Especialidad>>> mostrarTodasLasEspecialidades() {
		
		//declaramos una lista de especialidades
		List<Especialidad> especialidad = especialidadService.obtenerTodas();
		
		//evaluamos si el objeto especialidad tiene datos o no con un if
		if(especialidad.isEmpty()) {
		// si esta vacio, sin datos, va a mostrar un mensaje
			return ResponseUtil.notFound("No se encontraron especialidades");
		}else {
		// di hay datos, muestra el listado "especialidad"
			return ResponseUtil.success(especialidad);
		}
		
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Especialidad>> mostrarTipoEspecialidadPorId(@PathVariable("id") Integer id) {
		
		return (especialidadService.exists(id) ? ResponseUtil.badRequest("No se encontro la especialidad")
				: ResponseUtil.success(especialidadService.obtenerPorId(id)));
	}
	
	
	@PostMapping
	public ResponseEntity<APIResponse<Especialidad>> crearEspecialidad(@RequestBody Especialidad especialidad) {
		
		return (especialidadService.exists(especialidad.getId())) ? ResponseUtil.badRequest("Ya existe una especialidad")
				: ResponseUtil.created(especialidadService.guardar(especialidad));
				
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Especialidad>> modificarEspecialidad(@RequestBody Especialidad especialidad) {
	    return (especialidadService.exists(especialidad.getId())) ? ResponseUtil.badRequest("La especialidad no se puede modificar") 
	       : ResponseUtil.success(especialidadService.guardar(especialidad));
	}
	
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<String>> eliminarEspecialidad(@PathVariable("id") Integer id) {
		
	    if(especialidadService.exists(id)){
	    	especialidadService.eliminar(id);
	        return ResponseUtil.success("La especialidad fue elimada con exito"); 
	    }else {
	        return ResponseUtil.badRequest("No existe una especialidad con el Id especificado");
	    }

	}
		
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}
}
	


