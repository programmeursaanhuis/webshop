package model;

import java.time.LocalDate;

public class Account {
    private int id;
    private LocalDate openDatum;
    private boolean isActief;
    //TODO Replace this with an Adres object
    private int factuurAdres;
    //TODO Replace this with an Klant object
    private int klant;

    public Account(int id, LocalDate openDatum, boolean isActief, int factuurAdres, int klant) {
        this.id = id;
        this.openDatum = openDatum;
        this.isActief = isActief;
        this.factuurAdres = factuurAdres;
        this.klant = klant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOpenDatum() {
        return openDatum;
    }

    public void setOpenDatum(LocalDate openDatum) {
        this.openDatum = openDatum;
    }

    public boolean getActief() {
        return isActief;
    }

    public void setActief(boolean isActief) {
        this.isActief = isActief;
    }

    public int getFactuurAdres() {
        return factuurAdres;
    }

    public void setFactuurAdres(int factuurAdres) {
        this.factuurAdres = factuurAdres;
    }

    public int getKlant() {
        return klant;
    }

    public void setKlant(int klant) {
        this.klant = klant;
    }
}
