package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeController {
    @FXML
    VBox contentTable;

    @FXML
    Button addButton;

    /**
     * Appel de la méthode du modèle pour récuperer toutes les commande présentes dans la base de données.
     * pour chaque commande créer la ligne correspondante dans la vue Admin.Commandes
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        Commande c = new Commande();
        ArrayList<Commande> commandes = c.getCommandesInitialize();
        for (Commande commande: commandes) {
                createLine(commande);
        }

    }

    /**
     * pour une commandé crée dans la vue associée la ligne de la commande spécifique.
     * @param commande une commande
     */
    public void createLine(Commande commande) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 40, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numCommande = new Label(String.valueOf(commande.getNumCommande()));
        Label libelle = new Label(commande.getLibelle());
        Label poids = new Label(String.valueOf(commande.getPoids()) + " kg");
        Label horaire = new Label(commande.getHoraireDebut() + "h à " + commande.getHoraireFin() + "h");
        Label dateCommande = new Label(String.valueOf(commande.getDateCommande()));
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

        contentTable.getChildren().add(line);
    }

    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddCommandeInterface();
    }


}
