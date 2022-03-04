import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		DocumentBuilder analizador;
		Document doc;
		Node raiz;
		
		try {
			analizador = fabrica.newDocumentBuilder();
			doc = analizador.parse("concierto.xml");
			raiz = doc.getDocumentElement();
			
			System.out.println("Fecha y hora del concierto: "+ obtenerFechaHora(raiz));
			System.out.println("Participarán los siguientes grupos:");
			leerParticipantes(raiz);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	
	
	public static String obtenerFechaHora(Node raiz) {
		NodeList nodos = raiz.getChildNodes();
		String imprimir ="";
		for(int i = 1; i < nodos.getLength(); i++) {
			Node nodoHijo = nodos.item(i);
			imprimir += nodoHijo.getChildNodes().item(0).getTextContent() + " ";
			
		}
		
		return imprimir;
	}
	
	private static void leerParticipantes(Node raiz) {
		//Obtenemos los nodos hijos de concierto
		NodeList nodos = raiz.getChildNodes();
		Node participantes = nodos.item(0);
		
		//Obtenemos los nodos hijos de participantes. Es decir, los nodo participante
		NodeList numero = participantes.getChildNodes();
		String imprimir="	";
		//Recorremos cada uno de los participantes y obtenemos sus hijos
		for(int i = 0; i < numero.getLength(); i++) {
			Node nodoHijo = numero.item(i);
			NodeList detalles = nodoHijo.getChildNodes();
			
			for( int j = 0; j <detalles.getLength(); j++) {
				Node lectura = detalles.item(j);
				imprimir += lectura.getTextContent() + " ";
			}
			System.out.println(imprimir);
			imprimir="	";
		}
		
	}
	
	
		
	

}
