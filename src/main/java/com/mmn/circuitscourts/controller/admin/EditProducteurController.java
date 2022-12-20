package com.mmn.circuitscourts.controller.admin;
import com.mmn.circuitscourts.models.Producteur;
import com.mmn.circuitscourts.models.Proprietaire;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditProducteurController {
    @FXML
    TextField adresse, numTel, gps;

    @FXML
    ComboBox<String> proprietaire;

    public static int numSiret = 0;

    public Producteur getThisProducteur() throws SQLException {
       Producteur p = Producteur.producteurDAO.getBynumSiret(numSiret);
       return p;
    }

    public void proprietaireIntialize() throws SQLException {
        ArrayList<Proprietaire> proprietaires = Proprietaire.proprietaireDAO.getAll();
        ArrayList<String> propNames = new ArrayList<>();
        for (Proprietaire p: proprietaires) {
            propNames.add(p.getId()+"-"+p.getNom());
        }
        proprietaire.getItems().addAll(propNames);
    }

    public void initialize() throws SQLException {
        adresse.setText(getThisProducteur().getAdresse());
        numTel.setText(getThisProducteur().getNumTel());
        gps.setText(getThisProducteur().getCoordonneesGps());

    }

    public void onBackButton(MouseEvent mouseEvent) {
        ViewFactory.getInstance().showAdminProducteurInterface();
    }

    public void onEditButton(MouseEvent mouseEvent) {
        int idPropietaire = Integer.parseInt(proprietaire.getValue().split("-")[0]);
        //Producteur p = new Producteur()
    }
}
