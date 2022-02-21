package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	
	public static EntityManagerFactory emf;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		emf = Persistence.createEntityManagerFactory("garaje");
			
		EntityManager em = null;
		
		

	}

}
