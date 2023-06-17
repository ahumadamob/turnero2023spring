package imb3.turnero.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb3.turnero.entity.Salas;
import imb3.turnero.service.ISalasService;


@RestController
@RequestMapping("/api/v1/salas")
public class SalasController {
	
	@Autowired
	ISalasService salasService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Salas>>> mostrarTodos() {		
		APIResponse<List<Salas>> response = new APIResponse<List<Salas>>(200, null, salasService.buscarSalas());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Salas>> mostrarSalasPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Salas salas = salasService.buscarSalasPorId(id);
			APIResponse<Salas> response = new APIResponse<Salas>(HttpStatus.OK.value(), null, salas);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Sala con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Salas> response = new APIResponse<Salas>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Salas>> crearSalas(@RequestBody Salas salas) {
		if(this.existe(salas.getIdSalas())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una sala con el ID = " + salas.getIdSalas().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Salas> response = new APIResponse<Salas>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			salasService.guardarSalas(salas);
			APIResponse<Salas> response = new APIResponse<Salas>(HttpStatus.CREATED.value(), null, salas);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Salas>> modificarSalas(@RequestBody Salas salas) {
		if(this.existe(salas.getIdSalas())) {
			salasService.guardarSalas(salas);
			APIResponse<Salas> response = new APIResponse<Salas>(HttpStatus.OK.value(), null, salas);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una sala con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponse<Salas> response = new APIResponse<Salas>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Salas>> eliminarSalas(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			salasService.eliminarSalas(id);
			List<String> messages = new ArrayList<>();
			messages.add("La Sala que figura en el cuerpo ha sido eliminada") ;			
			APIResponse<Salas> response = new APIResponse<Salas>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una sala con el ID = " + id.toString());
			APIResponse<Salas> response = new APIResponse<Salas>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Salas salas = salasService.buscarSalasPorId(id);
			if(salas == null) {
				return false;				
			}else {
				return true;
		}
		}
	}
	}

	
	
