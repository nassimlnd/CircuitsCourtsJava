package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.EntrepriseDAO;

import java.sql.SQLException;

public class Entreprise {
    private long numSiret;
    private Proprietaire proprietaire;
    private String adresse;
    private String numTel;
    private String coordonneesGps;
    private int accountId;
    public static EntrepriseDAO entrepriseDAO = new EntrepriseDAO();

    public Entreprise(long numSiret, String adresse, Proprietaire proprietaire, String numTel, String coordonneesGps, int accountId) {
        this.numSiret = numSiret;
        this.proprietaire = proprietaire;
        this.adresse = adresse;
        this.numTel = numTel;
        this.coordonneesGps = coordonneesGps;
        this.accountId = accountId;
    }

    public Entreprise(String adresse, Proprietaire proprietaire, String numTel, String coordonneesGps, int accountId) {
        this.proprietaire = proprietaire;
        this.adresse = adresse;
        this.numTel = numTel;
        this.coordonneesGps = coordonneesGps;
        this.accountId = accountId;
    }

    public Entreprise(String adresse, Proprietaire proprietaire, String numTel, String coordonneesGps) throws SQLException {
        this.proprietaire = proprietaire;
        this.adresse = adresse;
        this.numTel = numTel;
        this.coordonneesGps = coordonneesGps;
        entrepriseDAO.add(this);
    }

    public long getNumSiret() {
        return numSiret;
    }

    public void setNumSiret(long numSiret) {
        this.numSiret = numSiret;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getCoordonneesGps() {
        return coordonneesGps;
    }

    public void setCoordonneesGps(String coordonneesGps) {
        this.coordonneesGps = coordonneesGps;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }
}

