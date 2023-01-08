package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Proprietaire;
import com.mmn.circuitscourts.models.Tournee;
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


public class EntrepriseController {
    static VBox popup;
    @FXML
    VBox contentTable, successPopup, confirmationDialog;
    @FXML
    Button addButton, okButton, cancelButton;
    @FXML
    Label popupMessage, popupTitle, descDialog;

    static Label message, title;

    public void initialize() throws SQLException {
        ArrayList<Entreprise> entreprises = Entreprise.entrepriseDAO.getAll();
        entreprises.forEach(entreprise -> {
            try {
                createLine(entreprise);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        popup = successPopup;
        message = popupMessage;
        title = popupTitle;
    }

    public static void showSuccessPopUp(String popupTitle, String popupMessage) {
        popup.setVisible(true);
        title.setText(popupTitle);
        message.setText(popupMessage);
    }

    public void createLine(Entreprise entreprise) throws SQLException {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 0, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numSiret = new Label(String.valueOf(entreprise.getNumSiret()));
        Label adresse = new Label(entreprise.getAdresse());
        Label numTel = new Label(entreprise.getNumTel());
        Label gps = new Label(entreprise.getCoordonneesGps());
        labels.add(numSiret);
        labels.add(adresse);
        labels.add(numTel);
        labels.add(gps);
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
        HBox.setMargin(edit, new Insets(0, 0, 0, 80));
        edit.setOnMouseClicked(mouseEvent -> {
            onEdit(entreprise.getNumSiret());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        delete.setOnMouseClicked(event -> {
            showConfirmationDialog(entreprise);
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);
        contentTable.getChildren().add(line);
    }


    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddEntrepriseInterface();
    }


    public void onClosePopup() {
        successPopup.setVisible(false);
    }


    private void onEdit(long numSiret) {
        ViewFactory.getInstance().showAdminEditEntrepriseInterface(numSiret);
    }


    private void onDelete(Entreprise entreprise) {
        try {
            Proprietaire.proprietaireDAO.removeFromPropSiret(entreprise.getNumSiret());
            Entreprise.entrepriseDAO.remove(entreprise.getNumSiret());
            System.out.println("[DEBUG]Entreprise deleted");
            contentTable.getChildren().clear();
            ArrayList<Entreprise> entreprises = Entreprise.entrepriseDAO.getAll();
            entreprises.forEach(e -> {
                try {
                    createLine(e);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showConfirmationDialog(Entreprise entreprise) {
        descDialog.setText("Voulez vous vraiment supprimer la tournée n°"+ entreprise.getNumSiret());
        confirmationDialog.setVisible(true);
        okButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
            onDelete(entreprise);
        });
        cancelButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
        });
    }

}
