package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.services.AccountDAO;
import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.VehiculeDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Vehicule {
    private String numImmate;
    private int poidsMax;

    private int numSiret;

    public static VehiculeDAO vehiculeDAO;

    static {
        try {
            vehiculeDAO = new VehiculeDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vehicule(String numImmate, int poidsmax) throws SQLException {
        this.numImmate = numImmate;
        this.poidsMax = poidsmax;
    }
    public Vehicule(String numImmate, int poidsmax, int numSiret) throws SQLException {
        this.numImmate = numImmate;
        this.poidsMax = poidsmax;
        this.numSiret = numSiret;

        if (!vehiculeDAO.exists(this)) {
            vehiculeDAO.add(this);
        }
    }

    public static ArrayList<Vehicule> getCommandesInitialize() throws SQLException {
        ArrayList<Vehicule> vehicules = vehiculeDAO.getAll();
        return vehicules;
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



    public static ArrayList<Vehicule>getVehiculesInitilize() throws SQLException {
        VehiculeDAO v = new VehiculeDAO();
        ArrayList<Vehicule> vehicules = v.getAll();
        return vehicules;
    }

    public ArrayList<Vehicule> getVehiculesInitilizeByProducteur() throws SQLException {
        VehiculeDAO v = new VehiculeDAO();
        int idUser = App.userConnected.getId();
        ArrayList<Vehicule> vehicules = v.getAllByProducteur(idUser);
        return vehicules;
    }


    public static int getNumSiretConnected() throws SQLException {
       int numSiretConnected = vehiculeDAO.getNumSiretConnected(App.userConnected.getId());
       return numSiretConnected;
    }

    public static ArrayList<Vehicule>getVehiculesInitilizeByid() throws SQLException {
        VehiculeDAO v = new VehiculeDAO();
        ArrayList<Vehicule> vehicules = v.getAllByProducteur(App.userConnected.getId());
        return vehicules;
    }
}
