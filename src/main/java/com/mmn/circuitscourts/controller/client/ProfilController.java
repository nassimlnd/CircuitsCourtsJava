package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfilController {

    @FXML
    Label labelUsername;
    @FXML
    Label labelGrade;

    public void initialize() {
        labelUsername.setText(App.userConnected.getIdentifiant());
        labelGrade.setText(App.userConnected.getGradeName());
    }
}
