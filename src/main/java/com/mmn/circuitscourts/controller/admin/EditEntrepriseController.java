package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Proprietaire;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class EditEntrepriseController {
    public static long numSiret = 0;
    @FXML
    TextField adresse, numTel, gps, nom, numTelProprio, adresseProprio, codePostal, city, codePostalEntreprise, villeEntreprise;
    @FXML
    Label title, popupMessage;
    @FXML
    VBox errorPopup;

    public Entreprise getThisEntreprise() throws SQLException {
        Entreprise p = Entreprise.entrepriseDAO.getById(numSiret);
        return p;
    }

    public void initialize() throws SQLException {
        initTextField();
    }

    public void initTextField() throws SQLException {
        title.setText("Modification de l'entreprise n°" + numSiret);
        adresse.setText(getThisEntreprise().getAdresse().split(":")[0]);
        codePostalEntreprise.setText(getThisEntreprise().getAdresse().split(":")[1]);
        villeEntreprise.setText(getThisEntreprise().getAdresse().split(":")[2]);
        numTel.setText(getThisEntreprise().getNumTel());
        gps.setText(getThisEntreprise().getCoordonneesGps());
        Proprietaire p = getThisEntreprise().getProprietaire();
        nom.setText(p.getNom());
        numTelProprio.setText(p.getNumTel());
        adresseProprio.setText(p.getAdresse().split(":")[0]);
        codePostal.setText(p.getAdresse().split(":")[1]);
        city.setText(p.getAdresse().split(":")[2]);
    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminEntrepriseInterface();
    }

    public void onEditButton() throws SQLException {
        if ((numTel.getText()).matches("^\\+?[0-9]{2,3} ?[0-9]{6,}$") && (numTelProprio.getText().matches("^\\+?[0-9]{2,3} ?[0-9]{6,}$"))) {
            if (adresse.getText().matches("^[0-9]{1,5}[a-zA-ZÀ-ÖØ-öø-ÿ \\-.']*$") && adresseProprio.getText().matches("^[0-9]{1,5}[a-zA-ZÀ-ÖØ-öø-ÿ \\-.']*$") && !codePostal.getText().equals("") && !city.getText().equals("")) {
                if (nom.getText().matches("^[a-zA-ZÀ-ÖØ-öø-ÿ]+(([',. -][a-zA-ZÀ-ÖØ-öø-ÿ])?[a-zA-ZÀ-ÖØ-öø-ÿ]*)*$")) {
                    if (gps.getText().matches("^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$")) {
                        Proprietaire proprio = getThisEntreprise().getProprietaire();
                        int numProprietaire = proprio.getId();
                        String fullAdresse = adresse.getText() + ":" + codePostal.getText() + ":" + city.getText();
                        Entreprise p = new Entreprise(getThisEntreprise().getNumSiret(), adresse.getText(), Proprietaire.proprietaireDAO.getById(numProprietaire), numTel.getText(), gps.getText(), getThisEntreprise().getAccountId());
                        Entreprise.entrepriseDAO.update(getThisEntreprise().getNumSiret(), p);
                        System.out.println("[DEBUG]Entreprise n°" + numSiret + " updated.");
                        ViewFactory.getInstance().showAdminEntrepriseInterface();
                        EntrepriseController.showSuccessPopUp("Entreprise modifiée !", "L'entreprise n°" + p.getNumSiret() + " a bien été modifiée !");
                    } else showErrorPopup("Le format des coordonnées GPS est mauvais.");
                } else showErrorPopup("Le nom est invalide");
            } else showErrorPopup("L'adresse est incorrecte ou invalide");
        } else showErrorPopup("Le numéro de téléphone est invalide");
    }

    public void showErrorPopup(String message) {
        errorPopup.setVisible(true);
        popupMessage.setText(message);
    }

    public void onClosePopup() {
        errorPopup.setVisible(false);
    }
}
