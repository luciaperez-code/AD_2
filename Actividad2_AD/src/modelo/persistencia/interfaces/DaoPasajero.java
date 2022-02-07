package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Pasajero;

//Interfaz para objeto Pasajero
public interface DaoPasajero {
	public boolean addPasajero(Pasajero p);
	public boolean borrarPorId(int idPasajero);
	public Pasajero buscarPorId(int id);
	public List<Pasajero> listar();
	public boolean addPasajeroACoche(int idPasajero, int idCoche);
	public boolean eliminarPasajeroACoche(int idPasajero, int idCoche);
	public List<Pasajero> pasajerosEnCoche(int idCoche);
}
