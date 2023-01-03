package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditClientController {

    /**
     * id du compte à modifier.
     */
    public static int clientId = 0;

    @FXML
    Label title;
    @FXML
    TextField nom, email, numTel, adresse, codePostal, city;

    /**
     * Récupère le compte à modifier.
     *
     * @return
     * @throws SQLException
     */
    public Client getThisClient() {
        try {
            return Client.client.getById(clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void onBackButton() {
        ViewFactory.getInstance().showAdminClientInterface();
    }

    /**
     * Préremplit les champs des informations du compe à modifier.
     *
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        title.setText("Modification du client n°" + clientId);

        textFieldInit();
    }

    /**
     * Initialise tous les textFields avec les valeurs actuelles du client
     */
    public void textFieldInit() {
        nom.setText(getThisClient().getNom());
        email.setText(getThisClient().getEmail());
        numTel.setText(getThisClient().getNumTel());

        adresse.setText(getThisClient().getAdresse().split(":")[0]);
        codePostal.setText(getThisClient().getAdresse().split(":")[1]);
        city.setText(getThisClient().getAdresse().split(":")[2]);
    }

    /**
     * Crée un nouveau compte sans id et update avec ce nouveau compte.
     * Renvoi à la page précédente une fois l'update effectuée.
     *
     * @throws SQLException
     */
    public void onEditButton() throws SQLException {
        if(nom.getText().matches("^[a-zA-ZÀ-ÖØ-öø-ÿ]+(([',. -][a-zA-ZÀ-ÖØ-öø-ÿ])?[a-zA-ZÀ-ÖØ-öø-ÿ]*)*$")){
            if(email.getText().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")){
                if (numTel.getText().matches("^\\+?[0-9]{2,3} ?[0-9]{6,}$")){
                    if(city.getText().matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$")) {
                        if(codePostal.getText().matches("^(F-)?((2[A|B])|[0-9]{2})[0-9]{3}$")){
                            if(adresse.getText().matches("^[0-9]{1,5}[A-Za-z .,'-]*$")){
                                String adresseFormated = adresse.getText() + ":" + codePostal.getText() + ":" + city.getText();
                                Client client = new Client(clientId, nom.getText(),  adresseFormated, numTel.getText(), email.getText());
                                Client.client.update(clientId, client);
                                System.out.println("[DEBUG]account n°" + clientId + " updated.");
                                ViewFactory.getInstance().showAdminClientInterface();
                            }else System.out.println("[DEBUG]Error : adresse");
                        }else System.out.println("[DEBUG]Error : code postal, de la forme F-75000 ou 75000");
                    }else System.out.println("[DEBUG]Error : city name");
                }else System.out.println("[DEBUG]Eror : numéro de tel format 0601020304");
            }else System.out.println("[DEBUG]Eror : email incorrect, de la forme nom.utilisateur@domaine.com");
        }else System.out.println("[DEBUG]Error : nom incorrect");
    }
}

