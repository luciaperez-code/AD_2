package beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


//Etiqueta del nodo raíz
@XmlRootElement(name="participante")

//Hacemos que las etiquetas salgan en un orden
@XmlType(propOrder = {
		"entrada",
	    "grupo"
	})
public class Participante {
	private String entrada, grupo;

	
	//JAXB necesita para funcionar del constructor por defecto de java
	public Participante() {
		
	}
	
	public Participante(String entrada, String grupo) {
		super();
		this.entrada = entrada;
		this.grupo = grupo;
	}

	
	//etiqueta opcional
	@XmlElement(name="entrada")
	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	@XmlElement(name="grupo")
	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "Participante [entrada=" + entrada + ", grupo=" + grupo + "]";
	}

		
}
