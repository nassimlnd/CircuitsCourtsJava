package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Tournee;

import java.sql.*;
import java.util.ArrayList;

public class TourneeDAO implements DAO<Tournee,Integer> {
    private Connection conn;

    public TourneeDAO() throws SQLException {
        this.conn = ConnectionMySQL.getInstance();
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
        int id = 0;
        String horaireDebut = null;
        String horaireFin = null;
        int numSiret = -1;
        int numImmat = -1;
        while(rs.next()){
            id=rs.getInt(1);
            horaireDebut=rs.getString(2);
            horaireFin=rs.getString(3);
            numSiret = rs.getInt(4);
            numImmat = rs.getInt(5);
            tournees.add(new Tournee(id,horaireDebut,horaireFin, numSiret, numImmat));
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
        String query = "SELECT * FROM tournee WHERE id="+id;
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        String horaireDebut = null;
        String horaireFin = null;
        int numSiret = -1;
        int numImmat = -1;
        if(rs.next()){
            horaireDebut=rs.getString(2);
            horaireFin=rs.getString(3);
            numSiret = rs.getInt(4);
            numImmat = rs.getInt(5);
        }
        return new Tournee (id,horaireDebut,horaireFin, numSiret, numImmat);



    }

    /**
     * permet d'ajouter une tournee dans la BD.
     *
     * @param tournee la tourner à ajouter.
     * @return retoure un boolean qui atteste de la reussite ou non de l'ajout.
     */
    @Override
    public boolean add(Tournee tournee) throws SQLException {
        String query = "INSERT INTO tournee(id=" + tournee.getId()+",horairedebut= '"+tournee.getHoraireDebut()+"',horaireFin= '"+tournee.getHoraireFin()+"'";
        PreparedStatement pst = conn.prepareStatement(query);
        return Boolean.valueOf(String.valueOf(pst.executeUpdate()));

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
        String query = "UPDATE tournee SET id ="+id+",horairedebut= '"+tournee.getHoraireDebut()+"',horaireFin= '"+tournee.getHoraireFin()+"'";
        PreparedStatement pst = conn.prepareStatement(query);
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
        String query  ="DELETE  FROM tournee WHERE id="+ id;
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }
    public int countById() throws Exception {
        String query = "SELECT COUNT(*) FROM tournee INNER JOIN producteur ON tournee.numSiret = producteur.numSiret INNER JOIN accounts ON producteur.accountId = accounts.accountId WHERE accounts.accountId = " + App.userConnected.getId();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else return 0;
    }

}
