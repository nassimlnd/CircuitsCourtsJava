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


    public void getNomProdInitialize() throws SQLException {

        ArrayList<String> namesAndSiret = User.accountDAO.getAllproducteursNameAndSiret();
        ArrayList<String> names = new ArrayList();
        for (String s : namesAndSiret) {
           names.add(s);
        }
        namesProd.getItems().addAll(names);
    }

    public void initialize() throws SQLException {
        getNomProdInitialize();
    }

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
                int numSiret = Vehicule.getNumSiretConnected();
                Vehicule vehicule = new Vehicule(String.valueOf(numImmat.getText()), parseInt(poids.getText()), numSiret);
                ViewFactory.getInstance().showProdVehiculesInterface();
                VehiculesController.showSuccessPopUp(vehicule.getNumImmat());
            }
        }
    }
}
