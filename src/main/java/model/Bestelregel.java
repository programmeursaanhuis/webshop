package model;

public class Bestelregel {
    private int id;
    private int aantal;
    private float prijs;
    //TODO Replace this with an Product object
    private int product;

    public Bestelregel(int id, int aantal, float prijs, int product) {
        this.id = id;
        this.aantal = aantal;
        this.prijs = prijs;
        this.product = product;
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
}
