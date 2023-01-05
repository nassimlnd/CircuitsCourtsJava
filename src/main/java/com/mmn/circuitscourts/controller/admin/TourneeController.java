package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Commande;
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

public class TourneeController {
    @FXML
    Button addButton, okButton, cancelButton;
    @FXML
    VBox contentTable, successPopup, confirmationDialog;
    @FXML
    Label popupTitle, popupSubtitle, descDialog;
    static Label titlePopup, subtitlePopup;
    static VBox popup;

    public void initialize() throws SQLException {
        initTable();
        titlePopup = popupTitle;
        subtitlePopup = popupSubtitle;
        popup = successPopup;
    }

    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddTourneeInterface();
    }

    public void initTable() {
        ArrayList<Tournee> tournees = null;
        try {
            tournees = Tournee.getTourneeList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Tournee tournee : tournees) {
            createLine(tournee);
        }
    }

    public void createLine(Tournee trn) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 0, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        Label idTournee = new Label(String.valueOf(trn.getId()));
        Label date = new Label(String.valueOf(trn.getDate()));
        Label horaire = new Label(trn.getHoraireDebut() + "h à " + trn.getHoraireFin() + "h");
        Label numSiret = new Label(String.valueOf(trn.getNumSiret()));
        Label numImmat = new Label(String.valueOf(trn.getNumImmat()));

        idTournee.getStyleClass().add("commande-tableview-line-cell");
        idTournee.setMaxHeight(1.7976931348623157E308);
        idTournee.setPrefHeight(1.7976931348623157E308);
        idTournee.setPrefWidth(91);

        date.getStyleClass().add("commande-tableview-line-cell");
        date.setMaxHeight(1.7976931348623157E308);
        date.setPrefHeight(1.7976931348623157E308);
        date.setPrefWidth(121);

        horaire.getStyleClass().add("commande-tableview-line-cell");
        horaire.setMaxHeight(1.7976931348623157E308);
        horaire.setPrefHeight(1.7976931348623157E308);
        horaire.setPrefWidth(151);

        numImmat.getStyleClass().add("commande-tableview-line-cell");
        numImmat.setMaxHeight(1.7976931348623157E308);
        numImmat.setPrefHeight(1.7976931348623157E308);
        numImmat.setPrefWidth(201);

        numSiret.getStyleClass().add("commande-tableview-line-cell");
        numSiret.setMaxHeight(1.7976931348623157E308);
        numSiret.setPrefHeight(1.7976931348623157E308);
        numSiret.setPrefWidth(151);

        line.getChildren().add(idTournee);
        line.getChildren().add(date);
        line.getChildren().add(horaire);
        line.getChildren().add(numImmat);
        line.getChildren().add(numSiret);

        Button edit = new Button();
        edit.getStyleClass().add("edit-button");
        Region editImg = new Region();
        editImg.getStyleClass().add("edit-button-img");
        edit.setGraphic(editImg);
        HBox.setMargin(edit, new Insets(0, 0, 0, 20));
        edit.setOnMouseClicked(mouseEvent -> {
            ViewFactory.getInstance().showAdminEditTourneeInterface(trn.getId());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setOnMouseClicked(mouseEvent -> {
            showConfirmationDialog(trn);
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);

        contentTable.getChildren().add(line);
    }

    private void showConfirmationDialog(Tournee tournee) {
        descDialog.setText("Voulez vous vraiment supprimer la tournée n°"+ tournee.getId());
        confirmationDialog.setVisible(true);
        okButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
            onDelete(tournee);
        });
        cancelButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
        });
    }

    private void onDelete(Tournee trn) {
        try {
            Tournee.tourneeDAO.remove(trn.getId());
            ArrayList<Commande> commandes = Commande.cmd.getAllByTournee(trn.getId());
            commandes.forEach(commande -> {
                commande.setIdTournee(0);
            });

            contentTable.getChildren().clear();
            initTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showPopupSuccess(String title, String message) {
        titlePopup.setText(title);
        subtitlePopup.setText(message);
        popup.setVisible(true);
    }

    public void onClosePopup() {
        popup.setVisible(false);
    }
}
