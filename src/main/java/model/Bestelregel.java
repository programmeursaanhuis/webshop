package model;

public class Bestelregel {
    private int id;
    private int aantal;
    private float prijs;
    private int product; //TODO Replace this with an Product object
    private int bestelling; //TODO Replace this with an Bestelling object

    public Bestelregel(int id, int aantal, float prijs, int product, int bestelling) {
        this.id = id;
        this.aantal = aantal;
        this.prijs = prijs;
        this.product = product;
        this.bestelling = bestelling;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public float getPrijs() {
        return prijs;
    }

    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getBestelling() {
        return bestelling;
    }

    public void setBestelling(int bestelling) {
        this.bestelling = bestelling;
    }
}
