package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountController {

    @FXML
    VBox contentTable;
    @FXML
    Button addButton;
    @FXML
    VBox successPopup;
    @FXML
    VBox confirmationDialog;
    @FXML
    Button okButton;
    @FXML
    Button cancelButton;
    @FXML
    Label descDialog;
    static VBox popup;

    /**
     * Méthode qui est appelé lors du chargement de la page fxml associée a ce controller.
     * Récupère via la DAO accountDAO tous les comptes constituants la BD
     * Pour chaque comptes on crée une ligne avec la fonction createLine() contenant les informations de ce compte.
     * initialisation de la popup de la confirmation.
     *
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        ArrayList<User> users = User.accountDAO.getAll();
        users.forEach(user -> {
            try {
                createLine(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        popup = successPopup;
    }

    /**
     * méthode appelé lors de la suppression d'un compte, rend visible la popup de confirmation de la supression
     */
    public static void showSuccessPopUp() {
        popup.setVisible(true);
    }


    /**
     * La page fxml est constituée d'un tableau, ce tableau n'est pas un tableau fxml il est seulement composée de lignes et de labels.
     * Permet la créarion d'une ligne du tableau pour pour un seul compte et de remplire  la ligne avec les informations de ce compte.
     * A la fin de chaque ligne crée les bouttons de modification et de suppression de la ligne.
     *
     * @param user le compte d'un utilisateur
     * @throws SQLException
     */
    public void createLine(User user) throws SQLException {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 0, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label id = new Label(String.valueOf(user.getId()));
        Label identifiant = new Label(user.getIdentifiant());
        Label mdp = new Label(user.getGradeName());
        Label grade = new Label(String.valueOf(user.getPassword()));
        labels.add(id);
        labels.add(identifiant);
        labels.add(grade);
        labels.add(mdp);

        labels.forEach(label -> {
            label.getStyleClass().add("commande-tableview-line-cell");
            label.setMaxHeight(1.7976931348623157E308);
            label.setPrefHeight(1.7976931348623157E308);
            label.setPrefWidth(141);
            label.setMinWidth(141);
            label.setMaxWidth(141);
            line.getChildren().add(label);
        });

        Button edit = new Button();
        edit.getStyleClass().add("edit-button");
        Region editImg = new Region();
        editImg.getStyleClass().add("edit-button-img");
        edit.setGraphic(editImg);
        edit.setPickOnBounds(true);
        HBox.setMargin(edit, new Insets(0, 0, 0, 30));
        edit.setOnMouseClicked(mouseEvent -> {
            onEdit(user.getId());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        delete.setOnMouseClicked(event -> {
            showConfirmationDialog(user.getId());
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);
        contentTable.getChildren().add(line);
    }

    /**
     * Méthode appelée lorsqu'un utilisateur appuie sur le boutton ajoutter sur la page fxml.
     * Appel de la méthode showAdminAddAccountInterface() de la classe ViewFactory qui permet de changer de scene dans l'application.
     */
    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddAccountInterface();
    }


    /**
     * Fermeture de la popup de confirmation.
     */
    public void onClosePopup() {
        successPopup.setVisible(false);
    }

    /**
     * Méthode appelée lorsqu'un utilisateur appuie sur le boutton modifier sur la page fxml.
     * Appel de la méthode showAdminEditAccountInterface() de la classe ViewFactory qui permet de changer de scene dans l'application.
     * @param AccountId qui est l'id du compte à modifier.
     */
    private void onEdit(int AccountId) {
        ViewFactory.getInstance().showAdminEditAccountInterface(AccountId);
    }


    /**
     * Suppression du compte et de la ligne de ce compte constituant le tableau.
     * La suppression se fait via l'appel de la méthode remove de la classe accountDAO
     * @param accountId id du compte à supprimer
     * @throws SQLException
     */
    private void onDelete(int accountId) throws SQLException {
        User.accountDAO.remove(accountId);
        System.out.println("[DEBUG]Account deleted");
        contentTable.getChildren().clear();
        ArrayList<User> users = User.accountDAO.getAll();
        users.forEach(user -> {
            try {
                createLine(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Affichage de la confiramtion de suppression du compte pour plus de sécurité.
     * La suppressin de compte ne s'effectue seulement si l'utilisateur clique sur le boutton "confirmer"
     * @param accountId
     */
    public void showConfirmationDialog(int accountId) {
        descDialog.setText("Voulez vous vraiment supprimer le compte n°" + accountId);
        confirmationDialog.setVisible(true);
        okButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
            try {
                onDelete(accountId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        cancelButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
        });
    }
}



