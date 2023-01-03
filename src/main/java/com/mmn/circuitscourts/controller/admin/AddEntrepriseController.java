package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Proprietaire;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddEntrepriseController {

    @FXML
    TextField adresse, numTel, gps, nom, numTelProprio, adresseProprio;

    public void onBackButton() {
        ViewFactory.getInstance().showAdminEntrepriseInterface();
    }

    public void onCreateButton() throws SQLException {
        Proprietaire proprietaire = new Proprietaire(nom.getText(), numTelProprio.getText(), adresseProprio.getText());
        new Entreprise(adresse.getText(), proprietaire, numTel.getText(), gps.getText());
        System.out.println("[DEBUG]Entreprise added, make connection");
        ViewFactory.getInstance().showAdminEntrepriseInterface();
    }
}
