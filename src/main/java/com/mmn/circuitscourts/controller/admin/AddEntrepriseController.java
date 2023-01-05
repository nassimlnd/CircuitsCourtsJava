package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Client;
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
        if ((numTel.getText()).matches("^\\+?[0-9]{2,3} ?[0-9]{6,}$") && (numTelProprio.getText().matches("^\\+?[0-9]{2,3} ?[0-9]{6,}$"))){
            if(ville.getText().matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$") && (villeProprio.getText().matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$"))) {
                if(codePostal.getText().matches("^(F-)?((2[A|B])|[0-9]{2})[0-9]{3}$") && (codePostalProprio.getText().matches("^(F-)?((2[A|B])|[0-9]{2})[0-9]{3}$"))){
                    if(adresse.getText().matches("^[0-9]{1,5}[a-zA-ZÀ-ÖØ-öø-ÿ \\-.']*$") && adresseProprio.getText().matches("^[0-9]{1,5}[a-zA-ZÀ-ÖØ-öø-ÿ \\-.']*$")){
                        if(nom.getText().matches("^[a-zA-ZÀ-ÖØ-öø-ÿ]+(([',. -][a-zA-ZÀ-ÖØ-öø-ÿ])?[a-zA-ZÀ-ÖØ-öø-ÿ]*)*$")) {
                            if (gps.getText().matches("^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$")){
                                Proprietaire p = new Proprietaire(nom.getText(), adresseProprio.getText(), numTelProprio.getText());
                                new Entreprise(adresse.getText(), p, numTel.getText(), gps.getText());
                                System.out.println("[DEBUG]Entreprise created.");
                                ViewFactory.getInstance().showAdminEntrepriseInterface();
                            }else System.out.println("[DEBUG]Error : gps de la forme de deux nombres séparés par une virgule, chacun pouvant être précédé d'un signe \"-\" ");
                        }else System.out.println("[DEBUG]Error : nom incorrect");
                    }else System.out.println("[DEBUG]Error : adresse de la forme '20 rue des platanes'.");
                }else System.out.println("[DEBUG]Error : code postal, de la forme F-75000 ou 75000");
            }else System.out.println("[DEBUG]Error : city name");
        }else System.out.println("[DEBUG]Eror : numéro de tel format 0601020304");
    }
}
