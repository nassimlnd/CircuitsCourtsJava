package com.mmn.circuitscourts.views;

import com.mmn.circuitscourts.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    public static ViewFactory viewFactory;
    private Stage mainStage;

    public ViewFactory(Stage stage) {
        this.mainStage = stage;
        this.mainStage.setResizable(false);
        this.mainStage.setMinHeight(700);
        this.mainStage.setMinWidth(1000);
        Image icon = new Image(App.class.getResource("images/icon.png").toExternalForm());
        this.mainStage.getIcons().add(icon);
        viewFactory = this;
    }

    public void showLoginInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Login/Login.fxml"));
        createScene(loader, "CircuitsCourts - Connexion");
    }

    public void showProducteurInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/Dashboard.fxml"));
        createScene(loader, "CircuitsCourts - Tableau de bord");
    }

    public void createScene(FXMLLoader loader, String title) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 1000, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.getStylesheets().add(App.class.getResource("css/style.css").toString());
        mainStage.setScene(scene);
        mainStage.setTitle(title);
    }

    public static ViewFactory getInstance() {
        return viewFactory;
    }
}
