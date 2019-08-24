package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import static javax.persistence.FetchType.EAGER;


/**
 * The persistent class for the adresse database table.
 * 
 */
@Entity
@NamedQuery(name="Adresse.findAll", query="SELECT a FROM Adresse a")
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte east;

	@Lob
	private String gebaeude;

	private BigDecimal gpsbreite;

	private BigDecimal gpslaenge;

	private byte north;

	private String ort;

	private String plz;

	private String strasse;

	//bi-directional many-to-one association to Besitzer
	@ManyToOne
	private Besitzer besitzer;

	//bi-directional many-to-one association to AdressePhoto
	@OneToMany(mappedBy="adresse", fetch = EAGER)
	private List<AdressePhoto> adressePhotos;

	public Adresse() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getEast() {
		return this.east;
	}

	public void setEast(byte east) {
		this.east = east;
	}

	public String getGebaeude() {
		return this.gebaeude;
	}

	public void setGebaeude(String gebaeude) {
		this.gebaeude = gebaeude;
	}

	public BigDecimal getGpsbreite() {
		return this.gpsbreite;
	}

	public void setGpsbreite(BigDecimal gpsbreite) {
		this.gpsbreite = gpsbreite;
	}

	public BigDecimal getGpslaenge() {
		return this.gpslaenge;
	}

	public void setGpslaenge(BigDecimal gpslaenge) {
		this.gpslaenge = gpslaenge;
	}

	public byte getNorth() {
		return this.north;
	}

	public void setNorth(byte north) {
		this.north = north;
	}

	public String getOrt() {
		return this.ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getPlz() {
		return this.plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return this.strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public Besitzer getBesitzer() {
		return this.besitzer;
	}

	public void setBesitzer(Besitzer besitzer) {
		this.besitzer = besitzer;
	}

	public List<AdressePhoto> getAdressePhotos() {
		return this.adressePhotos;
	}

	public void setAdressePhotos(List<AdressePhoto> adressePhotos) {
		this.adressePhotos = adressePhotos;
	}

	public AdressePhoto addAdressePhoto(AdressePhoto adressePhoto) {
		getAdressePhotos().add(adressePhoto);
		adressePhoto.setAdresse(this);

		return adressePhoto;
	}

	public AdressePhoto removeAdressePhoto(AdressePhoto adressePhoto) {
		getAdressePhotos().remove(adressePhoto);
		adressePhoto.setAdresse(null);

		return adressePhoto;
	}
	
	@Override
	public String toString() {
		return getStrasse();
	}

}