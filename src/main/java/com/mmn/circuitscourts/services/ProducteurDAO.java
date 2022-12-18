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

    @Override
    public int add(Producteur producteur) throws SQLException {
        return 0;
    }

    @Override
    public boolean update(Integer id, Producteur producteur) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(Integer id) throws SQLException {
        return false;
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
}
