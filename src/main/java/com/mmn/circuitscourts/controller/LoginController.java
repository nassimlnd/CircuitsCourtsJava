package com.mmn.circuitscourts.controller;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.services.AccountDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField textField;
    @FXML
    public PasswordField passwordField;
    @FXML
    private Button button;
    @FXML
    private VBox container;
    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (checkIdInput(t1)) {
                    textField.getStyleClass().clear();
                    textField.getStyleClass().add("login-input");
                    if (checkPasswordInput(passwordField.getText())) {
                        button.setDisable(false);
                        //System.out.println("ID : " + textField.getText() + "\nPassword : " + passwordField.getText());
                    }
                } else {
                    button.setDisable(true);
                    textField.getStyleClass().clear();
                    textField.getStyleClass().add("login-input");
                    textField.getStyleClass().add("login-input-error");
                }
            }
        });

        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (checkPasswordInput(t1)) {
                    passwordField.getStyleClass().clear();
                    passwordField.getStyleClass().add("password-input");
                    if (checkIdInput(textField.getText())) {
                        button.setDisable(false);
                        //System.out.println("ID : " + textField.getText() + "\nPassword : " + passwordField.getText());
                    }
                } else {
                    button.setDisable(true);
                    passwordField.getStyleClass().clear();
                    passwordField.getStyleClass().add("password-input");
                    passwordField.getStyleClass().add("password-input-error");
                }
            }
        });

        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER && checkIdInput(textField.getText()) && checkPasswordInput(passwordField.getText())) {
                    onLogin();
                }
            }
        });
        passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER && checkIdInput(textField.getText()) && checkPasswordInput(passwordField.getText())) {
                    onLogin();
                }
            }
        });
    }

    public void onLogin() {
        String identifiant = textField.getText();
        String password = User.getPasswordHashed(passwordField.getText());

        try {
            User.login(identifiant, password);
        } catch (Exception e) {
            errorLabel.setText(e.getMessage().split(":")[1]);
        }
    }

    /**
     * Vérifie que l'identifiant a une taille supérieure ou égale à 4, qu'il n'est pas vide et qu'il ne contient pas d'espaces
     * @param value l'identifiant à vérifier
     * @return true si l'identifiant est valide et false sinon
     */
    public boolean checkIdInput(String value) {
        if (value.length() >= 4 && !value.equals("") && !value.contains(" ")) {
            return true;
        } else return false;
    }

    /**
     * Vérifie que la mot de passe a une taille supérieure ou égale à 4, qu'il n'est pas vide et qu'il ne contient pas d'espaces
     * @param value le mot de passe à vérifier
     * @return true si le mot de passe est valide et false sinon
     */
    public boolean checkPasswordInput(String value) {
        // TODO: Change the value of the minimal number of characters
        if (value.length() >= 4 && !value.equals("") && !value.contains(" ")) {
            return true;
        } else return false;
    }
}
