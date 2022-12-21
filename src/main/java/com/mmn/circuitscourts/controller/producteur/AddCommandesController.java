package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.controller.producteur.CommandesController;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Producteur;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.services.ClientDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddCommandesController {

    @FXML
    TextField quantite, horaire, poids;

    @FXML
    ComboBox<String> article;
    @FXML
    ComboBox<String> client;

    @FXML
    DatePicker date;

    public void onCreateButton() throws SQLException {
        int idArticle = Integer.parseInt(article.getValue().split("-")[0]);
        String[] horaires = horaire.getText().split("-");
        String horaireDebut = horaires[0];
        String horaireFin = horaires[1];
        int idClient = Integer.parseInt(client.getValue().split("-")[0]);
        int quantity = Integer.parseInt(quantite.getText());

        Commande c = new Commande(idArticle, Double.parseDouble(poids.getText()), quantity, horaire.getText(), horaire.getText(), idClient, Producteur.producteurDAO.getByAccountId(App.userConnected.getId()).getNumSiret());
        CommandesController.showSuccessPopUp();
        ViewFactory.getInstance().showProdCommandesInterface();
    }

    /**
     * prend l'id du client et le concatène avec le nom de ce client pour mettre dans la ComboBox;
     */
    public void clientInitialize() throws SQLException {
        ClientDAO clientDAO = new ClientDAO();
        ArrayList<Client> lesClients = clientDAO.getAll();
        ArrayList<String> names = new ArrayList<>();
        for (Client client : lesClients) {
            names.add(client.getId()+"-"+client.getNom());
        }
        System.out.println(names);
        client.getItems().addAll(names);
        client.setValue(names.get(0));
    }
    public void onBackButton() {
        ViewFactory.getInstance().showProdCommandesInterface();
    }

}
