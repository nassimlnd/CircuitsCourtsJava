package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Client;
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

public class ClientController {

    static VBox popup;
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

    public void onClosePopup() {
    }

    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddCLientInterface();
    }


    /**
     * Récupère tous les clients de la base de données via la fonction getClientsInitilize().
     * Pour chaque client appel de la fonction createLine().
     *
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        ArrayList<User> clients = Client.getClientsInitialize();
        for (User u : clients) {
            createLine(u);
        }

    }

    /**
     * Supprime un client de la base de donnée via la méthode remove de la classe accountDAO, puis actualise le tableau avec un nouvelle initialisation des clients dans le tableau.
     *
     * @param accountId l'id du compte que l'Administrateur veut supprimer.
     * @throws SQLException
     */
    public void onDelete(int accountId) throws SQLException {
        User.accountDAO.remove(accountId);
        System.out.println("[DEBUG]Account deleted");
        contentTable.getChildren().clear();
        ArrayList<User> users = User.accountDAO.getAllClient();
        users.forEach(user -> {
            try {
                createLine(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Crée une ligne dans le tableau avec tous les champs d'un compte client de la base de donnée.
     * pour chaque ligne création des bouttons de modification et de suppression du compte.
     *
     * @param user le compte fournit les données à mettre dans la ligne.
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
     * lors de la suppression d'un compte permet de faire apparaitre une popUp de validation de la suppression du compte.
     *
     * @param accountId est l'id du compte à supprimer.
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


    /**
     * Renvoi vers la page de modification d'un compte.
     *
     * @param numSiret
     */
    private void onEdit(int numSiret) {
        ViewFactory.getInstance().showAdminEditClientInterface(numSiret);
    }

}

