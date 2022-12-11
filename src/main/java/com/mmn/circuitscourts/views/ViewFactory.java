package com.mmn.circuitscourts.views;

import com.mmn.circuitscourts.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;

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

    public Stage getMainStage() {
        return mainStage;
    }

    public void showLoginInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Login/Login.fxml"));
        System.out.println("[DEBUG]Login loaded.");
        createScene(loader, "CircuitsCourts - Connexion");
    }

    public void showProdDashboardInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/Dashboard.fxml"));
        System.out.println("[DEBUG]ProducteurDashboard loaded.");
        createScene(loader, "CircuitsCourts - Tableau de bord");
    }

    public void createScene(FXMLLoader loader, String title) {
        mainStage.setTitle(title);
        Scene scene = null;
        try {
            scene = new Scene(loader.load(), 1200, 800);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.getStylesheets().add(App.class.getResource("css/style.css").toString());
        mainStage.setScene(scene);
    }

    public static ViewFactory getInstance() {
        return viewFactory;
    }

    public void showProdCommandesInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/Commandes.fxml"));
        System.out.println("[DEBUG]ProducteurCommandes loaded.");
        createScene(loader, "CircuitsCourts - Commandes");
    }

    public void showProdVehiculesInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/Vehicules.fxml"));
        System.out.println("[DEBUG]ProducteurVehicules loaded.");
        createScene(loader, "CircuitsCourts - Véhicules");
    }

    public void showProdTourneeInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/Tournees.fxml"));
        System.out.println("[DEBUG]ProducteurTournee loaded.");
        createScene(loader, "CircuitsCourts - Tournées");
    }

    public void showAdminDashboardInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/Dashboard.fxml"));
        System.out.println("[DEBUG]AdminDashboard loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Tableau de bord");
    }

    public void showProdAddCommandeInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/AddCommande.fxml"));
        System.out.println("[DEBUG]ProducteurAddCommande loaded.");
        createScene(loader, "CircuitsCourts - Ajout de commande");
    }
}
