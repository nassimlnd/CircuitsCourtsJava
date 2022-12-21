package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.services.MarketplaceDAO;
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

public class ArticlesController {

    @FXML
    VBox contentTable;
    @FXML
    VBox confirmationDialog;
    @FXML
    Button okButton;
    @FXML
    Button cancelButton;
    @FXML Label descDialog;
    @FXML
    VBox successPopup;
    static VBox popup;
    static Label popupid;
    @FXML
    Label successPopupId;

    public void initialize() {
        Article.getAll().forEach(article -> createLine(article));
        popup = successPopup;
        popupid = successPopupId;
    }

    public static void showSuccessPopUp(int id) {
        popupid.setText("L'article n°"+id+" a bien été ajoutée !");
        popup.setVisible(true);
    }

    public void onClosePopup() {
        successPopup.setVisible(false);
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
        Button edit = new Button();
        edit.getStyleClass().add("edit-button");
        Region editImg = new Region();
        editImg.getStyleClass().add("edit-button-img");
        edit.setGraphic(editImg);
        edit.setPickOnBounds(true);
        HBox.setMargin(edit, new Insets(0,0,0, 20));
        edit.setOnMouseClicked(mouseEvent -> {
            onEdit(article.getId());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        delete.setOnMouseClicked(event -> {
            showConfirmationDialog(article.getId());
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);
        contentTable.getChildren().add(line);
    }
    public void onEdit(int id) {
        ViewFactory.getInstance().showProdEditArticleInterface(id);
    }
    public void showConfirmationDialog(int id) {
        descDialog.setText("Voulez vous vraiment supprimer l'article n°"+ id+" et les commandes qui lui sont liées");
        confirmationDialog.setVisible(true);
        okButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
            try {
                Commande.cmd.removeById(id);
                onDelete(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        cancelButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
        });
    }
    private void onDelete(int id) throws SQLException {
        Article.article.remove(id);
        System.out.println("[DEBUG]Article deleted");
        contentTable.getChildren().clear();
        Article.getAll().forEach(article -> createLine(article));



    }

}
