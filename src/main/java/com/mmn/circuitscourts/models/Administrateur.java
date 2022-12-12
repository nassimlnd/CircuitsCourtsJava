package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.ConnectionMySQL;

import java.sql.SQLException;
import java.util.ArrayList;

public class Administrateur extends Personne {



    public Administrateur(String nom, String adresse, String numTel) throws SQLException {
        super(nom, adresse, numTel);
    }
    public Administrateur(int id, String nom, String adresse, String numTel) throws SQLException {
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
