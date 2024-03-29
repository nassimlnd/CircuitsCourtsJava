package com.mmn.circuitscourts.controller.entreprise;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.*;
import com.mmn.circuitscourts.services.ClientDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;


public class AddCommandesController {
    @FXML
    Button plusHourDebut, plusMinutesDebut, minusHourDebut, minusMinutesDebut, plusHourFin, plusMinutesFin, minusMinutesFin, minusHourFin;
    @FXML
    Label hourDebut, minutesDebut, hourFin, minutesFin, popupMessage;
    @FXML
    TextField quantite;
    @FXML
    ComboBox<String> article, client;
    @FXML
    DatePicker date;
    @FXML
    VBox errorPopup;

    public void initialize() throws SQLException {
        clientInitialize();
        articleInitialise();
    }

    public void onCreateButton() throws SQLException {
        if (!(article.getValue() == null) && !(client.getValue() == null) && !quantite.getText().equals("") && !date.getValue().equals("") && !hourDebut.getText().equals("") && !hourFin.getText().equals("")) {
            if (Integer.parseInt(hourFin.getText()) > Integer.parseInt(hourDebut.getText())) {
                try {
                    int idArticle = Integer.parseInt(article.getValue().split("-")[0]);
                    String horaireDebut = hourDebut.getText() + ":" + minutesDebut.getText() + ":00";
                    String horaireFin = hourFin.getText() + ":" + minutesFin.getText() + ":00";
                    int idClient = Integer.parseInt(client.getValue().split("-")[0]);
                    int quantity = Integer.parseInt(quantite.getText());
                    Article article = Article.article.getById(idArticle);
                    Commande commande = new Commande(idArticle, (article.getWeight() * quantity), quantity, horaireDebut, horaireFin, idClient, Entreprise.entrepriseDAO.getByAccountId(App.userConnected.getId()).getNumSiret(), date.getValue());
                    ViewFactory.getInstance().showProdCommandesInterface();
                    CommandesController.showSuccessPopUp("Commande ajoutée !", "La commande n°" + commande.getNumCommande() + " a bien été ajoutée !");
                } catch (NumberFormatException e) {
                    showErrorPopup(e.getMessage());
                }
            } else showErrorPopup("L'horaire de début est avant celle de fin.");
        } else showErrorPopup("Vous devez remplir tous les champs !");
    }

    /**
     * prend l'id du client et le concatène avec le nom de ce client pour mettre dans la ComboBox;
     */
    public void clientInitialize() throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> lesClients = clientDAO.getAll();
        ArrayList<String> names = new ArrayList<>();
        for (Client client : lesClients) {
            names.add(client.getId() + "-" + client.getNom());
        }
        client.getItems().addAll(names);
        client.setValue(names.get(0));
    }

    public void articleInitialise() throws SQLException {

        MarketplaceDAO comDAO = new MarketplaceDAO();
        ArrayList<Article> articles = comDAO.getAll();
        ArrayList<String> names = new ArrayList<>();
        for (Article article1 : articles) {
            names.add(article1.getId() + "-" + article1.getName());
        }
        article.getItems().addAll(names);
    }

    public void onBackButton() {
        ViewFactory.getInstance().showProdCommandesInterface();
    }

    /**
     * Ajoute une heure au compteur
     */
    public void onPlusHourDebut() {
        int hour = Integer.parseInt(hourDebut.getText());
        if (hour < 23) {
            hour++;
            hourDebut.setText(String.valueOf(hour));
        } else if (hour == 23) {
            hour = 00;
            hourDebut.setText(String.valueOf(hour));
        }
    }

    /**
     * Ajoute une minute au compteur
     */
    public void onPlusMinutesDebut() {
        int minutes = Integer.parseInt(minutesDebut.getText());
        if (minutes < 59) {
            minutes++;
            minutesDebut.setText(String.valueOf(minutes));
        } else if (minutes == 59) {
            minutes = 0;
            minutesDebut.setText(String.valueOf(minutes));
        }
    }

    /**
     * Retire une heure au compteur
     */
    public void onMinusHourDebut() {
        int hour = Integer.parseInt(hourDebut.getText());
        if (hour > 0) {
            hour--;
            hourDebut.setText(String.valueOf(hour));
        } else if (hour == 0) {
            hour = 23;
            hourDebut.setText(String.valueOf(hour));
        }
    }

    /**
     * Retire une minute au compteur
     */
    public void onMinusMinutesDebut() {
        int minutes = Integer.parseInt(minutesDebut.getText());
        if (minutes > 0) {
            minutes--;
            minutesDebut.setText(String.valueOf(minutes));
        } else if (minutes == 0) {
            minutes = 59;
            minutesDebut.setText(String.valueOf(minutes));
        }
    }

    /**
     * Ajoute une heure au compteur
     */
    public void onPlusHourFin() {
        int hour = Integer.parseInt(hourFin.getText());
        if (hour < 23) {
            hour++;
            hourFin.setText(String.valueOf(hour));
        } else if (hour == 23) {
            hour = 00;
            hourFin.setText(String.valueOf(hour));
        }
    }

    /**
     * Ajoute une minute au compteur
     */
    public void onPlusMinutesFin() {
        int minutes = Integer.parseInt(minutesFin.getText());
        if (minutes < 59) {
            minutes++;
            minutesFin.setText(String.valueOf(minutes));
        } else if (minutes == 59) {
            minutes = 0;
            minutesFin.setText(String.valueOf(minutes));
        }
    }

    /**
     * Retire une heure au compteur
     */
    public void onMinusHourFin() {
        int hour = Integer.parseInt(hourFin.getText());
        if (hour > 0) {
            hour--;
            hourFin.setText(String.valueOf(hour));
        } else if (hour == 0) {
            hour = 23;
            hourFin.setText(String.valueOf(hour));
        }
    }

    /**
     * Retire une minute au compteur
     */
    public void onMinusMinutesFin() {
        int minutes = Integer.parseInt(minutesFin.getText());
        if (minutes > 0) {
            minutes--;
            minutesFin.setText(String.valueOf(minutes));
        } else if (minutes == 0) {
            minutes = 59;
            minutesFin.setText(String.valueOf(minutes));
        }
    }

    public void onClosePopup() {
        errorPopup.setVisible(false);
    }

    public void showErrorPopup(String message) {
        errorPopup.setVisible(true);
        popupMessage.setText(message);
    }
}
