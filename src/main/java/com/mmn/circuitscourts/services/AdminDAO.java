package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Administrateur;
import com.mmn.circuitscourts.models.Personne;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Martin
 * Classe de communication directe  avec la BD.
 * Classe génératrice qui permet de manipuler les Administrateurs.
 */
public class AdminDAO implements DAO<Administrateur, Integer> {
    /**
     * @param con, connection avec la BD;
     */

    private Connection con;

    public AdminDAO(String url, String login, String pwd) {
        this.con = ConnectionMySQL.getInstance();
    }

    /**
     * Permet de récuperer tout le contenu d'une table.
     *
     * @return une ArrayList  qui est constituée de l'entierté de la table
     */
    public ArrayList<Administrateur> getAll() throws SQLException {
        String query = "SELECT * FROM Administrateur";
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Administrateur> result = new ArrayList<>();
        int id = -1;
        String nom = "";
        String adresse = "";
        String numTel = "";
        while (resultSet.next()) {
            id = resultSet.getInt(1);
            nom = resultSet.getString(2);
            adresse = resultSet.getString(3);
            numTel = resultSet.getString(4);
            result.add(new Administrateur(id, nom, adresse, numTel));
        }
        return result;
    }

    /**
     * Permet de retrouver dans la bd un admin via son id. Change l'id existant pour l'incrémenter en dernier dans la table
     *
     * @param id
     * @return un objet administrateur
     */
    @Override
    public Administrateur getById(Integer id) throws SQLException {
        String query = "SELECT * FROM Administrateur WHERE id=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        int idAdmin = -1;
        String nom = "";
        String adresse = "";
        String numTel = "";
        if (rs.next()) {
            idAdmin = rs.getInt(1);
            nom = rs.getString(2);
            adresse = rs.getString(3);
            numTel = rs.getString(4);
        }
        return new Administrateur(idAdmin, nom, adresse, numTel);
    }

    public Integer add(Administrateur admin) throws SQLException {
        PreparedStatement pst = null;
        String nom = admin.getNom();
        String adresse = admin.getAdresse();
        String query2 = "INSERT INTO Administrateur(nom, prenom) VALUES (?, ?)";
        pst = con.prepareStatement(query2);
        pst.setString(1, nom);
        pst.setString(2, adresse);
        pst.executeUpdate();

        ResultSet resultSet = pst.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else throw new SQLException("ERREUR ADD ADMIN");
    }

    /**
     * Permet de mofifier dans la BD un  administrateur particulier. Ne change pas son ID.
     *
     * @param id , adinistrateur à modifier
     * @param admin qui va remplacer l'ancien.
     */
    public boolean update(Integer id, Administrateur admin) throws SQLException {
        String nom = admin.getNom();
        String adresse = admin.getAdresse();
        String numTel = admin.getNumTel();
        String query2 = "UPDATE Administrateur SET nom=?, prenom=? WHERE id =" + id;
        PreparedStatement pst = con.prepareStatement(query2);
        pst.setString(1, nom);
        pst.setString(2, adresse);
        pst.setString(3, numTel);
        pst.executeUpdate();
        return Boolean.valueOf(String.valueOf(pst.executeUpdate()));

    }

    /**
     * Permet de supprimer un  administrateur de la BD via son id
     *
     * @param id est l'id d'un admin
     */

    public boolean remove(Integer id) throws SQLException {
        String query = "DELETE  FROM Administrateur WHERE id=" + id;
        Statement st = con.createStatement();
        st.executeUpdate(query);
        return Boolean.valueOf(String.valueOf(st.executeUpdate(query)));
    }


}
