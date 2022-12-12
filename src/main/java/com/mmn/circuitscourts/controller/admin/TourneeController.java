package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

public class TourneeController {

    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddTourneeInterface();
    }
}
