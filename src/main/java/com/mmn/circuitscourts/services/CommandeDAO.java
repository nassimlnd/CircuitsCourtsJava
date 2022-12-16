package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Commande;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author  martin
 * @author nassim
 */
public class CommandeDAO implements DAO<Commande, Integer> {
    /**
     * @param con, connection avec la BD;
     */
    private Connection con;

    /**
     * constructeur qui permet d'établir une connection avec la base de donnée. la méthode getInstance est utilisée pour assurer l'uncité de la connection.
     */
    public CommandeDAO() {
        this.con = ConnectionMySQL.getInstance();
    }

    /**
     * implémentation de la méthode getAll de l'interface DAO.
     * @return une liste contenant toutes les commandes présents dans la base de données
     * @throws SQLException
     */
    @Override
    public ArrayList<Commande> getAll() throws SQLException {
        String query = "SELECT * FROM commande";
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Commande> result = new ArrayList<>();
        int numCommande = -1;
        String libelle = "";
        float poids = -1;
        String horaireDebut = "";
        String horaireFin = "";
        int idClient = -1;
        int idTournee = -1;
        int numSiret = -1;
        while (resultSet.next()) {
            numCommande = resultSet.getInt(1);
            libelle = resultSet.getString(2);
            poids = resultSet.getFloat(3);
            horaireDebut = resultSet.getString(4);
            horaireFin = resultSet.getString(5);
            idClient = resultSet.getInt(6);
            idTournee = resultSet.getInt(7);
            numSiret = resultSet.getInt(8);
            result.add(new Commande(numCommande, libelle, poids, horaireDebut, horaireFin, idClient, idTournee, numSiret));
        }
        return result;
    }

