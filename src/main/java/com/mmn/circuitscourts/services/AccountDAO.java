package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.User;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO implements DAO<User> {

    static Connection connection = ConnectionMySQL.getInstance("jdbc:mysql://localhost/circuitscourts?serverTimezone=Europe/Paris", "root", "");

    public AccountDAO() {}
    @Override
    public ArrayList<User> getAll() throws SQLException {
        String query = "SELECT * FROM accounts";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<User> accountsList = new ArrayList<>();

        while (resultSet.next()) {
            accountsList.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
        }

        return accountsList;
    }

    @Override
    public User getById(int id) throws SQLException {
        String query = "SELECT * FROM accounts WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        } else throw new SQLException("ID introuvable.");
    }

    @Override
    public void add(User user) throws SQLException {
        String query = "INSERT INTO accounts(identifiant, password, grade) VALUES (?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getIdentifiant());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getGrade());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            user.setId(resultSet.getInt(1));
        }
    }

    @Override
    public void update(int id, User user) throws SQLException {

    }

    @Override
    public void remove(int id) throws SQLException {

    }
}
