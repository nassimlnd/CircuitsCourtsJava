package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Commande;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
        while (resultSet.next()) {
            int numCommande = resultSet.getInt(1);
            int articleId = resultSet.getInt(2);
            double poids = resultSet.getDouble(3);
            int quantity = resultSet.getInt(4);
            String horaireDebut = String.valueOf(resultSet.getTime(5));
            String horaireFin = String.valueOf(resultSet.getTime(6));
            int idClient = resultSet.getInt(7);
            int idTournee = resultSet.getInt(8);
            Long numSiret = resultSet.getLong(9);
            LocalDate dateCommande = resultSet.getDate(10).toLocalDate();
            Commande commande = null;
            if (idTournee > 0) {
                commande = new Commande(numCommande, articleId, poids, quantity, horaireDebut, horaireFin, idClient, idTournee, numSiret, dateCommande);
            } else {
                commande = new Commande(numCommande, articleId, poids, quantity, horaireDebut, horaireFin, idClient, numSiret, dateCommande);
            }

            result.add(commande);
        }
        return result;
    }

    /**
     * implémentation de la méthode getById de l'interface DAO.
     * @return la Commande correspondante au numéro voulu.
     * @param id est le numéro de commande.
     * @throws SQLException
     */
    @Override
    public Commande getById(Integer id) throws SQLException {
        String query = "SELECT * FROM commande WHERE numCommande=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next()) {
            int numCommande = resultSet.getInt(1);
            int articleId = resultSet.getInt(2);
            double poids = resultSet.getDouble(3);
            int quantity = resultSet.getInt(4);
            String horaireDebut = String.valueOf(resultSet.getTime(5));
            String horaireFin = String.valueOf(resultSet.getTime(6));
            int idClient = resultSet.getInt(7);
            int idTournee = resultSet.getInt(8);
            long numSiret = resultSet.getLong(9);
            LocalDate dateCommande = resultSet.getDate(10).toLocalDate();
            Commande commande = null;
            if (idTournee > 0) {
                commande = new Commande(numCommande, articleId, poids, quantity, horaireDebut, horaireFin, idClient, idTournee, numSiret, dateCommande);
            } else {
                commande = new Commande(numCommande, articleId, poids, quantity, horaireDebut, horaireFin, idClient, numSiret, dateCommande);
            }


            return commande;
        } else throw new SQLException("Id de la commande introuvable");
    }

    /**
     * Implémentation de la méthode add de l'interface DAO.
     * Prend la commande avec tous ses paramètres que l'on veut ajouter à la base de données.
     * @param commande, la commande.
     * @return un boolean qui nous indique si l'executeUpdate s'est bien effectuée.
     * @throws SQLException
     */
    @Override
    public Integer add(Commande commande) throws SQLException {
        String query = "INSERT INTO Commande(articleId, poids, quantity, horaireDebut, horaireFin, idClient, numSiret, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, commande.getArticleId());
        pst.setDouble(2, commande.getPoids());
        pst.setInt(3, commande.getQuantity());
        pst.setTime(4, Time.valueOf(commande.getHoraireDebut()));
        pst.setTime(5, Time.valueOf(commande.getHoraireFin()));
        pst.setInt(6, commande.getIdClient());
        pst.setLong(7, commande.getNumSiret());
        pst.setDate(8, Date.valueOf(commande.getDateCommande()));
        pst.executeUpdate();

        ResultSet resultSet = pst.getGeneratedKeys();
        int result = 0;
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        } else throw new SQLException("ERREUR ADD COMMANDE");

        String query2 = "INSERT INTO logs(accountId, categorie, objectId, date, time) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement1 = con.prepareStatement(query2);
        preparedStatement1.setInt(1, App.userConnected.getId());
        preparedStatement1.setString(2, "newcommande");
        preparedStatement1.setInt(3, result);
        preparedStatement1.setDate(4, Date.valueOf(LocalDate.now()));
        preparedStatement1.setTime(5, Time.valueOf(LocalTime.now()));
        preparedStatement1.executeUpdate();

        return result;
    }

    /**
     * implémentation de la méthode update de l'interface DAO.
     * Modifie dans la base de donnée une commande en particulier.
     * @param numCommande permet de retrouver la à modifier
     * @param commande est la commande contenant les nouveaux attributs que l'on veut modifier dans la base de données.
     * @return boolean qui nous indique si l'executeUpdate s'est bien effectuée.
     * @throws SQLException
     */
    @Override
    public boolean update(Integer numCommande, Commande commande) throws SQLException {
        String query = "UPDATE Commande SET articleId=?, poids=?, quantity=?, horaireDebut=?, horaireFin=?, idClient=?, idTournee=?, numSiret=?, date=? WHERE numCommande =?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, commande.getArticleId());
        pst.setDouble(2, commande.getPoids());
        pst.setInt(3, commande.getQuantity());
        pst.setTime(4, Time.valueOf(commande.getHoraireDebut()));
        pst.setTime(5, Time.valueOf(commande.getHoraireFin()));
        pst.setInt(6, commande.getIdClient());
        pst.setInt(7, commande.getIdTournee());
        pst.setLong(8, commande.getNumSiret());
        pst.setDate(9, Date.valueOf(commande.getDateCommande()));
        pst.setInt(10, commande.getNumCommande());
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
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }

    /**
     * Compte le nombre de commandes d'un compte entreprise.
     * @return le nombre de commande appartenant au compte.
     * @throws Exception
     */
    public int countById() throws Exception {
        String query = "SELECT COUNT(*) FROM commande INNER JOIN entreprise ON commande.numSiret = entreprise.numSiret INNER JOIN accounts ON entreprise.accountId = accounts.accountId WHERE accounts.accountId = " + App.userConnected.getId();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else return 0;
    }


    /**
     * Permet de recuperer toutes les commandes appartenant à un prducteur.
     * @param accountId est l'id du compte de l'entreprise.
     * @return ArrayLis contenant toutes les commandes appartenant au entreprise voulu.
     * @throws SQLException
     */
    public ArrayList<Commande> getAllByEntreprise(int accountId) throws SQLException {
        String query = "SELECT * FROM  commande INNER JOIN entreprise ON Commande.numSiret=entreprise.numSiret WHERE entreprise.accountId=" + accountId;
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Commande> result = new ArrayList<>();
        while (resultSet.next()) {
            int numCommande = resultSet.getInt(1);
            int articleId = resultSet.getInt(2);
            double poids = resultSet.getDouble(3);
            int quantity = resultSet.getInt(4);
            String horaireDebut = String.valueOf(resultSet.getTime(5));
            String horaireFin = String.valueOf(resultSet.getTime(6));
            int idClient = resultSet.getInt(7);
            int idTournee = resultSet.getInt(8);
            long numSiret = resultSet.getLong(9);
            LocalDate dateCommande = resultSet.getDate(10).toLocalDate();
            Commande commande = null;
            if (idTournee > 0) {
                commande = new Commande(numCommande, articleId, poids, quantity, horaireDebut, horaireFin, idClient, idTournee, numSiret, dateCommande);
            } else {
                commande = new Commande(numCommande, articleId, poids, quantity, horaireDebut, horaireFin, idClient, numSiret, dateCommande);
            }
            result.add(commande);
        }
        return result;
    }

    public ArrayList<Commande> getByClientId(int clientId) throws SQLException {
        String query = "SELECT * FROM commande WHERE idClient=?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, clientId);

        ArrayList<Commande> commandes = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Commande commande = null;
            if (resultSet.getInt(7) > 0) {
                commande = new Commande(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDouble(3), resultSet.getInt(4), String.valueOf(resultSet.getTime(5)), String.valueOf(resultSet.getTime(6)), resultSet.getInt(7), resultSet.getInt(8), resultSet.getLong(9), resultSet.getDate(10).toLocalDate());
            } else {
                commande = new Commande(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDouble(3), resultSet.getInt(4), String.valueOf(resultSet.getTime(5)), String.valueOf(resultSet.getTime(6)), resultSet.getInt(7), resultSet.getInt(9), resultSet.getDate(10).toLocalDate());
            }


            commandes.add(commande);
        }
        return commandes;
    }
    public boolean removeById(int id) throws SQLException {
        String query="DELETE FROM commande WHERE articleid ="+id;
        Statement st = con.createStatement();
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }

    public ArrayList<Commande> getAllByTournee(int tourneeId) throws SQLException {
        String query = "SELECT * FROM commande WHERE idTournee=?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, tourneeId);
        ArrayList<Commande> commandes = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            commandes.add(new Commande(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDouble(3), resultSet.getInt(4), String.valueOf(resultSet.getTime(5)), String.valueOf(resultSet.getTime(6)), resultSet.getInt(7), resultSet.getInt(8), resultSet.getLong(9), resultSet.getDate(10).toLocalDate()));
        }
        return commandes;
    }
}
