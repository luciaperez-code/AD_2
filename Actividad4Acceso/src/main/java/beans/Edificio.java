package beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "edificio")
public class Edificio {
	
	private String calle;
	private String numero;
	private int numPlantas;
	private List<Vivienda> viviendas;
	
	public Edificio() {
		viviendas = new ArrayList<Vivienda>();
	}
	
	
	public Edificio(String calle, String numero, int numPlantas) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.numPlantas = numPlantas;
		viviendas = new ArrayList<Vivienda>();
	}


	@Override
	public String toString() {
		return "Edificio [calle=" + calle + ", numero=" + numero + ", numPlantas=" + numPlantas + ", viviendas="
				+ viviendas + "]";
	}


	@XmlElement
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@XmlElement
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@XmlElement
	public int getNumPlantas() {
		return numPlantas;
	}

	public void setNumPlantas(int numPlantas) {
		this.numPlantas = numPlantas;
	}

	@XmlElement
	@XmlElementWrapper(name = "viviendas")
	public List<Vivienda> getViviendas() {
		return viviendas;
	}

	public void setViviendas(List<Vivienda> viviendas) {
		this.viviendas = viviendas;
	}
	
	
	

}
