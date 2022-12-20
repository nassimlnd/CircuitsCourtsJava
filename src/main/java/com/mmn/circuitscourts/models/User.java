package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.services.AccountDAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe réprésentant un utilisateur donc un account dans la base de donneés.
 */
public class User {

    private int id;
    private String identifiant;
    private String password;
    private int grade;

    public static AccountDAO accountDAO = new AccountDAO();

    public User(String identifiant, String password, int grade) throws SQLException {
        this.identifiant = identifiant;
        this.password = password;
        this.grade = grade;

        AccountDAO accountDAO = new AccountDAO();
        this.id = accountDAO.add(this);
    }

    public User(int id, String identifiant, String password, int grade) {
        this.id = id;
        this.identifiant = identifiant;
        this.password = password;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getPassword() {
        return password;
    }

    public int getGrade() {
        return grade;
    }

    public String getGradeName() {
        switch (this.grade) {
            case 1:
                return "Client";
            case 2:
                return "Producteur";
            case 3:
                return "Administrateur";
            default:
                return "";
        }
    }

    public void setId(int id) {
        this.id = id;
    }

}


