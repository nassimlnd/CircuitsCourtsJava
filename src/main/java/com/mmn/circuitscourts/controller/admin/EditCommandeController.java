package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Producteur;
import com.mmn.circuitscourts.services.ClientDAO;
import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.services.ProducteurDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditCommandeController {
    public static int commandeId = 0;
    @FXML
    TextField quantite, horaire;
    @FXML
    ComboBox<String> client, numSiret, article;
    @FXML
    DatePicker datePicker;
    @FXML
    Label title;

    public Commande getCommande() throws SQLException {
        Commande c = Commande.getCommandeById(commandeId);
        return c;
    }

    public Article getArticle(Commande c) throws SQLException {
        Article a = Article.article.getById(c.getArticleId());
        return a;
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
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        ArrayList<Article> lesArticles = marketplaceDAO.getAll();
        ArrayList<String> namesArticles = new ArrayList<>();
        for (Article art : lesArticles) {
            namesArticles.add(art.getId() + "-" + art.getName());
        }
        article.getItems().addAll(namesArticles);
    }

    public void getNumSiretInitialize() throws SQLException {
        ArrayList<Producteur> producteurs = Producteur.producteurDAO.getAll();
        ArrayList<String> numSirets = new ArrayList<>();
        for (Producteur prd : producteurs) {
            numSirets.add(String.valueOf(prd.getNumSiret()));
        }
        numSiret.getItems().addAll(numSirets);
    }

    public void initialize() throws SQLException {
        title.setText("Modification de la commande n°"+ getCommande().getNumCommande());
        getArticlesInitialize();
        clientInitialize();
        getNumSiretInitialize();
        quantite.setText(String.valueOf(getCommande().getQuantity()));
        horaire.setText(getCommande().getHoraireDebut() + "-" + getCommande().getHoraireFin());
    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminCommandeInterface();
    }

    public void onEditButton() throws SQLException {
        int idArticle = Integer.parseInt(article.getValue().split("-")[0]);
        String[] horaires = horaire.getText().split("-");
        String horaireDebut = horaires[0];
        String horaireFin = horaires[1];
        String[] idClient = client.getValue().split("-");
        int quantity = Integer.parseInt(quantite.getText());
        int idC = Integer.parseInt(idClient[0]);
        int finalNumSiret = Integer.parseInt(numSiret.getValue());
        Commande c = new Commande(commandeId, idArticle, getCommande().getPoids(), quantity, horaireDebut, horaireFin, idC, finalNumSiret, getCommande().getDateCommande());
        CommandeDAO cmd = new CommandeDAO();
        if (cmd.update(commandeId, c)) {
            System.out.println("[DEBUG]Commande uptate");
        }
        ViewFactory.getInstance().showAdminCommandeInterface();

    }

}
