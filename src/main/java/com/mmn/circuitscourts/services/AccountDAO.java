package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

public class AccountDAO implements DAO<User,Integer> {

    static Connection connection = ConnectionMySQL.getInstance();

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
    public User getById(Integer id) throws SQLException {
        String query = "SELECT * FROM accounts WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        } else throw new SQLException("ID introuvable.");
    }

    @Override
    public boolean add(User user) throws SQLException {
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
        return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
    }

    @Override
    public boolean update(Integer id, User user) throws SQLException {
        String query = "UPDATE accounts SET identifiant=?, password=?, grade=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getIdentifiant());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getGrade());
        preparedStatement.setInt(4, user.getId());
        preparedStatement.executeUpdate();
        return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
    }

    @Override
    public boolean remove(Integer id) throws SQLException {
        String query = "DELETE FROM accounts WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
    }

    public String getPasswordByIdentifiant(String identifiant) throws Exception {
        String query = "SELECT password FROM accounts WHERE identifiant=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, identifiant);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else throw new Exception("Identifiant introuvable.");
    }

    public User connect(String identifiant, String password) throws Exception {
        String goodPass = getPasswordByIdentifiant(identifiant);

        if (password.equals(goodPass)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE identifiant=? AND password=?");
            preparedStatement.setString(1, identifiant);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
            } else throw new SQLException("Problème !");
        } else throw new Exception("Mot de passe incorrect.");
    }


    public User getName(String name) throws SQLException {
        String query = "SELECT * FROM accounts WHERE identifiant=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        } else throw new SQLException("ID introuvable.");
    }
}
