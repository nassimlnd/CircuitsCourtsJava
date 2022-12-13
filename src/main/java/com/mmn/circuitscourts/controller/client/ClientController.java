package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientController {

    @FXML
    Label welcomeLabel;

    public void initialize() {
        welcomeLabel.setText("Bonjour " + App.userConnected.getIdentifiant());
    }

}
