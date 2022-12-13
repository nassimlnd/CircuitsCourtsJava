package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.services.VehiculeDAO;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehiculesController {

    @FXML
    VBox commandeTable;

    public void initialize() throws SQLException {
        Vehicule vehicule = new Vehicule();
        ArrayList<Vehicule> vehicules = vehicule.getVehiculesInitilize();
        for (Vehicule v: vehicules) {
            createLine(v);
        }
    }

    public void createLine(Vehicule v) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 40, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numImmatriculation = new Label(String.valueOf(v.getNumImmate()));
        Label poids = new Label(String.valueOf(v.getPoidsMax()));
        labels.add(numImmatriculation);
        labels.add(poids);

        labels.forEach(label -> {
            label.getStyleClass().add("commande-tableview-line-cell");
            label.setMaxHeight(1.7976931348623157E308);
            label.setPrefHeight(1.7976931348623157E308);
            label.setPrefWidth(151);
            label.setMinWidth(151);
            label.setMaxWidth(151);
            line.getChildren().add(label);
        });

        commandeTable.getChildren().add(line);
    }
}
