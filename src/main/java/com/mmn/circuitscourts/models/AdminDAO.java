package com.mmn.circuitscourts.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAO implements DAO{

    /**
     * @author Martin
     * Classe de communication directe  avec la BD.
     * Classe génératrice qui permet de manipuler les Administrateurs.
     * @param con, connection avec la BD;
     */


        private Connection con;

        public AdminDAO(String url, String login, String pwd) throws SQLException {
            this.con = SingleConnection.getInstance(url, login, pwd);
        }

        /**
         * Permet de récuperer tout le contenu d'une table.
         * @return une ArrayList  qui est constituée de l'entierté de la table
         */
        public ArrayList<Administrateur> getAll() throws SQLException {
            String query = "SELECT * FROM ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, "Admin");
            ResultSet resultSet = st.executeQuery();
            ArrayList<Administrateur> result = new ArrayList<>();
            while(resultSet.next()){
                int id =
            }

        }

    /**
     * Permet de retrouver dans la bd un admin via son id.
     * @param id
     * @return un objet administrateur
     */
    public Administrateur getById(String id) {

    }

    /**
     * Permet d'enregistrer dans la BD un objet admin particulier.
     * @param o
     */
    @Override
    public void update(Object o) {

    }

    /**
     * Permet de supprimer un  administrateur de la BD
     * @param o
     */
    @Override
    public void remove(Object o) {

    }

    public void addProducteur(Producteur P){

    }

    public void addClient(Client c){

    }
}
