package com.mmn.circuitscourts.controller.entreprise;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditCommandeController {

    @FXML
    Button plusHourDebut, plusMinutesDebut, minusHourDebut, minusMinutesDebut, plusHourFin, plusMinutesFin, minusMinutesFin, minusHourFin;
    @FXML
    Label hourDebut, minutesDebut, hourFin, minutesFin;
    @FXML
    TextField quantite;
    @FXML
    ComboBox<String>  article;

    public static int commandeId;

    public void initialize() throws SQLException {
        getArticlesInitialize();
        initHoraireModule();
        quantite.setText(String.valueOf(getCommande().getQuantity()));
    }

    public void initHoraireModule() {
        try {
            hourDebut.setText(getCommande().getHoraireDebut().split(":")[0]);
            minutesDebut.setText(getCommande().getHoraireDebut().split(":")[1]);
            hourFin.setText(getCommande().getHoraireFin().split(":")[0]);
            minutesFin.setText(getCommande().getHoraireFin().split(":")[1]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Commande getCommande() throws SQLException {
        Commande c = Commande.getCommandeById(commandeId);
        return c;
    }
    public Article getArticle(Commande c) throws SQLException {
        Article a = Article.article.getById(c.getArticleId());
        return a;
    }
    public void getArticlesInitialize() throws SQLException {
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        ArrayList<Article> lesArticles = marketplaceDAO.getAll();
        ArrayList<String> namesArticles = new ArrayList<>();
        for(Article art : lesArticles){
            namesArticles.add(art.getId()+"-"+art.getName());
        }
        article.getItems().addAll(namesArticles);
        article.setValue(marketplaceDAO.getById(getCommande().getArticleId()).getId()+"-"+marketplaceDAO.getById(getCommande().getArticleId()).getName());
    }
    public void getQuantiteInitialize(){

    }
    public void onBackButton() {
        ViewFactory.getInstance().showProdCommandesInterface();
    }

    public void onEditButton(MouseEvent mouseEvent) throws SQLException {
        int idArticle = Integer.parseInt(article.getValue().split("-")[0]);
        String horaireDebut = hourDebut.getText()+":"+minutesDebut.getText()+":00";
        String horaireFin = hourFin.getText()+":"+minutesFin.getText()+":00";
        int quantity = Integer.parseInt(quantite.getText());
        int idC = getCommande().getIdClient();
        int finalNumSiret = getCommande().getNumSiret();
        Commande c = new Commande(commandeId, idArticle, getCommande().getPoids(), quantity, horaireDebut, horaireFin, idC, finalNumSiret, getCommande().getDateCommande());
        CommandeDAO cmd = new CommandeDAO();
        if (cmd.update(commandeId, c)){
            System.out.println("[DEBUG]Commande uptate");
        }
        ViewFactory.getInstance().showProdCommandesInterface();;
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
