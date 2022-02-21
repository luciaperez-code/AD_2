package modelo.entidad;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="conductores")
public class Conductor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	String nombre, dni;
	
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@ManyToMany
	@JoinTable(name="conductores_coche",
			joinColumns= {@JoinColumn(name= "fk_id_conductor", referencedColumnName="id")},
			inverseJoinColumns= {@JoinColumn(name="fk_id_coche", referencedColumnName ="id")})
	private List<Coche> coches;
	
	

}
