package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
     * Récupère une liste de string contenant les nom des entreprises et leurs
     * numéro de Siret.
     * Initialise dans la ComboBox tous les noms récupérés.
     * 
     * @throws SQLException
     */
    public void getNomProdInitialize() throws SQLException {
        ArrayList<String> namesAndSiret = User.accountDAO.getAllEntreprisesNameAndSiret();
        ArrayList<String> numSirets = new ArrayList<>();
        for (String names : namesAndSiret) {
            numSirets.add(names);
        }
        namesProd.getItems().addAll(numSirets);
    }

    /**
     * Appel de la méthode getNomProdInitialize() lors du chargement de la page.
     * 
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        getNomProdInitialize();
    }

    /**
     * Vérifie lors de la création du nouveau véhicule les regex, nottament celui de
     * la plaque d'immatriculation.
     * numImmat doit etre composé de 2 lettres en majuscules puis 3 chiffres puis 2
     * lettres en majuscules, le tout séparés par des tirets.
     * Crée le nouveau véhicules si le numéro d'immatriculation est valide.
     * 
     * @throws SQLException
     */
    public void onCreateButton() throws SQLException {
        if(!numImmat.getText().equals("") && !poids.getText().equals("") && !namesProd.getValue().equals("")){
            if(numImmat.getText().matches("^[A-Z]{2}-\\d{3}-[A-Z]{2}$")){
                ArrayList<Vehicule> v = Vehicule.vehiculeDAO.getAll();
                ArrayList<String> numImmats = new ArrayList<>();
                for (Vehicule vehicule: v) {
                    numImmats.add(vehicule.getNumImmat());
                }
                if(!numImmats.contains(numImmat.getText())){
                    try{
                        int numSiret = Integer.parseInt(namesProd.getValue().split("-")[1]);
                        Vehicule vehicule = new Vehicule(String.valueOf(numImmat.getText()), parseInt(poids.getText()), numSiret);
                        ViewFactory.getInstance().showAdminVehiculeInterface();
                        VehiculeController.showSuccessPopUp(vehicule.getNumImmat());
                    }catch(NumberFormatException e){
                        System.out.println(e);
                    }
                } else System.out.println("[DEBUG]Error : plaque déjà existante.");
            }else System.out.println("[DEBUG]Error : format de la plaque d'immatriculation AA-000-AA.");
        }else System.out.println("[DEBUG]Tous les champs doivent être saisis.");
    }

}




