package beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vivienda")
public class Vivienda {
	
	private int idVivienda;
	private String planta;
	private String puerta;
	private String inquilino;

	public Vivienda() {
		
	}

	public Vivienda(int idVivienda, String planta, String puerta, String inquilino) {
		super();
		this.idVivienda = idVivienda;
		this.planta = planta;
		this.puerta = puerta;
		this.inquilino = inquilino;
	}

	@XmlAttribute(name = "id")
	public int getIdVivienda() {
		return idVivienda;
	}

	public void setIdVivienda(int idVivienda) {
		this.idVivienda = idVivienda;
	}

	@XmlElement
	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	@XmlElement
	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	@XmlElement
	public String getInquilino() {
		return inquilino;
	}

	public void setInquilino(String inquilino) {
		this.inquilino = inquilino;
	}

	@Override
	public String toString() {
		return "Vivienda [idVivienda=" + idVivienda + ", planta=" + planta + ", puerta=" + puerta + ", inquilino="
				+ inquilino + "]";
	}
	
	
}
