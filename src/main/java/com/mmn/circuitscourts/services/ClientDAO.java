package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Client;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ClientDAO implements DAO<Client, Integer> {

    static Connection connection = ConnectionMySQL.getInstance();

    public ClientDAO() {

    }
    @Override
    public ArrayList<Client> getAll() throws SQLException {
        String query = "SELECT * FROM client";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Client> clients = new ArrayList<>();
        while (resultSet.next()) {
            clients.add(new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
        }
        return clients;
    }

    @Override
    public Client getById(Integer id) throws SQLException {
        String query = "SELECT * FROM client WHERE idC=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
        } else throw new SQLException("ID INTROUVABLE");
    }

    @Override
    public int add(Client client) throws SQLException {
        String query = "INSERT INTO clients(nom, adresse, numTel) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, client.getNom());
        preparedStatement.setString(2, client.getAdresse());
        preparedStatement.setString(3, client.getNumTel());
        preparedStatement.executeUpdate();

        String query2 = "INSERT INTO logs(accountId, categorie, date, time) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
        preparedStatement1.setInt(1, App.userConnected.getId());
        preparedStatement1.setString(2, "newclient");
        preparedStatement1.setDate(3, Date.valueOf(LocalDate.now()));
        preparedStatement1.setTime(4, Time.valueOf(LocalTime.now()));

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else throw new SQLException("ERREUR ADD CLIENT");
    }

    @Override
    public boolean update(Integer id, Client client) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(Integer id) throws SQLException {
        return false;
    }

    public Client getByAccountId(int accountId) throws SQLException {
        String query = "SELECT * FROM client WHERE accountId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, accountId);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
        } else throw new SQLException("ACCOUNTID INTROUVABLE.");
    }
}
