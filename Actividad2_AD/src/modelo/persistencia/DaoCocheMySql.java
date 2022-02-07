package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoCocheMySql implements DaoCoche {

	private Connection conexion;

	public boolean abrirConexion() {
		String url = "jdbc:mysql://localhost:3306/concesionario";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
		} catch (CommunicationsException ce) {
			System.out.println("No se ha podido establecer la comunicación con la BBDD");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addCoche(Coche c) {
		if (!abrirConexion()) {
			return false;
		}
		boolean alta = true;

		String query = "insert into COCHES (MATRICULA, MARCA, MODELO, COLOR) " + " values(?,?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);

			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setString(4, c.getColor());

			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.out.println("Matrícula duplicada");
			} else {
				e.printStackTrace();
			}

			alta = false;

		} finally {
			cerrarConexion();
		}

		if (alta) {
			System.out.println("Coche creado con éxito");
		} else {
			System.out.println("Lo sentimos, no se ha podido crear este coche");

		}

		return alta;
	}

	@Override
	public boolean borrarPorId(int id) {
		if (!abrirConexion()) {
			return false;
		}

		boolean borrado = true;
		String query = "delete from coches where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);

			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		if (borrado) {
			System.out.println("Coche con ID: " + id + " borrado con éxito");
		} else {
			System.out.println("Lo sentimos, no se ha podido borrar el coche con ID: " + id);
		}

		return borrado;
	}

	@Override
	public boolean modificar(Coche c) {
		if (!abrirConexion()) {
			return false;
		}
		boolean modificado = true;
		String query = "update COCHES set MATRICULA=?, MARCA=?, " + "MODELO=?, COLOR=? WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setString(4, c.getColor());
			ps.setInt(5, c.getId());

			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				modificado = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Modificar -> error al modificar el " + " coche " + c);
			modificado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		if (modificado) {
			System.out.println("Coche modificado con éxito");
		} else {
			System.out.println("Lo sentimos, no se ha podido modificar el coche con ID: " + c.getId());
		}

		return modificado;
	}

	@Override
	public Coche buscarPorId(int id) {
		if (!abrirConexion()) {
			return null;
		}
		Coche coche = null;

		String query = "select ID,MATRICULA, MARCA, MODELO, COLOR from COCHES " + "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setColor(rs.getString(5));

			}

			if (coche == null) {
				System.out.println("Coche no encontrado");
			}

		} catch (NullPointerException npe) {
			npe.printStackTrace();
			System.out.println("No hemos encontrado ningun Coche con ID: " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return coche;
	}

	@Override
	public List<Coche> listar() {
		if (!abrirConexion()) {
			return null;
		}
		List<Coche> listaCoches = new ArrayList<>();

		String query = "SELECT ID,MATRICULA, MARCA, MODELO, COLOR from coches";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setColor(rs.getString(5));

				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("Listar -> error al obtener los coches");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		if (listaCoches.isEmpty()) {
			System.out.println("La lista de coches está vacía :(");
		} else {
			System.out.println("Listando coches: " + listaCoches);
		}
		return listaCoches;
	}

	@Override
	public List<Coche> listarRellenarLista() {
			if(!abrirConexion()){
				return null;
			}		
			List<Coche> listaCoches = new ArrayList<>();
			
			String query = "SELECT ID,MATRICULA, MARCA, MODELO, COLOR from coches";
			try {
				PreparedStatement ps = conexion.prepareStatement(query);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					Coche coche = new Coche();
					coche.setId(rs.getInt(1));
					coche.setMatricula(rs.getString(2));
					coche.setMarca(rs.getString(3));
					coche.setModelo(rs.getString(4));
					coche.setColor(rs.getString(5));
					
					listaCoches.add(coche);
				}
			} catch (SQLException e) {
				System.out.println("Listar -> error al obtener los coches");
				e.printStackTrace();
			} finally {
				cerrarConexion();
			}
			
			if (listaCoches.isEmpty()) {
				System.out.println("La lista de coches está vacía :(");
				return null;
			}else {
				return listaCoches;
			}
	}

}
