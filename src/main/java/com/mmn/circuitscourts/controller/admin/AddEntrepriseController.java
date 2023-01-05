package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Proprietaire;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddEntrepriseController {

    @FXML
    TextField adresse, codePostal, ville, numTel, gps, nom, numTelProprio, adresseProprio, codePostalProprio, villeProprio;

    public void onBackButton() {
        ViewFactory.getInstance().showAdminEntrepriseInterface();
    }

    public void onCreateButton() throws SQLException {
        String adresseProprioFormated = adresseProprio.getText() + ":" + codePostalProprio.getText() + ":" + villeProprio.getText();
        String adresseEntrepriseFormated = adresse.getText() + ":" + codePostal.getText() + ":" + ville.getText();
        Proprietaire proprietaire = new Proprietaire(nom.getText(), numTelProprio.getText(), adresseProprioFormated);
        new Entreprise(adresseEntrepriseFormated, proprietaire, numTel.getText(), gps.getText());
        System.out.println("[DEBUG]Entreprise added, make connection");
        ViewFactory.getInstance().showAdminEntrepriseInterface();
    }
}
