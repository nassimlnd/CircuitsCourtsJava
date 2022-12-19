package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.models.Vehicule;

import java.sql.*;
import java.util.ArrayList;

public class VehiculeDAO implements DAO<Vehicule,String>{
    private Connection conn;

    public VehiculeDAO() throws SQLException {
        this.conn = ConnectionMySQL.getInstance();
    }
    /**
     * Permet de récuperer tout le contenu de la table vehicule.
     *
     * @return une ArrayList qui est constituée de l'entierté de la table.
     */
    @Override
    public ArrayList<Vehicule> getAll() throws SQLException {
        String query = "SELECT * FROM vehicule";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<Vehicule> vehicules = new ArrayList<>();
        String numImmat = null;
        int poidsMax = 0;
        int numSiret = -1;
        while(rs.next()){
            numImmat=rs.getString(1);
            poidsMax=rs.getInt(2);
            numSiret = rs.getInt(3);

            vehicules.add(new Vehicule(numImmat,poidsMax, numSiret));
        }
        return vehicules;
    }

    /**
     * Permet de trouver un vehicule dans la BD via à son numImmat et le retourner.
     *
     * @param numImmat est l'attribut qui permet d'identifier un vehicule.
     * @return retourne le vehicule en question.
     */
    @Override
    public Vehicule getById(String numImmat) throws SQLException {
        String query = "SELECT * FROM vehicule WHERE numImmat= '"+numImmat+"'";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        int poidsMax = 0;
        int numSiret = -1;
        if(rs.next()){
            poidsMax=rs.getInt(2);
            numSiret = rs.getInt(3);
        }
        return new Vehicule(numImmat,poidsMax, numSiret);
    }

    /**
     * permet de d'ajouter un vehicule dans la BD.
     *
     * @param vehicule le vehicule à ajouter dans la BD.
     * @return retoure un boolean qui atteste de la reussite ou non de l'ajout.
     */
    @Override
    public int add(Vehicule vehicule) throws SQLException {
        String query = "INSERT INTO vehicule(numImmat, poids, numSiret) VALUES ('" + vehicule.getNumImmate()+"', "+vehicule.getPoidsMax()+","+ vehicule.getnumSiret()+")";
        PreparedStatement pst = conn.prepareStatement(query);

        return pst.executeUpdate();
    }

    /**
     * Permet de modifier un vehicule particulier dans la BD.
     *
     * @param numImmat  permet de le vehicule à modifier.
     * @param vehicule  le nouveau vehicule avec lequelle remplacer l'ancien dans la BD.
     * @return retoure un boolean qui atteste de la reussite ou non de la modification.
     */
    @Override
    public boolean update(String numImmat, Vehicule vehicule) throws SQLException {
        String query = "UPDATE vehicule SET poids= "+vehicule.getPoidsMax()+" WHERE numImmat='"+ numImmat+"'";
        PreparedStatement pst = conn.prepareStatement(query);
        return Boolean.valueOf(String.valueOf(pst.executeUpdate()));
    }

    /**
     * Permet de supprimer un vehicule de la BD.
     *
     * @param numImmat permet de retrouver le vehicule à supprimer.
     * @return retoure un boolean qui atteste de la reussite ou non de la suppression.
     */
    @Override
    public boolean remove(String numImmat) throws SQLException {
        Statement st = conn.createStatement();
        String query1 = "DELETE  FROM vehicule WHERE numImmat='" + numImmat + "'";
        st.executeUpdate(query1);
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query1)));
    }

    /**
     * Permet de récuperer tout le contenu de la table vehicule.
     *
     * @return une ArrayList qui est constituée de l'entierté de la table.
     */

    public ArrayList<Vehicule> getAllByProducteur(int accountId) throws SQLException {
        String query = "SELECT * FROM  vehicule INNER JOIN Producteur ON Vehicule.numSiret=Producteur.numSiret WHERE Producteur.accountId=" + accountId;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<Vehicule> vehicules = new ArrayList<>();
        String numImmat = null;
        int poidsMax = 0;
        while(rs.next()){
            numImmat=rs.getString(1);
            poidsMax=rs.getInt(2);
            vehicules.add(new Vehicule(numImmat,poidsMax));
        }
        return vehicules;
    }

    public int getNumSiretConnected(int idUser) throws SQLException {
        String query = "SELECT DISTINCT Vehicule.numSiret FROM Vehicule INNER JOIN Producteur ON Vehicule.numSiret=Producteur.numSiret WHERE Producteur.accountId=" + idUser;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            int numSiret = rs.getInt(1);
            return numSiret;
        }
        throw new SQLException("numSiret introuvable");
    }

    public boolean exists(Vehicule vehicule) throws SQLException {
        String query = "SELECT * FROM vehicule WHERE numImmat=?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, vehicule.getNumImmate());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        } else return false;
    }
}
