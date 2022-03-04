package entidad;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "oficina_postal")

@XmlType(propOrder = { "idOficina", "nombre", "numeroTrabajadores", "direccion", "paquete" })
public class OficinaPostal implements Serializable{

	private int idOficina;
	private String nombre;
	private int numeroTrabajadores;
	private String direccion;
	private String paquete;
	
	public OficinaPostal() {
	}

	public OficinaPostal(int idOficina, String nombre, int numeroTrabajadores, String direccion, String paquete) {
		super();
		this.idOficina = idOficina;
		this.nombre = nombre;
		this.numeroTrabajadores = numeroTrabajadores;
		this.direccion = direccion;
		this.paquete = paquete;
	}
	
	public OficinaPostal(int idOficina, String nombre, int numeroTrabajadores, String direccion) {
		super();
		this.idOficina = idOficina;
		this.nombre = nombre;
		this.numeroTrabajadores = numeroTrabajadores;
		this.direccion = direccion;
	}

	
	public int getIdOficina() {
		return idOficina;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public int getNumeroTrabajadores() {
		return numeroTrabajadores;
	}

	
	public String getDireccion() {
		return direccion;
	}

	
	public String getPaquete() {
		return paquete;
	}
	
	public void setIdOficina(int idOficina) {
		this.idOficina = idOficina;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumeroTrabajadores(int numeroTrabajadores) {
		this.numeroTrabajadores = numeroTrabajadores;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "OficinaPostal [idOficina=" + idOficina + ", nombre=" + nombre + ", numeroTrabajadores="
				+ numeroTrabajadores + ", paquete=" + paquete + ", direccion=" + direccion + "]";
	}

}