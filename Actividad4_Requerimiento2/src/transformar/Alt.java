package transformar;

import java.io.File;
import java.io.StringWriter;
 
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import entidad.OficinaPostal;
import entidad.Paquete;
 
public class Alt { 
  public static void main(String[] args) 
  {
    //Java object. We will convert it to XML.
	  OficinaPostal p = new OficinaPostal(1, "Correos Miraflores", 20, "Avenida Miraflores, 6, Malaga");
		Paquete paquete = new Paquete();
		paquete.setNombreDestinatario("Romeo Romero");
		paquete.setDireccionDestinatario("C/Abedul 3, Madrid");
		paquete.setNombreRemitente("Rosa Rosales");
		paquete.setDireccionRemitente("C/Pinar 6, Valladolid");
		p.setPaquete(paquete.toString());
     
    //Method which uses JAXB to convert object to XML
    jaxbObjectToXML(p);
  }
 
  private static void jaxbObjectToXML(OficinaPostal p) 
  {
      try
      {
        //Create JAXB Context
          JAXBContext jaxbContext = JAXBContext.newInstance(OficinaPostal.class);
           
          //Create Marshaller
          Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
          //Required formatting??
          jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
         //Store XML to File
          File file = new File("oficinaPostal.xml");
           
          //Writes XML file to file-system
          jaxbMarshaller.marshal(p, file); 
      } 
      catch (JAXBException e) 
      {
          e.printStackTrace();
      }
  }
}