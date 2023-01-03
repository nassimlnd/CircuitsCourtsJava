package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class AddAccountController {
    @FXML
    ComboBox<String> grade;

    @FXML
    TextField identifiant;

    @FXML
    PasswordField mdp, confirmMdp;

    /**
     * Est déclenchée lors du chargement de la page fxml associée à ce controller.
     */
    public void initialize() {
        getGradesInitialize();
    }

    /**
     * Effectue le changement de la page lors d'un clique sur le boutton retour, renvoie à la page Account.fxml
     */
    public void onBackButton() {
        ViewFactory.getInstance().showAdminAccountInterface();
    }

    /**
     * Récupère les valeurs de tous les champs modifiables présents dans la page fxml et crée un utilisateur avec ces valeurs.
     * si le compte est crée renvoi à la page Account.fxml.
     *
     * @throws SQLException
     */
    public void onCreateButton() throws SQLException {
      if(!grade.getValue().equals("") && !identifiant.getText().equals("") && !mdp.getText().equals("") && !confirmMdp.getText().equals("")){
          if (identifiant.getText().matches("^[a-zA-ZÀ-ÖØ-öø-ÿ]+(([',. -][a-zA-ZÀ-ÖØ-öø-ÿ])?[a-zA-ZÀ-ÖØ-öø-ÿ]*)*$")){
              if (mdp.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
                  if (mdp.getText().equals(confirmMdp.getText())){
                      new User(identifiant.getText(), mdp.getText(), getGradeNumber(grade.getValue()));
                      System.out.println("[DEBUG]Account created.");
                      ViewFactory.getInstance().showAdminAccountInterface();
                  }else System.out.println("[DEBUG]Error : les mots de passe sont différents.");
              }else System.out.println("[DEBUG]Eror : au moins 8 caractères, au moins une lettre majuscule, au moins une lettre minuscule, au moins un chiffre, au moins un caractère spécial.");
          }else System.out.println("[DEBUG]Error : nom invalide.");
      }else System.out.println("[DEBUG]Error : Tous les champs sont obligatoires.");
    }

    /**
     * initialisation des tous les grades existants dans la comboBox contenant les grades, est appelée dans la méthode initialize.()
     */
    public void getGradesInitialize() {
        ArrayList<String> gradesNames = new ArrayList<>();
        gradesNames.add("Client");
        gradesNames.add("Entreprise");
        gradesNames.add("Administrateur");
        grade.setValue(gradesNames.get(0));
        grade.getItems().addAll(gradesNames);
    }

    /**
     * méthode associant un numéro en fonction du nom du grade, les grades correspondent à des numéros dans la base de données.
     *
     * @param grade
     * @return
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
}
