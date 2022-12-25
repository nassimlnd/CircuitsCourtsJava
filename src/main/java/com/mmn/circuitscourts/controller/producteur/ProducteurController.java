package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.LogsDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProducteurController {

    @FXML
    Button disconnectButton;
    @FXML
    VBox contentFeed;

    @FXML
    StackPane stackPane;

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
            logs.forEach(log -> createFeedLine(log));
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
        title.setPrefWidth(678);
        title.setPrefHeight(20);
        title.setStyle("-fx-font-size: 14");

        switch (log.get(2)) {
            case "newcommande" :
                title.setText("Nouvelle commande !");
                break;
            case "newclient" :
                title.setText("Nouveau client !");
                break;
            case "newtournee" :
                title.setText("Nouvelle tournée !");
                break;
        }

        Label subtitle = new Label("Subtitle");

        vBox.getChildren().add(title);
        vBox.getChildren().add(subtitle);
        line.getChildren().add(vBox);

        Label time = new Label("Time");
        HBox.setMargin(time, new Insets(0,0,10,0));

        if (Date.valueOf(log.get(4)) == Date.valueOf(LocalDate.now())) {

        } else {
            time.setText(String.valueOf(Date.valueOf(log.get(4)).getTime() - Date.valueOf(LocalDate.now()).getTime()));
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
