package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.Objects;

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
        if (!(identifiant.getText().isEmpty() && mdp.getText().isEmpty() && confirmMdp.getText().isEmpty())) {
            if (Objects.equals(mdp.getText(), confirmMdp.getText())) {
                new User(identifiant.getText(), mdp.getText(), 1);
                System.out.println("[DEBUG]Account added");
            }
            error.setText("Mauvaise confirmation du mot de passe !");
        }
        error.setText("tous les champs ne sont pas remplis !");


    }


    /**
     * renvoi vers la page Clients lors qu clique sur le boutton retour.
     */
    public void onBackButton() {
        ViewFactory.getInstance().showAdminClientInterface();
    }
}
