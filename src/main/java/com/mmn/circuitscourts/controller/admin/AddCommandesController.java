package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.views.ViewFactory;

public class AddCommandesController {

    public void onCreateButton() {
        ViewFactory.getInstance().showAdminCommandeInterface();
        CommandeController.showSuccessPopUp();
    }
    public void onBackButton(){
        ViewFactory.getInstance().showAdminCommandeInterface();
    }
}
