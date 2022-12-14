package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.services.TourneeDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Tournee {
    private int id;
    private String horaireDebut;
    private String horaireFin;

    private int numSiret;

    private int numImmat;

    public Tournee(int id, String horaireDebut, String horaireFin, int numSiret, int numImmat) {
        this.id = id;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.numSiret = numSiret;
        this.numImmat = numImmat;
    }

    public Tournee(String horaireDebut, String horaireFin, int numSiret, int numImmat) {
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.numSiret = numSiret;
        this.numImmat = numImmat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoraireDebut() {
        return horaireDebut;
    }

    public void setHoraireDebut(String horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public String getHoraireFin() {
        return horaireFin;
    }

    public void setHoraireFin(String horaireFin) {
        this.horaireFin = horaireFin;
    }

    public int getNumSiret() {
        return numSiret;
    }

    public void setNumSiret(int numSiret) {
        this.numSiret = numSiret;
    }

    public int getNumImmat() {
        return numImmat;
    }

    public void setNumImmat(int numImmat) {
        this.numImmat = numImmat;
    }

    public ArrayList<Tournee> getCommandesInitialize() throws SQLException {
        TourneeDAO t = new TourneeDAO();
        ArrayList<Tournee> tournees = t.getAll();
        return tournees;
    }

    public static ArrayList<Tournee> getCommandesInitializeByProducteur() throws SQLException {
        TourneeDAO t = new TourneeDAO();
        ArrayList<Tournee> tournees = t.getAllByProducteur(App.userConnected.getId());
        System.out.println(tournees);
        return tournees;
    }
}
