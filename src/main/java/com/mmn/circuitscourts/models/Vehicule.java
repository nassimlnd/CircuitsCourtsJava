package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.services.AccountDAO;
import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.VehiculeDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Vehicule {
    private String numImmat;
    private float poidsMax;

    private int numSiret;

    public static VehiculeDAO vehiculeDAO;

    static {
        try {
            vehiculeDAO = new VehiculeDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vehicule(String numImmat, float poidsmax, int numSiret) throws SQLException {
        this.numImmat = numImmat;
        this.poidsMax = poidsmax;
        this.numSiret = numSiret;

        if (!vehiculeDAO.exists(this)) {
            vehiculeDAO.add(this);
        }
    }

    public String getNumImmat() {
        return numImmat;
    }

    public void setNumImmat(String numImmat) {
        this.numImmat = numImmat;
    }

    public float getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(float poidsMax) {
        this.poidsMax = poidsMax;
    }

    public int getnumSiret() {
        return numSiret;
    }

    public static ArrayList<Vehicule>getVehiculesInitialize() throws SQLException {
        VehiculeDAO v = new VehiculeDAO();
        ArrayList<Vehicule> vehicules = v.getAll();
        return vehicules;
    }
}
