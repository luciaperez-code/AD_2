package main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import beans.Edificio;

public class XmlToObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			JAXBContext contexto = JAXBContext.newInstance(Edificio.class);
			//Esta vez creamos un objeto que nos permite pasar
			//de XML a Object, es decir deserializar
			Unmarshaller u = contexto.createUnmarshaller();
			File fichero = new File("Edificio.xml");
			if (fichero.exists()) {
				Edificio e = (Edificio) u.unmarshal(fichero);
				System.out.println(e.getCalle());
				System.out.println(e.getNumero());
				System.out.println(e.getNumPlantas());
				System.out.println(e.getViviendas());
				System.out.println(e);
			} else {
				System.out.println("Fichero XML Edificio.xml no encontrado");
			}

		} catch (JAXBException e) {
			System.out.println(e.getMessage());
		}
	}

}
