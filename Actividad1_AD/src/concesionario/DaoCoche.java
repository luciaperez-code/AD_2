package concesionario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DaoCoche {

	public static final String FICHERO = "coches.dat";
	public static String ficheroTexto = "coches.txt";
	private List<Coche> listaCoches;

	// Getter y setter
	public List<Coche> getListaCoches() {
		return listaCoches;
	}

	public void setListaCoches(List<Coche> listaCoches) {
		this.listaCoches = listaCoches;
	}
 
	// Funcionalidad

	// 0- Comprueba la existencia de coches.dat y crea el array_list
	// No hago addCoche (método1) porque sobreescribiría el ID y de esta forma
	// mantiene el que tenía en el fichero
	public boolean comprobacionInicial() {
		File fn = new File(FICHERO);
		try {

			// Si no existe, dejo colección ArrayList disponible
			if (!fn.exists()) {
				fn.createNewFile();
				System.out.println("Creado el archivo " + fn.getName());

				// Si existe, leo coches.dat y lleno una colección ArrayList con objetos Coche
			} else {
				System.out.println("Fichero ya creado " + fn.getName());

				try (FileInputStream fis = new FileInputStream(fn);
						ObjectInputStream ois = new ObjectInputStream(fis);) {

					listaCoches = (List<Coche>) ois.readObject();

					System.out.println("Guardando en ArrayList los coches que ya estaban en el fichero:");

					for (int i = 0; i < listaCoches.size(); i++) {
						System.out.println(listaCoches.get(i).toString());
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	// 1- Añadir por ID
	public boolean addCoche(Coche l) {

		boolean camposUnicos = true;

		for (int i = 0; i < listaCoches.size(); i++) {
			if (listaCoches.get(i).getId() == l.getId()) {
				System.out.println("No se puede añadir este coche, ID duplicado");
				camposUnicos = false;
			}
			if (listaCoches.get(i).getMatricula().equals(l.getMatricula())) {
				System.out.println("No se puede añadir este coche, matrícula duplicada");
				camposUnicos = false;
			}
		}

		if (camposUnicos == true) {
			listaCoches.add(l);
			System.out.println(l);
			System.out.println("Coche guardado :)");
		}

		return camposUnicos;
	}

	// 2- Borrar por ID
	public boolean borrarPorId(int id) {

		int cocheBorrar = 0;
		boolean borrar = false;
		for (int i = 0; i < listaCoches.size(); i++) {

			if (listaCoches.get(i).getId() == id) {
				cocheBorrar = i;
				borrar = true;
				break;
			}
		}

		if (borrar == true) {
			listaCoches.remove(cocheBorrar);
			System.out.println("Coche con ID: " + id + " borrado con éxito");
			return true;
		} else {
			System.out.println("No hemos encontrado ningún objeto con ID: " + id);
			return false;
		}

	}

	// 3- Consulta por ID
	public Coche buscarPorId(int id) {
		Coche Coche = null;

		for (Coche l : listaCoches) {
			if (l.getId() == id) {
				Coche = l;
				System.out.println(l);
				break;
			}
		}
		if (Coche == null) {
			System.out.println("No hemos encontrado ningún coche con ID: " + id);
		}
		return Coche;
	}

	// 4- Listado de coches
	public List<Coche> listarCoches() {

		if (listaCoches.isEmpty()) {
			System.out.println("Lo sentimos, la lista está vacía :(");

		} else {
			for (Coche p : listaCoches) {
				System.out.println(p);
			}
		}

		return listaCoches;
	}

	// 5- Exportar coches a un archivo de texto
	public void ficheroTexto(DaoCoche daoCoches) {

		try (FileWriter fw = new FileWriter(ficheroTexto); BufferedWriter bfw = new BufferedWriter(fw)) {

			for (Coche coche : daoCoches.listarCoches()) {
				bfw.write(coche.toString());
				bfw.newLine();
			}
			System.out.println("Fichero creado con éxito");

		} catch (FileNotFoundException fnf) {
			System.out.println("El fichero no existe");
			fnf.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 6- Guardar todo en el FICHERO
	public boolean guardarEnFichero() {

		try (FileOutputStream fos = new FileOutputStream(FICHERO);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			oos.writeObject(listaCoches);
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
