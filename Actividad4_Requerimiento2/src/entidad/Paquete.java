package entidad;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "paquete")

@XmlType(propOrder = { "idPaquete", "nombreDestinatario", "nombreRemitente", "direccionDestinatario",
		"direccionRemitente" })
public class Paquete implements Serializable{
	
	private int idPaquete;
	private String nombreDestinatario;
	private String nombreRemitente;
	private String direccionDestinatario;
	private String direccionRemitente;

	public Paquete() {
	}

	public Paquete(int idPaquete, String nombreDestinatario, String nombreRemitente, String direccionDestinatario,
			String direccionRemitente) {
		super();
		this.idPaquete = idPaquete;
		this.nombreDestinatario = nombreDestinatario;
		this.nombreRemitente = nombreRemitente;
		this.direccionDestinatario = direccionDestinatario;
		this.direccionRemitente = direccionRemitente;
	}
	
	
	@XmlAttribute(name = "id")
	public int getIdPaquete() {
		return idPaquete;
	}

	@XmlElement(name="nombre_destinatario")
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	@XmlElement(name="nombre_remitente")
	public String getNombreRemitente() {
		return nombreRemitente;
	}

	@XmlElement(name="direccion_destinatario")
	public String getDireccionDestinatario() {
		return direccionDestinatario;
	}

	@XmlElement(name="direccion_remitente")
	public String getDireccionRemitente() {
		return direccionRemitente;
	}

	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}

	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	public void setNombreRemitente(String nombreRemitente) {
		this.nombreRemitente = nombreRemitente;
	}

	public void setDireccionDestinatario(String direccionDestinatario) {
		this.direccionDestinatario = direccionDestinatario;
	}

	public void setDireccionRemitente(String direccionRemitente) {
		this.direccionRemitente = direccionRemitente;
	}

	@Override
	public String toString() {
		return "Paquete [idPaquete=" + idPaquete + ", nombreDestinatario=" + nombreDestinatario + ", nombreRemitente="
				+ nombreRemitente + ", direccionDestinatario=" + direccionDestinatario + ", direccionRemitente="
				+ direccionRemitente + "]";
	}

}
