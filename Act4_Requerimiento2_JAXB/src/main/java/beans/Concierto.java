package beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * <concierto>
 * 	<fecha>...</fecha>
 * 	<hora>...</hora>
 * 	<participantes>
 *  	<participante>
 * 		......
 * 		</participante>
 *
 *  	<participante>
 * 		......
 * 		</participante> 
 * 		......
 * 		......
 * 	</participantes>
 */

@XmlRootElement(name="concierto")
public class Concierto {
	private String fecha;
	private String hora;
	private List<Participante> participantes;

	public Concierto() {
		participantes = new ArrayList<Participante>();
	}

	
	public Concierto(String fecha, String hora) {
		super();
		this.fecha = fecha;
		this.hora = hora;
	}


	//Cada elemento del array se serializa a la etiqueta <participante>
	@XmlElement(name = "participante")
	
	//Creo la etiqueta <participantes> que engloba las etiquetas participante
	@XmlElementWrapper(name = "participantes")
	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	@Override
	public String toString() {
		return "Concierto [fecha=" + fecha + ", hora=" + hora + ", participantes=" + participantes + "]";
	}
	
	

}
