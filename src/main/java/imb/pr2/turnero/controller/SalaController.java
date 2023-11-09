package imb.pr2.turnero.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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

import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping("/api/v1/sala")
public class SalaController {
	
	@Autowired
	ISalaService salaService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Sala>>> mostrarTodasLasSalas() {
		List<Sala> sala = salaService.mostrarTodos();
		if(sala.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron salas");
		}else {
			return ResponseUtil.success(sala);
		}
		
	}
	
	@GetMapping("/disponibles")
	public ResponseEntity<APIResponse<List<Sala>>>mostrarSalasDisponibles(){
		List<Sala> salas = salaService.mostrarPorDisponibles(true);
		if(salas.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron salas disponibles");
		}else {
			return ResponseUtil.success(salas);
		}
	}
	
	@GetMapping("/nodisponibles")
	public ResponseEntity<APIResponse<List<Sala>>>mostrarSalasNoDisponibles(){
		List<Sala> salas = salaService.mostrarPorDisponibles(false);
		if(salas.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron salas NO disponibles");
		}else {
			return ResponseUtil.success(salas);
		}
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Sala>> mostrarSalaPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Sala sala = salaService.mostrarPorId(id);
			return ResponseUtil.success(sala);	
		}else {
			return ResponseUtil.notFound("No se encontraron salas con el id especificado");			
		}
	
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Sala>> crearSala(@RequestBody Sala sala) {
		
		return (this.existe(sala.getId())) ? ResponseUtil.badRequest("Ya existe una sala creada") 
				: ResponseUtil.created(salaService.guardar(sala));		
	}
	
	@PutMapping	//Esta notacion permite manejar las solicitudes HTTP PUT en una ruta especifica, en este caso:localhost:8080/api/v1/sala.
	public ResponseEntity<APIResponse<Sala>> modificarSala(@RequestBody Sala sala) { //Esta sera la firma del metodo. Recibe el objeto "sala" en el cuerpo de la solicitud utilizando la notacion "@RequestBody" y nos devuelve um "ResponseEntity" que contendra un objeto de respuesta "APIResponse" que envuelve un objeto "sala".
		if(this.existe(sala.getId())) { //Se verifica si sala con el Id proporcionado en el objeto "sala" ya existe.
			return ResponseUtil.created(salaService.guardar(sala)); //Si sala con el Id especificado existe, se ejecuta el bloque el cual guardara los cambios realizados.
		}else {
			return ResponseUtil.notFound("No se encontraron salas con el id especificado");	//Si no existe, se ejecuta el bloque el cual devolvera un eror 404 y un mensaje que nos indicara que no se han encontrado salas con el Id especificado.
		}

	}
	@PutMapping("/habilitarsala/{id}")
	public ResponseEntity<APIResponse<Sala>> habilitarSala(@PathVariable("id") Integer id){
		if(salaService.exist(id)) {
			Sala sala = salaService.mostrarPorId(id);
			sala.setDisponibles(true);
			salaService.guardar(sala);
			return ResponseUtil.success(sala);
		}else {
			return ResponseUtil.badRequest("No existe la sala");
		}
	}
	
	@PutMapping("/deshabilitarsala/{id}")
	public ResponseEntity<APIResponse<Sala>> deshabilitarSala(@PathVariable("id") Integer id){
		if(salaService.exist(id)) {
			Sala sala = salaService.mostrarPorId(id);
			sala.setDisponibles(false);
			salaService.guardar(sala);
			return ResponseUtil.success(sala);
		}else {
			return ResponseUtil.badRequest("No existe la sala");
		}
	}	
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<String>> eliminarSala(@PathVariable("id") Integer id) {
	    if(salaService.exist(id)){
	    	salaService.eliminar(id);
	        return ResponseUtil.success("La sala fue elimada con exito"); 
	    }else {
	        return ResponseUtil.badRequest("No existe una sala con el Id especificado");
	    }
	
	}
	@DeleteMapping("/eliminardeshabilitada/{id}")
	public ResponseEntity<APIResponse<String>> eliminarSalaSoloDeshabilitada(@PathVariable("id") Integer id){
		if(salaService.exist(id)) {
			Sala sala = salaService.mostrarPorId(id);
			if(sala.isDisponibles() == true) {
				return ResponseUtil.notFound("Esta sala no se puede eliminar porque esta Habilitada");
			}else {
		    	salaService.eliminar(id);
		        return ResponseUtil.success("La sala fue elimada con exito");			
			}
		}else {
			return ResponseUtil.badRequest("No existe una sala con el Id especificado");
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
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}
	
}

	
	
