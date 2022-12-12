package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.models.Vehicule;

import java.sql.*;
import java.util.ArrayList;

public class VehiculeDAO implements DAO<Vehicule,String>{
    private Connection conn;
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
        while(rs.next()){

            numImmat=rs.getString(2);
            poidsMax=rs.getInt(3);
            vehicules.add(new Vehicule(numImmat,poidsMax));
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
        if(rs.next()){
            poidsMax=rs.getInt(2);
        }
        return new Vehicule(numImmat,poidsMax);
    }

    /**
     * permet de d'ajouter un vehicule dans la BD.
     *
     * @param vehicule le vehicule à ajouter dans la BD.
     * @return retoure un boolean qui atteste de la reussite ou non de l'ajout.
     */
    @Override
    public boolean add(Vehicule vehicule) throws SQLException {
        String query = "INSERT INTO vehicule(numImmat='" + vehicule.getNumImmate()+"',poidsMax= "+vehicule.getPoidsMax();
        PreparedStatement pst = conn.prepareStatement(query);
        return Boolean.valueOf(String.valueOf(pst.executeUpdate()));
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
        String query = "UPDATE vehicule SET numImmat='" + vehicule.getNumImmate()+"',poidsMax= "+vehicule.getPoidsMax();
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
        String query  ="DELETE  FROM vehicule WHERE numImmat='"+ numImmat+"'";
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }
}