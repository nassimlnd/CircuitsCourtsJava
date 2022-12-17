package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

public class CommandeInfoController {
    public static int commandeId = 0;

    public void initialize() {

    }

    public void onBackButton() {
        ViewFactory.getInstance().showClientCommandesInterface();
    }
}
