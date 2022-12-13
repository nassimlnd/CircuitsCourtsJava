package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

public class AddVehiculeController {

    public void onBackButton(MouseEvent mouseEvent) {
        ViewFactory.getInstance().showAdminVehiculeInterface();
    }
}
