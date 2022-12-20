package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

public class EditCommandeController {
    @FXML
    TextField horaire,quantite;


    @FXML
    ComboBox<String>  article;

    public static int commandeId ;

    public void initialize() throws SQLException {
        getArticlesInitialize();
        quantite.setText("");
        horaire.setText(getCommande().getHoraireDebut()+"-"+getCommande().getHoraireFin());
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
        String[] horaires = horaire.getText().split("-");
        String horaireDebut = horaires[0];
        String horaireFin = horaires[1];
        int idC = getCommande().getIdClient();
        int finalNumSiret = getCommande().getNumSiret();
        Commande c = new Commande(commandeId, idArticle, getCommande().getPoids(), horaireDebut, horaireFin, idC, finalNumSiret);
        CommandeDAO cmd = new CommandeDAO();
        if (cmd.update(commandeId, c)){
            System.out.println("[DEBUG]Commande uptate");
        }
        ViewFactory.getInstance().showProdCommandesInterface();;
    }

}
