package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

public class AddCommandesProducteur {
    public void onBackButton(){
        ViewFactory.getInstance().showAdminCommandeInterface();
    }
}
