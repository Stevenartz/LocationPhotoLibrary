package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import config.JPAUtil;
import model.Adresse;
import model.Besitzer;

public class Main {

	public static void main(String[] args) {
		new Main();
	}

	
	Main() {
		
		BesitzerDAO bDAO = new BesitzerDAO();
		System.out.println(bDAO.findById(1).getAnsprechpartner());
		System.out.println(">>> Before: " + bDAO.findAll());

		
		
		// ADD
//		Besitzer besitzer = new Besitzer();
//		besitzer.setAnsprechpartner("Jasmin Ulrich");
//		besitzer.setFirma(3);
//		
//		bDAO.create(besitzer);
		
		// DELETE
//		Besitzer besitzer = bDAO.findById(2);
//		System.out.println(bDAO.delete(6));
		
		System.out.println(">>> After: " + bDAO.findAll());
	}
	
	
	
}
