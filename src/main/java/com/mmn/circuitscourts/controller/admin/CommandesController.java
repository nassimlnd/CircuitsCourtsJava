package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Article;
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


/**
 * Controller qui gère la vue Commandes.fxml
 */
public class CommandesController {
    static VBox popup;
    @FXML
    VBox contentTable;
    @FXML
    Button addButton;
    @FXML
    VBox successPopup;
    @FXML
    VBox confirmationDialog;
    @FXML
    Button okButton;
    @FXML
    Button cancelButton;
    @FXML
    Label descDialog;

    /**
     * Affichage de la popup de réussite de l'action.
     */
    public static void showSuccessPopUp() {
        popup.setVisible(true);
    }

    /**
     * Appel de la méthode du modèle pour récuperer toutes les commande présentes dans la base de données.
     * pour chaque commande créer la ligne correspondante dans la vue Admin.Commandes
     *
     * @throws SQLException si
     */
    public void initialize() throws SQLException {
        ArrayList<Commande> commandes = Commande.getCommandesInitialize();
        commandes.forEach(commande -> {
            try {
                createLine(commande);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        popup = successPopup;
    }

    /**
     * Création d'une ligne dans le tableau des commandes depuis la commande entrée en paramètre.
     *
     * @param commande La commande que l'on veut afficher.
     */
    public void createLine(Commande commande) throws SQLException {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPrefHeight(64);
        line.setPrefWidth(850);
        line.setPadding(new Insets(0, 0, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        ArrayList<Label> labels = new ArrayList<>();
        Label numCommande = new Label(String.valueOf(commande.getNumCommande()));
        Label articleId = new Label(String.valueOf(getArticle(commande).getName()));
        Label poids = new Label(String.valueOf(commande.getPoids()) + " kg");
        Label horaire = new Label(commande.getHoraireDebut() + "h à " + commande.getHoraireFin() + "h");
        Label dateCommande = new Label(String.valueOf(commande.getDateCommande()));
        labels.add(numCommande);
        labels.add(articleId);
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
        HBox.setMargin(edit, new Insets(0, 0, 0, 30));
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

    /**
     * Appel l'instance de ViewFactory qui affichera l'interface d'ajout de commande
     */
    public void onAddButton() {
        ViewFactory.getInstance().showAdminAddCommandeInterface();
    }

    /**
     * Fonction de fermeture de la popup
     */
    public void onClosePopup() {
        successPopup.setVisible(false);
    }

    /**
     * Fonction de handle du bouton de modification
     *
     * @param numCommande
     */
    private void onEdit(int numCommande) {
        ViewFactory.getInstance().showAdminEditCommandeInterface(numCommande);
    }


    private void onDelete(int numCommande) throws SQLException {
        Commande.cmd.remove(numCommande);
        System.out.println("[DEBUG]Commande deleted");
        contentTable.getChildren().clear();
        ArrayList<Commande> commandes = Commande.getCommandesInitialize();
        commandes.forEach(commande -> {
            try {
                createLine(commande);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Récupère un article lié a une commande.
     *
     * @param c est la commande en question.
     * @return l'article lié à la commande en paramètre.
     * @throws SQLException
     */
    public Article getArticle(Commande c) throws SQLException {
        Article a = Article.article.getById(c.getArticleId());
        return a;
    }

    public void showConfirmationDialog(int numCommande) {
        descDialog.setText("Voulez vous vraiment supprimer la commande n°" + numCommande);
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
