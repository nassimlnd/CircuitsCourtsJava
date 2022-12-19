package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.services.ClientDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ProfilController {

    @FXML
    Label labelUsername;
    @FXML
    Label labelGrade;

    @FXML
    Label identifiant;
    @FXML
    Label password;
    @FXML
    Label numTel;
    @FXML
    Label clientName;

    public void initialize() throws SQLException {
        labelUsername.setText(App.userConnected.getIdentifiant());
        labelGrade.setText(App.userConnected.getGradeName());

        identifiant.setText(App.userConnected.getIdentifiant());
        password.setText(App.userConnected.getPassword());
        numTel.setText(getClient().getNumTel());
        clientName.setText(getClient().getNom());
    }

    public Client getClient() throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.getByAccountId(App.userConnected.getId());
    }

    public void onBrowse() {

    }
}
