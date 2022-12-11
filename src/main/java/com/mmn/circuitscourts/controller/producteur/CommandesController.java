package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.User;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Date;

public class CommandesController {

    @FXML
    TableView<Commande> tableView;
    @FXML
    TableColumn numero;
    @FXML
    TableColumn libelle;
    @FXML
    TableColumn poids;
    @FXML
    TableColumn horaire;
    @FXML
    TableColumn dateCommande;

    @FXML
    public void initialize() {
        Commande commande = new Commande(1, "test", 150, "10", "20", 2, 2, 1);
        Commande commande2 = new Commande(2, "test", 150, "10", "20", 2, 2, 1);

        initTableColumn();
        ObservableList<Commande> list1 = FXCollections.observableArrayList(commande, commande2);

        tableView.setItems(list1);

    }

    public void initTableColumn() {
        numero.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("numCommande"));
        libelle.setCellValueFactory(new PropertyValueFactory<Commande, String>("libelle"));
        poids.setCellValueFactory(new PropertyValueFactory<Commande, Float>("libelle"));
        horaire.setCellValueFactory(new PropertyValueFactory<Commande, String>("horaireDebut"));
        dateCommande.setCellValueFactory(new PropertyValueFactory<Commande, Date>("dateCommande"));
        numero.setResizable(false);
        libelle.setResizable(false);
        poids.setResizable(false);
        horaire.setResizable(false);
        dateCommande.setResizable(false);
    }
}
