package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.services.ClientDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class ProfilController {

    @FXML
    Label labelUsername, labelGrade, identifiant, password, numTel, emailAdress, clientName, deliveryAdress;
    @FXML
    VBox editEmailContainer, editPasswordContainer, editAdresseContainer, editNumTelContainer;
    @FXML
    TextField newEmail, newEmailR, newAdresse, newCodePostal, newCity, newNumTel, newNumTelR;
    @FXML
    PasswordField newPassword, newPasswordR;
    @FXML
    Button btnEditEmail, btnEditPassword, btnEditNumTel, btnEditAdresse;

    public void initialize() throws SQLException {
        labelUsername.setText(App.userConnected.getIdentifiant());
        labelGrade.setText(App.userConnected.getGradeName());
        identifiant.setText(App.userConnected.getIdentifiant());
        password.setText(App.userConnected.getPassword());
        numTel.setText(getClient().getNumTel());
        emailAdress.setText(getClient().getEmail());
        clientName.setText(getClient().getNom());
        deliveryAdress.setText(getClient().getAdresse());

        initTextFields();
    }

    private void initTextFields() {
        newEmail.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.equals("") && t1.equals(newEmailR.getText())) {
                if (Pattern.compile("^(.+)@(\\S+)$").matcher(newEmail.getText()).matches()) {
                    btnEditEmail.setDisable(false);
                }
            } else {
                btnEditEmail.setDisable(true);
            }
        });

        newEmailR.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.equals("") && t1.equals(newEmail.getText())) {
                if (Pattern.compile("^(.+)@(\\S+)$").matcher(newEmail.getText()).matches()) {
                    btnEditEmail.setDisable(false);
                }
            } else {
                btnEditEmail.setDisable(true);
            }
        });

        newPassword.textProperty().addListener(((observableValue, s, t1) -> {
            if (!t1.equals("") && t1.equals(newPasswordR.getText())) {
                btnEditPassword.setDisable(false);
            } else {
                btnEditEmail.setDisable(true);
            }
        }));

        newPasswordR.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.equals("") && t1.equals(newPassword.getText())) {
                btnEditPassword.setDisable(false);
            } else {
                btnEditEmail.setDisable(true);
            }
        });

        newCodePostal.textProperty().addListener(((observableValue, s, t1) -> {
            if (checkCodePostal(t1)) {
                if (!newAdresse.getText().equals("") && checkCity(newCity.getText())) {
                    btnEditAdresse.setDisable(false);
                }
            } else btnEditAdresse.setDisable(true);
        }));

        newCity.textProperty().addListener((observableValue, s, t1) -> {
            if (checkCity(t1)) {
                if (checkCodePostal(newCodePostal.getText()) && !newAdresse.getText().equals("")) {
                    btnEditAdresse.setDisable(false);
                }
            } else btnEditAdresse.setDisable(true);
        });

        newNumTel.textProperty().addListener((observableValue, s, t1) -> {

        });

        newNumTelR.textProperty().addListener((observableValue, s, t1) -> {

        });
    }

    public Client getClient() throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.getByAccountId(App.userConnected.getId());
    }

    public void onNumTelEdit() {
        editNumTelContainer.setVisible(true);
    }

    public void onPasswordEdit() {
        editPasswordContainer.setVisible(true);
    }

    public void onAdresseEdit() {
        editAdresseContainer.setVisible(true);
    }

    public void onEmailEdit() {
        editEmailContainer.setVisible(true);
    }

    public void onCloseEdit() {
        editEmailContainer.setVisible(false);
        editAdresseContainer.setVisible(false);
        editPasswordContainer.setVisible(false);
        editNumTelContainer.setVisible(false);
    }

    public void onEditEmail() {
        if (newEmail.getText().equals(newEmailR.getText())) {
            String email = newEmailR.getText();
            try {
                getClient().setEmail(email);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            newEmail.setText("");
            newEmailR.setText("");
            newEmail.getStyleClass().clear();
            newEmailR.getStyleClass().clear();
            newEmail.getStyleClass().add("text-field");
            newEmailR.getStyleClass().add("text-field");
            emailAdress.setText(email);
            onCloseEdit();
        }
    }

    public void onEditPassword() {
        if (newPassword.getText().equals(newPasswordR.getText())) {
            String passwordHashed = User.getPasswordHashed(newPasswordR.getText());

            App.userConnected.setPassword(passwordHashed);

            newPassword.setText("");
            newPasswordR.setText("");
        }
    }

    public void onEditAdresse() {

    }

    public void onEditNumTel() {

    }

    public boolean checkCodePostal(String codePostal) {
        return Pattern.compile("/^(?:0[1-9]|[1-8]\\d|9[0-8])\\d{3}$/").matcher(codePostal).matches();
    }

    public boolean checkCity(String city) {
        return Pattern.compile("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$").matcher(city).matches();
    }
}
