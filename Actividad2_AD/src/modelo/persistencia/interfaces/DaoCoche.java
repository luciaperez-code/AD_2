package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

//Esta interfaz define un CRUD para el objeto Coche

public interface DaoCoche {
	public boolean addCoche(Coche c);
	public boolean borrarPorId(int id);
	public boolean modificar(Coche c);
	public Coche buscarPorId(int id);
	public List<Coche> listar();
	public List<Coche> listarRellenarLista();
}
