package com.mmn.circuitscourts.controller.entreprise;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.controller.admin.VehiculeController;
import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AddVehiculeController {

    @FXML
    TextField numImmat, poids;
    @FXML
    VBox errorPopup;
    @FXML
    Label popupMessage;

    public void onBackButton() {
        ViewFactory.getInstance().showProdVehiculesInterface();
    }

    public void onCreateButton() throws SQLException {
        if(!numImmat.getText().equals("") && !poids.getText().equals("")){
            if(numImmat.getText().matches("^[A-Z]{2}-\\d{3}-[A-Z]{2}$")){
                ArrayList<Vehicule> v = Vehicule.vehiculeDAO.getAll();
                ArrayList<String> numImmats = new ArrayList<>();
                for (Vehicule vehicule: v) {
                    numImmats.add(vehicule.getNumImmat());
                }
                if(!numImmats.contains(numImmat.getText())){
                    try{
                        Entreprise entreprise = Entreprise.entrepriseDAO.getByAccountId(App.userConnected.getId());
                        long numSiret = entreprise.getNumSiret();
                        Vehicule vehicule = new Vehicule(String.valueOf(numImmat.getText()), parseInt(poids.getText()), numSiret);
                        ViewFactory.getInstance().showProdVehiculesInterface();
                        VehiculesController.showSuccessPopUp("Véhicule ajouté !", "Le véhicule immatriculé : " + vehicule.getNumImmat() + " a bien été ajouté !");
                    }catch(NumberFormatException e){
                        System.out.println(e);
                    }
                } else showErrorPopup("Un véhicule avec la même plaque d'immatriculation existe déjà.");
            }else showErrorPopup("Le format de la plaque d'immatriculation est incorrect.");
        }else showErrorPopup("Vous devez remplir tous les champs !");
    }

    public void onClosePopup() {
        errorPopup.setVisible(false);
    }

    public void showErrorPopup(String message) {
        errorPopup.setVisible(true);
        popupMessage.setText(message);
    }

}
