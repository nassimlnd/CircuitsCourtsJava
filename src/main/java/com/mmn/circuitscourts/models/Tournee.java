package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.services.TourneeDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe Tournée sert à créer l'objet Tournée.
 */
public class Tournee {
    private int id;
    private LocalDate date;
    private String horaireDebut;
    private String horaireFin;
    private int numSiret;

    private String numImmat;

    public static TourneeDAO tourneeDAO = new TourneeDAO();

    /**
     * constructeur avec l'id de la tournée.
     * @param id
     * @param horaireDebut
     * @param horaireFin
     * @param numSiret
     * @param numImmat
     */
    public Tournee(int id, LocalDate date, String horaireDebut, String horaireFin, int numSiret, String numImmat) {
        this.id = id;
        this.date = date;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.numSiret = numSiret;
        this.numImmat = numImmat;
    }

    /**
     * constructeur sans l'id de la tournée.
     * @param horaireDebut
     * @param horaireFin
     * @param numSiret
     * @param numImmat
     */
    public Tournee(LocalDate date, String horaireDebut, String horaireFin, int numSiret, String numImmat) {
        this.date = date;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.numSiret = numSiret;
        this.numImmat = numImmat;

        try {
            this.id = tourneeDAO.add(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getNumImmat() {
        return numImmat;
    }

    public void setNumImmat(String numImmat) {
        this.numImmat = numImmat;
    }

    /**
     * méthode qui apelle  via TourneeDAO la méthode getAll()
     * @return l'ArrayListe de toutes les tournées
     * @throws SQLException
     */
    public  static ArrayList<Tournee> getCommandesInitialize() throws SQLException {
        TourneeDAO t = new TourneeDAO();
        ArrayList<Tournee> tournees = t.getAll();
        return tournees;
    }

    /**
     * méthode qui apelle  via TourneeDAO la méthode getAllByProducteur().
     * @return l'ArrayList des tournées appartenant au producteur connecté à l'application.
     * @throws SQLException
     */
    public static ArrayList<Tournee> getCommandesInitializeByProducteur() throws SQLException {
        TourneeDAO t = new TourneeDAO();
        ArrayList<Tournee> tournees = t.getAllByProducteur(App.userConnected.getId());
        return tournees;
    }
}
