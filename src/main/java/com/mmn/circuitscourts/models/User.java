package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.services.AccountDAO;
import com.mmn.circuitscourts.views.ViewFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        this.password = getPasswordHashed(password);
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

    public void setPassword(String password) {
        this.password = password;

        try {
            accountDAO.update(this.id, this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getGrade() {
        return grade;
    }

    public String getGradeName() {
        switch (this.grade) {
            case 1:
                return "Client";
            case 2:
                return "Entreprise";
            case 3:
                return "Administrateur";
            default:
                return "";
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGradeNumber(String grade){
        switch (grade){
            case "Client" :
                return 1;
            case "Entreprise" :
                return 2;
            case "Administrateur":
                return 3;
            default:
                return 1;
        }
    }

    public static String getPasswordHashed(String password) {
        String hashedPassword = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
            hashedPassword = bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return hashedPassword;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void login(String identifiant, String password) throws Exception {
        AccountDAO accountDAO = new AccountDAO();
        try {
            App.userConnected = accountDAO.connect(identifiant, password);
            System.out.println("[DEBUG]User (" + App.userConnected.getIdentifiant() + ", " + App.userConnected.getGrade() +") connected.");
            switch (App.userConnected.getGrade()) {
                case 1:
                    ViewFactory.getInstance().showClientDashboardInterface();
                    break;
                case 2:
                    ViewFactory.getInstance().showProdDashboardInterface();
                    break;
                case 3:
                    ViewFactory.getInstance().showAdminDashboardInterface();
                    break;
                default:
                    throw new Exception("GRADE ERROR");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void setGrade(int grade) {
        this.grade=grade;
    }

}


