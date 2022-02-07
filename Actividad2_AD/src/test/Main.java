package test;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DaoCoche daoCoche = new DaoCocheMySql();
		DaoPasajero daoPasajero = new DaoPasajeroMySql();

		try (Scanner sc = new Scanner(System.in)) {

			boolean continuar = true;

			do {
				System.out.println("Menú del servidor de concesionario:\n" + "1- Añadir nuevo coche\r\n"
						+ "2- Borrar coche por ID\r\n" + "3- Consulta coche por ID\r\n"
						+ "4- Modificar coche por ID\r\n" + "5- Listado de coches\r\n" + "6- Gestión de pasajeros\r\n"
						+ "7- Terminar el programa");
				String opcionMenu = sc.nextLine();

				try {
					switch (opcionMenu) {

					case "1":
						System.out.println("Escriba una matrícula");
						String matricula = sc.nextLine();
						System.out.println("Escriba un marca");
						String marca = sc.nextLine();
						System.out.println("Escriba un modelo");
						String modelo = sc.nextLine();
						System.out.println("Escriba un color");
						String color = sc.nextLine();
						Coche c1 = new Coche(matricula, marca, modelo, color);

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
						System.out.println("Escriba un id");
						String idsMod = sc.nextLine();
						Integer idMod = Integer.parseInt(idsMod);
						System.out.println("Escriba una matrícula");
						String matriculaMod = sc.nextLine();
						System.out.println("Escriba un marca");
						String marcaMod = sc.nextLine();
						System.out.println("Escriba un modelo");
						String modeloMod = sc.nextLine();
						System.out.println("Escriba un color");
						String colorMod = sc.nextLine();
						Coche c2 = new Coche(idMod, matriculaMod, marcaMod, modeloMod, colorMod);

						daoCoche.modificar(c2);
						break;

					case "5":
						daoCoche.listar();
						break;

					case "6":
						System.out.println("*****************************************");
						boolean continuarPasajeros = true;

						do {
							System.out.println("Menú pasajeros:\n" + "1- Añadir nuevo pasajero\r\n"
									+ "2- Borrar pasajero por id\r\n" + "3- Consulta pasajero por ID\r\n"
									+ "4- Listar todos los pasajeros\r\n" + "5- Añadir pasajero a coche\r\n"
									+ "6- Eliminar pasajero de un coche\r\n"
									+ "7- Listar todos los pasajeros de un coche\r\n"
									+ "8- Salir del menú de pasajeros");
							String opcionSubmenu = sc.nextLine();

							try {

								switch (opcionSubmenu) {

								case "1":
									System.out.println("Escriba un nombre");
									String nombre = sc.nextLine();
									System.out.println("Escriba un edad");
									String edadS = sc.nextLine();
									int edad = Integer.parseInt(edadS);
									System.out.println("Escriba un peso");
									String pesoS = sc.nextLine();
									Double peso = Double.parseDouble(pesoS);
									Pasajero p1 = new Pasajero(nombre, edad, peso);

									daoPasajero.addPasajero(p1);
									break;

								case "2":
									System.out.println("Escriba el ID del pasajero a borrar");
									String idsPasajeroBorrar = sc.nextLine();
									Integer idPasajeroBorrar = Integer.parseInt(idsPasajeroBorrar);

									daoPasajero.borrarPorId(idPasajeroBorrar);
									break;

								case "3":
									System.out.println("Escriba un ID del pasajero a consultar");
									String idsConsultaPasajero = sc.nextLine();
									Integer idConsultaPasajero = Integer.parseInt(idsConsultaPasajero);

									daoPasajero.buscarPorId(idConsultaPasajero);
									break;

								case "4":
									daoPasajero.listar();
									break;

								case "5":
									System.out.println("Estos son los coches disponibles");
									daoCoche.listar();
									
									System.out.println("Escriba el ID del pasajero a añadir");
									String idsPasajeroAdd = sc.nextLine();
									Integer idPasajeroAdd = Integer.parseInt(idsPasajeroAdd);

									System.out.println("Escriba el ID del coche al que añadir");
									String idsCocheAdd = sc.nextLine();
									Integer idCocheAdd = Integer.parseInt(idsCocheAdd);

									daoPasajero.addPasajeroACoche(idPasajeroAdd, idCocheAdd);
									break;

								case "6":
									
									System.out.println("Estos son los coches y sus pasajeros: ");
									List<Coche> listaCoches = new ArrayList<Coche>();
					                listaCoches = daoCoche.listarRellenarLista();

					                for (Coche car : listaCoches) {
					                    //System.out.println(car);
					                    daoPasajero.pasajerosEnCoche(car.getId());
					                }
					                
									System.out.println("Escriba el ID del pasajero");
									String idsPasajeroDel = sc.nextLine();
									Integer idPasajeroDel = Integer.parseInt(idsPasajeroDel);

									System.out.println("Escriba el ID del coche al que eliminar el pasajero");
									String idsCocheDel = sc.nextLine();
									Integer idCocheDel = Integer.parseInt(idsCocheDel);

									daoPasajero.eliminarPasajeroACoche(idPasajeroDel, idCocheDel);
									break;

								case "7":
									System.out.println("Escriba un ID del coche");
									String idsCocheList = sc.nextLine();
									Integer idCocheList = Integer.parseInt(idsCocheList);

									daoPasajero.pasajerosEnCoche(idCocheList);
									break;

								case "8":
									continuarPasajeros = false;
									break;

								default:
									System.out.println("Petición incorrecta");
									break;
								}
							} catch (NumberFormatException ex) {
								System.out.println("Formato no válido, recuerde que ID, edad y peso son números");
								System.out.println(ex.getMessage());
							} catch (Exception e) {
								e.printStackTrace();
							}

						} while (continuarPasajeros);
						System.out.println("*****************************************\n");
						
						break;

					case "7":
						System.out.println("Programa terminado. ¡Hasta pronto!");
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
