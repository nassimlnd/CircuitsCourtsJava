package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.services.ClientDAO;
import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommandesController {

    @FXML
    VBox contentTable;

    public void initialize() {
        showCommandes();
    }

    public void showCommandes() {
        ClientDAO clientDAO = new ClientDAO();
        CommandeDAO commandeDAO = new CommandeDAO();
        Client client = null;
        ArrayList<Commande> commandes = null;
        try {
            client = clientDAO.getByAccountId(App.userConnected.getId());
            commandes = commandeDAO.getByClientId(client.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        commandes.forEach(commande -> {
            try {
                createLine(commande);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void createLine(Commande commande) throws SQLException {
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        Article article = marketplaceDAO.getById(commande.getArticleId());

        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 40, 0, 40));
        line.getStyleClass().add("client-commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numCommande = new Label(String.valueOf(commande.getNumCommande()));
        Label articleName = new Label(String.valueOf(article.getName()));
        Label poids = new Label(commande.getPoids() + " kg");
        Label horaire = new Label(commande.getHoraireDebut() + "h à " + commande.getHoraireFin() + "h");
        Label dateCommande = new Label(String.valueOf(commande.getDateCommande()));
        labels.add(numCommande);
        labels.add(articleName);
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

        line.setOnMouseClicked(mouseEvent -> {
            ViewFactory.getInstance().showClientCommandesInfoInterface(commande.getNumCommande());
        });

        contentTable.getChildren().add(line);
    }


}
