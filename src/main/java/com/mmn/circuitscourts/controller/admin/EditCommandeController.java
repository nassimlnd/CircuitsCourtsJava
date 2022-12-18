package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

public class EditCommandeController {
    public static int commandeId = 0;

    public void initialize() {
        ViewFactory.getInstance().showAdminCommandeInterface();
    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminCommandeInterface();
    }
}
