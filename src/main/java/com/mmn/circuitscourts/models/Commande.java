package com.mmn.circuitscourts.models;

public class Commande {
    private int numCommande;
    private String libelle;
    private float poids;
    private String horaireDebut;
    private String horaireFin;
    private int idClient;
    private int idTournee;
    private int numSiret;
    public Commande(int numCommande, String libelle, float poids, String horaireDebut, String horaireFin, int idClient, int idTournee, int numSiret) {
        this.numCommande = numCommande;
        this.libelle = libelle;
        this.poids = poids;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.idClient = idClient;
        this.idTournee = idTournee;
        this.numSiret = numSiret;
    }

    public Commande(String libelle, float poids, String horaireDebut, String horaireFin, int idClient, int idTournee, int numSiret) {
        this.libelle = libelle;
        this.poids = poids;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.idClient = idClient;
        this.idTournee = idTournee;
        this.numSiret = numSiret;
    }
    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
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

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdTournee() {
        return idTournee;
    }

    public void setIdTournee(int idTournee) {
        this.idTournee = idTournee;
    }

    public int getNumSiret() {
        return numSiret;
    }

    public void setNumSiret(int numSiret) {
        this.numSiret = numSiret;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "numCommande=" + numCommande +
                ", libelle='" + libelle + '\'' +
                ", poids=" + poids +
                ", horaireDebut='" + horaireDebut + '\'' +
                ", horaireFin='" + horaireFin + '\'' +
                ", idClient=" + idClient +
                ", idTournee=" + idTournee +
                ", numSiret=" + numSiret +
                '}';
    }
}
