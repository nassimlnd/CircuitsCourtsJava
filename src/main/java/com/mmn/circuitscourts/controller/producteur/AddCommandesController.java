package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.views.ViewFactory;

public class AddCommandesController {

    public void onCreateButton() {

    }

    public void onBackButton() {
        ViewFactory.getInstance().showProdCommandesInterface();
    }
}
