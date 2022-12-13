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
    TextField textField;
    @FXML
    PasswordField passwordField;
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
                    if (checkIdInput(passwordField.getText())) {
                        button.setDisable(false);
                        System.out.println("ID : " + textField.getText() + "\nPassword : " + passwordField.getText());
                    }
                } else {
                    button.setDisable(true);
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
                    if (checkPasswordInput(textField.getText())) {
                        button.setDisable(false);
                        System.out.println("ID : " + textField.getText() + "\nPassword : " + passwordField.getText());
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
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    onLogin();
                }
            }
        });
        passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    onLogin();
                }
            }
        });
    }

    public void onLogin() {
        String identifiant = textField.getText();
        String password = passwordField.getText();

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
                    errorLabel.setText("GRADE ERROR.");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorLabel.setText(e.getMessage());
        }
    }

    /*@FXML
    private void switchScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/Dashboard.fxml"));
        Parent root = loader.load();
        Scene scene = button.getScene();
        root.translateXProperty().set(scene.getWidth());

        StackPane parentContainer = (StackPane) button.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            ((Stage) scene.getWindow()).setTitle("CircuitsCourts - Tableau de bord");
            parentContainer.getChildren().remove(container);
        });
        timeline.play();
    }*/

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
