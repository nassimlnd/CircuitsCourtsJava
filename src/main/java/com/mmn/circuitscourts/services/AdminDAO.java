package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Administrateur;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Martin
 * Classe de communication directe  avec la BD.
 * Classe génératrice qui permet de manipuler les Administrateurs.
 */
public class AdminDAO implements DAO {
    /**
     * @param con, connection avec la BD;
     */

    private Connection con;

    public AdminDAO(String url, String login, String pwd) {
        this.con = ConnectionMySQL.getInstance(url, login, pwd);
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
    public Administrateur getById(int id) throws SQLException {
        String query = "SELECT * FROM Administrateur WHERE id=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int idAdmin = -1;
            String nom = "";
            String adresse = "";
            String numTel = "";
            idAdmin = rs.getInt(1);
            nom = rs.getString(2);
            adresse = rs.getString(3);
            numTel = rs.getString(4);

            return new Administrateur(idAdmin, nom, adresse, numTel);
        }
        //TODO : créer exception pour spécifier si on ne trouve pas d'admin avec cet id (exception 1)
        throw new SQLException();
    }

    @Override
    public void add(Object o) throws SQLException {
        if (o instanceof Administrateur) {
            String nom = ((Administrateur) o).getNom();
            String adresse = ((Administrateur) o).getAdresse();
            String query2 = "INSERT INTO Administrateur(nom, prenom) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query2);
            pst.setString(1, nom);
            pst.setString(2, adresse);
            pst.executeUpdate();
        }
        //TODO : créer exception pour spécifier que l'objet passé en parametre n'est pas un admin (exception 2)
        else throw new SQLException();
    }

    /**
     * Permet de mofifier dans la BD un  administrateur particulier. Ne change pas son ID.
     *
     * @param id , adinistrateur à modifier
     * @param o  objet  qui va remplacer l'ancien.
     */
    public void update(int id, Object o) throws SQLException {
        if (o instanceof Administrateur) {
            String nom = ((Administrateur) o).getNom();
            String adresse = ((Administrateur) o).getAdresse();
            String numTel = ((Administrateur) o).getNumTel();
            String query2 = "UPDATE Administrateur SET nom=?, prenom=? WHERE id =" + id;
            PreparedStatement pst = con.prepareStatement(query2);
            pst.setString(1, nom);
            pst.setString(2, adresse);
            pst.setString(3, numTel);
            pst.executeUpdate();
        }//TODO : (exception 2)
    }

    /**
     * Permet de supprimer un  administrateur de la BD via son id
     *
     * @param id
     */
    public void remove(int id) throws SQLException {
        //TODO : (exception 1)
        String query = "DELETE  FROM Administrateur WHERE id=" + id;
        Statement st = con.createStatement();
        st.executeUpdate(query);
    }


}
