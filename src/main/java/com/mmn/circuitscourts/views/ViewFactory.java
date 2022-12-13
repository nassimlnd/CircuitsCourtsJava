package com.mmn.circuitscourts.views;

import com.mmn.circuitscourts.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;

/**
 * Les méthodes show() servent à créer insstancier nouvelle scène depuis un fichier FXML.
 * Pour cela il faut créer un FXMLLoader qui prend en paramètre les chemin du fichier fxml correspondant.
 * Puis une scène est créée avec le loader et un titre.
 */
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
            e.printStackTrace();
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

    //PAS DE FICHIER FXML COMMANDES DANS LE PROJET
    public void showAdminClientInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/Client.fxml"));
        System.out.println("[DEBUG]AdminClientInterface loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Clients");
    }

    //==============new============
    public void showAdminCommandeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/Commandes.fxml"));
        System.out.println("[DEBUG]AdminCommandeInterface loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Commandes");
    }

    //============new ===========
    public void showAdminTourneeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/Tournees.fxml"));
        System.out.println("[DEBUG]AdminTourneeInterface loaded");
        createScene(loader, "[ADMIN]CircuitsCourts - Tournées");
    }

    //=============new ===============
    public void showAdminVehiculeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/Vehicules.fxml"));
        System.out.println("[DEBUG]AdminVehiculeInterface loaded");
        createScene(loader, "[ADMIN]CircuitsCourts - Véhicules");
    }

    //=========new ==============
    public void showAdminAddCommandeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/AddCommande.fxml"));
        System.out.println("[DEBUG]AdminAddCommandesInterface loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Ajouter Commandes");
    }

    //=======new=========
    public void showAdminAddTourneeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/AddTournees.fxml"));
        System.out.println("[DEBUG]AdminAddTourneesInterface loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Ajouter Tournées");
    }
    //========new==========
    public void showAdminAddVehiculeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/AddVehicule.fxml"));
        System.out.println("[DEBUG]AdminAddVehiculeInterface loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Ajouter Véhicules");
    }

    public void showProdAddCommandeInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/AddCommande.fxml"));
        System.out.println("[DEBUG]ProducteurAddCommande loaded.");
        createScene(loader, "CircuitsCourts - Ajout de commande");
    }

    public void showClientDashboardInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Client/Dashboard.fxml"));
        System.out.println("[DEBUG]ClientDashboard loaded.");
        createScene(loader, "CircuitsCourts - Tableau de bord");
    }
}
