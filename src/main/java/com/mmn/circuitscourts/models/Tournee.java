package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.TourneeDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Tournee {
    private int id;
    private String horaireDebut;
    private String horaireFin;


    public Tournee(){

    }
    public Tournee(int id, String horaireDebut, String horaireFin) {
        this.id = id;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
    }

    public Tournee(String horaireDebut, String horaireFin) {
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
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

    public ArrayList<Tournee> getCommandesInitialize() throws SQLException {
        TourneeDAO t = new TourneeDAO();
        ArrayList<Tournee> tournees = t.getAll();
        System.out.println(tournees);
        return tournees;
    }
}
