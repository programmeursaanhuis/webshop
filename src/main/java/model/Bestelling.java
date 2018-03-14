package model;

public class Bestelling {
    private int id;
    //TODO Replace this with an Adres object
    private int afleverAdres;
    //TODO Replace this with an Bestelregel object
    private int bestelling_regel;

    public Bestelling(int id, int afleverAdres, int bestelling_regel) {
        this.id = id;
        this.afleverAdres = afleverAdres;
        this.bestelling_regel = bestelling_regel;
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

    public int getBestelling_regel() {
        return bestelling_regel;
    }

    public void setBestelling_regel(int bestelling_regel) {
        this.bestelling_regel = bestelling_regel;
    }
}
