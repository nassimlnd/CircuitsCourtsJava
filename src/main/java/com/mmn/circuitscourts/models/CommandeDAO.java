package com.mmn.circuitscourts.models;

import java.sql.*;
import java.util.ArrayList;

public class CommandeDAO implements DAO{
    /**
     * @param con, connection avec la BD;
     */

    private Connection con;

    public CommandeDAO(String url, String login, String pwd) throws SQLException {
        this.con = SingleConnection.getInstance(url, login, pwd);
    }


    @Override
    public ArrayList getAll() throws SQLException {
        String query = "SELECT * FROM commande";
        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery(query);
        ArrayList<Commande> result = new ArrayList<>();
        int numCommande = -1;
        String libelle ="";
        float poids = -1;
        String horaireDebut = "";
        String horaireFin = "";
        int idClient  = -1;
        int idTournee = -1;
        int numSiret = -1;
        while(resultSet.next()){
            numCommande = resultSet.getInt(1);
            libelle = resultSet.getString(2);
            poids = resultSet.getFloat(3);
            horaireDebut = resultSet.getString(4);
            horaireFin = resultSet.getString(5);
            idClient = resultSet.getInt(6);
            idTournee = resultSet.getInt(7);
            numSiret = resultSet.getInt(8);
            result.add(new Commande(numCommande, libelle, poids, horaireDebut, horaireFin, idClient, idTournee, numSiret));
        }
        return result;
    }


    @Override
    public Commande getById(int numCommande) throws SQLException {
        String query = "SELECT * FROM commande WHERE numCommande=?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, numCommande);
        ResultSet resultSet = pst.executeQuery();
        if(resultSet.next()){
            int numCommandeDB = -1;
            String libelle ="";
            float poids = -1;
            String horaireDebut = "";
            String horaireFin = "";
            int idClient  = -1;
            int idTournee = -1;
            int numSiret = -1;

            numCommande = resultSet.getInt(1);
            libelle = resultSet.getString(2);
            poids = resultSet.getFloat(3);
            horaireDebut = resultSet.getString(4);
            horaireFin = resultSet.getString(5);
            idClient = resultSet.getInt(6);
            idTournee = resultSet.getInt(7);
            numSiret = resultSet.getInt(8);
            return new Commande(numCommande, libelle, poids, horaireDebut, horaireFin, idClient, idTournee, numSiret);
        }
        //TODO : créer exception pour spécifier si on ne trouve pas d'admin avec cet id (exception 1)
        throw new SQLException();
    }

    @Override
    public void add(Object o) throws SQLException {
        if(o instanceof Commande){
            String query2 = "INSERT INTO Commande(libelle, poids, horaireDebut, horaireFin, idClient, idTournee, numSiret) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query2);
            pst.setString(1, ((Commande) o).getLibelle());
            pst.setFloat(2, ((Commande) o).getPoids());
            pst.setString(3, ((Commande) o).getHoraireDebut());
            pst.setString(4, ((Commande) o).getHoraireFin());
            pst.setInt(5, ((Commande) o).getIdClient());
            pst.setInt(6, ((Commande) o).getIdTournee());
            pst.setInt(7, ((Commande) o).getNumSiret());
            pst.executeUpdate();
        }
        //TODO : créer exception pour spécifier que l'objet passé en parametre n'est pas un admin (exception 2)
        else throw new SQLException();
    }

    @Override
    public void update(int numCommande, Object o) throws SQLException {
        if(o instanceof Commande){
            String libelle = ((Commande) o).getLibelle();
            float poids = ((Commande) o).getPoids();
            String horaireDebut = ((Commande) o).getHoraireDebut();
            String horaireFin = ((Commande) o).getHoraireFin();
            int idClient  = ((Commande) o).getIdClient();
            int idTournee = ((Commande) o).getIdTournee();
            int numSiret = ((Commande) o).getNumSiret();
            String query2 = "UPDATE Commande SET libelle=?, poids=?, horaireDebut=?, horaireFin=?, idClient=?, idTournee=?, numSiret=? WHERE numCommande =" + numCommande;
            PreparedStatement pst = con.prepareStatement(query2);
            pst.setString(1, ((Commande) o).getLibelle());
            pst.setFloat(2, ((Commande) o).getPoids());
            pst.setString(3, ((Commande) o).getHoraireDebut());
            pst.setString(4, ((Commande) o).getHoraireFin());
            pst.setInt(5, ((Commande) o).getIdClient());
            pst.setInt(6, ((Commande) o).getIdTournee());
            pst.setInt(7, ((Commande) o).getNumSiret());
            pst.executeUpdate();
        }//TODO : (exception 2)
    }

    @Override
    public void remove(int numCommande) throws SQLException {
        //TODO : (exception 1)
        String query  ="DELETE  FROM Commande WHERE numCommande="+ numCommande;
        Statement st = con.createStatement();
        st.executeUpdate(query);
    }
}
