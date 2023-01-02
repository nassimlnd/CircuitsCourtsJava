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
    static Label popupId;
    @FXML
    VBox successPopup, confirmationDialog, contentTable;
    @FXML
    Button okButton, cancelButton, addButton;
    @FXML
    Label descDialog, popupSubtitle;


    /**
     * Récupère tous les clients de la base de données via la fonction getClientsInitilize().
     * Pour chaque client appel de la fonction createLine().
     *
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        ArrayList<Client> clients = Client.client.getAll();
        for (Client client : clients) {
            createLine(client);
        }

        popup = successPopup;
        popupId = popupSubtitle;
    }

    /**
     * Supprime un client de la base de donnée via la méthode remove de la classe accountDAO, puis actualise le tableau avec un nouvelle initialisation des clients dans le tableau.
     *
     * @param clientId l'id du compte que l'Administrateur veut supprimer.
     * @throws SQLException
     */
    public void onDelete(int clientId) throws SQLException {
        Client.client.remove(clientId);
        System.out.println("[DEBUG]Client deleted");
        contentTable.getChildren().clear();
        ArrayList<Client> clients = Client.client.getAll();
        clients.forEach(client -> {
            try {
                createLine(client);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Crée une ligne dans le tableau avec tous les champs d'un compte client de la base de donnée.
     * pour chaque ligne création des bouttons de modification et de suppression du compte.
     *
     * @param client le client fournit les données à mettre dans la ligne.
     * @throws SQLException
     */
    public void createLine(Client client) throws SQLException {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 0, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label id = new Label(String.valueOf(client.getId()));
        Label nom = new Label(client.getNom());
        Label email = new Label(client.getEmail());
        Label numTel = new Label(client.getNumTel());
        labels.add(id);
        labels.add(nom);
        labels.add(email);
        labels.add(numTel);

        labels.forEach(label -> {
            label.getStyleClass().add("commande-tableview-line-cell");
            label.setMaxHeight(1.7976931348623157E308);
            label.setPrefHeight(1.7976931348623157E308);
            label.setPrefWidth(151);
            label.setMinWidth(151);
            label.setMaxWidth(151);
            line.getChildren().add(label);
        });

        Button edit = new Button();
        edit.getStyleClass().add("edit-button");
        Region editImg = new Region();
        editImg.getStyleClass().add("edit-button-img");
        edit.setGraphic(editImg);
        edit.setPickOnBounds(true);
        HBox.setMargin(edit, new Insets(0, 0, 0, 150));
        edit.setOnMouseClicked(mouseEvent -> {
            onEdit(client.getId());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        delete.setOnMouseClicked(event -> {
            showConfirmationDialog(client.getId());
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);
        contentTable.getChildren().add(line);
    }

    /**
     * lors de la suppression d'un compte permet de faire apparaitre une popUp de validation de la suppression du compte.
     *
     * @param clientId est l'id du compte à supprimer.
     */
    public void showConfirmationDialog(int clientId) {
        descDialog.setText("Voulez vous vraiment supprimer le client n°" + clientId);
        confirmationDialog.setVisible(true);
        okButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
            try {
                onDelete(clientId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        cancelButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
        });
    }

    /**
     * Renvoi vers la page de modification d'un client.
     *
     * @param clientId
     */
    private void onEdit(int clientId) {
        ViewFactory.getInstance().showAdminEditClientInterface(clientId);
    }

    public void onClosePopup() {
        popup.setVisible(false);
    }

    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddCLientInterface();
    }

    public static void showSuccessPopUp(int id) {
        popup.setVisible(true);
        popupId.setText("Le client n°" + id +" a bien été ajouté !");
    }

}

