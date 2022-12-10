package com.mmn.circuitscourts.models;

public class Producteur {
    private int numSiret;

    private Proprietaire proprietaire;
    private String adresse;
    private String numTel;
    private String coordonneesGps;

    public Producteur(int numSiret, String adresse, Proprietaire proprietaire, String numTel, String coordonneesGps) {
        this.numSiret = numSiret;
        this.proprietaire = proprietaire;
        this.adresse = adresse;
        this.numTel = numTel;
        this.coordonneesGps = coordonneesGps;
    }



    public int getNumSiret() {
        return numSiret;
    }

    public void setNumSiret(int numSiret) {
        this.numSiret = numSiret;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getCoordonneesGps() {
        return coordonneesGps;
    }

    public void setCoordonneesGps(String coordonneesGps) {
        this.coordonneesGps = coordonneesGps;
    }
}
