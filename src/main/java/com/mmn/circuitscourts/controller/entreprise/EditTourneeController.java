package com.mmn.circuitscourts.controller.entreprise;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.application.Platform;
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
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class EditTourneeController {
    public static int tourneeId = 0;
    @FXML
    VBox chooserContentTable, chooser, contentTable, errorPopup, loadingContainer;
    @FXML
    Label hourDebut, hourFin, minutesDebut, minutesFin, totalWeight, popupMessage, mainTitle;
    @FXML
    ComboBox<String> vehiculeCb;
    @FXML
    DatePicker datePicker;

    private ArrayList<Commande> commandes = Commande.cmd.getAllByTournee(tourneeId);

    public EditTourneeController() throws SQLException {
    }

    public void initialize() {
        mainTitle.setText("Modification de la tournée n°"+tourneeId);
        initVehicules();
        initCommandesTable();
        initClockModule();
        datePicker.setValue(getTournee().getDate());
    }

    private void initClockModule() {
        hourDebut.setText(getTournee().getHoraireDebut().split(":")[0]);
        minutesDebut.setText(getTournee().getHoraireDebut().split(":")[1]);
        hourFin.setText(getTournee().getHoraireFin().split(":")[0]);
        minutesFin.setText(getTournee().getHoraireFin().split(":")[1]);
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
        errorPopup.setVisible(false);
    }

    /**
     * Initiliase les véhicules dans la comboBox
     */
    private void initVehicules() {
        try {
            ArrayList<Vehicule> vehicules = Vehicule.vehiculeDAO.getAllByEntreprise(Entreprise.entrepriseDAO.getByAccountId(App.userConnected.getId()).getNumSiret());
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
        commandes.remove(commande);
        initCommandesTable();
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
        loadingContainer.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initChooserTable();
                loadingContainer.setVisible(false);
                chooser.setVisible(true);
            }
        });
    }

    private void initChooserTable() {
        try {
            ArrayList<Commande> commandes = Commande.getCommandesInitialize();
            chooserContentTable.getChildren().clear();
            commandes.forEach(commande -> {
                if (!(commande.getIdTournee() > 0)) {
                    createChooserLine(commande);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createChooserLine(Commande commande) {
        HBox line = new HBox();
        line.setAlignment(Pos.CENTER_LEFT);
        line.setMinHeight(64);
        line.setPrefHeight(64);
        line.setMaxWidth(818);
        line.setPadding(new Insets(0, 0, 0, 40));
        line.getStyleClass().add("commande-tableview-line");
        line.setStyle("-fx-cursor: hand");
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

        line.setOnMouseClicked(mouseEvent -> {
            onAdd(commande);
            initCommandesTable();
            chooser.setVisible(false);
        });

        chooserContentTable.getChildren().add(line);
    }

    public void onAdd(Commande commande) {
        Boolean contains = false;
        for (Commande c : commandes) {
            if (c.getNumCommande() == commande.getNumCommande()) {
                contains = true;
            }
        }
        if (commandes.isEmpty() || !contains) {
            commandes.add(commande);
        } else {
            showErrorPopup("La commande est déjà présente dans la tournée");
        }
    }

    private void showErrorPopup(String message) {
        popupMessage.setText(message);
        errorPopup.setVisible(true);
    }

    public void onEdit(MouseEvent mouseEvent) {
        String horaireDebut = hourDebut.getText()+":"+minutesDebut.getText()+":00";
        String horaireFin = hourFin.getText()+":"+minutesFin.getText()+":00";

        if (commandes.isEmpty()) {
            showErrorPopup("Il n'y a pas de commandes dans votre tournée !");
            return;
        }

        if (vehiculeCb.getValue() == null) {
            showErrorPopup("Vous devez selectionner un véhicule !");
            return;
        }

        double totalWeight = 0;
        LocalDate localDate = datePicker.getValue();
        boolean isBefore = false;
        for (Commande commande : commandes) {
            totalWeight += commande.getPoids();
            if (localDate.isBefore(commande.getDateCommande())) {
                isBefore = true;
            }
        }

        if (isBefore) {
            showErrorPopup("La date de la tournée est avant celle d'une \ncommande.");
            return;
        }

        if (Time.valueOf(horaireDebut).before(Time.valueOf(horaireFin))) {
            try {
                Vehicule vehicule = Vehicule.vehiculeDAO.getById(vehiculeCb.getValue().split(" - ")[0]);
                if (vehicule.getPoidsMax() < totalWeight) {
                    showErrorPopup("Le poids total est supérieur à ce que peut \nsupporter le véhicule");
                    return;
                }
                Tournee tournee = new Tournee(tourneeId, datePicker.getValue(), horaireDebut, horaireFin, Entreprise.entrepriseDAO.getByAccountId(App.userConnected.getId()).getNumSiret(), vehicule.getNumImmat());
                Commande.cmd.getAll().forEach(commande -> {
                    if (commande.getIdTournee() == tourneeId) {
                        commande.setIdTournee(0);
                    }
                });
                commandes.forEach(commande -> commande.setIdTournee(tournee.getId()));
                Tournee.tourneeDAO.update(tourneeId, tournee);
                System.out.println("ok");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ViewFactory.getInstance().showProdTourneeInterface();
            TourneeController.showPopupSuccess("Tournée modifiée !", "La tournée n°" + tourneeId + " a bien été modifiée.");
        } else {
            showErrorPopup("L'horaire de fin est avant celui de début.");
        }
    }

    public void onClose(MouseEvent mouseEvent) {
        chooser.setVisible(false);
    }
}
