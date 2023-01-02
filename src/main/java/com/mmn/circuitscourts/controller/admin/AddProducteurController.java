package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Producteur;
import com.mmn.circuitscourts.models.Proprietaire;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddProducteurController {

    @FXML
    TextField adresse, numTel, gps, nom, numTelProprio, adresseProprio;

    public void onBackButton() {
        ViewFactory.getInstance().showAdminProducteurInterface();
    }

    public void onCreateButton() throws SQLException {
        Proprietaire proprietaire = new Proprietaire(nom.getText(), numTelProprio.getText(), adresseProprio.getText());
        new Producteur(adresse.getText(), proprietaire, numTel.getText(), gps.getText());
        System.out.println("[DEBUG]producteur added, make connection");
        ViewFactory.getInstance().showAdminProducteurInterface();
    }
}
