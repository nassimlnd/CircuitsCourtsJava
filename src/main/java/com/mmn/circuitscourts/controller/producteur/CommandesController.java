package com.mmn.circuitscourts.controller.producteur;
import com.mmn.circuitscourts.models.Commande;
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


public class CommandesController {
    @FXML
    VBox contentTable;

    @FXML
    Button addButton;
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
        line.setMaxWidth(850);
        line.setPadding(new Insets(0, 20, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numCommande = new Label(String.valueOf(commande.getNumCommande()));
        Label libelle = new Label(String.valueOf(commande.getArticleId()));
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
        HBox.setMargin(edit, new Insets(0,0,0, 20));
        edit.setOnMouseClicked(mouseEvent -> {
            onEdit(commande.getNumCommande());
        });

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        delete.setOnMouseClicked(event -> {
            showConfirmationDialog(commande.getNumCommande());
        });

        line.getChildren().add(edit);
        line.getChildren().add(delete);
        contentTable.getChildren().add(line);
    }
    public void onClosePopup() {
        successPopup.setVisible(false);
    }

    public void onEdit(int id) {
        ViewFactory.getInstance().showProdEditCommandeInterface(id);
    }
    private void onDelete(int numCommande) throws SQLException {
        Commande.cmd.remove(numCommande);
        System.out.println("[DEBUG]Commande deleted");
        contentTable.getChildren().clear();
        ArrayList<Commande> commandes = Commande.getCommandesInitialize();
        commandes.forEach(commande -> {
            createLine(commande);
        });
    }
    public void showConfirmationDialog(int numCommande) {
        descDialog.setText("Voulez vous vraiment supprimer la commande n°"+ numCommande);
        confirmationDialog.setVisible(true);
        okButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
            try {
                onDelete(numCommande);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        cancelButton.setOnMouseClicked(mouseEvent -> {
            confirmationDialog.setVisible(false);
        });
    }


}
