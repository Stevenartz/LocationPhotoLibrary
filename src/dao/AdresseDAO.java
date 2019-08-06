package dao;

import model.Adresse;

public class AdresseDAO extends AbstractDAO {

	public Adresse findById(int id) {
		return (Adresse) super.findById(Adresse.class, id);
	}

	public void create(Adresse adresse) {
		super.create(adresse);
	}
	
	public boolean delete(int id) {
		return super.delete(id, Adresse.class);
	}
	
}
