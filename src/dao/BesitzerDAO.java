package dao;

import java.util.List;

import model.Besitzer;

public class BesitzerDAO extends AbstractDAO {

	public BesitzerDAO() {
		super();
	}
	
	public Besitzer findById(int id) {
		return (Besitzer) super.findById(Besitzer.class, id);
	}
	
	public List<Besitzer> findAll() {
		return super.findAll(Besitzer.class);
	}
	
	public void create(Besitzer besitzer) {
		super.create(besitzer);
	}
	
	public boolean delete(int id) {
		return super.delete(id, Besitzer.class);
	}
}
