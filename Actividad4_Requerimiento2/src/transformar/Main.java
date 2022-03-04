package transformar;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import entidad.OficinaPostal;
import entidad.Paquete;

//persona es oficina postal y direccion es paquete
public class Main {
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
			contexto = JAXBContext.newInstance(OficinaPostal.class);//inyeccion de dependecia
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
			 * Prueba a ejecutar el programa con los valores true y 
			 * false para ver la diferencia.
			 */
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			//creamos la persona y su direccion
			OficinaPostal p = new OficinaPostal(1, "Correos Miraflores", 20, "Avenida Miraflores, 6, Malaga");
			Paquete paquete = new Paquete();
			paquete.setNombreDestinatario("Romeo Romero");
			paquete.setDireccionDestinatario("C/Abedul 3, Madrid");
			paquete.setNombreRemitente("Rosa Rosales");
			paquete.setDireccionRemitente("C/Pinar 6, Valladolid");
			p.setPaquete(paquete.toString());
			
			//Convertimos un objeto a xml y lo imprimimos por pantalla
			//m.marshal(p, System.out);
			System.out.println(p);
			//tambien podemos crear un fichero
			m.marshal(p, new File("paquete_romeo.xml"));
		} catch (JAXBException e) {
			System.out.println("Error convertiendo el objeto a formato XML");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		   
		} 
	}
		
	
	

