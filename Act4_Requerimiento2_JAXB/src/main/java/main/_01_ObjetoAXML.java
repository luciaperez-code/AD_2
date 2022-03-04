package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import beans.Concierto;
import beans.Participante;


public class _01_ObjetoAXML {
	public static void main(String[] args) {

		JAXBContext contexto;
		try {
			/*
			 * Obtiene el contexto asociado a la clase Persona, con dicho
			 * contexto podremos convertir el objeto a un xml y a la inversa. 
			 * Provoca una excepción de tipo JAXBException si la clase Persona 
			 * no cumple los requisitos para la conversión a XML, es decir, 
			 * contener las anotaciones necesarias y no cuenta con un constructor 
			 * sin argumentos.
			 */
			contexto = JAXBContext.newInstance(Concierto.class);//inyeccion de dependecia
		} catch (JAXBException e) {
			System.out.println("Error creando el contexto");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}

		Marshaller m;
		try {
			/*
			 * Obtiene el objeto Marshaller asociado al contexto.
			 * Con dicho objeto podremos convertir un objeto en xml
			 * es decir, lo serializaremos
			 */
			m = contexto.createMarshaller();
			/*
			 * stablecer la propiedad JAXB_FORMATTED_OUTPUT con el valor true 
			 * permite que en la conversión a formato XML se incluyan retornos 
			 * de carro e indentación (sangrado del texto). 

			 */
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			//creamos la persona y su direccion
			Concierto c = new Concierto("20-oct-21", "21:30");
			Participante p1 = new Participante("21:30", "Las Ardillas de Dakota");
			Participante p2 = new Participante("22:15", "Fito y fitipaldis");
			Participante p3 = new Participante("23:00", "Coldplay");
			List participantes = new ArrayList();
			participantes.add(p1);
			participantes.add(p2);
			participantes.add(p3);
			
			c.setParticipantes(participantes);
			
			//Convertimos un objeto a xml y lo imprimimos por pantalla
			m.marshal(c, System.out);
			//tambien podemos crear un fichero
			m.marshal(c, new File("concierto.xml"));
		} catch (JAXBException e) {
			System.out.println("Error convertiendo el objeto a formato XML");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
