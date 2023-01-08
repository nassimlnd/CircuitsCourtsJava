package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Tournee;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TourneeDAO implements DAO<Tournee, Integer> {
    private static Connection conn = ConnectionMySQL.getInstance();
    private int numSiret;

    public TourneeDAO() {
    }

    /**
     * Permet de récuperer tout le contenu d'une table.
     *
     * @return une ArrayList qui est constituée de l'entierté de la table
     */
    @Override
    public ArrayList<Tournee> getAll() throws SQLException {
        String query = "SELECT * FROM tournee";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<Tournee> tournees = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(1);
            LocalDate date = rs.getDate(2).toLocalDate();
            String horaireDebut = String.valueOf(rs.getTime(3));
            String horaireFin = String.valueOf(rs.getTime(4));
            long numSiret = rs.getLong(5);
            String numImmat = rs.getString(6);
            tournees.add(new Tournee(id, date, horaireDebut, horaireFin, numSiret, numImmat));
        }

        return tournees;
    }

    /**
     * Permet de trouver une tournée avec son id dans la BD.
     *
     * @param id attribut qui sert a identifier une tournee.
     * @return retourne la tournee en queston.
     */
    @Override
    public Tournee getById(Integer id) throws SQLException {
        String query = "SELECT * FROM tournee WHERE idT=" + id;
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            LocalDate date = rs.getDate(2).toLocalDate();
            String horaireDebut = String.valueOf(rs.getTime(3));
            String horaireFin = String.valueOf(rs.getTime(4));
            long numSiret = rs.getLong(5);
            String numImmat = rs.getString(6);
            return new Tournee(id, date, horaireDebut, horaireFin, numSiret, numImmat);
        } else throw new SQLException("Id introuvable.");
    }

    /**
     * permet d'ajouter une tournee dans la BD.
     *
     * @param tournee la tourner à ajouter.
     * @return retoure un boolean qui atteste de la reussite ou non de l'ajout.
     */
    @Override
    public Integer add(Tournee tournee) throws SQLException {
        String query = "INSERT INTO tournee(date, horaireDebut, horaireFin, numSiret, numImmat) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setDate(1, Date.valueOf(tournee.getDate()));
        pst.setTime(2, Time.valueOf(tournee.getHoraireDebut()));
        pst.setTime(3, Time.valueOf(tournee.getHoraireFin()));
        pst.setLong(4, tournee.getNumSiret());
        pst.setString(5, tournee.getNumImmat());
        pst.executeUpdate();

        String query2 = "INSERT INTO logs(accountId, categorie, objectId, date, time) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement1 = conn.prepareStatement(query2);
        preparedStatement1.setInt(1, App.userConnected.getId());
        preparedStatement1.setString(2, "newcommande");
        preparedStatement1.setInt(3, tournee.getId());
        preparedStatement1.setDate(4, Date.valueOf(LocalDate.now()));
        preparedStatement1.setTime(5, Time.valueOf(LocalTime.now()));
        preparedStatement1.executeUpdate();

        ResultSet resultSet = pst.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else throw new SQLException("ERREUR ADD TOURNEE");

    }

    /**
     * Permet de modifier une tournee dans la BD.
     *
     * @param id      qui sert a trouver la tourne a modifier dans la BD.
     * @param tournee la nouvelle version de la tournee avec laquelle remplacer l'ancienne dans la BD.
     * @return retoure un boolean qui atteste de la reussite ou non de la modification.
     */
    @Override
    public boolean update(Integer id, Tournee tournee) throws SQLException {
        String query = "UPDATE tournee SET date=?, horaireDebut=?, horaireFin=?, numSiret=?, numImmat=? WHERE idT=?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setDate(1, Date.valueOf(tournee.getDate()));
        pst.setTime(2, Time.valueOf(tournee.getHoraireDebut()));
        pst.setTime(3, Time.valueOf(tournee.getHoraireFin()));
        pst.setLong(4, tournee.getNumSiret());
        pst.setString(5, tournee.getNumImmat());
        pst.setInt(6, tournee.getId());
        return Boolean.valueOf(String.valueOf(pst.executeUpdate()));
    }

    /**
     * Permet de supprimer une tournee de la BD.
     *
     * @param id permet de retrouver la tournee à supprimer
     * @return retoure un boolean qui atteste de la reussite ou non de la suppresion de la tournee.
     */
    @Override
    public boolean remove(Integer id) throws SQLException {
        String query = "DELETE FROM tournee WHERE idT=" + id;
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }

    public int countById() throws Exception {
        String query = "SELECT COUNT(*) FROM tournee INNER JOIN entreprise ON tournee.numSiret = entreprise.numSiret INNER JOIN accounts ON entreprise.accountId = accounts.accountId WHERE accounts.accountId = " + App.userConnected.getId();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else return 0;
    }

    public ArrayList<Tournee> getAllByEntreprise(int accountId) throws SQLException {
        String query = "SELECT * FROM tournee INNER JOIN entreprise ON tournee.numSiret=entreprise.numSiret WHERE entreprise.accountId=" + accountId;
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        ArrayList<Tournee> tournees = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt(1);
            LocalDate date = rs.getDate(2).toLocalDate();
            String horaireDebut = String.valueOf(rs.getTime(3));
            String horaireFin = String.valueOf(rs.getTime(4));
            long numSiret = rs.getLong(5);
            String numImmat = rs.getString(6);
            tournees.add(new Tournee(id, date, horaireDebut, horaireFin, numSiret, numImmat));
        }
        return tournees;
    }


}


