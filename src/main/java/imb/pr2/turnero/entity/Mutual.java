package imb.pr2.turnero.entity;

/*Las líneas 11 a 16 son importaciones. 
 Jakarta persistence nos permite mapear objetos de java a tablas en una base de datos relacional.
 Se basa en anotaciones agregadas a las clases para especificar como se relacionan con las tablas 
 de las bases de datos.
 Jakarta Validation es útil para garantizar que los datos de entrada de la aplicación cumplan 
 con las reglas definidas antes de ser procesados o almacenados en la base de datos. 
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/* En la línea 24 se declara la clase publica "Mutual", es decir que esta puede ser accedida y 
utilizada en cualquier otra clase en cualquier otro paquete. En la linea 23 la anotación indica 
es una entidad que será mapeada en la BD.  
*/

@Entity
public class Mutual {

/* idMutual, nombre y beneficios son atributos de la clase. Integer porque el tipo de atibuto es
un entero y permite que el valor sea nulo, string porque representa secuencia de carácteres.
La anotación @Id indica que idMutual será clave primaria.
@NotBlank indica que el nombre de la mutual no puede estar vacío y @Size indica que se limita la 
longitud a un máximo de 20 carácteres  
 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idMutual;
	@NotBlank(message = "El nombre de la mutual no puede estar vacío.")
	@Size(max = 20, message = "El nombre de la mutual no debe exceder los 20 caracteres.")
	private String nombre;
	private String beneficios;
	
/* Los getter son un método que se utiliza para obtener el valor de un atributo privado.
El nombre empieza con "get" seguido del nombre del atributo del valor que se desea recuperar.
El retorno devuelve el valor del atributo idMutual, nombre o beneficios.
 */

	public Integer getId() {
		return idMutual;
	}

/* Los setter son un método que se utiliza para modificar el valor de un atributo privado. 
El nombre comienza con "set" seguido del nombre del atributo que se desea modificar.
Un setter recibe un argumento que debe ser del mismo tipo de datos que el atributo que se está modificando.
Permiten la escritura de datos e incluye lógica de validación y control para modificar los datos.
 */
	public void setId(Integer id) {
		this.idMutual = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getBeneficios() {
		return beneficios;
	}
	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}
	
}
