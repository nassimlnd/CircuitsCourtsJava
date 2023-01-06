package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public void initialize() {
        initFields();
    }

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

    public void initFields() {
        title.setText("Modification du véhicule n°" + numImmat);
        vehicule.setText(getThisVehicule().getNumImmat());
        poids.setText(String.valueOf(getThisVehicule().getPoidsMax()));

        ArrayList<Entreprise> entreprises = null;
        try {
            entreprises = Entreprise.entrepriseDAO.getAll();
            ArrayList<String> values = new ArrayList<>();
            entreprises.forEach(entreprise -> values.add(entreprise.getNumSiret() + "-" + entreprise.getProprietaire().getNom()));
            entreprisesCb.getItems().addAll(values);
            entreprisesCb.setValue(getThisVehicule().getNumSiret() + "-" + Entreprise.entrepriseDAO.getById(getThisVehicule().getNumSiret()).getProprietaire().getNom());
        } catch (SQLException e) {
            showErrorPopup("Une erreur est survenue lors du chargement du véhicule ! \n(SQL ERROR)");
        }
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
            VehiculeController.showSuccessPopUp("Véhicule modifié !", "Le véhicule immatriculé : " + v.getNumImmat() + " a bien \nété modifié !");
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
