package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the besitzer database table.
 * 
 */
@Entity
@NamedQuery(name="Besitzer.findAll", query="SELECT b FROM Besitzer b")
public class Besitzer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String ansprechpartner;

	private String email;

	private int firma;

	private String telefon;

	//bi-directional many-to-one association to Adresse
	@OneToMany(mappedBy="besitzer")
	private List<Adresse> adresses;

	public Besitzer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnsprechpartner() {
		return this.ansprechpartner;
	}

	public void setAnsprechpartner(String ansprechpartner) {
		this.ansprechpartner = ansprechpartner;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFirma() {
		return this.firma;
	}

	public void setFirma(int firma) {
		this.firma = firma;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<Adresse> getAdresses() {
		return this.adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public Adresse addAdress(Adresse adress) {
		getAdresses().add(adress);
		adress.setBesitzer(this);

		return adress;
	}

	public Adresse removeAdress(Adresse adress) {
		getAdresses().remove(adress);
		adress.setBesitzer(null);

		return adress;
	}

	@Override
	public String toString() {
		return getFirma() + " - " + getAnsprechpartner();
	}
}