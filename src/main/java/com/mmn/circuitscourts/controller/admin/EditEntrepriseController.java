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
    public static int numSiret = 0;
    @FXML
    TextField adresse, numTel, gps;
    @FXML
    ComboBox<String> prodNonProprietaire;
    @FXML
    Label title;

    public Entreprise getThisEntreprise() throws SQLException {
        Entreprise p = Entreprise.entrepriseDAO.getById(numSiret);
        return p;
    }


    public void initialize() throws SQLException {
        title.setText("Modification de l'entreprise n°" + numSiret);
        adresse.setText(getThisEntreprise().getAdresse());
        numTel.setText(getThisEntreprise().getNumTel());
        gps.setText(getThisEntreprise().getCoordonneesGps());

    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminEntrepriseInterface();
    }

    public void onEditButton() throws SQLException {
        Proprietaire proprio = getThisEntreprise().getProprietaire();
        int numProprietaire = proprio.getId();
        Entreprise p = new Entreprise(getThisEntreprise().getNumSiret(), adresse.getText(), Proprietaire.proprietaireDAO.getById(numProprietaire), numTel.getText(), gps.getText(), getThisEntreprise().getAccountId());
        Entreprise.entrepriseDAO.update(getThisEntreprise().getNumSiret(), p);
        System.out.println("[DEBUG]Entreprise n°" + numSiret + " updated.");
        ViewFactory.getInstance().showAdminEntrepriseInterface();
    }
}
