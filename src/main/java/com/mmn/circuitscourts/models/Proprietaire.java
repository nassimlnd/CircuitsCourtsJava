package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.ProprietaireDAO;

import java.sql.SQLException;

public class Proprietaire extends Personne {

    private int id;

    public static ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
    public Proprietaire(String nom, String adresse, String numTel) throws SQLException {
        super(nom, adresse, numTel);
        this.id = proprietaireDAO.add(this);
    }

    public Proprietaire(int id, String nom, String adresse, String numTel) {
        super(nom, adresse, numTel);
        this.id = id;
    }



    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
