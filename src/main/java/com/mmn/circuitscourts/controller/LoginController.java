package com.mmn.circuitscourts.controller;

import com.mmn.circuitscourts.App;
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
    public void initialize() {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (checkInput(t1)) {
                    textField.getStyleClass().clear();
                    textField.getStyleClass().add("login-input");
                    if (checkInput(passwordField.getText())) {
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
                if (checkInput(t1)) {
                    passwordField.getStyleClass().clear();
                    passwordField.getStyleClass().add("password-input");
                    if (checkInput(textField.getText())) {
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
                    try {
                        switchScene();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void onLogin() {
        ViewFactory.getInstance().showProducteurInterface();
    }

    @FXML
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
    }

    public boolean checkInput(String value) {
        if (value.length() > 5 && !value.equals("")) {
            return true;
        } else return false;
    }
}
