package com.mmn.circuitscourts.controller.producteur;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Vehicule;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AddVehiculeController {

    @FXML
    TextField numImmat, poids;

    @FXML
    Button onCreateButton;
    public void onBackButton(MouseEvent mouseEvent) {
        ViewFactory.getInstance().showProdVehiculesInterface();
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
