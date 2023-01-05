package com.mmn.circuitscourts.controller.entreprise;

import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.services.LogsDAO;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;

public class EntrepriseController {

    @FXML
    VBox contentFeed;
    @FXML
    BorderPane container;
    @FXML
    Label commandesCount, tourneeCount;

    @FXML
    public void initialize() {
        try {
            commandesCount.setText(String.valueOf(Commande.cmd.countById()));
            tourneeCount.setText(String.valueOf(Tournee.tourneeDAO.countById()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        initFeed();
    }

    private void initFeed() {
        LogsDAO logsDAO = new LogsDAO();
        try {
            ArrayList<ArrayList<String>> logs = logsDAO.getAll();
            for (int i = logs.size() - 1; i >= 0; i--) {
                createFeedLine(logs.get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createFeedLine(ArrayList<String> log) {
        HBox line = new HBox();
        line.setPrefHeight(64);
        line.setAlignment(Pos.CENTER_LEFT);
        line.setPadding(new Insets(0,0,0,25));
        line.getStyleClass().add("dashboard-feed-line");
        VBox.setMargin(line, new Insets(10, 0, 0, 0));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label();
        title.setPrefWidth(640);
        title.setPrefHeight(20);
        title.setStyle("-fx-font-size: 14");

        Label subtitle = new Label("Subtitle");

        switch (log.get(2)) {
            case "newcommande" :
                title.setText("Nouvelle commande !");
                subtitle.setText("La commande n°" + log.get(3));
                break;
            case "newclient" :
                title.setText("Nouveau client !");
                subtitle.setText("Le client n°" + log.get(3));
                break;
            case "newtournee" :
                title.setText("Nouvelle tournée !");
                subtitle.setText("La tournée n°" + log.get(3));
                break;
        }

        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        line.getChildren().add(vBox);

        Label time = new Label("Time");
        HBox.setMargin(time, new Insets(0,0,10,0));

        if (Date.valueOf(log.get(4)) == Date.valueOf(LocalDate.now())) {

        } else {
            Period periode = Period.between(Date.valueOf(log.get(4)).toLocalDate(), LocalDate.now());
            if (periode.getDays() > 31) {
                if (periode.getMonths() > 12) {
                    time.setText("Il y a " + periode.getYears() + " année(s).");
                } else {
                    time.setText("Il y a " + periode.getMonths() + " mois.");
                }
            } else if (periode.getDays() == 0) {
                Time t1 = Time.valueOf(log.get(5));
                Time now = Time.valueOf(LocalTime.now());

                long diff = now.getTime() - t1.getTime();

                if ((diff / 1000) > 60) {
                    if (diff / (1000 * 60) > 60) {
                        time.setText("Il y a " + diff / (1000 * 60 * 60) + " heures");
                    } else time.setText("Il y a " + diff / (1000 * 60) + " minutes");
                } else time.setText("Il y a " + diff / 1000 + " secondes");
            } else {
                time.setText("Il y a " + periode.getDays() + " jour(s).");
            }
        }

        line.getChildren().add(time);

        contentFeed.getChildren().add(line);
    }

    private void resize(SVGPath svg, double width, double height) {

        double originalWidth = svg.prefWidth(-1);
        double originalHeight = svg.prefHeight(originalWidth);

        double scaleX = width / originalWidth;
        double scaleY = height / originalHeight;

        svg.setScaleX(scaleX);
        svg.setScaleY(scaleY);
    }


    /*public void onDisconnectButtonClick() throws IOException {
        disconnectButton.setDisable(true);
        disconnectContainer.setDisable(true);

        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Login/Login.fxml"));
        Parent root = loader.load();
        Scene scene = disconnectButton.getScene();
        root.translateXProperty().set(scene.getWidth());

        StackPane parentContainer = (StackPane) disconnectButton.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            ((Stage) scene.getWindow()).setTitle("CircuitsCourts - Tableau de bord");
            parentContainer.getChildren().remove(container);
        });
        timeline.play();

    }*/
}
