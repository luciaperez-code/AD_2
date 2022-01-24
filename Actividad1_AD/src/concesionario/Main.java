package concesionario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Coche> listaCoche = new ArrayList<Coche>();
		DaoCoche daoCoche = new DaoCoche();
		daoCoche.setListaCoches(listaCoche);

		// Primero compruebo si existe el fichero coches.dat y creo el ArrayList
		daoCoche.comprobacionInicial();

		try (Scanner sc = new Scanner(System.in)) {

			boolean continuar = true;

			do {
				System.out.println("Menú del servidor de concesionario:\n" + "1- Añadir nuevo coche\r\n"
						+ "2- Borrar coche por ID\r\n" + "3- Consulta coche por ID\r\n" + "4- Listado de coches\r\n"
						+ "5- Exportar coches a archivo de texto\r\n" + "6- Terminar el programa");
				String opcionMenu = sc.nextLine();

				try {
					switch (opcionMenu) {

					case "1":
						System.out.println("Escriba un ID");
						String idString = sc.nextLine();
						int id = Integer.parseInt(idString);
						System.out.println("Escriba una matrícula");
						String matricula = sc.nextLine();
						System.out.println("Escriba un marca");
						String marca = sc.nextLine();
						System.out.println("Escriba un modelo");
						String modelo = sc.nextLine();
						System.out.println("Escriba un color");
						String color = sc.nextLine();
						Coche c1 = new Coche(id, matricula, marca, modelo, color);

						daoCoche.addCoche(c1);
						break;

					case "2":
						System.out.println("Escriba un id");
						String idsBorrar = sc.nextLine();
						Integer idBorrar = Integer.parseInt(idsBorrar);

						daoCoche.borrarPorId(idBorrar);

						break;

					case "3":
						System.out.println("Escriba un id");
						String idsConsulta = sc.nextLine();
						Integer idConsulta = Integer.parseInt(idsConsulta);

						daoCoche.buscarPorId(idConsulta);

						break;

					case "4":
						daoCoche.listarCoches();

						break;

					case "5":
						daoCoche.ficheroTexto(daoCoche);
						break;

					case "6":
						System.out.println("Guardando cambios...");
						daoCoche.guardarEnFichero();
						System.out.println("Listo, programa terminado. ¡Hasta pronto!");
						continuar = false;
						break;

					default:
						System.out.println("Petición incorrecta");
						break;
					}

					// Capturo posibles excepciones en la entrada de datos por consola
				} catch (NumberFormatException ex) {
					System.out.println("Formato no válido, recuerde que ID es un número");
					System.out.println(ex.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}

			} while (continuar);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
