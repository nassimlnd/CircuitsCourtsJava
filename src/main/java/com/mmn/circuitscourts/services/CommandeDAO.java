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
        while (resultSet.next()) {
            int numCommande = resultSet.getInt(1);
            int articleId = resultSet.getInt(2);
            double poids = resultSet.getDouble(3);
            String horaireDebut = resultSet.getString(4);
            String horaireFin = resultSet.getString(5);
            int idClient = resultSet.getInt(6);
            int idTournee = resultSet.getInt(7);
            int numSiret = resultSet.getInt(8);
            Commande commande = new Commande(numCommande, articleId, poids, horaireDebut, horaireFin, idClient, numSiret);
            commande.setIdTournee(idTournee);
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
            String horaireDebut = resultSet.getString(4);
            String horaireFin = resultSet.getString(5);
            int idClient = resultSet.getInt(6);
            int idTournee = resultSet.getInt(7);
            int numSiret = resultSet.getInt(8);
            Commande commande = new Commande(numCommande, articleId, poids, horaireDebut, horaireFin, idClient, numSiret);
            commande.setIdTournee(idTournee);
            return commande;
        }
        //TODO : créer exception pour spécifier si on ne trouve pas d'admin avec cet id (exception 1)
        throw new SQLException();
    }

    /**
     * Implémentation de la méthode add de l'interface DAO.
     * Prend la commande avec tous ses paramètres que l'on veut ajouter à la base de données.
     * @param commande, la commande.
     * @return un boolean qui nous indique si l'executeUpdate s'est bien effectuée.
     * @throws SQLException
     */
    @Override
    public int add(Commande commande) throws SQLException {
        String query2 = "INSERT INTO Commande(articleId, poids, horaireDebut, horaireFin, idClient, numSiret) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
        pst.setInt(1, commande.getArticleId());
        pst.setDouble(2, commande.getPoids());
        pst.setString(3, commande.getHoraireDebut());
        pst.setString(4, commande.getHoraireFin());
        pst.setInt(5, commande.getIdClient());
        pst.setInt(6, commande.getNumSiret());
        pst.executeUpdate();

        ResultSet resultSet = pst.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else throw new SQLException("ERREUR ADD COMMANDE");
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
        String query = "UPDATE Commande SET articleId=?, poids=?, horaireDebut=?, horaireFin=?, idClient=?, numSiret=? WHERE numCommande =?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, commande.getArticleId());
        pst.setDouble(2, commande.getPoids());
        pst.setString(3, commande.getHoraireDebut());
        pst.setString(4, commande.getHoraireFin());
        pst.setInt(5, commande.getIdClient());
        pst.setInt(6, commande.getNumSiret());
        pst.setInt(7, commande.getNumCommande());
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
     * Compte le nombre de commandes d'un compte producteur.
     * @return le nombre de commande appartenant au compte.
     * @throws Exception
     */
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
        while (resultSet.next()) {
            int numCommande = resultSet.getInt(1);
            int articleId = resultSet.getInt(2);
            double poids = resultSet.getDouble(3);
            String horaireDebut = resultSet.getString(4);
            String horaireFin = resultSet.getString(5);
            int idClient = resultSet.getInt(6);
            int idTournee = resultSet.getInt(7);
            int numSiret = resultSet.getInt(8);
            Commande commande = new Commande(numCommande, articleId, poids, horaireDebut, horaireFin, idClient, numSiret);
            commande.setIdTournee(idTournee);
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
            Commande commande = new Commande(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDouble(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(8));
            commande.setIdTournee(resultSet.getInt(7));
            commandes.add(commande);
        }
        return commandes;
    }
    public boolean removeById(int id) throws SQLException {
        String query="DELETE FROM commande WHERE articleid ="+id;
        Statement st = con.createStatement();
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }
}
