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

    /**
     * L'id du compte que l'on veut modifier.
     */
    public static int accountId = 0;
    @FXML
    Label title;
    @FXML
    TextField identifiant, mdp;
    @FXML
    ComboBox<String> grade;

    /**
     * Récupère le compte et ses informations que l'utilisateur veut modifier.
     *
     * @return le compte de l'utilisarteur connecté.
     * @throws SQLException
     */
    public User getThisAccount() throws SQLException {
        return User.accountDAO.getById(accountId);
    }

    /**
     * Renvoi à la page des comptes de l'administrateur lors du clique sur le bouton retour.
     */
    public void onBackButton() {
        ViewFactory.getInstance().showAdminAccountInterface();
    }

    public void initialize() throws SQLException {
        User u = getThisAccount();
        title.setText("Modification du compte n°" + accountId);
        identifiant.setText(u.getIdentifiant());
        mdp.setText(u.getPassword());
        getGradesInitialize();
    }

    /**
     * Initialise dans la ComboBox des grades tous les grades existants, le grade du compte à mofifier est set comme valeur par défaut.
     *
     * @throws SQLException
     */
    public void getGradesInitialize() throws SQLException {
        ArrayList<String> gradesNames = new ArrayList<>();
        gradesNames.add("Client");
        gradesNames.add("Entreprise");
        gradesNames.add("Administrateur");
        grade.setValue(getThisAccount().getGradeName());
        grade.getItems().addAll(gradesNames);
    }

    /**
     * @param grade nom d'un grade.
     * @return le numéro du grade correspondant au texte.
     */
    public int getGradeNumber(String grade) {
        switch (grade) {
            case "Client":
                return 1;
            case "Entreprise":
                return 2;
            case "Administrateur":
                return 3;
            default:
                return 1;
        }
    }

    /**
     * Création d'un nouvel objet compte sans id et update dans la base de donnée.
     *
     * @throws SQLException
     */
    public void onEditButton() throws SQLException {
        if(identifiant.getText().matches("^[a-zA-Z'-]+$")){
            if(mdp.getText().matches("/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/")){
                User u = new User(accountId, identifiant.getText(), mdp.getText(), getGradeNumber(grade.getValue()));
                User.accountDAO.update(accountId, u);
                System.out.println("[DEBUG]User n°" + accountId + " updated.");
                ViewFactory.getInstance().showAdminAccountInterface();
            }else System.out.println("[DEBUG]Eror : au moins 8 caractères, au moins une lettre majuscule, au moins une lettre minuscule, au moins un chiffre, au moins un caractère spécial. ");
        }else System.out.println("[DEBUG]error : nom incorrect");
    }
}
