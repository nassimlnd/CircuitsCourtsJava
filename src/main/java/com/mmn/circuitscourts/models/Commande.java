package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.CommandeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Commande {
    private int numCommande;
    private String libelle;
    private float poids;
    private String horaireDebut;
    private String horaireFin;
    private int idClient;
    private int idTournee;
    private int numSiret;
    private Date dateCommande;

    public Commande() throws SQLException {

    }

    public Commande(int numCommande, String libelle, float poids, String horaireDebut, String horaireFin, int idClient, int idTournee, int numSiret) throws SQLException {
        this.numCommande = numCommande;
        this.libelle = libelle;
        this.poids = poids;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.idClient = idClient;
        this.idTournee = idTournee;
        this.numSiret = numSiret;
        this.dateCommande = Calendar.getInstance().getTime();
    }

    public Commande(String libelle, float poids, String horaireDebut, String horaireFin, int idClient, int idTournee, int numSiret) throws SQLException {
        this.libelle = libelle;
        this.poids = poids;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.idClient = idClient;
        this.idTournee = idTournee;
        this.numSiret = numSiret;
        this.dateCommande = Calendar.getInstance().getTime();
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

    public Date getDateCommande() {
        return dateCommande;
    }


    public CommandeDAO cmd = new CommandeDAO();
    /**
     *recupères commandes grâce a la vonction getAll de commandes DAO
     * @return arrayList de commandes, retourne toutes les commades dans la base de données
     * @throws SQLException
     */
    public ArrayList<Commande> getCommandesInitialize() throws SQLException {
        ArrayList<Commande> commandes = new ArrayList<Commande>();
        commandes = cmd.getAll();
        return commandes;
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
