package modelo.entidad;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="marcas")
public class Marca {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	private String nombre, pais, telefono, direccion;
	
	@OneToMany(mappedBy="marca", cascade=CascadeType.PERSIST)
	private List<Coche> coches;

	public Marca(String nombre, String pais, String telefono, String direccion) {
		super();
		this.nombre = nombre;
		this.pais = pais;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	

}
