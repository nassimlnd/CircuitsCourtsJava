package com.mmn.circuitscourts.controller.entreprise;

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
        ArrayList<Tournee> tournees = Tournee.getTourneeByEntreprise();
        tournees.forEach(tournee -> createLine(tournee));

        titlePopup = popupTitle;
        subtitlePopup = popupSubtitle;
        popup = successPopup;
    }

    public void onAddButton() {
        ViewFactory.getInstance().showEntrepriseAddTourneeInterface();
    }

    public void createLine(Tournee trn) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setMinHeight(64);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 40, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numTournee = new Label(String.valueOf(trn.getId()));
        Label date = new Label(trn.getDate().toString());
        Label horaire = new Label(trn.getHoraireDebut() + "h à " + trn.getHoraireFin() + "h");
        Label numImmat = new Label(String.valueOf(trn.getNumImmat()));
        labels.add(numTournee);
        labels.add(date);
        labels.add(horaire);
        labels.add(numImmat);

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
        HBox.setMargin(edit, new Insets(0, 0, 0, 100));
        edit.setOnMouseClicked(mouseEvent -> {
            ViewFactory.getInstance().showProdEditTourneeInterface(trn.getId());
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

    private void onDelete(Tournee trn) throws SQLException {
        Tournee.tourneeDAO.remove(trn.getId());
        ArrayList<Commande> commandes = Commande.cmd.getAllByTournee(trn.getId());
        commandes.forEach(commande -> {
            commande.setIdTournee(0);
        });

        ArrayList<Tournee> tournees = Tournee.getTourneeByEntreprise();
        tournees.forEach(tournee -> createLine(tournee));
    }

    public void showConfirmationDialog(Tournee tournee) {
        descDialog.setText("Voulez vous vraiment supprimer la tournée n°"+ tournee.getId());
        confirmationDialog.setVisible(true);
        okButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
            try {
                onDelete(tournee);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        cancelButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
        });
    }

    public void onClosePopup() {
        popup.setVisible(false);
    }

    public static void showPopupSuccess(String title, String message) {
        titlePopup.setText(title);
        subtitlePopup.setText(message);
        popup.setVisible(true);
    }
}
