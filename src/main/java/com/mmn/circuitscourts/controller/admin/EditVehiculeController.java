package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class EditVehiculeController {

    @FXML
    TextField poids;

    @FXML
    Label vehicule;

    public static String numImmat = "";

    public void onBackButton(){
        ViewFactory.getInstance().showAdminVehiculeInterface();
    }

    public Vehicule getThisVehicule() throws SQLException {
        Vehicule v = Vehicule.vehiculeDAO.getById(numImmat);
        return v;
    }

    public void initialize() throws SQLException {
        vehicule.setText(getThisVehicule().getNumImmate());
        poids.setText(String.valueOf(getThisVehicule().getPoidsMax()));
    }

    public void onEditButton() throws SQLException {
        if(parseInt(poids.getText())>0) {
            Vehicule v = new Vehicule(vehicule.getText(), parseInt(poids.getText()));
            Vehicule.vehiculeDAO.update(numImmat, v);
            System.out.println("[DEBUG]Vehicule updated");
            ViewFactory.getInstance().showProdVehiculesInterface();
        }
    }
}
