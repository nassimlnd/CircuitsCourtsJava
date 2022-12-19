package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditVehiculeController {
    @FXML
    ComboBox<String> vehicule;

    @FXML
    TextField poids;

    public static String numImmat = "";

    public void onBackButton(){
        ViewFactory.getInstance().showAdminVehiculeInterface();
    }

    public Vehicule getThisVehicule() throws SQLException {
        Vehicule v = Vehicule.vehiculeDAO.getById(numImmat);
        return v;
    }

    public void getVeichuleInitilaize() throws SQLException {
        ArrayList<Vehicule> vehicules= Vehicule.vehiculeDAO.getAll();
        ArrayList<String> namesVehicule = new ArrayList<>();
        for (Vehicule v: vehicules) {
            namesVehicule.add(v.getNumImmate());
        }
        vehicule.getItems().addAll(namesVehicule);
    }

    public void initialize() throws SQLException {
        getVeichuleInitilaize();
        poids.setText(String.valueOf(getThisVehicule().getPoidsMax()));
    }

    public void onEditButton() throws SQLException {
        Vehicule v = new Vehicule(vehicule.getValue(), Integer.parseInt(poids.getText()));
        Vehicule.vehiculeDAO.update(numImmat, v);
        System.out.println("[DEBUG]Vehicule updated");
        ViewFactory.getInstance().showAdminVehiculeInterface();
    }
}
