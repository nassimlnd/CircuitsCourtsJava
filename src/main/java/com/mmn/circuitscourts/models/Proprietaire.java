package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.ProprietaireDAO;

public class Proprietaire extends Personne {

    private String nom;
    private int id;

    public static ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
    public Proprietaire(String nom, String adresse, String numTel) {
        super(nom, adresse, numTel);
    }

    public Proprietaire(int id, String nom, String adresse, String numTel) {
        super(nom, adresse, numTel);
        this.id = id;
    }

    public String getNom(){
        return this.nom;
    }

}
