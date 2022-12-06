package com.mmn.circuitscourts.models;

public class Personne {
    private int id;
    private String nom;
    private String adresse;
    private String numTel;

    public Personne(String nom, String adresse, String numTel) {
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
}
