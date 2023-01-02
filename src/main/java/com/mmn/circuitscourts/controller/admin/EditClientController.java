package com.mmn.circuitscourts.controller.admin;

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
    public static int accountId = 0;
    @FXML
    TextField identifiant, mdp;
    @FXML
    Label title;

    /**
     * Récupère le compte à modifier.
     *
     * @return
     * @throws SQLException
     */
    public User getThisClient() throws SQLException {
        User u = User.accountDAO.getById(accountId);
        return u;
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
        title.setText("Modification du client n°" + accountId);
        identifiant.setText(getThisClient().getIdentifiant());
        mdp.setText(getThisClient().getPassword());
    }

    /**
     * Crée un nouveau compte sans id et update avec ce nouveau compte.
     * Renvoi à la page précédente une fois l'update effectuée.
     *
     * @throws SQLException
     */
    public void onEditButton() throws SQLException {
        User u = new User(accountId, identifiant.getText(), mdp.getText(), 1);
        User.accountDAO.update(accountId, u);
        System.out.println("[DEBUG]account n°" + accountId + " updated.");
        ViewFactory.getInstance().showAdminClientInterface();
    }
}
