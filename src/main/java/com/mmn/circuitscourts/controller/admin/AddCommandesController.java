package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddCommandesController {

    @FXML
    TextField article;

    @FXML
    TextField quantite;

    @FXML
    ComboBox client;

    @FXML
    TextField horaire;

    @FXML
    DatePicker datePicker;

    @FXML
    Button cmdButton;

    @FXML
    ComboBox tournee;

    @FXML
    ComboBox numSiret;

    public void onCreateButton() {
        ViewFactory.getInstance().showAdminCommandeInterface();
        CommandeController.showSuccessPopUp();
    }

    public void onBackButton() {

        ViewFactory.getInstance().showAdminCommandeInterface();
    }

    public void clientInitialize() throws SQLException {
        ArrayList<User> lesClients = Client.getClientsInitialize();
        ArrayList<String> names = new ArrayList<>();
        for (User u : lesClients) {
            names.add(u.getId()+"-"+u.getIdentifiant());
        }
        System.out.println(names);
        client.getItems().addAll(names);
        client.setValue(names.get(0));
    }



}


