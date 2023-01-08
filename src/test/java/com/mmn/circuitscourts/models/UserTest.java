package com.mmn.circuitscourts.models;

import static org.junit.Assert.assertEquals;



import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.services.CommandeDAO;
import javafx.application.Application;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {


    @Test
    public void getPasswordHashed() throws SQLException {
        User user = new User(1, "magomed", "123456", 2);
        String hashedPassword = User.getPasswordHashed(user.getPassword());
        assertNotEquals(user.getPassword(), hashedPassword);
        assertEquals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", hashedPassword);
    }

    @Test
    public void setPassword() {
        User user = new User(1, "magomed", "123456", 2);
        String newPassword = "newTestPassword";
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    public void getGradeNumber() {
        User user = new User(1, "magomed", "123456", 2);
        assertEquals(1, user.getGradeNumber("Client"));
        assertEquals(2, user.getGradeNumber("Entreprise"));
        assertEquals(3, user.getGradeNumber("Administrateur"));
        assertEquals(1, user.getGradeNumber("Inconnu")); // par défaut, le grade est "Client"
    }

    @Test
    public void getGradeName() {
        User user = new User(1, "magomed", "123456", 2);
        assertEquals("Entreprise", user.getGradeName());
        user.setGrade(2);
        assertEquals("Entreprise", user.getGradeName());
        user.setGrade(3);
        assertEquals("Administrateur", user.getGradeName());
        user.setGrade(1);
        assertEquals("Client", user.getGradeName()); // pour un grade inconnu, la méthode retourne une chaîne vide
    }

    @Test
    public void getId() {
        User user = new User(1, "identifiant", "password", 1);
        assertEquals(1, user.getId());
    }

    @Test
    public void setId() {
        User user = new User(1, "identifiant", "password", 1);
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    public void getIdentifiant() {
        User user = new User(1, "identifiant", "password", 1);
        assertEquals("identifiant", user.getIdentifiant());
    }

    @Test
    public void getPassword() {
        User user = new User(1, "identifiant", "password", 1);
        assertEquals("password", user.getPassword());
    }


    @Test
    public void getGrade() {
        User user = new User(1, "identifiant", "password", 1);
        assertEquals(1, user.getGrade());
    }
    @Test
    public void setGrade() {
        User user = new User(1, "identifiant", "password", 1);
        user.setGrade(2);
        assertEquals(2, user.getGrade());
    }


   /* @Test
    public void Login() throws Exception {
       // Application.launch(App.class);
        User user = new User("magomed","123456",2);
        User.login(user.getIdentifiant(), user.getPassword());
        assertNotNull(App.userConnected);
        assertEquals(user.getIdentifiant(), App.userConnected.getIdentifiant());
        assertEquals(User.getPasswordHashed(user.getPassword()), App.userConnected.getPassword());
        assertEquals(user.getGrade(), App.userConnected.getGrade());
    }
    */




}