package com.mmn.circuitscourts.controller.producteur;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.sql.SQLException;
import java.util.ArrayList;


public class CommandesController {
    @FXML
    VBox contentTable;

    @FXML
    Button addButton;

    @FXML
    VBox successPopup;

    static VBox popup;


    public void initialize() throws SQLException {
        ArrayList<Commande> commandes = Commande.getCommandesInitializeByAccountId();
        commandes.forEach(commande -> createLine(commande));
        popup = successPopup;
    }

    public static void showSuccessPopUp() {
        popup.setVisible(true);
    }

    public void onAddButton() {
        ViewFactory.getInstance().showProdAddCommandeInterface();
    }

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
    public void onClosePopup() {
        successPopup.setVisible(false);
    }


}
