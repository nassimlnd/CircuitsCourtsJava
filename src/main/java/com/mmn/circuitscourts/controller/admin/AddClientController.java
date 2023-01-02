package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class AddClientController {
    @FXML
    TextField identifiant;

    @FXML
    PasswordField mdp, confirmMdp;

    @FXML
    Label error;


    /**
     * méthode qui récupère les données saisis dans les champs puis crée un nouveau compte et l'ajoute à la base de données via le constructeur qui ajoutte l'objet à sa création.
     *
     * @throws SQLException
     */
    public void onCreateButton() throws SQLException {
        error.setText("");
        new User(identifiant.getText(), mdp.getText(), 1);
        System.out.println("[DEBUG]Account added");
    }


    /**
     * renvoi vers la page Clients lors qu clique sur le boutton retour.
     */
    public void onBackButton() {
        ViewFactory.getInstance().showAdminClientInterface();
    }
}
