package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.AccountDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Client extends Personne{
    private int id;

    private int accountId;

    public Client(String nom, String adresse, String numTel) {
        super(nom, adresse, numTel);
    }

    public Client(int id, String nom, String adresse, String numTel, int accountId) {
        super(nom, adresse, numTel);
        this.id = id;
        this.accountId = accountId;
    }

    public static ArrayList<User> getClientsInitialize() throws SQLException {
        AccountDAO account = new AccountDAO();
        ArrayList<User> clients = account.getAllClient();
        System.out.println(clients);
        return clients;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
