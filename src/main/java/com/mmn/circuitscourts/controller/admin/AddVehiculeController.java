package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

public class AddVehiculeController {
    public void onBackButton(MouseEvent mouseEvent) {
        ViewFactory.getInstance().showAdminVehiculeInterface();
    }
}
