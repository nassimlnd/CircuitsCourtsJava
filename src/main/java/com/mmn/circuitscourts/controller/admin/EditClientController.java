package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditClientController {

    /**
     * id du compte à modifier.
     */
    public static int clientId = 0;

    @FXML
    Label title;
    @FXML
    TextField nom, email, numTel, adresse, codePostal, city;

    /**
     * Récupère le compte à modifier.
     *
     * @return
     * @throws SQLException
     */
    public Client getThisClient() {
        try {
            return Client.client.getById(clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminClientInterface();
    }

    /**
     * Préremplit les champs des informations du compe à modifier.
     *
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        title.setText("Modification du client n°" + clientId);

        textFieldInit();
    }

    /**
     * Initialise tous les textFields avec les valeurs actuelles du client
     */
    public void textFieldInit() {
        nom.setText(getThisClient().getNom());
        email.setText(getThisClient().getEmail());
        numTel.setText(getThisClient().getNumTel());

        adresse.setText(getThisClient().getAdresse().split(":")[0]);
        codePostal.setText(getThisClient().getAdresse().split(":")[1]);
        city.setText(getThisClient().getAdresse().split(":")[2]);
    }

    /**
     * Crée un nouveau compte sans id et update avec ce nouveau compte.
     * Renvoi à la page précédente une fois l'update effectuée.
     *
     * @throws SQLException
     */
    public void onEditButton() throws SQLException {
        String adresseFormated = adresse.getText() + ":" + codePostal.getText() + ":" + city.getText();
        Client client = new Client(clientId, nom.getText(),  adresseFormated, numTel.getText(), email.getText());
        Client.client.update(clientId, client);
        System.out.println("[DEBUG]account n°" + clientId + " updated.");
        ViewFactory.getInstance().showAdminClientInterface();
    }
}
