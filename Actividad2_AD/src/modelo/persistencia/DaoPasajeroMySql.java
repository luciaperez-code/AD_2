package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero{

	private Connection conexion;
	
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/concesionario";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (CommunicationsException ce) {
			System.out.println("No se ha podido establecer la comunicación con la BBDD");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	@Override
	public boolean addPasajero(Pasajero p) {
		// TODO Auto-generated method stub
		
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into PASAJEROS (NOMBRE, EDAD, PESO) "
				+ " values(?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);

			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.out.println("Matrícula duplicada");
			}else {
				e.printStackTrace();
			}
            
			alta = false;
		
		}finally{
			cerrarConexion();
		}
		
		if(alta) {
			System.out.println("Pasajero creado con éxito");
		}else {
			System.out.println("Lo sentimos, no se ha podido crear este pasajero");

		}
		
		return alta;
	}

	
	@Override
	public boolean borrarPorId(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from PASAJEROS where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		if (borrado) {
			System.out.println("Pasajero con ID: " + id + " borrado con éxito");
		}else {
			System.out.println("Lo sentimos, no se ha podido borrar el pasajero con ID: " + id);
		}
		
		return borrado; 
	}

	
	@Override
	public Pasajero buscarPorId(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Pasajero Pasajero = null;
		
		String query = "select ID,NOMBRE, EDAD, PESO, ID_COCHE from PASAJEROS "
				+ "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Pasajero = new Pasajero();
				Pasajero.setId(rs.getInt(1));
				Pasajero.setNombre(rs.getString(2));
				Pasajero.setEdad(rs.getInt(3));
				Pasajero.setPeso(rs.getDouble(4));
				Pasajero.setCoche(rs.getInt(5));

			}
		} catch (NullPointerException npe) {
			System.out.println("No hemos encontrado ningun Pasajero con ID: " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		if (Pasajero.equals(null)) {
			System.out.println("No hemos encontrado ningun Pasajero con ID: " + id);
		}else {
			System.out.println("Pasajero encontrado: " + Pasajero);
		}
		
		return Pasajero;
	}

	@Override
	public List<Pasajero> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "SELECT ID,NOMBRE, EDAD, PESO, ID_COCHE from PASAJEROS";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				pasajero.setCoche(rs.getInt(5));
				
				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("Listar -> error al obtener los Pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		if (listaPasajeros.isEmpty()) {
			System.out.println("La lista de Pasajeros está vacía :(");
		}else {
			System.out.println("Listando Pasajeros: " + listaPasajeros);
		}
		return listaPasajeros;
	}


	@Override
	public boolean addPasajeroACoche(int idPasajero, int idCoche) {
		// TODO Auto-generated method stub
		boolean pasajeroAñadido = true;
		
		if(!abrirConexion()){
			pasajeroAñadido = false;
		}		
		
		String query = "UPDATE PASAJEROS set ID_COCHE=? WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
						
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				pasajeroAñadido = false;
		} catch(SQLIntegrityConstraintViolationException e) { 
			System.out.println("El coche con ID: " + idCoche + " no existe");
			pasajeroAñadido = false;
		}catch (Exception e) {
			pasajeroAñadido = false;
			System.out.println("Añadir -> error al obtener los Pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		if (!pasajeroAñadido) {
			System.out.println("No hemos podido añadir al pasajero " + idPasajero + " al coche " + idCoche);
		}else {
			System.out.println("Pasajero con ID: " + idPasajero + " añadido con éxito al coche " + idCoche);
		}
		
		return pasajeroAñadido;
	}

	@Override
	public boolean eliminarPasajeroACoche(int idPasajero, int idCoche) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean eliminado = true;
		String query = "UPDATE PASAJEROS set ID_COCHE=null WHERE ID=? AND ID_COCHE = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idPasajero);
			ps.setInt(2, idCoche);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				eliminado = false;
		} catch (SQLException e) {
			eliminado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		if (eliminado) {
			System.out.println("Pasajero con ID: " + idPasajero + " borrado con éxito del coche: " + idCoche);
		}else {
			System.out.println("Lo sentimos, no se ha podido borrar el pasajero con ID: " + idPasajero + " del coche: " + idCoche);
		}
		
		return eliminado; 
	}

	@Override
	public List<Pasajero> pasajerosEnCoche(int idCoche) {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "SELECT ID,NOMBRE, EDAD, PESO, ID_COCHE from PASAJEROS WHERE ID_COCHE = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				pasajero.setCoche(rs.getInt(5));
				
				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("Listar -> error al obtener los Pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		if (listaPasajeros.isEmpty()) {
			System.out.println("No hay ningún pasajero para ese coche :(");
		}else {
			System.out.println("Pasajeros que están en el coche " + idCoche + " --> " + listaPasajeros);
		}
		return listaPasajeros;
	}
	
	

}
