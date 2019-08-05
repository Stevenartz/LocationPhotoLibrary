package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the adresse_photo database table.
 * 
 */
@Entity
@Table(name="adresse_photo")
@NamedQuery(name="AdressePhoto.findAll", query="SELECT a FROM AdressePhoto a")
public class AdressePhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte isthauptsitzphoto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date zugeordnetam;

	//bi-directional many-to-one association to Adresse
	@ManyToOne
	private Adresse adresse;

	//bi-directional many-to-one association to Photo
	@ManyToOne
	private Photo photo;

	public AdressePhoto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getIsthauptsitzphoto() {
		return this.isthauptsitzphoto;
	}

	public void setIsthauptsitzphoto(byte isthauptsitzphoto) {
		this.isthauptsitzphoto = isthauptsitzphoto;
	}

	public Date getZugeordnetam() {
		return this.zugeordnetam;
	}

	public void setZugeordnetam(Date zugeordnetam) {
		this.zugeordnetam = zugeordnetam;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}