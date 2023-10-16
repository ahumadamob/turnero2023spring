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
		List<Mutual> mutual = mutualService.obtenerTodas();
		return (mutual.isEmpty()) ? ResponseUtil.notFound("No posee mutual")
		: ResponseUtil.success(mutual);
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Mutual>> mostrarMutualPorId(@PathVariable("id") Integer id) {
		Mutual mutual = mutualService.obtenerPorId(id);
		return(mutualService.exists(id)) ? ResponseUtil.success(mutual)
		: ResponseUtil.badRequest("No se encontró la mutual, revise el parámetro");
	}
		
	@PostMapping
	public ResponseEntity<APIResponse<Mutual>> crearMutual(@RequestBody Mutual mutual) {
		return (mutualService.exists(mutual.getId())) ? ResponseUtil.badRequest("Ya existe una mutual")
		: ResponseUtil.created(mutualService.guardar(mutual));
		}
	
	
	@PutMapping
	public ResponseEntity<APIResponse<Mutual>> modificarMutual(@RequestBody Mutual mutual) {
		return (mutualService.exists(mutual.getId())) ? ResponseUtil.success(mutualService.guardar(mutual)) 
				: ResponseUtil.badRequest("No existe una mutual con el id especificado, para crear una nueva utilice el verbo POST");
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<String>> eliminarMutual(@PathVariable("id") Integer id) {
		    if(mutualService.exists(id)){
		    	mutualService.eliminar(id);
		        return ResponseUtil.success("La mutual fue elimada"); 
		    }else {
		        return ResponseUtil.badRequest("No existe una mutual con el Id especificado");
		    }
		}
			
	
	
	@ExceptionHandler(ConstraintViolationException.class)
		public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
		
	}
	
}
