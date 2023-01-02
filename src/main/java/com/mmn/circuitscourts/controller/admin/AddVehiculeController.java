package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.controller.producteur.VehiculesController;
import com.mmn.circuitscourts.models.Producteur;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.services.AccountDAO;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AddVehiculeController {
    @FXML
    TextField numImmat, poids;
    @FXML
    ComboBox<String> namesProd;
    public void onBackButton() {
        ViewFactory.getInstance().showAdminVehiculeInterface();
    }


    /**
     * Récupère une liste de string contenant les nom des producteurs et leurs numéro de Siret.
     * Initialise dans la ComboBox tous les noms récupérés.
     * @throws SQLException
     */
    public void getNomProdInitialize() throws SQLException {
        ArrayList<String> namesAndSiret = User.accountDAO.getAllproducteursNameAndSiret();
        ArrayList<String> numSirets = new ArrayList<>();
        for (String names : namesAndSiret) {
            numSirets.add(names);
        }
        namesProd.getItems().addAll(numSirets);
    }


    /**
     * Appel de la méthode getNomProdInitialize() lors du chargement de la page.
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        getNomProdInitialize();
    }

    /**
     * Vérifie lors de la création du nouveau véhicule les regex, nottament celui de la plaque d'immatriculation.
     * numImmat doit etre composé de 2 lettres en majuscules puis 3 chiffres puis 2 lettres en majuscules, le tout séparés par des tirets.
     * Crée le nouveau véhicules si le numéro d'immatriculation est valide.
     * @throws SQLException
     */
    public void onCreateButton() throws SQLException {
        String[] immat = numImmat.getText().split("-");
        boolean identique=false;
        if(immat[0].matches("([A-Z][A-Z])")&&immat[1].matches("([0-9][0-9][0-9])")&&immat[2].matches("([A-Z][A-Z])")&&parseInt(poids.getText())>0){
            ArrayList<Vehicule> v = new ArrayList<>();
            v=Vehicule.vehiculeDAO.getAll();
            for(int i=0;i<v.size();i++){
                if(v.get(i).getNumImmat().equals(numImmat.getText())){
                    identique=true;
                    break;
                }
            }
            if(identique==false) {
                int numSiret = Integer.parseInt(namesProd.getValue().split("-")[1]);
                Vehicule vehicule = new Vehicule(String.valueOf(numImmat.getText()), parseInt(poids.getText()), numSiret);
                ViewFactory.getInstance().showProdVehiculesInterface();
                VehiculesController.showSuccessPopUp(vehicule.getNumImmat());
            }
        }
    }
}
