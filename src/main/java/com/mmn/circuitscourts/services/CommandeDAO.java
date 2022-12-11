package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Commande;

import java.sql.*;
import java.util.ArrayList;

public class CommandeDAO implements DAO<Commande>{
    /**
     * @param con, connection avec la BD;
     */
    private Connection con;

    public CommandeDAO() throws SQLException {
        this.con = ConnectionMySQL.getInstance();
    }

    @Override
    public ArrayList<Commande> getAll() throws SQLException {
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
    public boolean add(Commande o) throws SQLException {
        boolean execute = false;
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
            return execute = true;
        }
        //TODO : créer exception pour spécifier que l'objet passé en parametre n'est pas un admin (exception 2)
        else return execute;
    }

    @Override
    public boolean update(int numCommande, Commande o) throws SQLException {
        boolean execute = false;
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
            return execute = false;
        }//TODO : (exception 2)
        return execute;
    }

    @Override
    public boolean remove(int numCommande) throws SQLException {
        //TODO : (exception 1)
        boolean execute = false;
        String query  ="DELETE  FROM Commande WHERE numCommande="+ numCommande;
        Statement st = con.createStatement();
        st.executeUpdate(query);
        return execute = true;
    }

    public int countById() throws Exception {
        String query = "SELECT COUNT(*) FROM commande INNER JOIN producteur ON commande.numSiret = producteur.numSiret INNER JOIN accounts ON producteur.accountId = accounts.accountId WHERE accounts.accountId = " + App.userConnected.getId();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else return 0;
    }
}
