package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.AccountDAO;
import com.mmn.circuitscourts.services.ClientDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Client extends Personne{
    private int id;
    private int accountId;
    private String email;

    public static ClientDAO client = new ClientDAO();

    public Client(String nom, String adresse, String numTel, String email) {
        super(nom, adresse, numTel);
        this.email = email;

        try {
            this.id = client.add(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client(int id, String nom, String adresse, String numTel, String email, int accountId) {
        super(nom, adresse, numTel);
        this.id = id;
        this.accountId = accountId;
        this.email = email;
    }

    public Client(int id, String nom, String adresse, String numTel, String email) {
        super(nom, adresse, numTel);
        this.id = id;
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

        try {
            client.update(this.id, this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
