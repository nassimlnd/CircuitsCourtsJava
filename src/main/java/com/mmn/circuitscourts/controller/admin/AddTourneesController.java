package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AddTourneesController {

    public void onBackButton() {
        ViewFactory.getInstance().showAdminTourneeInterface();
    }
}