    /**
     * implémentation de la méthode getById de l'interface DAO.
     * @return la Commande correspondante au numéro voulu.
     * @param numCommande est le numéro de commande.
     * @throws SQLException
     */
    @Override
    public Commande getById(Integer numCommande) throws SQLException {
        String query = "SELECT * FROM commande WHERE numCommande=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, numCommande);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next()) {
            int numCommandeDB = -1;
            String libelle = "";
            float poids = -1;
            String horaireDebut = "";
            String horaireFin = "";
            int idClient = -1;
            int idTournee = -1;
            int numSiret = -1;

            numCommande = resultSet.getInt(1);
            libelle = resultSet.getString(2);
            poids = resultSet.getFloat(3);
            horaireDebut = resultSet.getString(4);
            horaireFin = resultSet.getString(5);
            idClient = resultSet.getInt(6);
            idTournee = resultSet.getInt(7);
            numSiret = resultSet.getInt(8);
            return new Commande(numCommande, libelle, poids, horaireDebut, horaireFin, idClient, idTournee, numSiret);
        }
        //TODO : créer exception pour spécifier si on ne trouve pas d'admin avec cet id (exception 1)
        throw new SQLException();
    }

    /**
     * Implémentation de la méthode add de l'interface DAO.
     * Prend la commande avec tous ses paramètres que l'on veut ajouter à la base de données.
     * @param o, la commande.
     * @return un boolean qui nous indique si l'executeUpdate s'est bien effectuée.
     * @throws SQLException
     */
    @Override
    public boolean add(Commande o) throws SQLException {
        String query2 = "INSERT INTO Commande(libelle, poids, horaireDebut, horaireFin, idClient, numSiret) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query2);
        pst.setString(1, ((Commande) o).getLibelle());
        pst.setFloat(2, ((Commande) o).getPoids());
        pst.setString(3, ((Commande) o).getHoraireDebut());
        pst.setString(4, ((Commande) o).getHoraireFin());
        pst.setInt(5, ((Commande) o).getIdClient());
       // pst.setInt(6, ((Commande) o).getIdTournee());
        pst.setInt(6, ((Commande) o).getNumSiret());
        pst.executeUpdate();
        return Boolean.valueOf(String.valueOf(pst.executeUpdate()));
    }

    /**
     * implémentation de la méthode update de l'interface DAO.
     * Modifie dans la base de donnée une commande en particulier.
     * @param numCommande permet de retrouver la à modifier
     * @param o est la commande contenant les nouveaux attributs que l'on veut modifier dans la base de données.
     * @return boolean qui nous indique si l'executeUpdate s'est bien effectuée.
     * @throws SQLException
     */
    @Override
    public boolean update(Integer numCommande, Commande o) throws SQLException {
        String libelle = ((Commande) o).getLibelle();
        float poids = ((Commande) o).getPoids();
        String horaireDebut = ((Commande) o).getHoraireDebut();
        String horaireFin = ((Commande) o).getHoraireFin();
        int idClient = ((Commande) o).getIdClient();
        int idTournee = ((Commande) o).getIdTournee();
        int numSiret = ((Commande) o).getNumSiret();
        String query2 = "UPDATE Commande SET libelle=?, poids=?, horaireDebut=?, horaireFin=?, idClient=?, idTournee=?, numSiret=? WHERE numCommande =" + numCommande;
        PreparedStatement pst = con.prepareStatement(query2);
        pst.setString(1, ((Commande) o).getLibelle());
        pst.setFloat(2, ((Commande) o).getPoids());
        pst.setString(3, ((Commande) o).getHoraireDebut());
        pst.setString(4, ((Commande) o).getHoraireFin());
        pst.setInt(5, ((Commande) o).getIdClient());
        pst.setInt(6, ((Commande) o).getIdTournee());
        pst.setInt(7, ((Commande) o).getNumSiret());
        pst.executeUpdate();
        return Boolean.valueOf(String.valueOf(pst.executeUpdate()));
    }

    /**
     * Permer de suprimmer de la base de données  une commande en particulier.
     * @param numCommande permet de retrouver la commande à supprimer
     * @return boolean qui nous indique si l'executeUpdate s'est bien effectuée.
     * @throws SQLException
     */
    @Override
    public boolean remove(Integer numCommande) throws SQLException {
        String query = "DELETE  FROM Commande WHERE numCommande=" + numCommande;
        Statement st = con.createStatement();
        st.executeUpdate(query);
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }

    public int countById() throws Exception {
        String query = "SELECT COUNT(*) FROM commande INNER JOIN producteur ON commande.numSiret = producteur.numSiret INNER JOIN accounts ON producteur.accountId = accounts.accountId WHERE accounts.accountId = " + App.userConnected.getId();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else return 0;
    }


    /**
     * Permet de recuperer toutes les commandes appartenant à un prducteur.
     * @param accountId est l'id du compte du producteur.
     * @return ArrayLis contenant toutes les commandes appartenant au producteur voulu.
     * @throws SQLException
     */
    public ArrayList<Commande> getAllByProducteur(int accountId) throws SQLException {
        String query = "SELECT * FROM  commande INNER JOIN Producteur ON Commande.numSiret=Producteur.numSiret WHERE Producteur.accountId=" + accountId;
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Commande> result = new ArrayList<>();
        int numCommande = -1;
        String libelle = "";
        float poids = -1;
        String horaireDebut = "";
        String horaireFin = "";
        int idClient = -1;
        int idTournee = -1;
        int numSiret = -1;
        while (resultSet.next()) {
            numCommande = resultSet.getInt(1);
            libelle = resultSet.getString(2);
            poids = resultSet.getFloat(3);
            horaireDebut = resultSet.getString(4);
            horaireFin = resultSet.getString(5);
            idClient = resultSet.getInt(6);
            idTournee = resultSet.getInt(7);
            numSiret = resultSet.getInt(8);
            result.add(new Commande(numCommande, libelle, poids, horaireDebut, horaireFin, idClient, idTournee, numSiret));
        }
        return result;
    }
}
