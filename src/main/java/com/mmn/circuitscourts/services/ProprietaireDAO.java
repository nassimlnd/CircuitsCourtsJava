package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Proprietaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProprietaireDAO implements DAO<Proprietaire, Integer> {

    static Connection connection = ConnectionMySQL.getInstance();
    @Override
    public ArrayList<Proprietaire> getAll() throws SQLException {
        return null;
    }

    @Override
    public Proprietaire getById(Integer id) throws SQLException {
        String query = "SELECT * FROM proprietaire WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Proprietaire(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        } else throw new SQLException("ID INTROUVABLE");
    }

    @Override
    public int add(Proprietaire proprietaire) throws SQLException {
        return 0;
    }

    @Override
    public boolean update(Integer id, Proprietaire proprietaire) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(Integer id) throws SQLException {
        return false;
    }
}
