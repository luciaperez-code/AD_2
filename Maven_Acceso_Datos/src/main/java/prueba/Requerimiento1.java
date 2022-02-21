package prueba;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mysql.cj.Query;

import modelo.entidad.Autor;
import modelo.entidad.Editorial;
import modelo.entidad.Libreria;
import modelo.entidad.Libro;
import java.util.List;

public class Requerimiento1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("PruebaJPA");
		EntityManager em = factoria.createEntityManager();
		
		//notese que no le pongo el id ya que la bbdd me lo asignará
		Autor autor1 = new Autor("Ernesto", "Girona Santos", new Date());
		Autor autor2 = new Autor("Bebi", "Nacido García", new Date());
		Autor autor3 = new Autor("Francisca", "Sánchez Literata", new Date());
		
		//deberian ser listas de libros, no libros independientes en las editoriales
		Editorial editorial1 = new Editorial(null, "Trébol Gafotas", "C/Rue 8");
		Editorial editorial2 = new Editorial(null, "Intelectura", "C/Novelas 12");
		
		Libro libro1 = new Libro("Don Aventurero", 12.50, editorial1, autor1);
		Libro libro2 = new Libro("La Celestina", 5.50, editorial1, autor1);
		Libro libro3 = new Libro("Pirata", 50, editorial2, autor2);
		Libro libro4 = new Libro("Cocina Master Chef", 60.50, editorial1, autor3);
		Libro libro5 = new Libro("Cocina con la Abuela", 7.00, editorial2, autor2);
		Libro libro6 = new Libro("Cocina con Rafa Nadal", 0, editorial2, autor2);
		Libro libro7 = new Libro("Amor y Rafa Nadal", 8.50, editorial1, autor3);
		Libro libro8 = new Libro("Decadencia con Rafa Nadal", 4.50, editorial2, autor1);
		
		//para añadir listas
		List<Libro >listaAutor1 = new ArrayList<Libro>();
		List<Libro >listaAutor2= new ArrayList<Libro>();
		List<Libro >listaAutor3 = new ArrayList<Libro>();
		
		listaAutor1.add(libro1);
		listaAutor1.add(libro2);
		listaAutor1.add(libro8);
		listaAutor2.add(libro3);
		listaAutor2.add(libro5);
		listaAutor2.add(libro6);
		listaAutor3.add(libro4);
		listaAutor3.add(libro7);
		
		autor1.setLibros(listaAutor1);
		autor2.setLibros(listaAutor2);
		autor3.setLibros(listaAutor3);
		
		List<Libro> librosLibreria1 = new ArrayList<Libro>();
		librosLibreria1.add(libro1);
		librosLibreria1.add(libro2);
		librosLibreria1.add(libro3);
		librosLibreria1.add(libro4);
		List<Libro> librosLibreria2 = new ArrayList<Libro>();
		librosLibreria2.add(libro5);
		librosLibreria2.add(libro6);
		librosLibreria2.add(libro7);
		librosLibreria2.add(libro8);
		
		Libreria libreria1 = new Libreria(null, "Leonardo", "Leonardo Libero", "Avenida Da Vinci, 3", librosLibreria1);
		Libreria libreria2 = new Libreria(null, "Novelia", "Novelia Prosa", "C/Larga, 5", librosLibreria2);
		
		//Creamos listas de librerias para poder asignarselas a los libros, que en el constructor esperan recibir una List
		List<Libreria> librerias = new ArrayList<Libreria>();
		librerias.add(libreria1);
		libro1.setLibrerias(librerias);
		libro2.setLibrerias(librerias);
		libro3.setLibrerias(librerias);
		libro4.setLibrerias(librerias);
		List<Libreria> librerias2 = new ArrayList<Libreria>();
		librerias2.add(libreria2);
		libro5.setLibrerias(librerias2);
		libro6.setLibrerias(librerias2);
		libro7.setLibrerias(librerias2);
		libro8.setLibrerias(librerias2);
		
		
		
		//Siempre que modifiquemos la bbdd, ya sea por inserts, updates, deletes, etc.
		//debemos de abrir un contexto de transaccionalidad
		EntityTransaction et = em.getTransaction();
		et.begin();//empezamos la transaccion
		em.persist(autor1);//con este metodo, guardaremos el objeto persona en la tabla de bbdd
		em.persist(autor2);
		em.persist(autor3);
		
		em.persist(editorial1);
		em.persist(editorial2);
		em.persist(libro1);
		em.persist(libro2);
		em.persist(libro3);
		em.persist(libro4);
		em.persist(libro5);
		em.persist(libro6);
		em.persist(libro7);
		em.persist(libro8);
		em.persist(libreria1);
		em.persist(libreria2);
		
		et.commit();//Persistimos los cambios
		
		System.out.println("Datos añadidos con éxito");
		
		System.out.println("************REQUERIMIENTO 2************");
		javax.persistence.Query libros1 = em.createQuery("Select l from Libro l");
		List<Libro> lista = libros1.getResultList();
		System.out.println("Consulta 1: Los libros almacenados en nuestra base de datos son:");
		for (Libro l : lista){
			System.out.println("El título es " + l.getTitulo() + ", el autor es " + l.getAutores().getNombre() + " y la editorial es " + l.getEditoriales().getNombre() + ".");
		}
		
		System.out.println("");
		javax.persistence.Query autores = em.createQuery("Select a from Autor a");
		List<Autor> listaAutor = autores.getResultList();
		System.out.println("Consulta 2: Los autores almacenados en nuestra base de datos son:");
		for (Autor a : listaAutor){
			System.out.println("El autor es " + a.getNombre() +  " y sus libros son " + a.getLibros());
		}
		
		System.out.println("");
		javax.persistence.Query libreriasQuery = em.createQuery("Select l from Libreria l");
		List<Libreria> listaLibreria = libreriasQuery.getResultList();
		System.out.println("Consulta 3: Las librerías y sus libros asociados son:");
		for (Libreria l : listaLibreria){
			System.out.println("Librería: " + l.getNombre() +  " y sus libros son " + l.getLibros());
		}
		
		System.out.println("");
		javax.persistence.Query libros2 = em.createQuery("Select l from Libro l");
		List<Libro> listaLibros2 = libros2.getResultList();
		System.out.println("Consulta 4: Los libros dados de alta y sus librerías son:");
		for (Libro l : listaLibros2){
			System.out.println("Libro: " + l.getTitulo() +  " y su librería es " + l.getLibrerias());
		}
		
		em.close();//cerrar el entity manager
			
		
		
	}

}
