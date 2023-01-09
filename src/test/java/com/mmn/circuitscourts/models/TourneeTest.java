package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.services.TourneeDAO;
import javafx.application.Application;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TourneeTest {

    @Test
    @Order(1)
    public void testGetters() {
        // Créez un objet Tournee à tester
        Tournee tournee = new Tournee(1, LocalDate.now(), "08:00", "12:00", (long)111222333444555.0 , "AB-123-CD");

        // Vérifiez que les getters renvoient les valeurs attendues
        assertEquals(1, tournee.getId());
        assertEquals(LocalDate.now(), tournee.getDate());
        assertEquals("08:00", tournee.getHoraireDebut());
        assertEquals("12:00", tournee.getHoraireFin());
        assertEquals((long)111222333444555.0, tournee.getNumSiret());
        assertEquals("AB-123-CD", tournee.getNumImmat());
    }

    @Test
    @Order(2)
    public void testSetters() {
        // Créez un objet Tournee à tester
        Tournee tournee = new Tournee(1, LocalDate.now(), "08:00", "12:00", (long)111222333444555.0, "AB-123-CD");

        // Modifiez les valeurs de l'objet Tournee avec les setters
        tournee.setId(2);
        tournee.setDate(LocalDate.now());
        tournee.setHoraireDebut("09:00");
        tournee.setHoraireFin("13:00");
        tournee.setNumSiret((long)111222333444555.0);
        tournee.setNumImmat("CD-123-AB");

        // Vérifiez que les setters ont bien modifié les valeurs de l'objet Tournee
        assertEquals(2, tournee.getId());
        assertEquals(LocalDate.now(), tournee.getDate());
        assertEquals("09:00", tournee.getHoraireDebut());
        assertEquals("13:00", tournee.getHoraireFin());
        assertEquals((long)111222333444555.0, tournee.getNumSiret());
        assertEquals("CD-123-AB", tournee.getNumImmat());
    }


    @Test
    @Order(3)
    void TestGetTourneeList() throws SQLException {
        Application.launch(App.class);
        TourneeDAO tDao = new TourneeDAO();
        // Créez une liste de tournées à insérer dans la base de données avant le test
        Tournee tournee1 = new Tournee(LocalDate.now(), "08:00:00", "12:00:00", (long)11122233344456.0, "AA-123-AA");
        Tournee tournee2 = new Tournee(LocalDate.now(), "09:00:00", "13:00:00", (long)11122233344456.0, "AA-123-AA");
        Tournee tournee3 = new Tournee(LocalDate.now(), "10:00:00", "14:00:00", (long)11122233344456.0, "AA-123-AA");

        // Récupérez la liste de toutes les tournées de la base de données
        ArrayList<Tournee> tournees = Tournee.getTourneeList();

        // on verifie que la tournee est bien dans la arrayList
        assertEquals(tournees.get(tournee1.getId()-1).toString(),tournee1.toString());
        assertEquals(tournees.get(tournee2.getId()-1).toString(),tournee2.toString());
        assertEquals(tournees.get(tournee3.getId()-1).toString(),tournee3.toString());
    }


    @Test
    @Order(4)
    void testGetTourneeByEntreprise() throws SQLException {
        Tournee tournee1 = new Tournee(LocalDate.now(), "08:00:00", "12:00:00", (long)11122233344455.0, "AA-123-AA");
        Tournee tournee2 = new Tournee(LocalDate.now(), "09:00:00", "13:00:00", (long)11122233344455.0, "AA-123-AA");
        Tournee tournee3 = new Tournee(LocalDate.now(), "10:00:00", "14:00:00", (long)11122233344455.0, "AA-123-AA");

        ArrayList<Tournee> tournees = Tournee.getTourneeByEntreprise();
        long UserNumSiret=Entreprise.entrepriseDAO.getByAccountId(App.userConnected.getId()).getNumSiret();
        boolean verif =true;

        for(int i =0;i<tournees.size();i++){

            if(tournees.get(i).getNumSiret()==UserNumSiret){

            }else{
                verif=false;
                break;
            }
        }
        assertTrue(verif);

    }
    @Test
    public void testToString() {
        // Créer un objet Tournee
        Tournee tournee = new Tournee(1, LocalDate.of(2020, 1, 1), "09:00", "17:00", (long)11122233344455.0, "AA-123-AA");

        // Appeler la méthode à tester
        String result = tournee.toString();

        // Créer la chaîne de caractères attendue
        String expected = "Tournee{id=1, date=2020-01-01, horaireDebut='09:00', horaireFin='17:00', numSiret=11122233344455, numImmat='AA-123-AA'}";

        // Vérifier que la méthode retourne la chaîne de caractères attendue
        assertEquals(expected, result);
    }
}