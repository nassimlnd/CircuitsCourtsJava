package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class AddClientController {
    @FXML
    TextField nom, email, numTel, adresse, codePostal, city;
    @FXML
    VBox errorPopup;
    @FXML
    Label popupMessage;

    /**
     * Fonction qui récupère les données saisis dans les champs puis crée un nouveau compte et l'ajoute à la base de données via le constructeur qui ajoutte l'objet à sa création.
     *
     */
    public void onCreateButton() {
        String adresseFormated = adresse.getText() + ":" + codePostal.getText() + ":" + city.getText();
        Client client = new Client(nom.getText(), adresseFormated, numTel.getText(), email.getText());
        System.out.println("[DEBUG]Client added");
        ViewFactory.getInstance().showAdminClientInterface();
        ClientController.showSuccessPopUp(client.getId());
    }

    /**
     * Renvoi vers la page Clients lorsque l'on clique sur le bouton retour.
     */
    public void onBackButton() {
        ViewFactory.getInstance().showAdminClientInterface();
    }

    private void showErrorPopup(String message) {
        popupMessage.setText(message);
        errorPopup.setVisible(true);
    }

    public void onClosePopup() {
        errorPopup.setVisible(false);
    }
}
