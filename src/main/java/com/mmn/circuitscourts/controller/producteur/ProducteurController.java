package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ProducteurController {
    @FXML
    Label activeTab;

    @FXML
    Button disconnectButton;
    @FXML
    VBox disconnectContainer;

    @FXML
    BorderPane container;

    @FXML
    SVGPath svgPath;

    @FXML
    public void initialize() {
        activeTab.getStyleClass().add("sidebar-tab-active");

        Region icon = new Region();
        disconnectButton.getStyleClass().add("sidebar-profil-disconnect-button");
        disconnectButton.setPickOnBounds(true);
        icon.getStyleClass().add("icon");
        disconnectButton.setGraphic(icon);

        //resize(svgPath, 24, 24);
    }

    private void resize(SVGPath svg, double width, double height) {

        double originalWidth = svg.prefWidth(-1);
        double originalHeight = svg.prefHeight(originalWidth);

        double scaleX = width / originalWidth;
        double scaleY = height / originalHeight;

        svg.setScaleX(scaleX);
        svg.setScaleY(scaleY);
    }

    public void onDisconnect() {
        ViewFactory.getInstance().showLoginInterface();
    }

    @FXML
    public void onDisconnectButtonClick() throws IOException {
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

    }
}
