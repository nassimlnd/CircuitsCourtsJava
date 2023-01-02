package com.mmn.circuitscourts.controller.admin;
import com.mmn.circuitscourts.models.*;
import com.mmn.circuitscourts.services.AccountDAO;
import com.mmn.circuitscourts.services.ClientDAO;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller qui gère la vue AddCommande.fxml
 */
public class AddCommandesController {

    @FXML
    DatePicker datePicker;
    @FXML
    ComboBox<String> article;
    @FXML
    TextField quantite;
    @FXML
    ComboBox<String> client;
    @FXML
    TextField horaire;
    @FXML
    ComboBox<Integer> tournee;
    @FXML
    ComboBox<String> numSiret;

    /**
     * Récupère toutes les informations entrées dans les inputs, et créer une commande avec.
     * Renvoie sur la page des Commandes et affiche la popup correspondantes à la réussite de l'opération
     */
    public void onCreateButton() throws SQLException {
        int idArticle = Integer.parseInt(article.getValue().split("-")[0]);

        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        Article a = marketplaceDAO.getById(idArticle);
        int poids = (int) (a.getWeight()* Integer.parseInt(quantite.getText()));

        String[] horaires = horaire.getText().split("-");
        String horaireDebut = horaires[0];
        String horaireFin = horaires[1];
        int idC = Integer.parseInt(client.getValue().split("-")[0]);
        int quantity = Integer.parseInt(quantite.getText());
        int finalNumSiret = Integer.parseInt(numSiret.getValue().split("-")[1]);
        new Commande(idArticle, poids, quantity, horaireDebut, horaireFin, idC, finalNumSiret, datePicker.getValue());
        System.out.println("Commande added.");
        ViewFactory.getInstance().showAdminCommandeInterface();
        CommandesController.showSuccessPopUp();
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

    public void initialize() throws SQLException {
       articleInitialize();
       siretInitialize();
       tourneesInitialize();
       horaire.setText("-");
    }

    public void siretInitialize() throws SQLException {
        ArrayList<String> users = User.accountDAO.getAllproducteursNameAndSiret();
        numSiret.getItems().addAll(users);
    }

    public void tourneesInitialize() throws SQLException {
        ArrayList<Tournee> tournees = Tournee.tourneeDAO.getAll();
        ArrayList<Integer> idT = new ArrayList<>();
        for (Tournee t: tournees) {
            idT.add(t.getId());
        }
        tournee.getItems().addAll(idT);
    }

}





