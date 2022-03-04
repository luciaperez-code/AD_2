package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import beans.Edificio;
import beans.Vivienda;

public class ObjectToXml {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Vivienda> viviendas = new ArrayList<Vivienda>();
		JAXBContext contexto;
		try {
			
			contexto = JAXBContext.newInstance(Edificio.class);
		} catch (JAXBException e) {
			System.out.println("Error creando el contexto");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
		
		Marshaller m;
		try {
			
			m = contexto.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			//creamos la persona y su direccion
			Edificio e = new Edificio("Gran Via", "138", 37);
			Vivienda vivienda = new Vivienda();
			vivienda.setIdVivienda(1);
			vivienda.setPlanta("Primero");;
			vivienda.setPuerta("A");
			vivienda.setInquilino("Marisa Pérez");
			viviendas.add(vivienda);
			
			Vivienda vivienda2 = new Vivienda();
			vivienda2.setIdVivienda(2);
			vivienda2.setPlanta("Bajo");;
			vivienda2.setPuerta("D");
			vivienda2.setInquilino("Pedro Fernández");
			viviendas.add(vivienda2);
			
			e.setViviendas(viviendas);
			
			//Convertimos un objeto a xml y lo imprimimos por pantalla
			m.marshal(e, System.out);
			//tambien podemos crear un fichero
			m.marshal(e, new File("Edificio.xml"));
		} catch (JAXBException e) {
			System.out.println("Error convertiendo el objeto a formato XML");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
