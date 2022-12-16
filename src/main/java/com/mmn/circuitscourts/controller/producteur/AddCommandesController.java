package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.controller.producteur.CommandesController;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.User;
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
    TextField article, quantite, horaire, poids;

    @FXML
    ComboBox<String> client;

    @FXML
    DatePicker date;

    public void onCreateButton() throws SQLException {
        String[] idClient = client.getValue().split("-");
        int id = Integer.parseInt(idClient[0]);
        Commande c = new Commande(article.getText(), Integer.parseInt(poids.getText()),horaire.getText(), horaire.getText(), id, 1 );
        CommandesController.showSuccessPopUp();
        c.addCommandeToDb(c);
        ViewFactory.getInstance().showProdCommandesInterface();
    }

    /**
     * prend l'id du client et le concatène avec le nom de ce client pour mettre dans la ComboBox;
     */
    public void clientInitialize() throws SQLException {
        ArrayList<User> lesClients = Client.getClientsInitialize();
        ArrayList<String> names = new ArrayList<>();
        for (User u : lesClients) {
            names.add(u.getId()+"-"+u.getIdentifiant());
        }
        System.out.println(names);
        client.getItems().addAll(names);
        client.setValue(names.get(0));
    }
    public void onBackButton() {
        ViewFactory.getInstance().showProdCommandesInterface();
    }

}
