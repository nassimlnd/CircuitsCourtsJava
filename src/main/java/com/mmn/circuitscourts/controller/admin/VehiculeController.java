package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Vehicule;
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

public class VehiculeController {

    @FXML
    VBox contentTable, confirmationDialog, successPopup;
    @FXML
    Button okButton, cancelButton;
    @FXML
    Label descDialog;

    static VBox popup;
    static Label popupId;


    public void initialize() throws SQLException {
        ArrayList<Vehicule> vehicules = Vehicule.getVehiculesInitilize();
        for (Vehicule v : vehicules) {
            createLine(v);
        }
    }

    public void createLine(Vehicule vehicule) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 0, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numImmatriculation = new Label(String.valueOf(vehicule.getNumImmat()));
        Label poids = new Label(String.valueOf(vehicule.getPoidsMax()));
        Label numSiret = new Label(String.valueOf(vehicule.getnumSiret()));
        labels.add(numImmatriculation);
        labels.add(poids);
        labels.add(numSiret);

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
        HBox.setMargin(edit, new Insets(0, 0, 0, 290));
        edit.setOnMouseClicked(mouseEvent -> {
            onEdit(vehicule.getNumImmat());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        delete.setOnMouseClicked(event -> {
            showConfirmationDialog(vehicule.getNumImmat());
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);
        contentTable.getChildren().add(line);
    }

    public void onEdit(String numImmat) {
        ViewFactory.getInstance().showAdminEditVehiculeInterface(numImmat);
    }

    public void onDelete(String numImmat) throws SQLException {
        Vehicule.vehiculeDAO.remove(numImmat);
        System.out.println("[DEBUG]Véhicule deleted");
        contentTable.getChildren().clear();
        ArrayList<Vehicule> vehicules = Vehicule.getCommandesInitialize();
        vehicules.forEach(vehicule -> {
            createLine(vehicule);
        });
    }

    public void showConfirmationDialog(String numImmat) {
        descDialog.setText("Voulez vous vraiment supprimer le véhicule immatriculé : " + numImmat);
        confirmationDialog.setVisible(true);
        okButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
            try {
                onDelete(numImmat);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        cancelButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
        });
    }

    public static void showSuccessPopUp(String id) {
        popup.setVisible(true);
        popupId.setText("Le véhicule immatriculé : " + id +" a bien été ajoutée !");
    }

    public void onClosePopup() {
        popup.setVisible(false);
    }

    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddVehiculeInterface();
    }
}

