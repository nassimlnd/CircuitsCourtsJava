package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Proprietaire;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditEntrepriseController {
    public static long numSiret = 0;
    @FXML
    TextField adresse, numTel, gps, nom, numTelProprio, adresseProprio;
    @FXML
    ComboBox<String> prodNonProprietaire;
    @FXML
    Label title;

    public Entreprise getThisEntreprise() throws SQLException {
        Entreprise p = Entreprise.entrepriseDAO.getById(numSiret);
        return p;
    }

    public void initialize() throws SQLException {
        initTextField();
    }

    public void initTextField() throws SQLException {
        title.setText("Modification de l'entreprise n°" + numSiret);
        adresse.setText(getThisEntreprise().getAdresse());
        numTel.setText(getThisEntreprise().getNumTel());
        gps.setText(getThisEntreprise().getCoordonneesGps());
        Proprietaire p = getThisEntreprise().getProprietaire();
        nom.setText(p.getNom());
        numTelProprio.setText(p.getNumTel());
        adresseProprio.setText(p.getAdresse());
    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminEntrepriseInterface();
    }

    public void onEditButton() throws SQLException {
        if ((numTel.getText()).matches("^\\+?[0-9]{2,3} ?[0-9]{6,}$") && (numTelProprio.getText().matches("^\\+?[0-9]{2,3} ?[0-9]{6,}$"))) {
            if (adresse.getText().matches("^[0-9]{1,5}[a-zA-ZÀ-ÖØ-öø-ÿ \\-.']*$") && adresseProprio.getText().matches("^[0-9]{1,5}[a-zA-ZÀ-ÖØ-öø-ÿ \\-.']*$")) {
                if (nom.getText().matches("^[a-zA-ZÀ-ÖØ-öø-ÿ]+(([',. -][a-zA-ZÀ-ÖØ-öø-ÿ])?[a-zA-ZÀ-ÖØ-öø-ÿ]*)*$")) {
                    if (gps.getText().matches("^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$")) {
                        Proprietaire proprio = getThisEntreprise().getProprietaire();
                        int numProprietaire = proprio.getId();
                        Entreprise p = new Entreprise(getThisEntreprise().getNumSiret(), adresse.getText(), Proprietaire.proprietaireDAO.getById(numProprietaire), numTel.getText(), gps.getText(), getThisEntreprise().getAccountId());
                        Entreprise.entrepriseDAO.update(getThisEntreprise().getNumSiret(), p);
                        System.out.println("[DEBUG]Entreprise n°" + numSiret + " updated.");
                        ViewFactory.getInstance().showAdminEntrepriseInterface();
                        EntrepriseController.showSuccessPopUp("Entreprise modifiée !", "L'entreprise n°" + p.getNumSiret() + " a bien été modifiée !");
                    } else System.out.println("[DEBUG]Error : coordonnées gps de la forme de deux nombres séparés par une virgule, chacun pouvant être précédé d'un signe \"-\" ");
                } else System.out.println("[DEBUG]Error : nom incorrect");
            } else System.out.println("[DEBUG]Error : adresse");
        } else System.out.println("[DEBUG]Eror : numéro de tel format 0601020304");
    }
}
