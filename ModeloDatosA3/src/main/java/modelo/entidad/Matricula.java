package modelo.entidad;

import javax.persistence.*;

@Entity
@Table(name="matriculas")
public class Matricula {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String numero;
	
	@OneToOne( mappedBy="matricula")
	private Coche coche;

	
	public Matricula() {
		super();
	}

	public Matricula(int id, String numero, Coche coche) {
		super();
		this.id = id;
		this.numero = numero;
		this.coche = coche;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}
	
	
	

}
