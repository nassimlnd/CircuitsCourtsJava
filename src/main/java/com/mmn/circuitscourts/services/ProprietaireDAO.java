package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Proprietaire;

import java.sql.*;
import java.util.ArrayList;

public class ProprietaireDAO implements DAO<Proprietaire, Integer> {

    static Connection connection = ConnectionMySQL.getInstance();
    @Override
    public ArrayList<Proprietaire> getAll() throws SQLException {
        return null;
    }

    @Override
    public Proprietaire getById(Integer id) throws SQLException {
        System.out.println(id);
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
        String query2 = "INSERT INTO Proprietaire (nom, adresse, numTel) VALUES ( ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, proprietaire.getNom());
        pst.setString(2, proprietaire.getAdresse());
        pst.setString(3, proprietaire.getNumTel());
        pst.executeUpdate();
        ResultSet resultSet = pst.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else throw new SQLException("ERREUR ADD COMMANDE");
    }


    @Override
    public boolean update(Integer id, Proprietaire proprietaire) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(Integer id) throws SQLException {
        String query = "DELETE FROM proprietaire WHERE id ="+id;
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        return  Boolean.valueOf(String.valueOf(st.executeUpdate(query)));

    }

    public boolean removeFromPropSiret(int numSiret) throws SQLException {
        String query = "DELETE p FROM proprietaire p INNER JOIN entreprise entre  ON p.id=entre.proprietaire WHERE entre.numSiret = "+ numSiret;
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));

    }
}
