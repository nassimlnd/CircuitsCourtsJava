package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

public class AddArticlesController {
    public void onBackButton() {
        ViewFactory.getInstance().showProdArticlesInterface();
    }
}
