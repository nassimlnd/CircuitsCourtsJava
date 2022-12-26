package com.mmn.circuitscourts.views;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.controller.admin.EditClientController;
import com.mmn.circuitscourts.controller.client.CommandeInfoController;
import com.mmn.circuitscourts.controller.client.NewCommandeController;
import com.mmn.circuitscourts.controller.producteur.EditCommandeController;
import com.mmn.circuitscourts.controller.producteur.EditTourneeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

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
        createScene(loader, "[ADMIN]CircuitsCourts - Ajout d'une commande");
    }

    //=======new=========
    public void showAdminAddTourneeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/AddTournees.fxml"));
        System.out.println("[DEBUG]AdminAddTourneesInterface loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Ajout d'une tournée");
    }
    //========new==========
    public void showAdminAddVehiculeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/AddVehicule.fxml"));
        System.out.println("[DEBUG]AdminAddVehiculeInterface loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Ajout d'un véhicule");
    }

    public void showProducteurAddVehiculeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/AddVehicule.fxml"));
        System.out.println("[DEBUG]AdminAddVehiculeInterface loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Ajout d'une tournée");
    }

    public void showProducteurAddTourneeInterface(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/AddTournees.fxml"));
        System.out.println("[DEBUG]ProducteurAddTournee loaded.");
        createScene(loader, "CircuitsCourts - Ajout d'une tournée");
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

    public void showProdArticlesInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/Articles.fxml"));
        System.out.println("[DEBUG]ProdArticles loaded.");
        createScene(loader, "CircuitsCourts - Articles");
    }

    public void showProdAddArticlesInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/AddArticles.fxml"));
        System.out.println("[DEBUG]ProdAddArticles loaded.");
        createScene(loader, "CircuitsCourts - Ajout d'un article");
    }

    public void showClientMarketplaceInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Client/Marketplace.fxml"));
        System.out.println("[DEBUG]ClientMarketPlace loaded.");
        createScene(loader, "CircuitsCourts - Marketplace");

    }

    public void showAdminProducteurInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/Producteur.fxml"));
        System.out.println("[DEBUG]AdminProducteur loaded.");
        createScene(loader, "[ADMIN]CircuitsCourts - Producteurs");
    }

    public void showClientProfilInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Client/Profil.fxml"));
        System.out.println("[DEBUG]ClientProfil loaded.");
        createScene(loader, "CircuitsCourts - Profil");
    }

    public void showClientNewCommandeInterface(int id) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Client/NewCommande.fxml"));
        System.out.println("[DEBUG]ClientNewCommande loaded.");
        NewCommandeController.articleId = id;
        createScene(loader, "CircuitsCourts - Nouvelle commande");
    }

    public void showClientCommandesInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Client/Commandes.fxml"));
        System.out.println("[DEBUG]ClientCommandes loaded.");
        createScene(loader, "CircuitsCourts - Mes commandes");
    }

    public void showClientCommandesInfoInterface(int id) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Client/CommandeInfo.fxml"));
        System.out.println("[DEBUG]ClientCommandeInfo loaded.");
        CommandeInfoController.commandeId = id;
        createScene(loader, "CircuitsCourts - Commande n°" + id);
    }

    public void showProdEditCommandeInterface(int id) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/EditCommande.fxml"));
        System.out.println("[DEBUG]ProdEditCommande loaded.");
        EditCommandeController.commandeId = id;
        createScene(loader, "CircuitsCourts - Modification de la commande n°" + id);
    }

    public void showAdminEditCommandeInterface(int numCommande) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/EditCommande.fxml"));
        System.out.println("[ADMIN]AdminEditCommande loaded.");
        com.mmn.circuitscourts.controller.admin.EditCommandeController.commandeId = numCommande;
        createScene(loader, "CircuitsCourts - Modification de la commande n°" + numCommande);
    }

    public void showAdminEditVehiculeInterface(String numImmat){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/EditVehicule.fxml"));
        System.out.println("[ADMIN]EditVehicule loaded.");
        com.mmn.circuitscourts.controller.admin.EditVehiculeController.numImmat = numImmat;
        createScene(loader, "CircuitsCourts - Modification du véhicule n°"+ numImmat);

    }

    public void showAdminEditProducteurInterface(int numSiret){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/EditProducteur.fxml"));
        System.out.println("[ADMIN]EditProducteur loaded.");
        com.mmn.circuitscourts.controller.admin.EditProducteurController.numSiret = numSiret;
        createScene(loader, "CircuitsCourts - Modification du producteur n°"+ numSiret);

    }

    public void showAdminAddProducteurInterface(){
        FXMLLoader loader = new FXMLLoader((App.class.getResource("fxml/Admin/AddProducteur.fxml")));
        System.out.println("[ADMIN]AddProducteur loaded.");
        createScene(loader, "CircuitsCourts - Ajout d'un producteur");
    }
    public void showProdEditVehiculeInterface(String numImmat){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/EditVehicule.fxml"));
        System.out.println("[PRODUCTEUR]EditVehicule loaded.");
        com.mmn.circuitscourts.controller.producteur.EditVehiculeController.numImmat = numImmat;
        createScene(loader, "CircuitsCourts - Modification du véhicule n°"+ numImmat);

    }
    public void showProdEditArticleInterface(int id){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/EditArticle.fxml"));
        System.out.println("[PRODUCTEUR]EditArticle loaded.");
        com.mmn.circuitscourts.controller.producteur.EditArticleController.id = id;
        createScene(loader, "CircuitsCourts - Modification du véhicule n°"+ id);

    }

    public void showAdminAddAccountInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/AddAccount.fxml"));
        System.out.println("[DEBUG]AddAccount loaded");
        createScene(loader , "CircuitsCourts - Ajout d'un compte");
    }

    public void showAdminAccountInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/Account.fxml"));
        System.out.println("[DEBUG]Account loaded.");
        createScene(loader, "CircuitsCourt - Comptes");
    }

    public void showAdminEditAccountInterface(int accountId) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/EditAccount.fxml"));
        System.out.println("[DEBUG]EditAccount loaded.");
        com.mmn.circuitscourts.controller.admin.EditAccountController.accountId = accountId;
        createScene(loader, "CircuitsCourts - Modification du compte n°"+ accountId);


    }

    public void showProdEditTourneeInterface(int id) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Producteur/EditTournee.fxml"));
        System.out.println("[DEBUG]EditTournee loaded.");
        EditTourneeController.tourneeId = id;
        createScene(loader, "CircuitsCourts - Modification de la tournée n°" + id);
    }

    public void showAdminEditClientInterface(int accountId) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/EditCLient.fxml"));
        System.out.println("[DEBUG]EditCLient loaded.");
        EditClientController.accountId = accountId;
        createScene(loader, "CircuitsCourts - Modification du client n°"+ accountId);


    }

    public void showAdminAddCLientInterface() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Admin/AddClient.fxml"));
        System.out.println("[DEBUG]AddClientInterface loaded.");
        createScene(loader, "CircuitsCourts - Ajout d'un client");
    }
}

