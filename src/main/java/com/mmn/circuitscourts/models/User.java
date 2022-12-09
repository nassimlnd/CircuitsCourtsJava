package com.mmn.circuitscourts.models;

/**
 * Classe réprésentant un utilisateur donc un account dans la base de donneés.
 */
public class User {

    private int id;
    private String identifiant;
    private String password;
    private int grade;

    public User(String identifiant, String password, int grade) {
        this.identifiant = identifiant;
        this.password = password;
        this.grade = grade;
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

    public void setId(int id) {
        this.id = id;
    }
}
