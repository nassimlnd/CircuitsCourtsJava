package com.mmn.circuitscourts.controller.admin;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;


public class EditAccountController {

    @FXML
    Label title;
    @FXML
    TextField identifiant, mdp;

    @FXML
    ComboBox<String> grade;

    public static int accountId = 0;

    public User getThisAccount() throws SQLException {
        return User.accountDAO.getById(accountId);
    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminAccountInterface();
    }

    public void initialize() throws SQLException {
        User u = getThisAccount();
        title.setText("Modification du compte n°"+ accountId);
        identifiant.setText(u.getIdentifiant());
        mdp.setText(u.getPassword());
        getGradesInitialize();
    }
    public void getGradesInitialize() throws SQLException {
        ArrayList<String> gradesNames = new ArrayList<>();
        gradesNames.add("Client");gradesNames.add("Producteur");gradesNames.add("Administrateur");
        grade.setValue(getThisAccount().getGradeName());
        grade.getItems().addAll(gradesNames);
    }

    public int getGradeNumber(String grade) {
        switch (grade) {
            case "Client":
                return 1;
            case "Producteur":
                return 2;
            case "Administrateur":
                return 3;
            default:
                return 1;
        }
    }

    public void onEditButton() throws SQLException {
        User u = new User(accountId, identifiant.getText(), mdp.getText(), getGradeNumber(grade.getValue()));
        User.accountDAO.update(accountId, u);
        System.out.println("[DEBUG]User n°"+ accountId+" updated.");
        ViewFactory.getInstance().showAdminAccountInterface();
    }
}
