package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Commande;

import java.sql.*;
import java.util.ArrayList;

public class CommandeDAO implements DAO<Commande, Integer> {
    /**
     * @param con, connection avec la BD;
     */
    private Connection con;

    public CommandeDAO() {
        this.con = ConnectionMySQL.getInstance();
    }

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
            result.add(new Commande(numCommande, articleId, poids, horaireDebut, horaireFin, idClient, idTournee, numSiret));
        }
        return result;
    }


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
            return new Commande(numCommande, articleId, poids, horaireDebut, horaireFin, idClient, idTournee, numSiret);
        }
        //TODO : créer exception pour spécifier si on ne trouve pas d'admin avec cet id (exception 1)
        throw new SQLException();
    }

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

    @Override
    public boolean update(Integer numCommande, Commande commande) throws SQLException {
        String query = "UPDATE Commande SET articleId=?, poids=?, horaireDebut=?, horaireFin=?, idClient=?, idTournee=?, numSiret=? WHERE numCommande =?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, commande.getArticleId());
        pst.setDouble(2, commande.getPoids());
        pst.setString(3, commande.getHoraireDebut());
        pst.setString(4, commande.getHoraireFin());
        pst.setInt(5, commande.getIdClient());
        pst.setInt(6, commande.getIdTournee());
        pst.setInt(7, commande.getNumSiret());
        pst.setInt(8, commande.getNumCommande());
        return Boolean.valueOf(String.valueOf(pst.executeUpdate()));
    }

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
            result.add(new Commande(numCommande, articleId, poids, horaireDebut, horaireFin, idClient, idTournee, numSiret));
        }
        return result;
    }
}
