package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehiculeController {

    @FXML
    VBox contentTable;

    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddVehiculeInterface();
    }

    public void initialize() throws SQLException {
        ArrayList<Vehicule> vehicules = Vehicule.getVehiculesInitilize();
        for (Vehicule v: vehicules) {
            createLine(v);
        }
    }

    public void createLine(Vehicule vehicule) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 40, 0, 40));
        line.getStyleClass().add("vehicule-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numImmatriculation = new Label(String.valueOf(vehicule.getNumImmate()));
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
        HBox.setMargin(edit, new Insets(0, 0, 0, 30));
        edit.setOnMouseClicked(mouseEvent -> {
            onEdit(vehicule.getNumImmate());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        delete.setOnMouseClicked(event -> {
            try {
                onDelete((vehicule.getNumImmate()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);
        contentTable.getChildren().add(line);
    }


    public void onClosePopup() {
    }

    public void onEdit(String numImmat){
        ViewFactory.getInstance().showAdminEditVehiculeInterface(numImmat);
    }

    public void onDelete(String numImmat) throws SQLException {
        Vehicule.vehiculeDAO.remove(numImmat);
        System.out.println("[DEBUG]Commande deleted");
        contentTable.getChildren().clear();
        ArrayList<Vehicule> vehicules = Vehicule.getCommandesInitialize();
        vehicules.forEach(vehicule -> {
            createLine(vehicule);
        });
    }
}

