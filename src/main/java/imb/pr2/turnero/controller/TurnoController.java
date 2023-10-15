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

import imb.pr2.turnero.entity.Turno;
import imb.pr2.turnero.service.ITurnoService;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/turno")
public class TurnoController {
	
	@Autowired
	ITurnoService turnoService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Turno>>> mostrarTodosLosTurnos() {	
		List<Turno> turnos = turnoService.buscarTodos();
		return (turnos.isEmpty())
				? ResponseUtil.notFound("No se encontraron turnos.")
				: ResponseUtil.success(turnos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Turno>> mostrarTurnoPorId(@PathVariable("id") Integer id) {
		return (turnoService.existe(id))
				? ResponseUtil.success(turnoService.buscarPorId(id))
				: ResponseUtil.notFound("No se encontró un turno con id " + id.toString() + ".");	
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Turno>> crearTurno(@RequestBody Turno turno) {
		return (turnoService.existe(turno.getId())) 
				? ResponseUtil.badRequest("Ya existe un turno con id " + turno.getId().toString() + ".") 
				: ResponseUtil.created(turnoService.guardar(turno));		
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Turno>> modificarTurno(@RequestBody Turno turno) {
		return (turnoService.existe(turno.getId()))
				? ResponseUtil.created(turnoService.guardar(turno))
				: ResponseUtil.badRequest("No existe un turno con id " + turno.getId().toString() + ".");
	}
	
	/* 
	En este caso utilizamos la anotación DeleteMapping para mapear las solicitudes que utilicen el método HTTP Delete hacia el método eliminarTurno.
	Así asignaremos la dirección de la API que se encargará de dar respuesta a estas peticiones.
	Dentro de los paréntesis estableceremos la ruta. 
	En este caso lo único que agregaremos a la ruta es una path variable que se utiliza para capturar un valor específico enviado por la URL.
	*/
	@DeleteMapping("/{id}")	
	/*
	Declaramos un método llamado eliminarTurno. En su signatura se establece que es público, que devuelve un objeto de tipo ResponseEntity 
	que utiliza la clase APIResponse para darle formato de respuesta al objeto Turno que se devolverá a través de la API.
	Este objeto ResponseEntity será una respuesta HTTP y puede contener un cuerpo, encabezados y un código de estado.
	A su vez APIResponse puede llevar un código de estado, mensajes e información.
	La anotación @PathVariable la utilizamos para tomar las variables ingresadas a través de la URL e ingresarlas por parámetros al método.
	*/
	public ResponseEntity<APIResponse<Turno>> eliminarTurno(@PathVariable("id") Integer id) {
		/*
		Dentro del cuerpo del método evaluaremos una condición a través de la función existe que creamos en los servicios de la entidad Turno.
		Esta determina si existe o no un registro en la base de datos con el id que le pasamos por parámetros.
		Si existe, lo que hará es eliminar el registro a través de la función eliminar que creamos en el servicio de turno.
		Además devolveremos como respuesta de la API un mensaje diciendo que el turno ha sido eliminado junto con un código 200.
		Si no existe, entraremos en una estructura else que no eliminará ningun registro.
		Solamente devolveremos como respuesta de la API un mensaje diciendo que no existe un turno con ese id junto a un código 400.
		Todos los objetos ResponseEntity junto con el APIResponse determinado que se devuelven se crean a través de los métodos estáticos 
		declarados en nuestra clase ResponseUtil que nos permite crear respuestas para nuestra API con un formato determinado.
		 */
		if (turnoService.existe(id)) {
			turnoService.eliminar(id);
			return ResponseUtil.success("El turno con id " + id.toString() + " ha sido eliminado.");
		} else {
			return ResponseUtil.badRequest("No existe un turno con id " + id.toString() + ".");
		}		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
	}



}
