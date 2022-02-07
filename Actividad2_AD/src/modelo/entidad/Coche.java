package modelo.entidad;
import java.io.Serializable;

//Todos los objetos que queramos guardar en un fichero DEBEN de implemntar la interfaz serializable
//La interfaz serializable es lo que se llama una intermaz de "marcado" y sirve para decirle a
//la JVM que permite salir de la JVM los objetos. 
public class Coche implements Serializable {

	private static final long serialVersionUID = 7954100877448377269L;
	
	private int id;
	private String matricula, marca, modelo, color;
	
		
	public Coche() {
		super();
	}

	public Coche(String matricula, String marca, String modelo, String color) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
	}
	
	
	public Coche(int id, String matricula, String marca, String modelo, String color) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
	}

	//REVISAR SI NECESITO LOS SETTER Y SI NO, QUITARLOS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color="
				+ color + "]";
	}
	
	
	
}
	//private transient int edad;//con transient no serializamos este campo, es decir no lo 
								//guardamos en el fichero
	
