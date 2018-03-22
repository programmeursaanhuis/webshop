package nl.hu.v2iac.webshopapp.model;

public class Bestelling {
    private int id;
    //TODO Replace this with an Adres object
    private int afleverAdres;

    public Bestelling(int id, int afleverAdres) {
        this.id = id;
        this.afleverAdres = afleverAdres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAfleverAdres() {
        return afleverAdres;
    }

    public void setAfleverAdres(int afleverAdres) {
        this.afleverAdres = afleverAdres;
    }
}
