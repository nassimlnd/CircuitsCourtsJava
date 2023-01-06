package com.mmn.circuitscourts.controller.entreprise;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class EditVehiculeController {

    public static String numImmat;

    @FXML
    TextField poids;
    @FXML
    Label vehicule, popupMessage, title;
    @FXML
    VBox errorPopup;

    public void initialize() throws SQLException {
        initFields();
    }

    public void onBackButton(){
        ViewFactory.getInstance().showProdVehiculesInterface();
    }

    public Vehicule getThisVehicule() throws SQLException {
        Vehicule v = Vehicule.vehiculeDAO.getById(numImmat);
        return v;
    }

    public void initFields() {
        title.setText("Modification du véhicule n°" + numImmat);
        try {
            vehicule.setText(getThisVehicule().getNumImmat());
            poids.setText(String.valueOf(getThisVehicule().getPoidsMax()));
        } catch (SQLException e) {
            showErrorPopup("Une erreur est survenue lors du chargement du véhicule ! \n(SQL ERROR)");
        }
    }
    public void onEditButton() {
        if(poids.getText().equals("") || Float.parseFloat(poids.getText()) <= 0) {
            showErrorPopup("Le poids doit être supérieur à 0");
            return;
        }

        Vehicule v = null;
        try {
            v = new Vehicule(vehicule.getText(), Float.parseFloat(poids.getText()), Entreprise.entrepriseDAO.getByAccountId(App.userConnected.getId()).getNumSiret());
            Vehicule.vehiculeDAO.update(numImmat, v);
            System.out.println("[DEBUG]Vehicule updated");
            ViewFactory.getInstance().showProdVehiculesInterface();
            VehiculesController.showSuccessPopUp("Véhicule modifié !", "Le véhicule immatriculé : " + v.getNumImmat() + " a bien été modifié !");
        } catch (SQLException e) {
            showErrorPopup("Une erreur est survenue lors de la modification du véhicule. \n(SQL ERROR)");
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
