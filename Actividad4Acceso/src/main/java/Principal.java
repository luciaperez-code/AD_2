import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		DocumentBuilder analizador;
		Document doc;
		
		try {
			analizador = fabrica.newDocumentBuilder();
			// Creamos nuevo documento vacio
			doc = analizador.newDocument();
			// Añadimos elemento raiz
			Element concierto = doc.createElement("concierto");
			doc.appendChild(concierto);
			// Añadimos tres contactos al elemento raíz agenda.
			agregarParticipante(concierto, doc);
			// Guardamos en disco el nuevo documento XML.
			guardar(doc);
			
			System.out.println("El archivo se ha creado con éxito");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void agregarParticipante(Element concierto, Document doc) {
		
		Element participantes = doc.createElement("participantes");
		concierto.appendChild(participantes);
		
		
		
		Element participante = doc.createElement("participante");
		participantes.appendChild(participante);
		
		Element entrada1 = doc.createElement("entrada");
		Text textoEntrada1 = doc.createTextNode("21:20");
		Element grupo1 = doc.createElement("grupo");
		Text textoGrupo1 = doc.createTextNode("Las Ardillas de Dakota");
		grupo1.appendChild(textoGrupo1);
		entrada1.appendChild(textoEntrada1);
		participante.appendChild(entrada1);
		participante.appendChild(grupo1);
		
		Element participante2 = doc.createElement("participante");
		participantes.appendChild(participante2);
		
		Element entrada2 = doc.createElement("entrada");
		Text textoEntrada2 = doc.createTextNode("22:15");
		Element grupo2 = doc.createElement("grupo");
		Text textoGrupo2 = doc.createTextNode("Fito y Fitipaldis");
		grupo2.appendChild(textoGrupo2);
		entrada2.appendChild(textoEntrada2);
		participante2.appendChild(entrada2);
		participante2.appendChild(grupo2);
		
		Element participante3 = doc.createElement("participante");
		participantes.appendChild(participante3);
		
		Element entrada3 = doc.createElement("entrada");
		Text textoEntrada3 = doc.createTextNode("23:00");
		Element grupo3 = doc.createElement("grupo");
		Text textoGrupo3 = doc.createTextNode("Coldplay");
		grupo3.appendChild(textoGrupo3);
		entrada3.appendChild(textoEntrada3);
		participante3.appendChild(entrada3);
		participante3.appendChild(grupo3);
		
		Element fecha = doc.createElement("fecha");
		Text textoFecha = doc.createTextNode("20-oct-2018");
		Element hora = doc.createElement("hora");
		Text textoHora = doc.createTextNode("21:30");
		hora.appendChild(textoHora);
		fecha.appendChild(textoFecha);
		concierto.appendChild(fecha);
		concierto.appendChild(hora);
		
		
	}

	private static void guardar(Document doc) throws TransformerException {
		//fabrica de Transformes
		TransformerFactory fabricaConversor = TransformerFactory.newInstance();
		//creamos el objeto Transfomer, que nos permitira serializar el arbol
		//dom a un fichero
		Transformer conversor = fabricaConversor.newTransformer();
		//creamos la fuente de la cual sacaremos el arbol dom
		DOMSource fuente = new DOMSource(doc); 
		//Creamos el flujo de salida, al fichero que queremos (tubito)
		StreamResult resultado = new StreamResult(new File("concierto.xml"));
		//por ultimo, serializamos los datos
		conversor.transform(fuente, resultado);
	}
}
