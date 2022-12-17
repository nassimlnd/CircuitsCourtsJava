package com.mmn.circuitscourts.models;

public class Proprietaire extends Personne {

    private int id;

    public Proprietaire(String nom, String adresse, String numTel) {
        super(nom, adresse, numTel);
    }

    public Proprietaire(int id, String nom, String adresse, String numTel) {
        super(nom, adresse, numTel);
        this.id = id;
    }


}
