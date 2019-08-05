package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@NamedQuery(name="Photo.findAll", query="SELECT p FROM Photo p")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String bemerkung;

	private String breite;

	private String dateiname;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datumimport;

	private byte east;

	private BigDecimal gpsbreite;

	private BigDecimal gpslaenge;

	private String hoehe;

	private byte north;

	private String titel;

	//bi-directional many-to-one association to AdressePhoto
	@OneToMany(mappedBy="photo")
	private List<AdressePhoto> adressePhotos;

	public Photo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBemerkung() {
		return this.bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

	public String getBreite() {
		return this.breite;
	}

	public void setBreite(String breite) {
		this.breite = breite;
	}

	public String getDateiname() {
		return this.dateiname;
	}

	public void setDateiname(String dateiname) {
		this.dateiname = dateiname;
	}

	public Date getDatumimport() {
		return this.datumimport;
	}

	public void setDatumimport(Date datumimport) {
		this.datumimport = datumimport;
	}

	public byte getEast() {
		return this.east;
	}

	public void setEast(byte east) {
		this.east = east;
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

	public String getHoehe() {
		return this.hoehe;
	}

	public void setHoehe(String hoehe) {
		this.hoehe = hoehe;
	}

	public byte getNorth() {
		return this.north;
	}

	public void setNorth(byte north) {
		this.north = north;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public List<AdressePhoto> getAdressePhotos() {
		return this.adressePhotos;
	}

	public void setAdressePhotos(List<AdressePhoto> adressePhotos) {
		this.adressePhotos = adressePhotos;
	}

	public AdressePhoto addAdressePhoto(AdressePhoto adressePhoto) {
		getAdressePhotos().add(adressePhoto);
		adressePhoto.setPhoto(this);

		return adressePhoto;
	}

	public AdressePhoto removeAdressePhoto(AdressePhoto adressePhoto) {
		getAdressePhotos().remove(adressePhoto);
		adressePhoto.setPhoto(null);

		return adressePhoto;
	}

}