package model;

public class Product {
	private int id;
	private String naam;
	private String afbeelding;
	private float prijs;
	private String omschrijving;
	private int categorie;

	public Product(int id, String naam, String afbeelding, float prijs, String omschrijving, int categorie) {
		this.id = id;
		this.naam = naam;
		this.afbeelding = afbeelding;
		this.prijs = prijs;
		this.omschrijving = omschrijving;
		this.categorie = categorie;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}

	public float getPrijs() {
		return prijs;
	}

	public void setPrijs(float prijs) {
		this.prijs = prijs;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}
}
