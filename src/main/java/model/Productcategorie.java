package model;

public class Productcategorie {
    private int id;
    //TODO Replace this with an Categorie object
    private int categorie;
    //TODO Replace this with an Product object
    private int product;

    public Productcategorie(int id, int categorie, int product) {
        this.id = id;
        this.categorie = categorie;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
