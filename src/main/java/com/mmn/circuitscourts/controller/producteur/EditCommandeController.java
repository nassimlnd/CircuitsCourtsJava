package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

public class EditCommandeController {

    public static int commandeId = 0;

    public void initialize() {

    }

    public void onBackButton() {
        ViewFactory.getInstance().showProdCommandesInterface();
    }
}
