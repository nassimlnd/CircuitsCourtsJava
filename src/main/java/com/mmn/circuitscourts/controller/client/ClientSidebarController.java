package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ClientSidebarController {
    @FXML
    Button dashboardTab;
    @FXML
    Button profilTab;
    @FXML
    Button commandesTab;
    @FXML
    Button adressesTab;
    @FXML
    Button marketplaceTab;
    @FXML
    Button ticketTab;

    @FXML
    Label userName;
    @FXML
    Label userGrade;

    public void initialize() {
        String title = ViewFactory.getInstance().getMainStage().getTitle();
        userName.setText(App.userConnected.getIdentifiant());
        userGrade.setText(App.userConnected.getGradeName());

        switch (title) {
            case "CircuitsCourts - Tableau de bord" :
                dashboardTab.getStyleClass().add("sidebar-tab-active");
                break;
            case "CircuitsCourts - Profil" :
                profilTab.getStyleClass().add("sidebar-tab-active");
                break;
            case "CircuitsCourts - Mes commandes" :
                commandesTab.getStyleClass().add("sidebar-tab-active");
                break;
            case "CircuitsCourts - Mes adresses" :
                adressesTab.getStyleClass().add("sidebar-tab-active");
                break;
            case "CircuitsCourts - Marketplace" :
                marketplaceTab.getStyleClass().add("sidebar-tab-active");
                break;
            case "CircuitsCourts - Ouvrir un ticket" :
                ticketTab.getStyleClass().add("sidebar-tab-active");
                break;
        }
    }

    public void onDisconnect() {

    }

    public void onDashboardButton() {
    }

    public void onProfilButton() {
    }

    public void onCommandesButton() {
    }

    public void onAdressesButton() {
    }

    public void onMarketplaceButton() {
    }

    public void onTicketButton() {
    }
}