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


    public void onCreateButton() throws SQLException {
        error.setText("");
        if(!(identifiant.getText().isEmpty() && mdp.getText().isEmpty() && confirmMdp.getText().isEmpty())){
            if(Objects.equals(mdp.getText(), confirmMdp.getText())){
                User u  = new User(identifiant.getText(), mdp.getText(), 1);
                System.out.println("[DEBUG]Account added");
            }
            error.setText("Mauvaise confirmation du mot de passe !");
        }
        error.setText("tous les champs ne sont pas remplis !");


    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminClientInterface();
    }
}
