package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class EditVehiculeController {

    public static String numImmat = "";
    @FXML
    Label title, vehicule, popupMessage;
    @FXML
    TextField poids;
    @FXML
    ComboBox<String> entreprisesCb;
    @FXML
    VBox errorPopup;

    public void onBackButton() {
        ViewFactory.getInstance().showAdminVehiculeInterface();
    }

    public Vehicule getThisVehicule() {
        Vehicule v = null;
        try {
            v = Vehicule.vehiculeDAO.getById(numImmat);
        } catch (SQLException e) {
            showErrorPopup("Une erreur est survenue lors du chargement du véhicule ! \n(SQL ERROR)");
        }
        return v;
    }

    public void initialize() {
        title.setText("Modification du véhicule n°" + numImmat);
        vehicule.setText(getThisVehicule().getNumImmat());
        poids.setText(String.valueOf(getThisVehicule().getPoidsMax()));
    }

    public void onEditButton() {
        if (Float.parseFloat(poids.getText()) <= 0) {
            showErrorPopup("Le poids doit être supérieur à 0 !");
            return;
        }

        if (entreprisesCb.getValue() == null) {
            showErrorPopup("Une entreprise propriétaire doit être sélectionner !");
            return;
        }

        Vehicule v = null;
        try {
            v = new Vehicule(vehicule.getText(), Float.parseFloat(poids.getText()), Integer.valueOf(entreprisesCb.getValue().split("-")[0]));
            Vehicule.vehiculeDAO.update(numImmat, v);
            System.out.println("[DEBUG]Vehicule updated");
            ViewFactory.getInstance().showAdminVehiculeInterface();
        } catch (SQLException e) {
            showErrorPopup("Une erreur a eu lieu lors de la modification du véhicule !\n(SQL ERROR)");
        }
    }

    public void showErrorPopup(String message) {
        popupMessage.setText(message);
        errorPopup.setVisible(true);
    }

    public void onClosePopup() {
        errorPopup.setVisible(false);
    }
}
