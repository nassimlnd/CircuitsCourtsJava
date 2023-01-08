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
        User user = new User(1,"magomed","123456",2);
        String hashedPassword = User.getPasswordHashed(user.getPassword());
        assertNotEquals(user.getPassword(), hashedPassword);
        assertEquals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",hashedPassword);
    }

    @Test
    public void testSetPassword() {
        User user = new User(1,"magomed","123456",2);
        String newPassword = "newTestPassword";
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    public void testGetGradeNumber() {
        User user = new User(1,"magomed","123456",2);
        assertEquals(1, user.getGradeNumber("Client"));
        assertEquals(2, user.getGradeNumber("Entreprise"));
        assertEquals(3, user.getGradeNumber("Administrateur"));
        assertEquals(1, user.getGradeNumber("Inconnu")); // par défaut, le grade est "Client"
    }

    @Test
    public void testGetGradeName() {
        User user = new User(1,"magomed","123456",2);
        assertEquals("Entreprise", user.getGradeName());
        user.grade = 2;
        assertEquals("Entreprise", user.getGradeName());
        user.grade = 3;
        assertEquals("Administrateur", user.getGradeName());
        user.grade = 1;
        assertEquals("Client", user.getGradeName()); // pour un grade inconnu, la méthode retourne une chaîne vide
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