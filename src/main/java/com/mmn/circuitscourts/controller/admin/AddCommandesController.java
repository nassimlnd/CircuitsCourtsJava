package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.*;
import com.mmn.circuitscourts.services.ClientDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Controller qui gère la vue AddCommande.fxml
 */
public class AddCommandesController {

    @FXML
    Button plusHourDebut, plusMinutesDebut, minusHourDebut, minusMinutesDebut, plusHourFin, plusMinutesFin, minusMinutesFin, minusHourFin;
    @FXML
    DatePicker date;
    @FXML
    TextField quantite;
    @FXML
    ComboBox<String> entreprises, client, article;
    @FXML
    Label hourDebut, minutesDebut, hourFin, minutesFin, popupMessage;
    @FXML
    VBox errorPopup;


    public void initialize() throws SQLException {
        articleInitialize();
        clientInitialize();
        siretInitialize();
    }

    /**
     * Récupère toutes les informations entrées dans les inputs, et créer une commande avec.
     * Renvoie sur la page des Commandes et affiche la popup correspondantes à la réussite de l'opération
     */
    public void onCreateButton() throws SQLException {
        if (!(entreprises.getValue() == null) && !(client.getValue() == null) && !(article.getValue() == null)){
            if(!quantite.getText().isEmpty()){
                if(!date.getValue().equals("")){
                    try {
                        String horaireDebut = hourDebut.getText() + ":" + minutesDebut.getText() + ":00";
                        String horaireFin = hourFin.getText() + ":" + minutesFin.getText() + ":00";
                        if (!Time.valueOf(horaireDebut).toLocalTime().isAfter(Time.valueOf(horaireFin).toLocalTime())){
                            int quantity = Integer.parseInt(quantite.getText());

                            int idArticle = Integer.parseInt(article.getValue().split("-")[0]);

                            Article a = Article.article.getById(idArticle);
                            double poids = a.getWeight() * Integer.parseInt(quantite.getText());

                            int idClient = Integer.parseInt(client.getValue().split("-")[0]);

                            long finalNumSiret = Long.parseLong(entreprises.getValue().split("-")[0]);
                            Commande commande = new Commande(idArticle, poids, quantity, horaireDebut, horaireFin, idClient, finalNumSiret, date.getValue());
                            System.out.println("[DEBUG]Commande added.");

                            ViewFactory.getInstance().showAdminCommandeInterface();
                            CommandesController.showSuccessPopUp("Commande ajoutée !", "La commande n°" + commande.getNumCommande() + " a bien été ajoutée !");
                        }else showErrorPopup("L'horaire de début est avant celle de fin.");
                    }catch (NumberFormatException e){
                        showErrorPopup("Veuillez saisir uniquement des chiffres dans quantité");
                    }
                }else showErrorPopup("Veuillez selectionner une date.");
            }else showErrorPopup("La quantité doit être un entier supérieur à 0");
        }else showErrorPopup("Vous devez remplir tous les champs !");
    }

    /**
     * Fonction de retour vers la vue commande de l'administrateur.
     */
    public void onBackButton() {
        ViewFactory.getInstance().showAdminCommandeInterface();
    }

    /**
     * Fonction qui initialise les clients dans la comboBox afin de pouvoir selectionner le client correspondant à la commande.
     *
     * @throws SQLException Renvoie une exception si la requête échoue.
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


    public void articleInitialize() throws SQLException {
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        ArrayList<Article> lesArticles = marketplaceDAO.getAll();
        ArrayList<String> namesArticles = new ArrayList<>();
        for (Article art : lesArticles) {
            namesArticles.add(art.getId() + "-" + art.getName());
        }
        article.getItems().addAll(namesArticles);
    }

    public void siretInitialize() throws SQLException {
        ArrayList<Entreprise> entreprise = Entreprise.entrepriseDAO.getAll();
        ArrayList<String> list = new ArrayList<>();
        entreprise.forEach(p -> {
            list.add(p.getNumSiret() + "-" + p.getProprietaire().getNom());
        });
        entreprises.getItems().addAll(list);
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





