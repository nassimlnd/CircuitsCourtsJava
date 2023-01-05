package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Administrateur;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddAccountController {
    @FXML
    ComboBox<String> grade, entityCb;
    @FXML
    VBox entityContainer, errorPopup;
    @FXML
    TextField identifiant;
    @FXML
    PasswordField mdp, confirmMdp;
    @FXML
    Label popupMessage;

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
        if (!(grade.getValue() == null) && !identifiant.getText().equals("") && !mdp.getText().equals("") && !confirmMdp.getText().equals("")) {
            if (grade.getValue() == "Client" || grade.getValue() == "Entreprise") {
                if (entityCb.getValue() == null) {
                    showErrorPopup("Erreur lors de la création du compte", "Vous devez remplir tous les champs !");
                    return;
                }
            }

            if (!identifiant.getText().matches("^[a-zA-ZÀ-ÖØ-öø-ÿ]+(([',. -][a-zA-ZÀ-ÖØ-öø-ÿ])?[a-zA-ZÀ-ÖØ-öø-ÿ]*)*$")) {
                showErrorPopup("Erreur lors de la création du compte", "L'identifiant entré est invalide.");
                return;
            }

            if (!mdp.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*])(?=\\S+$).{8,}$")) {
                showErrorPopup("Erreur lors de la création du compte", "Le mot de passe doit contenir au moins\n8 caractères, une lettre majuscule, \nune lettre minuscule, un chiffre, et un caractère spécial.");
                return;
            }

            if (!mdp.getText().equals(confirmMdp.getText())) {
                showErrorPopup("Erreur lors de la création du compte", "Les mots de passes saisis sont différents.");
                return;
            }

            if (!User.accountDAO.exists(identifiant.getText())) {
                User user = new User(identifiant.getText(), mdp.getText(), getGradeNumber(grade.getValue()));
                switch (grade.getValue()) {
                    case "Client":
                        Client.client.getById(Integer.valueOf(entityCb.getValue().split("-")[0])).setAccountId(user.getId());
                        break;
                    case "Entreprise":
                        Entreprise.entrepriseDAO.getById(Integer.valueOf(entityCb.getValue().split("-")[0])).setAccountId(user.getId());
                        break;
                    case "Administrateur":
                        break;
                }
                System.out.println("[DEBUG]Account created.");
                ViewFactory.getInstance().showAdminAccountInterface();
            } else showErrorPopup("Erreur lors de la création du compte", "L'identifiant saisie est déjà utilisé.");
        } else showErrorPopup("Erreur lors de la création du compte", "Vous devez remplir tous les champs !");
    }

    /**
     * initialisation des tous les grades existants dans la comboBox contenant les grades, est appelée dans la méthode initialize.()
     */
    public void getGradesInitialize() {
        ArrayList<String> gradesNames = new ArrayList<>();
        gradesNames.add("Client");
        gradesNames.add("Entreprise");
        gradesNames.add("Administrateur");
        grade.getItems().addAll(gradesNames);

        grade.valueProperty().addListener((observableValue, s, t1) -> {
            initEntity(t1);
        });
    }

    public void initEntity(String entity) {
        entityCb.getItems().clear();
        switch (entity) {
            case "Client":
                entityContainer.setVisible(false);
                try {
                    ArrayList<Client> clients = Client.client.getAll();
                    ArrayList<String> values = new ArrayList<>();
                    clients.forEach(client -> {
                        if (client.getAccountId() <= 0) {
                            values.add(client.getId() + "-" + client.getNom());
                        }
                    });
                    entityCb.setPromptText("Choisissez le client");
                    entityCb.getItems().addAll(values);
                    entityContainer.setVisible(true);
                } catch (SQLException e) {
                    showErrorPopup("Erreur de chargement", "Une erreur s'est produite lors du chargement des clients");
                }
                break;
            case "Entreprise":
                entityContainer.setVisible(false);
                try {
                    ArrayList<Entreprise> entreprises = Entreprise.entrepriseDAO.getAll();
                    ArrayList<String> values = new ArrayList<>();
                    entreprises.forEach(entreprise -> {
                        if (entreprise.getAccountId() <= 0) {
                            values.add(entreprise.getNumSiret() + "-" + entreprise.getProprietaire().getNom());
                        }
                    });
                    entityCb.setPromptText("Choisissez l'entreprise");
                    entityCb.getItems().addAll(values);
                    entityContainer.setVisible(true);
                } catch (SQLException e) {
                    showErrorPopup("Erreur de chargement", "Une erreur s'est produite lors du chargement des entreprises");
                }
                break;
            case "Administrateur":
                entityContainer.setVisible(false);
                break;
        }
    }

    private void showErrorPopup(String title, String message) {
        popupMessage.setText(message);
        errorPopup.setVisible(true);
    }

    public void onClosePopup() {
        errorPopup.setVisible(false);
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
