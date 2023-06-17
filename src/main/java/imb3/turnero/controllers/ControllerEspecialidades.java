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


import imb3.turnero.entity.TipoEspecialidad;
import imb3.turnero.service.IEspecialidadesService;

@RestController
@RequestMapping("/api/v1/especialidades")
public class ControllerEspecialidades {
	
	@Autowired
	IEspecialidadesService servicioEspecialidades;
	
	@GetMapping
	public ResponseEntity<APIResponseEspecialidades<List<TipoEspecialidad>>> mostrarTodos() {		
		APIResponseEspecialidades<List<TipoEspecialidad>> response = new APIResponseEspecialidades<List<TipoEspecialidad>>(200, null, servicioEspecialidades.ObtenerEspecialidades());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponseEspecialidades<TipoEspecialidad>> mostrarTipoEspecialidadPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			TipoEspecialidad especialidad = servicioEspecialidades.ObtenerEspecialidadesPorId(id);
			APIResponseEspecialidades<TipoEspecialidad> response = new APIResponseEspecialidades<TipoEspecialidad>(HttpStatus.OK.value(), null, especialidad);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la especialidad con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponseEspecialidades<TipoEspecialidad> response = new APIResponseEspecialidades<TipoEspecialidad>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	
	}
	
	@PostMapping
	public ResponseEntity<APIResponseEspecialidades<TipoEspecialidad>> crearCategoria(@RequestBody TipoEspecialidad especialidad) {
		if(this.existe(especialidad.getIdEspecialidad())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una categoria con el ID = " + especialidad.getIdEspecialidad().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponseEspecialidades<TipoEspecialidad> response = new APIResponseEspecialidades<TipoEspecialidad>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			servicioEspecialidades.guardarTipoEspecialidad(especialidad);
			APIResponseEspecialidades<TipoEspecialidad> response = new APIResponseEspecialidades<TipoEspecialidad>(HttpStatus.CREATED.value(), null, especialidad);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponseEspecialidades<TipoEspecialidad>> modificarCategoria(@RequestBody TipoEspecialidad especialidad) {
		if(this.existe(especialidad.getIdEspecialidad())) {
			servicioEspecialidades.guardarTipoEspecialidad(especialidad);
			APIResponseEspecialidades<TipoEspecialidad> response = new APIResponseEspecialidades<TipoEspecialidad>(HttpStatus.OK.value(), null, especialidad);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una especialidad con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponseEspecialidades<TipoEspecialidad> response = new APIResponseEspecialidades<TipoEspecialidad>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponseEspecialidades<TipoEspecialidad>> eliminarTipoEspecialidad(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			servicioEspecialidades.eliminarTipoEspecialidad(id);
			List<String> messages = new ArrayList<>();
			messages.add("La especialidad que figura en el cuerpo ha sido eliminada") ;			
			APIResponseEspecialidades<TipoEspecialidad> response = new APIResponseEspecialidades<TipoEspecialidad>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una especialidad con el ID = " + id.toString());
			APIResponseEspecialidades<TipoEspecialidad> response = new APIResponseEspecialidades<TipoEspecialidad>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			TipoEspecialidad especialidad = servicioEspecialidades.ObtenerEspecialidadesPorId(id);
			if(especialidad == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
	
	
	}


