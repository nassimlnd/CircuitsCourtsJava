package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Article;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            articles.add(new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getDouble(5)));
        }

        return articles;
    }

    @Override
    public Article getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public boolean add(Article article) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Integer id, Article article) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(Integer id) throws SQLException {
        return false;
    }
}
