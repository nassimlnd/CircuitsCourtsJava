package com.mmn.circuitscourts.controller.entreprise;

import com.mmn.circuitscourts.models.Vehicule;
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

public class VehiculesController {
    @FXML
    VBox successPopup, contentTable;
    @FXML
    Label popupSubtitle;
    static VBox popup;
    static Label popupId;


    public void initialize() throws SQLException {
        ArrayList<Vehicule> vehicules = Vehicule.getVehiculesInitilize();
        for (Vehicule v : vehicules) {
            createLine(v);
        }
        popup = successPopup;
        popupId = popupSubtitle;
    }

    public static void showSuccessPopUp(String id) {
        popup.setVisible(true);
        popupId.setText("Le véhicule immatriculé : " + id + " a bien été ajoutée !");
    }

    public void createLine(Vehicule v) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 40, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numImmatriculation = new Label(String.valueOf(v.getNumImmat()));
        Label poids = new Label(String.valueOf(v.getPoidsMax()));
        labels.add(numImmatriculation);
        labels.add(poids);

        labels.forEach(label -> {
            label.getStyleClass().add("commande-tableview-line-cell");
            label.setMaxHeight(1.7976931348623157E308);
            label.setPrefHeight(1.7976931348623157E308);
            label.setPrefWidth(200);
            label.setMinWidth(200);
            label.setMaxWidth(200);
            line.getChildren().add(label);

        });
        Button edit = new Button();
        edit.getStyleClass().add("edit-button");
        Region editImg = new Region();
        editImg.getStyleClass().add("edit-button-img");
        edit.setGraphic(editImg);
        edit.setPickOnBounds(true);
        HBox.setMargin(edit, new Insets(0, 0, 0, 320));
        edit.setOnMouseClicked(mouseEvent -> {
            onEdit(v.getNumImmat());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        delete.setOnMouseClicked(event -> {
            try {
                onDelete(v.getNumImmat());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);

        contentTable.getChildren().add(line);
    }

    public void onAddButton() {
        ViewFactory.getInstance().showEntrepriseAddVehiculeInterface();
    }

    public void onClosePopup() {
        popup.setVisible(false);
    }

    public void onEdit(String numImmat) {
        ViewFactory.getInstance().showProdEditVehiculeInterface(numImmat);
    }

    public void onDelete(String numImmat) throws SQLException {
        Vehicule.vehiculeDAO.remove(numImmat);
        System.out.println("[DEBUG]Commande deleted");
        contentTable.getChildren().clear();
        ArrayList<Vehicule> vehicules = Vehicule.getVehiculesInitilize();
        vehicules.forEach(vehicule -> {
            createLine(vehicule);
        });
    }
}
