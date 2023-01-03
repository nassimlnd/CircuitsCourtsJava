package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class EditCommandeController {
    public static int commandeId = 0;

    @FXML
    Button plusHourDebut, plusMinutesDebut, minusHourDebut, minusMinutesDebut, plusHourFin, plusMinutesFin, minusMinutesFin, minusHourFin;
    @FXML
    TextField quantite;
    @FXML
    ComboBox<String> client, entreprise, article;
    @FXML
    DatePicker date;
    @FXML
    Label title, hourDebut, minutesDebut, hourFin, minutesFin;


    public void initialize() throws SQLException {
        title.setText("Modification de la commande n°"+ getCommande().getNumCommande());

        getArticlesInitialize();
        clientInitialize();
        getNumSiretInitialize();
        initFields();
    }

    public Commande getCommande() throws SQLException {
        Commande c = Commande.getCommandeById(commandeId);
        return c;
    }

    public void clientInitialize() throws SQLException {
        ArrayList<Client> clients = Client.client.getAll();
        ArrayList<String> noms = new ArrayList<>();
        for (Client client : clients) {
            noms.add(client.getId() + "-" + client.getNom());
        }
        client.getItems().addAll(noms);
    }

    public void getArticlesInitialize() throws SQLException {
        ArrayList<Article> lesArticles = Article.article.getAll();
        ArrayList<String> namesArticles = new ArrayList<>();
        for (Article art : lesArticles) {
            namesArticles.add(art.getId() + "-" + art.getName());
        }
        article.getItems().addAll(namesArticles);
    }

    public void getNumSiretInitialize() throws SQLException {
        ArrayList<Entreprise> entreprises = Entreprise.entrepriseDAO.getAll();
        ArrayList<String> numSirets = new ArrayList<>();
        for (Entreprise entreprise : entreprises) {
            numSirets.add(entreprise.getNumSiret()+"-"+entreprise.getProprietaire().getNom());
        }
        entreprise.getItems().addAll(numSirets);
    }

    public void initFields() {
        try {
            Commande commande = getCommande();
            Article article1 = getArticle(commande);
            Client client1 = Client.client.getById(commande.getIdClient());
            Entreprise entreprise1 = Entreprise.entrepriseDAO.getById(commande.getNumSiret());

            hourDebut.setText(commande.getHoraireDebut().split(":")[0]);
            minutesDebut.setText(commande.getHoraireDebut().split(":")[1]);
            hourFin.setText(commande.getHoraireFin().split(":")[0]);
            minutesFin.setText(commande.getHoraireFin().split(":")[1]);

            quantite.setText(String.valueOf(commande.getQuantity()));
            date.setValue(commande.getDateCommande());
            article.setValue(article1.getId()+"-"+article1.getName());
            client.setValue(client1.getId()+"-"+client1.getNom());
            entreprise.setValue(entreprise1.getNumSiret()+"-"+entreprise1.getProprietaire().getNom());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Article getArticle(Commande c) throws SQLException {
        Article a = Article.article.getById(c.getArticleId());
        return a;
    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminCommandeInterface();
    }

    public void onEditButton() throws SQLException {
        if (!(entreprise.getValue() == null) && !(client.getValue() == null) && !(article.getValue() == null)){
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

                            int finalNumSiret = Integer.parseInt(entreprise.getValue().split("-")[0]);
                            Commande c = new Commande(commandeId,idArticle, poids, quantity, horaireDebut, horaireFin, idClient, finalNumSiret, date.getValue());
                            Commande.cmd.update(commandeId, c);
                            System.out.println("[DEBUG]Commande n°"+ commandeId +" updated.");

                            ViewFactory.getInstance().showAdminCommandeInterface();
                            //CommandesController.showSuccessPopUp();
                        }else System.out.println("[DEBUG]Error : horaire début après l'horaire de fin.");
                    }catch (NumberFormatException e){
                        System.out.println("error NumberFormatException");
                    }
                }else System.out.println("[DEBUG]Error : choisir une date.");
            }else System.out.println("[DEBUG]Error : la quantié doit être un entier.");
        }else System.out.println("[DEBUG]Error : tous les champs sont vides");
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

}
