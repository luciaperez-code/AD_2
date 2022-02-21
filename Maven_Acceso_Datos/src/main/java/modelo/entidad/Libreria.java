package modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "librerias")
public class Libreria {

	// Librería, tendrá un id, un nombre, un nombre del dueño,
	// una dirección y una colección de libros.
	// Además, hay que tener en cuenta que un
	// libro puede estar en diferentes librerías.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String ownerName;
	private String direccion;

	// Solo hacemos cascade cuando damos de alta
	@ManyToMany(cascade = CascadeType.PERSIST)
	// En este caso @JoinTable daremos instrucciones para crear la tabla intermedia
	// que JPA creara para hacer la realcion "Many to Many"
	// Usa los siguientes parametros
	// 1. name -> El nombre de la tabla intermedia
	// 2. joinColumns -> las columnas FK y PK que aporta esta entidad (COMERCIAL)
	// 3. inverseJoinColumns -> las columnas FK y PK que me aporta la otra Entidad
	// (CLIENTE)
	@JoinTable(name = "librerias_libros", joinColumns = {
			@JoinColumn(name = "fk_id_libreria", referencedColumnName = "id") }, // FK que aporta libreria
			inverseJoinColumns = { @JoinColumn(name = "fk_id_libro", referencedColumnName = "id") }) // FKs que aportan
																										// el resto de
																										// entidades
	private List<Libro> libros;

	public Libreria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Libreria(Integer id, String nombre, String ownerName, String direccion,
			List<Libro> libros) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ownerName = ownerName;
		this.direccion = direccion;
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Libreria [id=" + id + ", nombre=" + nombre + ", ownerName=" + ownerName + ", direccion=" + direccion
				+ ", coleccionLibros=" + ", libros=" + libros + "]";
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getDireccion() {
		return direccion;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
}
