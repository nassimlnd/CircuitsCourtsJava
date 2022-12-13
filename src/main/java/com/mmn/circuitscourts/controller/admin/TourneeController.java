package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class TourneeController {
     @FXML
     VBox commandeTable;

    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddTourneeInterface();
    }

    public void initialize() throws SQLException {
        Tournee t = new Tournee();
        ArrayList<Tournee> tournees = t.getCommandesInitialize();
        System.out.println(tournees);
        for (Tournee tournee: tournees) {
            createLine(tournee);
        }
    }

    public void createLine(Tournee trn) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 40, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label idTournee = new Label(String.valueOf(trn.getId()));
        Label horaire = new Label(trn.getHoraireDebut() + "h à " + trn.getHoraireFin() + "h");
        labels.add(idTournee);
        labels.add(horaire);

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
