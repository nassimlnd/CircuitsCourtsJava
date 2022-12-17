package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class AddVehiculeController {

    @FXML
    TextField numImmat, poids;

    @FXML
    Button onCreateButton;
    public void onBackButton(MouseEvent mouseEvent) {
        ViewFactory.getInstance().showProdVehiculesInterface();
    }

    public void onCreateButton() throws SQLException {
        int numSiret = Vehicule.getNumSiretConnected();
        Vehicule v = new Vehicule(String.valueOf(numImmat.getText()), Integer.parseInt(poids.getText()), numSiret);
        ViewFactory.getInstance().showProdVehiculesInterface();
        VehiculesController.showSuccessPopUp(v.getNumImmate());
    }
}
