package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.services.MarketplaceDAO;
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

public class ArticlesController {

    @FXML
    VBox contentTable;

    public void initialize() {
        Article.getAll().forEach(article -> createLine(article));
    }

    public void onAddButton() {
        ViewFactory.getInstance().showProdAddArticlesInterface();
    }

    public void createLine(Article article) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 40, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label id = new Label(String.valueOf(article.getId()));
        Label name = new Label(article.getName());
        Label categorie = new Label(article.getCategorie());
        Label price = new Label(article.getPrice() + " €");
        Label weight = new Label(article.getWeight() + " kg");
        labels.add(id);
        labels.add(name);
        labels.add(categorie);
        labels.add(price);
        labels.add(weight);

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
}
