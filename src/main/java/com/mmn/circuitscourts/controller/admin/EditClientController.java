package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.PasswordAuthentication;
import java.sql.SQLException;

public class EditClientController {

    @FXML
    TextField identifiant, mdp;

    @FXML
    Label title;

    public static int accountId = 0;

    public User getThisClient() throws SQLException {
        User u = User.accountDAO.getById(accountId);
        return u;
    }
    public void onBackButton() {
        ViewFactory.getInstance().showAdminClientInterface();
    }

    public void initialize() throws SQLException {
        title.setText("Modification du client n°"+ accountId);
        identifiant.setText(getThisClient().getIdentifiant());
        mdp.setText(getThisClient().getPassword());
    }

    public void onEditButton() throws SQLException {
        User u = new User(accountId, identifiant.getText(), mdp.getText(), 1);
        User.accountDAO.update(accountId, u);
        System.out.println("[DEBUG]account n°"+accountId +" updated.");
        ViewFactory.getInstance().showAdminClientInterface();
    }
}
