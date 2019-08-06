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
		AdresseDAO aDAO = new AdresseDAO();
		System.out.println(bDAO.findById(1).getAdresses());
		System.out.println(">>> Before: " + bDAO.findAll());

		
		// ADD
//		Adresse ad = new Adresse();
//		ad.setStrasse("Seebelstrasse 31");
//		ad.setPlz("8422");
//		ad.setOrt("Pfungen");
//		ad.setGebaeude("VZA");
//		ad.setBesitzer(bDAO.findById(1));
//		
//		aDAO.create(ad);
		
		// DELETE
//		System.out.println(aDAO.delete(1));
		
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
