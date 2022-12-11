package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.User;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Date;

public class CommandesController {
    @FXML
    VBox commandeTable;

    @FXML
    public void initialize() {
        Commande commande = new Commande(1, "test", 150, "10", "20", 2, 2, 1);
        Commande commande2 = new Commande(2, "test", 150, "10", "20", 2, 2, 1);

        createLine();
        //initTableColumn();
        //ObservableList<Commande> list1 = FXCollections.observableArrayList(commande, commande2);

        //tableView.setItems(list1);

    }

    public void createLine() {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(56);
        line.setPrefWidth(950);
        line.setPadding(new Insets(0, 40, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numCommande = new Label("1");
        Label libelle = new Label("line");
        Label poids = new Label("test");
        Label horaire = new Label("test");
        Label dateCommande = new Label("test");
        labels.add(numCommande);
        labels.add(libelle);
        labels.add(poids);
        labels.add(horaire);
        labels.add(dateCommande);

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
