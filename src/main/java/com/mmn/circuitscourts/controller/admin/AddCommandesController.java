package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.services.ClientDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller qui gère la vue AddCommande.fxml
 */
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
    ComboBox tournee;
    @FXML
    ComboBox numSiret;

    /**
     * Récupère toutes les informations entrées dans les inputs, et créer une commande avec.
     * Renvoie sur la page des Commandes et affiche la popup correspondantes à la réussite de l'opération
     */
    public void onCreateButton() {
        ViewFactory.getInstance().showAdminCommandeInterface();
        CommandesController.showSuccessPopUp();
    }

    /**
     * Fonction de retour vers la vue précédente.
     */
    public void onBackButton() {
        ViewFactory.getInstance().showAdminCommandeInterface();
    }

    /**
     * Fonction qui initialise les clients dans la comboBox afin de pouvoir selectionner le client correspondant à la commande.
     * @throws SQLException Renvoie une exception si la requête échoue.
     */
    public void clientInitialize() throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> lesClients = clientDAO.getAll();
        ArrayList<String> names = new ArrayList<>();
        for (Client client : lesClients) {
            names.add(client.getId()+"-"+client.getNom());
        }
        client.getItems().addAll(names);
        client.setValue(names.get(0));
    }





}


