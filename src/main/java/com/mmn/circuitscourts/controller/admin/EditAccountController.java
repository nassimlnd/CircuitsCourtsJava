package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;


public class EditAccountController {
    /**
     * L'id du compte que l'on veut modifier.
     */
    public static int accountId = 0;
    @FXML
    Label title, popupMessage;
    @FXML
    VBox errorPopup, entityContainer;
    @FXML
    TextField identifiant, mdp;
    @FXML
    ComboBox<String> grade, entityCb;

    public void initialize() throws SQLException {
        initFields();
    }

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

    public void initFields() {
        title.setText("Modification du compte n°" + accountId);
        try {
            identifiant.setText(getThisAccount().getIdentifiant());
            mdp.setText(getThisAccount().getPassword());
            getGradesInitialize();

            grade.valueProperty().addListener((observableValue, s, t1) -> {
                initEntity(t1);
            });
        } catch (SQLException e) {
            showErrorPopup("Une erreur est survenue lors du chargement du compte ! \n(SQL ERROR)");
        }

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
                    showErrorPopup("Une erreur s'est produite lors du chargement des clients");
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
                    showErrorPopup("Une erreur s'est produite lors du chargement des entreprises");
                }
                break;
            case "Administrateur":
                entityContainer.setVisible(false);
                break;
        }
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
        if(!identifiant.getText().matches("^[a-zA-ZÀ-ÖØ-öø-ÿ]+(([',. -][a-zA-ZÀ-ÖØ-öø-ÿ])?[a-zA-ZÀ-ÖØ-öø-ÿ]*)*$")){
            System.out.println("[DEBUG]error : nom incorrect");
            return;
        }

        if(!mdp.getText().matches("/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/")){
            System.out.println("[DEBUG]Eror : au moins 8 caractères, au moins une lettre majuscule, au moins une lettre minuscule, au moins un chiffre, au moins un caractère spécial.");
            return;
        }

        User u = new User(accountId, identifiant.getText(), mdp.getText(), getGradeNumber(grade.getValue()));
        User.accountDAO.update(accountId, u);
        System.out.println("[DEBUG]User n°" + accountId + " updated.");
        ViewFactory.getInstance().showAdminAccountInterface();
    }

    public void showErrorPopup(String message) {
        popupMessage.setText(message);
        errorPopup.setVisible(true);
    }

    public void onClosePopup() {
        errorPopup.setVisible(false);
    }
}
