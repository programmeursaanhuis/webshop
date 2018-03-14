package model;

public class Klant {
    private int id;
    private String naam;
    private String afbeelding;
    //TODO Replace this with an Adres object
    private int woonadres;

    public Klant(int id, String naam, String afbeelding, int woonadres) {
        this.id = id;
        this.naam = naam;
        this.afbeelding = afbeelding;
        this.woonadres = woonadres;
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

    public int getWoonadres() {
        return woonadres;
    }

    public void setWoonadres(int woonadres) {
        this.woonadres = woonadres;
    }
}
