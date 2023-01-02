package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Producteur;
import com.mmn.circuitscourts.models.Proprietaire;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;


public class ProducteurController {
    static VBox popup;
    @FXML
    VBox contentTable;
    @FXML
    Button addButton;
    @FXML
    VBox successPopup;

    public static void showSuccessPopUp() {
        popup.setVisible(true);
    }

    public void initialize() throws SQLException {
        ArrayList<Producteur> producteurs = Producteur.producteurDAO.getAll();
        producteurs.forEach(producteur -> {
            try {
                createLine(producteur);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        popup = successPopup;
    }

    public void createLine(Producteur producteur) throws SQLException {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 0, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numSiret = new Label(String.valueOf(producteur.getNumSiret()));
        Label adresse = new Label(producteur.getAdresse());
        Label numTel = new Label(producteur.getNumTel());
        Label gps = new Label(producteur.getCoordonneesGps());
        labels.add(numSiret);
        labels.add(adresse);
        labels.add(numTel);
        labels.add(gps);
        labels.forEach(label -> {
            label.getStyleClass().add("commande-tableview-line-cell");
            label.setMaxHeight(1.7976931348623157E308);
            label.setPrefHeight(1.7976931348623157E308);
            label.setPrefWidth(141);
            label.setMinWidth(141);
            label.setMaxWidth(141);
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
            onEdit(producteur.getNumSiret());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        delete.setOnMouseClicked(event -> {
            try {
                onDelete((producteur.getNumSiret()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);
        contentTable.getChildren().add(line);
    }


    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddProducteurInterface();
    }


    public void onClosePopup() {
        successPopup.setVisible(false);
    }


    private void onEdit(int numSiret) {
        ViewFactory.getInstance().showAdminEditProducteurInterface(numSiret);
    }


    private void onDelete(int numSiret) throws SQLException {
        Proprietaire.proprietaireDAO.removeFromPropSiret(numSiret);
        Producteur.producteurDAO.remove(numSiret);
        System.out.println("[DEBUG]Producteur deleted");
        contentTable.getChildren().clear();
        ArrayList<Producteur> producteurs = Producteur.producteurDAO.getAll();
        producteurs.forEach(producteur -> {
            try {
                createLine(producteur);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
