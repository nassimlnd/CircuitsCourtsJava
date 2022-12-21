package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.*;
import com.mmn.circuitscourts.services.*;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditCommandeController {
    @FXML
    TextField quantite, horaire;

    @FXML
    ComboBox<String> client, numSiret, article;

    @FXML
    DatePicker datePicker;

    public static int commandeId = 0;

    public  Commande getCommande() throws SQLException {
        Commande c = Commande.getCommandeById(commandeId);
        return c;
    }

    public Article getArticle(Commande c) throws SQLException {
        Article a = Article.article.getById(c.getArticleId());
        return a;
    }

    public void clientInitialize() throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> lesClients = clientDAO.getAll();
        ArrayList<String> names = new ArrayList<>();
        for (Client client : lesClients) {
            names.add(client.getId()+"-"+client.getNom());
        }
        client.getItems().addAll(names);
    }
    public void getArticlesInitialize() throws SQLException {
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        ArrayList<Article> lesArticles = marketplaceDAO.getAll();
        ArrayList<String> namesArticles = new ArrayList<>();
        for(Article art : lesArticles){
            namesArticles.add(art.getId()+"-"+art.getName());
        }
        article.getItems().addAll(namesArticles);
    }

    public void getNumSiretInitialize() throws SQLException {
        ProducteurDAO producteur = new ProducteurDAO();
        ArrayList<Producteur> lesProducteurs = producteur.getAll();
        ArrayList<String> numSirets = new ArrayList<>();
        for (Producteur prd : lesProducteurs) {
            System.out.println(prd.getNumSiret());
            numSirets.add(String.valueOf(prd.getNumSiret()));
        }
        numSiret.getItems().addAll(numSirets);
    }

    public void initialize() throws SQLException {
        getArticlesInitialize();
        clientInitialize();
        getNumSiretInitialize();
        quantite.setText("");
        horaire.setText(getCommande().getHoraireDebut()+"-"+getCommande().getHoraireFin());
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
        if (cmd.update(commandeId, c)){
            System.out.println("[DEBUG]Commande uptate");
        }
        ViewFactory.getInstance().showAdminCommandeInterface();

    }

}
