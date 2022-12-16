package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.controller.producteur.ProducteurController;
import com.mmn.circuitscourts.services.AccountDAO;
import com.mmn.circuitscourts.services.CommandeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe Commande sert à créer l'objet Commande.
 */
public class Commande {
    /**
     * tous les attributs qui constituent la commande.
     */
    private int numCommande;
    private String libelle;
    private float poids;
    private String horaireDebut;
    private String horaireFin;
    private int idClient;
    private int idTournee;
    private int numSiret;
    private Date dateCommande;
    /**
     * instanciation statique de CommandeDAO pour avoir acces au méthodes de la classe CommandeDAO.
     */
    public static CommandeDAO cmd = new CommandeDAO();

    /**
     * Constructeur avec le numCommande.
     * @param numCommande
     * @param libelle
     * @param poids
     * @param horaireDebut
     * @param horaireFin
     * @param idClient
     * @param idTournee
     * @param numSiret
     * @throws SQLException
     */
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

    /**
     * constructeur sans numCommande.
     * @param libelle
     * @param poids
     * @param horaireDebut
     * @param horaireFin
     * @param idClient
     * @param numSiret
     * @throws SQLException
     */
    public Commande(String libelle, float poids, String horaireDebut, String horaireFin, int idClient, int numSiret) throws SQLException {
        this.libelle = libelle;
        this.poids = poids;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.idClient = idClient;
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


    /**
     * recupères commandes grâce a la fonction getAll de commandes DAO
     * @return arrayList de commandes, retourne toutes les commades dans la base de données
     * @throws SQLException
     */
    public static ArrayList<Commande> getCommandesInitialize() throws SQLException {
        return cmd.getAll();
    }

    /**
     * Récupère les commandes du producteur depuis la base de données
     * @return arrayList de commandes, retourne toutes les commandes dans la base de données d'un certain producteur
     * @throws SQLException
     */
    public static ArrayList<Commande> getCommandesInitializeByAccountId() throws SQLException {
        int idUser = App.userConnected.getId();
        ArrayList<Commande> commandes = cmd.getAllByProducteur(idUser);
        System.out.println("[DATABASE]CommandesByAccountID done.");
        return commandes;
    }

    @Override
    public String toString() {
        return "Commande{" + "numCommande=" + numCommande + ", libelle='" + libelle + '\'' + ", poids=" + poids + ", horaireDebut='" + horaireDebut + '\'' + ", horaireFin='" + horaireFin + '\'' + ", idClient=" + idClient + ", idTournee=" + idTournee + ", numSiret=" + numSiret + '}';
    }

    /**
     * fonction qui via CommandeDao apelle la méthode add().
     * @param commande
     * @throws SQLException
     */
    public static void  addCommandeToDb(Commande commande) throws SQLException {
        cmd.add(commande);
    }
}
