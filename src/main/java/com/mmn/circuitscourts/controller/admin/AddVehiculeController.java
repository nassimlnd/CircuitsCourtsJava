package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Producteur;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.services.AccountDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddVehiculeController {
    @FXML
    TextField numImmat, poids;
    @FXML
    ComboBox<String> namesProd;
    public void onBackButton() {
        ViewFactory.getInstance().showAdminVehiculeInterface();
    }


    public void getNomProdInitialize() throws SQLException {

        ArrayList<String> namesAndSiret = User.accountDAO.getAllproducteursNameAndSiret();
        ArrayList<String> names = new ArrayList();
        for (String s : namesAndSiret) {
           names.add(s);
        }
        namesProd.getItems().addAll(names);
    }

    public void initialize() throws SQLException {
        getNomProdInitialize();
    }

    public void onCreateButton() throws SQLException {

        String[] temp =  namesProd.getValue().split("-");
        int newNumSiret = Integer.parseInt(temp[1]);
        Vehicule v = new Vehicule(numImmat.getText(), Integer.parseInt(poids.getText()),newNumSiret);
        System.out.println(numImmat.getText());
        Vehicule.vehiculeDAO.add(v);
        System.out.println("[DEBUG]Vehicule Added.");
        ViewFactory.getInstance().showAdminVehiculeInterface();
    }
}
