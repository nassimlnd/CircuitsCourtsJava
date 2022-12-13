package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

public class AddTourneesController {

    public void onBackButton(MouseEvent mouseEvent) {
        ViewFactory.getInstance().showAdminTourneeInterface();
    }
}
