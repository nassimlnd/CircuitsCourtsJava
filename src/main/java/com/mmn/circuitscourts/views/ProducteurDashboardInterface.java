package com.mmn.circuitscourts.views;

import com.mmn.circuitscourts.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ProducteurDashboardInterface {

    FXMLLoader loader;
    Scene scene;
    String title;

    public ProducteurDashboardInterface() throws IOException {
        this.title = "CircuitsCourts - Tableau de bord";
        this.loader = new FXMLLoader(App.class.getResource("fxml/Producteur/Dashboard.fxml"));
        this.scene = new Scene(loader.load(), 1000, 700);
        this.scene.getStylesheets().add(App.class.getResource("css/style.css").toString());
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public String getTitle() {
        return title;
    }

    public Scene getScene() {
        return scene;
    }
}
