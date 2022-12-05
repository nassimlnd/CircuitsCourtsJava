package com.mmn.circuitscourts.views;

import com.mmn.circuitscourts.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Interface de connexion
 */
public class LoginInterface {

    //static Class appClass = App.class;

    FXMLLoader loader;
    Scene scene;
    String title;

    public LoginInterface() throws IOException {
        this.title = "CircuitsCourts - Connexion";
        this.loader = new FXMLLoader(App.class.getResource("fxml/Login/Login.fxml"));
        this.scene = new Scene(loader.load(), 1000, 700);
        this.scene.getStylesheets().add(App.class.getResource("css/style.css").toString());
    }

    public String getTitle() {
        return title;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Scene getScene() {
        return scene;
    }
}
