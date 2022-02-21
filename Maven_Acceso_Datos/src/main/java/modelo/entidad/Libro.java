package modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "libros")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;


	private double precio;


	@ManyToOne
	@JoinColumn(name = "fk_editorial", referencedColumnName = "id")
	private Editorial editorial;

	@ManyToOne
	@JoinColumn(name = "fk_autor", referencedColumnName = "id")
	private Autor autor;
	
	@ManyToMany(mappedBy = "libros")
	private List<Libreria> librerias;
	

	public List<Libreria> getLibrerias() {
		return librerias;
	}

	public void setLibrerias(List<Libreria> librerias) {
		this.librerias = librerias;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	
	
	public double getPrecio() {
		return precio;
	}

	public Editorial getEditoriales() {
		return editorial;
	}

	public Autor getAutores() {
		return autor;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setEditorial(Editorial editoriales) {
		this.editorial = editoriales;
	}

	public void setAutores(Autor autor) {
		this.autor = autor;
	}

	public Libro(String titulo, double precio, Editorial editorial, Autor autor) {
		super();
		this.titulo = titulo;
		this.precio = precio;
		this.editorial = editorial;
		this.autor = autor;
	}

	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", precio=" + precio + ", editoriales=" + editorial
				+ ", autores=" + autor + "]";
	}

	// @ManyToMany(mappedBy="libro", cascade=CascadeType.PERSIST)
	// private List<Libreria> librerias;
}
