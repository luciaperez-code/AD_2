package modelo.entidad;

import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="coches")
public class Coche {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	String modelo;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="fk_marca", referencedColumnName="id")
	private Marca marca;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_matricula", referencedColumnName= "id")
	private Matricula matricula;
	
	@ManyToMany(mappedBy="coches", cascade=CascadeType.PERSIST)
	
	private List<Conductor> conductores;

	public int getId() {
		return id;
	}

	public List<Conductor> getConductores() {
		return conductores;
	}

	public void setConductores(List<Conductor> conductores) {
		this.conductores = conductores;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Coche(int id, String modelo, Date fecha, Marca marca, Matricula matricula) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.fecha = fecha;
		this.marca = marca;
		this.matricula = matricula;
	}

	public Coche() {
		super();
	}
	
	

}
