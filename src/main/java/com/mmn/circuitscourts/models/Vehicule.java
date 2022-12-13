package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.VehiculeDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Vehicule {
    private String numImmate;
    private int poidsMax;

    private int numSiret;

    public Vehicule() {

    }

    public Vehicule(String numImmate, int poidsmax, int numSiret) {
        this.numImmate = numImmate;
        this.poidsMax = poidsmax;
        this.numSiret = numSiret;
    }

    public String getNumImmate() {
        return numImmate;
    }

    public void setNumImmate(String numImmate) {
        this.numImmate = numImmate;
    }

    public int getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(int poidsMax) {
        this.poidsMax = poidsMax;
    }

    public int getnumSiret() {
        return numSiret;
    }

    public ArrayList<Vehicule> getVehiculesInitilaze() throws SQLException {
        VehiculeDAO v = new VehiculeDAO();
        ArrayList<Vehicule> vehicules = v.getAll();
        return vehicules;
    }
}
