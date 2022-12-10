package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AdminSidebarController {

    @FXML
    Label userName;
    @FXML
    Label userGrade;
    @FXML
    Button dashboardTab;
    @FXML
    Button commandesTab;
    @FXML
    Button tourneeTab;
    @FXML
    Button vehiculesTab;
    @FXML
    Button producteurTab;
    @FXML
    Button clientTab;
    @FXML
    Button accountsTab;

    public void initialize() {
        String title = ViewFactory.getInstance().getMainStage().getTitle();
        userName.setText(App.userConnected.getIdentifiant());
        userGrade.setText(App.userConnected.getGradeName());

        switch (title) {
            case ("[ADMIN]CircuitsCourts - Tableau de bord"):
                dashboardTab.getStyleClass().add("sidebar-tab-active");
                break;
            case ("[ADMIN]CircuitsCourts - Commandes"):
                commandesTab.getStyleClass().add("sidebar-tab-active");
                break;
            case ("[ADMIN]CircuitsCourts - Tournées"):
                tourneeTab.getStyleClass().add("sidebar-tab-active");
                break;
            case ("[ADMIN]CircuitsCourts - Véhicules"):
                vehiculesTab.getStyleClass().add("sidebar-tab-active");
                break;
            case ("[ADMIN]CircuitsCourts - Producteur"):
                producteurTab.getStyleClass().add("sidebar-tab-active");
                break;
            case ("[ADMIN]CircuitsCourts - Client"):
                clientTab.getStyleClass().add("sidebar-tab-active");
                break;
            case ("[ADMIN]CircuitsCourts - Comptes"):
                accountsTab.getStyleClass().add("sidebar-tab-active");

        }
    }

    public void onDisconnect() {
        App.userConnected = null;
        System.out.println("[DEBUG]User disconnected.");
        ViewFactory.getInstance().showLoginInterface();
    }

    public void onDashboardButton() {

    }

    public void onCommandesButton() {

    }

    public void onTourneeButton() {

    }

    public void onVehiculesButton() {

    }

    public void onProducteurButton() {

    }

    public void onClientButton() {

    }

    public void onAccountButton() {

    }
}
