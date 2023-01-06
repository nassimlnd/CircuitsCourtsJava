package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.services.CommandeDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe Commande sert à créer l'objet Commande.
 */
public class Commande {
    /**
     * tous les attributs qui constituent la commande.
     */
    private int numCommande;
    private int articleId;
    private double poids;
    private int quantity;
    private String horaireDebut;
    private String horaireFin;
    private int idClient;
    private int idTournee;
    private long numSiret;
    private LocalDate dateCommande;
    /**
     * instanciation statique de CommandeDAO pour avoir acces au méthodes de la classe CommandeDAO.
     */
    public static CommandeDAO cmd = new CommandeDAO();

    
    /**
     * Constructeur avec le numCommande.
     * @param numCommande
     * @param articleId
     * @param poids
     * @param horaireDebut
     * @param horaireFin
     * @param idClient
     * @param numSiret
     * @throws SQLException
     */
    public Commande(int numCommande, int articleId, double poids, int quantity, String horaireDebut, String horaireFin, int idClient, long numSiret, LocalDate dateCommande) throws SQLException {
        this.numCommande = numCommande;
        this.articleId = articleId;
        this.poids = poids;
        this.quantity = quantity;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.idClient = idClient;
        this.numSiret = numSiret;
        this.dateCommande = dateCommande;
    }

    public Commande(int numCommande, int articleId, double poids, int quantity, String horaireDebut, String horaireFin, int idClient, int idTournee, Long numSiret, LocalDate dateCommande) throws SQLException {
        this.numCommande = numCommande;
        this.articleId = articleId;
        this.poids = poids;
        this.quantity = quantity;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.idClient = idClient;
        this.idTournee = idTournee;
        this.numSiret = numSiret;
        this.dateCommande = dateCommande;
    }


    /**
     * constructeur sans numCommande.
     * @param articleId
     * @param poids
     * @param horaireDebut
     * @param horaireFin
     * @param idClient
     * @param numSiret
     * @throws SQLException
     */
    public Commande(int articleId, double poids, int quantity, String horaireDebut, String horaireFin, int idClient, long numSiret, LocalDate datePicker) throws SQLException {
        this.articleId = articleId;
        this.poids = poids;
        this.quantity = quantity;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.idClient = idClient;
        this.numSiret = numSiret;
        this.dateCommande = datePicker;

        CommandeDAO commandeDAO = new CommandeDAO();
        this.numCommande = commandeDAO.add(this);
    }

    public int getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(int numCommande) {
        this.numCommande = numCommande;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
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

    public void setIdTournee(Integer idTournee) {
        this.idTournee = idTournee;
        try {
            cmd.update(this.numCommande, this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long getNumSiret() {
        return numSiret;
    }

    public void setNumSiret(int numSiret) {
        this.numSiret = numSiret;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Récupère toutes les commandes grâce à la fonction getAll de commandes DAO
     * @return arrayList de commandes, retourne toutes les commandes dans la base de données
     * @throws SQLException
     */
    public static ArrayList<Commande> getCommandesInitialize() throws SQLException {
        return cmd.getAll();
    }

    /**
     * Récupère les commandes de l'entreprise depuis la base de données
     * @return arrayList de commandes, retourne toutes les commandes dans la base de données d'un certain producteur
     * @throws SQLException
     */
    public static ArrayList<Commande> getCommandesInitializeByAccountId() throws SQLException {
        int idUser = App.userConnected.getId();
        ArrayList<Commande> commandes = cmd.getAllByEntreprise(idUser);
        System.out.println("[DATABASE]CommandesByAccountID done.");
        return commandes;
    }

    public static Commande getCommandeById(int numCommande) throws SQLException {
        Commande temp = cmd.getById(numCommande);
        return temp;
    }

    @Override
    public String toString() {
        return "Commande{" + "numCommande=" + numCommande + ", articleId='" + articleId + '\'' + ", poids=" + poids + ", horaireDebut='" + horaireDebut + '\'' + ", horaireFin='" + horaireFin + '\'' + ", idClient=" + idClient + ", idTournee=" + idTournee + ", numSiret=" + numSiret + '}';
    }

    /**
     * fonction qui via CommandeDao apelle la méthode add().
     * @param commande
     * @throws SQLException
     */
    public static void addCommandeToDb(Commande commande) throws SQLException {
        cmd.add(commande);
    }
}
