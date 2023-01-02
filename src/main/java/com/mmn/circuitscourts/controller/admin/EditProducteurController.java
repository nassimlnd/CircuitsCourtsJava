package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Producteur;
import com.mmn.circuitscourts.models.Proprietaire;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditProducteurController {
    public static int numSiret = 0;
    @FXML
    TextField adresse, numTel, gps;
    @FXML
    ComboBox<String> prodNonProprietaire;
    @FXML
    Label title;

    public Producteur getThisProducteur() throws SQLException {
        Producteur p = Producteur.producteurDAO.getBynumSiret(numSiret);
        return p;
    }


    public void initialize() throws SQLException {
        title.setText("Modification du producteur n°" + numSiret);
        adresse.setText(getThisProducteur().getAdresse());
        numTel.setText(getThisProducteur().getNumTel());
        gps.setText(getThisProducteur().getCoordonneesGps());

    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminProducteurInterface();
    }

    public void onEditButton() throws SQLException {
        Proprietaire proprio = getThisProducteur().getProprietaire();
        int numProprietaire = proprio.getId();
        Producteur p = new Producteur(getThisProducteur().getNumSiret(), adresse.getText(), Proprietaire.proprietaireDAO.getById(numProprietaire), numTel.getText(), gps.getText(), getThisProducteur().getAccountId());
        Producteur.producteurDAO.update(getThisProducteur().getNumSiret(), p);
        System.out.println("[DEBUG] producteur n°" + numSiret + " updated.");
        ViewFactory.getInstance().showAdminProducteurInterface();
    }
}
