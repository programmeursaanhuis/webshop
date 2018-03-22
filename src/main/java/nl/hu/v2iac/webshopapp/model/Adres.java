package nl.hu.v2iac.webshopapp.model;

public class Adres {
    private int id;
    private String straat;
    private int straatnummer;

    public Adres(int id, String straat, int straatnummer) {
        this.id = id;
        this.straat = straat;
        this.straatnummer = straatnummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getStraatnummer() {
        return straatnummer;
    }

    public void setStraatnummer(int straatnummer) {
        this.straatnummer = straatnummer;
    }
}
