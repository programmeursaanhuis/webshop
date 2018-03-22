package nl.hu.v2iac.webshopapp.model;

import java.time.LocalDate;

public class Aanbieding {
    private int id;
    private LocalDate vanDatum;
    private LocalDate totDatum;
    //TODO Replace this with an Product object
    private int product;
    private float prijs;
    private String reclametekst;

    public Aanbieding(int id, LocalDate vanDatum, LocalDate totDatum, int product, float prijs, String reclametekst) {
        this.id = id;
        this.vanDatum = vanDatum;
        this.totDatum = totDatum;
        this.product = product;
        this.prijs = prijs;
        this.reclametekst = reclametekst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getVanDatum() {
        return vanDatum;
    }

    public void setVanDatum(LocalDate vanDatum) {
        this.vanDatum = vanDatum;
    }

    public LocalDate getTotDatum() {
        return totDatum;
    }

    public void setTotDatum(LocalDate totDatum) {
        this.totDatum = totDatum;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public float getPrijs() {
        return prijs;
    }

    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    public String getReclametekst() {
        return reclametekst;
    }

    public void setReclametekst(String reclametekst) {
        this.reclametekst = reclametekst;
    }
}
