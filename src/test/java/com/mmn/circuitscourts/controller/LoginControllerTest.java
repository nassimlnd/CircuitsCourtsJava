package com.mmn.circuitscourts.controller;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.MainApp;
import javafx.application.Application;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.engine.extension.*;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @BeforeEach
    void setUp() throws SQLException, IOException {
        //MainApp.main();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initialize() {
    }

    @Test
    void onLogin() {


        LoginController loginController = new LoginController();
        loginController.textField = new TextField("admin");
        loginController.passwordField = new PasswordField();
        loginController.passwordField.setText("1234");
        loginController.onLogin();

        assertEquals(App.userConnected.getIdentifiant(), "admin");


    }

    @Test
    void checkIdInput() {
    }

    @Test
    void checkPasswordInput() {
    }
}