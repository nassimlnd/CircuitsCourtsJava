package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Article;

import java.sql.*;
import java.util.ArrayList;

public class MarketplaceDAO implements DAO<Article, Integer> {
    static Connection connection = ConnectionMySQL.getInstance();

    public MarketplaceDAO() {

    }
    @Override
    public ArrayList<Article> getAll() throws SQLException {
        String query = "SELECT * FROM articles";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<Article> articles = new ArrayList<>();

        while (resultSet.next()) {
            articles.add(new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getInt(8), resultSet.getInt(7)));
        }

        return articles;
    }

    public ArrayList<String> getAllTags() throws SQLException {
        String query = "SELECT DISTINCT categorie FROM articles";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<String> tags = new ArrayList<>();

        while (resultSet.next()) {
            tags.add(resultSet.getString(1));
        }

        return tags;
    }

    @Override
    public Article getById(Integer id) throws SQLException {
        String query = "SELECT * FROM articles WHERE idArticle = "+id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getInt(8), resultSet.getInt(7));
        } else throw new SQLException("ID INTROUVABLE");
    }

    @Override
    public int add(Article article) throws SQLException {
        String query = "INSERT INTO articles(name, categorie, description, price, weight, numSiret, imageId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, article.getName());
        preparedStatement.setString(2, article.getCategorie());
        preparedStatement.setString(3, article.getDescription());
        preparedStatement.setDouble(4, article.getPrice());
        preparedStatement.setDouble(5, article.getWeight());
        preparedStatement.setInt(6, article.getNumSiret());
        preparedStatement.setInt(7, article.getImageId());

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else throw new SQLException("ERREUR ADD ARTICLE");
    }

    @Override
    public boolean update(Integer id, Article article) throws SQLException {
        String query = "UPDATE article SET name=?, categorie=?, description=?, price=?, weight?, imageId=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, article.getName());
        preparedStatement.setString(2, article.getCategorie());
        preparedStatement.setDouble(3, article.getPrice());
        preparedStatement.setDouble(4, article.getWeight());
        preparedStatement.setInt(5, article.getImageId());

        return Boolean.valueOf(String.valueOf(preparedStatement.executeUpdate()));
    }

    @Override
    public boolean remove(Integer id) throws SQLException {
        return false;
    }

    public ArrayList<Article> getByTag(String tagName) throws SQLException {
        String query = "SELECT * FROM articles WHERE categorie=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, tagName);

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Article> articles = new ArrayList<>();
        while (resultSet.next()) {
            articles.add(new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getInt(8), resultSet.getInt(7)));
        }
        return articles;
    }
}
