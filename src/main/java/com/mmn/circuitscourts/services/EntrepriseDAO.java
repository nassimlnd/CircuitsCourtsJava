package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Entreprise;

import java.sql.*;
import java.util.ArrayList;

public class EntrepriseDAO implements DAO<Entreprise, Long>{

    static Connection connection = ConnectionMySQL.getInstance();
    @Override
    public ArrayList<Entreprise> getAll() throws SQLException {
        String query = "SELECT * FROM entreprise";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Entreprise> entreprises = new ArrayList<>();
        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
        while (resultSet.next()){
            entreprises.add(new Entreprise(resultSet.getLong(1), resultSet.getString(2), proprietaireDAO.getById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
        }
        return entreprises;
    }

    @Override
    public Entreprise getById(Long id) throws SQLException {
        String query = "SELECT * FROM entreprise WHERE numSiret=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();

        if (resultSet.next()) {
            return new Entreprise(resultSet.getLong(1), resultSet.getString(2), proprietaireDAO.getById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
        } else throw new SQLException("NUMSIRET INTROUVABLE");
    }

    /**
     * ajout d'une entreprise sans accont id pour l'admin, c'est l'admin qui lie un compte déja existant à une entreprise.
     * @param entreprise
     * @return
     * @throws SQLException
     */
    @Override
    public int add(Entreprise entreprise) throws SQLException {
        String query2 = "INSERT INTO entreprise (adresse, proprietaire, numTel, coordoneesGPS) VALUES ( ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, entreprise.getAdresse());
        pst.setInt(2, entreprise.getProprietaire().getId());
        pst.setString(3, entreprise.getNumTel());
        pst.setString(4, entreprise.getCoordonneesGps());
        pst.executeUpdate();
        ResultSet resultSet = pst.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else throw new SQLException("ERREUR ADD Entreprise");
    }


    @Override
    public boolean update(Long numSiret, Entreprise entreprise) throws SQLException {
        String query = "UPDATE entreprise SET adresse=?, proprietaire=?, numTel=?, coordoneesGPS=?, accountId=? WHERE numSiret=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entreprise.getAdresse());
        preparedStatement.setInt(2, entreprise.getProprietaire().getId());
        preparedStatement.setString(3, entreprise.getNumTel());
        preparedStatement.setString(4,entreprise.getCoordonneesGps());
        preparedStatement.setInt(5,entreprise.getAccountId());
        preparedStatement.setLong(6,entreprise.getNumSiret());
        return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
    }

    @Override
    public boolean remove(Long numSiret) throws SQLException {
        String query  ="DELETE  FROM entreprise WHERE numSiret="+ numSiret;
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }

    public Entreprise getByAccountId(int accountId) throws SQLException {
        String query = "SELECT * FROM entreprise WHERE accountId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, accountId);

        ResultSet resultSet = preparedStatement.executeQuery();
        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
        if (resultSet.next()) {
            return new Entreprise(resultSet.getLong(1), resultSet.getString(2), proprietaireDAO.getById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6));
        } else throw new SQLException("ID INTROUVABLE");
    }

    public ArrayList<Entreprise> getAllNonProprietaire() throws SQLException {
        String query = "SELECT * FROM entreprise WHERE entreprise.proprietaire NOT IN(SELECT entreprise.proprietaire FROM entreprise INNER JOIN proprietaire ON entreprise.proprietaire = proprietaire.id) ";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Entreprise> entreprises = new ArrayList<>();
        ProprietaireDAO proprietaireDAO = new ProprietaireDAO();
        while (resultSet.next()){
            entreprises.add(new Entreprise(resultSet.getLong(1), resultSet.getString(2), proprietaireDAO.getById(resultSet.getInt(3)), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
        }
        return entreprises;
    }
}
