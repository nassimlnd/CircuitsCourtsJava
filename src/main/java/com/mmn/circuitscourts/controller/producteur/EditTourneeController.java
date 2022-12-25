package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditTourneeController {
    public static int tourneeId = 0;
    @FXML
    VBox chooserContentTable, chooser, contentTable, errorPopup, loadingContainer;
    @FXML
    Label hourDebut, hourFin, minutesDebut, minutesFin, totalWeight;
    @FXML
    ComboBox<String> vehiculeCb;
    @FXML
    DatePicker datePicker;

    private ArrayList<Commande> commandes = new ArrayList<>();

    public void initialize() {
        initVehicules();
        initCommandesTable();
    }

    public Tournee getTournee() {
        try {
            return Tournee.tourneeDAO.getById(tourneeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBackButton() {
        ViewFactory.getInstance().showProdTourneeInterface();
    }

    public void onClosePopup(MouseEvent mouseEvent) {
    }

    /**
     * Initiliase les véhicules dans la comboBox
     */
    private void initVehicules() {
        try {
            ArrayList<Vehicule> vehicules = Vehicule.vehiculeDAO.getAllByProducteur(App.userConnected.getId());
            ArrayList<String> values = new ArrayList<>();
            vehicules.forEach(vehicule -> values.add(vehicule.getNumImmat() + " - PoidsMax: " + vehicule.getPoidsMax() + " kg"));
            vehiculeCb.getItems().addAll(values);
            if (getTournee().getNumImmat() != null) {
                vehiculeCb.setValue(getTournee().getNumImmat() + " - PoidsMax: " + Vehicule.vehiculeDAO.getById(getTournee().getNumImmat()).getPoidsMax() + " kg");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du chargement des véhicules");
            ;
        }
    }

    /**
     * Initialise les commandes et le poids total de la tournée
     */
    private void initCommandesTable() {
        contentTable.getChildren().clear();
        commandes.forEach(commande -> createCommandeLine(commande));

        double weight = 0;
        for (Commande commande : commandes) {
            weight += commande.getPoids();
        }
        totalWeight.setText(weight + " kg");
    }

    public void createCommandeLine(Commande commande) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setMinHeight(64);
        line.setMaxWidth(394);
        line.setPadding(new Insets(0, 0, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        Label text = new Label("Commande n°" + commande.getNumCommande());
        line.getChildren().add(text);

        Button delete = new Button();
        delete.getStyleClass().add("delete-button");
        Region deleteImg = new Region();
        deleteImg.getStyleClass().add("delete-button-img");
        delete.setGraphic(deleteImg);
        delete.setPickOnBounds(true);
        HBox.setMargin(delete, new Insets(0, 0, 0, 60));
        delete.setOnMouseClicked(event -> {
            onDelete(commande);
        });

        line.getChildren().add(delete);

        contentTable.getChildren().add(line);
    }

    private void onDelete(Commande commande) {
    }

    /**
     * Ajoute une heure au compteur
     */
    public void onPlusHourDebut() {
        int hour = Integer.parseInt(hourDebut.getText());
        if (hour < 23) {
            hour++;
            hourDebut.setText(String.valueOf(hour));
        } else if (hour == 23) {
            hour = 00;
            hourDebut.setText(String.valueOf(hour));
        }
    }

    /**
     * Ajoute une minute au compteur
     */
    public void onPlusMinutesDebut() {
        int minutes = Integer.parseInt(minutesDebut.getText());
        if (minutes < 59) {
            minutes++;
            minutesDebut.setText(String.valueOf(minutes));
        } else if (minutes == 59) {
            minutes = 0;
            minutesDebut.setText(String.valueOf(minutes));
        }
    }

    /**
     * Retire une heure au compteur
     */
    public void onMinusHourDebut() {
        int hour = Integer.parseInt(hourDebut.getText());
        if (hour > 0) {
            hour--;
            hourDebut.setText(String.valueOf(hour));
        } else if (hour == 0) {
            hour = 23;
            hourDebut.setText(String.valueOf(hour));
        }
    }

    /**
     * Retire une minute au compteur
     */
    public void onMinusMinutesDebut() {
        int minutes = Integer.parseInt(minutesDebut.getText());
        if (minutes > 0) {
            minutes--;
            minutesDebut.setText(String.valueOf(minutes));
        } else if (minutes == 0) {
            minutes = 59;
            minutesDebut.setText(String.valueOf(minutes));
        }
    }

    /**
     * Ajoute une heure au compteur
     */
    public void onPlusHourFin() {
        int hour = Integer.parseInt(hourFin.getText());
        if (hour < 23) {
            hour++;
            hourFin.setText(String.valueOf(hour));
        } else if (hour == 23) {
            hour = 00;
            hourFin.setText(String.valueOf(hour));
        }
    }

    /**
     * Ajoute une minute au compteur
     */
    public void onPlusMinutesFin() {
        int minutes = Integer.parseInt(minutesFin.getText());
        if (minutes < 59) {
            minutes++;
            minutesFin.setText(String.valueOf(minutes));
        } else if (minutes == 59) {
            minutes = 0;
            minutesFin.setText(String.valueOf(minutes));
        }
    }

    /**
     * Retire une heure au compteur
     */
    public void onMinusHourFin() {
        int hour = Integer.parseInt(hourFin.getText());
        if (hour > 0) {
            hour--;
            hourFin.setText(String.valueOf(hour));
        } else if (hour == 0) {
            hour = 23;
            hourFin.setText(String.valueOf(hour));
        }
    }

    /**
     * Retire une minute au compteur
     */
    public void onMinusMinutesFin() {
        int minutes = Integer.parseInt(minutesFin.getText());
        if (minutes > 0) {
            minutes--;
            minutesFin.setText(String.valueOf(minutes));
        } else if (minutes == 0) {
            minutes = 59;
            minutesFin.setText(String.valueOf(minutes));
        }
    }

    public void onAddButton(MouseEvent mouseEvent) {
    }

    public void onCreate(MouseEvent mouseEvent) {
    }

    public void onClose(MouseEvent mouseEvent) {
    }
}
