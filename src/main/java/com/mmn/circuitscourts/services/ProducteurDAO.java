package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Producteur;

import java.sql.*;
import java.util.ArrayList;

public class ProducteurDAO implements DAO<Producteur, Integer>{

    static Connection connection = ConnectionMySQL.getInstance();
    @Override
    public ArrayList<Producteur> getAll() throws SQLException {
        String query = "SELECT * FROM producteur";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Producteur> producteurs = new ArrayList<>();
        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
        while (resultSet.next()){
            System.out.println(resultSet.getInt(3));
            producteurs.add(new Producteur(resultSet.getInt(1), resultSet.getString(2), proprietaireDAO.getById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
        }
        return producteurs;
    }

    @Override
    public Producteur getById(Integer id) throws SQLException {
        String query = "SELECT * FROM producteur WHERE numSiret=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();

        if (resultSet.next()) {
            return new Producteur(resultSet.getInt(1), resultSet.getString(2), proprietaireDAO.getById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
        } else throw new SQLException("NUMSIRET INTROUVABLE");
    }

    /**
     * ajout d'un producteur sans accont id pour l'admin, c'est l'admin qui lie un compte déja existant à un producteur.
     * @param producteur
     * @return
     * @throws SQLException
     */
    @Override
    public int add(Producteur producteur) throws SQLException {
        String query2 = "INSERT INTO Producteur (adresse, proprietaire, numTel, coordoneesGPS) VALUES ( ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, producteur.getAdresse());
        pst.setInt(2, producteur.getProprietaire().getId());
        pst.setString(3, producteur.getNumTel());
        pst.setString(4, producteur.getNumTel());
        pst.executeUpdate();
        ResultSet resultSet = pst.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else throw new SQLException("ERREUR ADD Producteur");
    }


    @Override
    public boolean update(Integer numSiret, Producteur producteur) throws SQLException {
        String query = "UPDATE producteur SET adresse=?, proprietaire=?, numTel=?, coordoneesGPS=?, accountId=? WHERE numSiret=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, producteur.getAdresse());
        preparedStatement.setInt(2, producteur.getProprietaire().getId());
        preparedStatement.setString(3, producteur.getNumTel());
        preparedStatement.setString(4,producteur.getCoordonneesGps());
        preparedStatement.setInt(5,producteur.getAccountId());
        preparedStatement.setInt(6,producteur.getNumSiret());
        return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
    }

    @Override
    public boolean remove(Integer numSiret) throws SQLException {
        String query  ="DELETE  FROM producteur WHERE numSiret="+ numSiret;
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }

    public Producteur getByAccountId(int accountId) throws SQLException {
        String query = "SELECT * FROM producteur WHERE accountId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, accountId);

        ResultSet resultSet = preparedStatement.executeQuery();
        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
        if (resultSet.next()) {
            return new Producteur(resultSet.getInt(1), resultSet.getString(2), proprietaireDAO.getById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
        } else throw new SQLException("ID INTROUVABLE");
    }

    public Producteur getBynumSiret(int numSiret) throws SQLException {
        String query = "SELECT * FROM producteur WHERE numSiret=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, numSiret);  
        ResultSet resultSet = preparedStatement.executeQuery();
        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
        if (resultSet.next()) {
            return new Producteur(resultSet.getInt(1), resultSet.getString(2), proprietaireDAO.getById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
        } else throw new SQLException("ID INTROUVABLE");
    }

    public ArrayList<Producteur> getAllNonProprietaire() throws SQLException {
        String query = "SELECT * FROM producteur WHERE producteur.proprietaire NOT IN(SELECT producteur.proprietaire FROM producteur INNER JOIN proprietaire ON producteur.proprietaire = proprietaire.id) ";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Producteur> producteurs = new ArrayList<>();
        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
        while (resultSet.next()){
            producteurs.add(new Producteur(resultSet.getInt(1), resultSet.getString(2), proprietaireDAO.getById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
        }
        return producteurs;
    }
}
