package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AddVehiculeController {
    @FXML
    TextField numImmat, poids;
    @FXML
    ComboBox<String> namesProd;
    @FXML
    VBox errorPopup;
    @FXML
    Label popupMessage;


    /**
     * Appel de la méthode getNomProdInitialize() lors du chargement de la page.
     *
     * @throws SQLException
     */
    public void initialize() throws SQLException {
        getEntreprises();
    }

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
    public void getEntreprises() throws SQLException {
        ArrayList<Entreprise> entreprises = Entreprise.entrepriseDAO.getAll();
        ArrayList<String> values = new ArrayList<>();

        entreprises.forEach(entreprise -> values.add(entreprise.getNumSiret() + "-" + entreprise.getProprietaire().getNom()));

        namesProd.getItems().addAll(values);
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
        if(!numImmat.getText().equals("") && !poids.getText().equals("") && !(namesProd.getValue() == null)){
            if(numImmat.getText().matches("^[A-Z]{2}-\\d{3}-[A-Z]{2}$")){
                ArrayList<Vehicule> v = Vehicule.vehiculeDAO.getAll();
                ArrayList<String> numImmats = new ArrayList<>();
                for (Vehicule vehicule: v) {
                    numImmats.add(vehicule.getNumImmat());
                }
                if(!numImmats.contains(numImmat.getText())){
                    try{
                        long numSiret = Long.parseLong(namesProd.getValue().split("-")[0]);
                        Vehicule vehicule = new Vehicule(String.valueOf(numImmat.getText()), parseInt(poids.getText()), numSiret);
                        ViewFactory.getInstance().showAdminVehiculeInterface();
                        VehiculeController.showSuccessPopUp("Véhicule ajouté !", "Le véhicule immatriculé : " + vehicule.getNumImmat() + " a bien \nété ajouté !");
                    }catch(NumberFormatException e){
                        System.out.println(e);
                    }
                } else showErrorPopup("Un véhicule avec la même immatriculation \nexiste déjà.");
            }else showErrorPopup("Le format de la plaque est invalide.\nIl doit être sous la forme : AA-123-BB");
        }else showErrorPopup("Vous devez remplir tous les champs !");
    }

    public void showErrorPopup(String message) {
        popupMessage.setText(message);
        errorPopup.setVisible(true);
    }

    public void onClosePopup() {
        errorPopup.setVisible(false);
    }

}




