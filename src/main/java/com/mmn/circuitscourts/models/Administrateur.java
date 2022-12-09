package com.mmn.circuitscourts.models;

public class Administrateur extends Personne {


    public Administrateur(String nom, String adresse, String numTel) {
        super(nom, adresse, numTel);
    }
    public Administrateur(int id, String nom, String adresse, String numTel) {
        super(id, nom, adresse, numTel);
    }

    @Override
    public String toString() {
        return "Administrateur{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", adresse='" + getAdresse() + '\'' +
                '}';
    }
}
