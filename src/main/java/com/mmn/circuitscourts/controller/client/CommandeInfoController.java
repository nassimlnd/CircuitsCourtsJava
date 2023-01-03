package com.mmn.circuitscourts.controller.client;

import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.services.CommandeDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.services.EntrepriseDAO;
import com.mmn.circuitscourts.services.TourneeDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.sql.SQLException;

public class CommandeInfoController {

    @FXML
    Label mainTitle;
    @FXML
    Label articleName;
    @FXML
    Label totalWeight;
    @FXML
    Label entrepriseName;
    @FXML
    Label dateCommande;
    @FXML
    Label secondState;
    @FXML
    Region secondStateImg;
    @FXML
    Label thirdState;
    @FXML
    Region thirdStateImg;
    public static int commandeId = 0;

    public void initialize() throws SQLException {
        mainTitle.setText("Commande n°" + commandeId);
        setDetails();
        setState();
    }

    public void onBackButton() {
        ViewFactory.getInstance().showClientCommandesInterface();
    }

    public void setDetails() throws SQLException {
        CommandeDAO commandeDAO = new CommandeDAO();
        Commande commande = commandeDAO.getById(commandeId);
        dateCommande.setText(String.valueOf(commande.getDateCommande()));

        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        Article article = marketplaceDAO.getById(commande.getArticleId());
        articleName.setText(article.getName());

        EntrepriseDAO entrepriseDAO = new EntrepriseDAO();
        Entreprise entreprise = entrepriseDAO.getById(commande.getNumSiret());
        entrepriseName.setText(entreprise.getProprietaire().getNom());

        totalWeight.setText(commande.getPoids() + " kg");
    }

    public void setState() throws SQLException {
        CommandeDAO commandeDAO = new CommandeDAO();
        Commande commande = commandeDAO.getById(commandeId);
        TourneeDAO tourneeDAO = new TourneeDAO();

        if (String.valueOf(commande.getIdTournee()).equals("null")) {

        } else if (commande.getIdTournee() > 0) {
            secondState.getStyleClass().clear();
            secondState.getStyleClass().add("client-commande-state-done");
            secondStateImg.getStyleClass().clear();
            secondStateImg.getStyleClass().add("client-commande-second-state-img-done");
        } else if (commande.getIdTournee() > 0) {
            Tournee tournee = tourneeDAO.getById(commande.getIdTournee());
        }
    }
}
