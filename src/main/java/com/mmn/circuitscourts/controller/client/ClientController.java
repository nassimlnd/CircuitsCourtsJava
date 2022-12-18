package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ClientController {

    @FXML
    Label welcomeLabel;

    public void initialize() {
        welcomeLabel.setText("Bonjour " + App.userConnected.getIdentifiant());
    }

    public void onCommandeButton() {
        ViewFactory.getInstance().showClientCommandesInterface();
    }

    public void onProfilButton() {
        ViewFactory.getInstance().showClientProfilInterface();
    }
}
