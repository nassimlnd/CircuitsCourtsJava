package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarController {

    @FXML
    Button dashboardTab;
    @FXML
    Button commandesTab;
    @FXML
    Button tourneeTab;
    @FXML
    Button vehiculesTab;

    @FXML
    public void initialize() {
        String title = ViewFactory.getInstance().getMainStage().getTitle();
        System.out.println(title);

        switch (title) {
            case "CircuitsCourts - Tableau de bord" :
                dashboardTab.getStyleClass().add("sidebar-tab-active");
                break;
            case "CircuitsCourts - Commandes" :
                commandesTab.getStyleClass().add("sidebar-tab-active");
                break;
            case "CircuitsCourts - Tournées" :
                tourneeTab.getStyleClass().add("sidebar-tab-active");
                break;
            case "CircuitsCourts - Véhicules" :
                vehiculesTab.getStyleClass().add("sidebar-tab-active");
        }
    }

    public void onDisconnect() {
        ViewFactory.getInstance().showLoginInterface();
    }

    public void onDashboardButton() {
        String title = ViewFactory.getInstance().getMainStage().getTitle();
        if (title != "CircuitsCourts - Tableau de bord") {
            ViewFactory.getInstance().showProdDashboardInterface();
        }
    }

    public void onCommandesButton() {
        String title = ViewFactory.getInstance().getMainStage().getTitle();
        if (title != "CircuitsCourts - Commandes") {
            ViewFactory.getInstance().showProdCommandesInterface();
        }
    }

    public void onTourneeButton() {
        String title = ViewFactory.getInstance().getMainStage().getTitle();
        if (title != "CircuitsCourts - Tournées") {
            ViewFactory.getInstance().showProdTourneeInterface();
        }
    }

    public void onVehiculesButton() {
        String title = ViewFactory.getInstance().getMainStage().getTitle();
        if (title != "CircuitsCourts - Véhicules") {
            ViewFactory.getInstance().showProdVehiculesInterface();
        }
    }
}
