package com.mmn.circuitscourts.models;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.services.CommandeDAO;
import javafx.application.Application;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommandeTest {

    @Test
    @Order(1)
    void testGetters() throws SQLException {
        Commande cmd = new Commande(1, 2, 3.5, 1, "09:00", "17:00", 1,(long)11122233344456.0 , LocalDate.now());
        assertEquals(1, cmd.getNumCommande());
        assertEquals(2, cmd.getArticleId());
        assertEquals(3.5, cmd.getPoids());
        assertEquals(1, cmd.getQuantity());
        assertEquals("09:00", cmd.getHoraireDebut());
        assertEquals("17:00", cmd.getHoraireFin());
        assertEquals(1, cmd.getIdClient());
        assertEquals((long) (long)11122233344456.0, cmd.getNumSiret());
        assertEquals(LocalDate.now(), cmd.getDateCommande());
    }
    @Test
    @Order(2)
    void testSetters() throws SQLException {
        Commande cmd = new Commande(1, 2, 3.5, 1, "09:00", "17:00", 1, (long)11122233344456.0, LocalDate.now());
        cmd.setArticleId(3);
        cmd.setPoids(4.5);
        cmd.setQuantity(2);
        cmd.setHoraireDebut("10:00");
        cmd.setHoraireFin("18:00");
        cmd.setIdClient(2);
        cmd.setNumSiret((long) 11122233344456.0);
        assertEquals(3, cmd.getArticleId());
        assertEquals(4.5, cmd.getPoids());
        assertEquals(2, cmd.getQuantity());
        assertEquals("10:00", cmd.getHoraireDebut());
        assertEquals("18:00", cmd.getHoraireFin());
        assertEquals(2, cmd.getIdClient());
         assertEquals((long)11122233344456.0, cmd.getNumSiret());
    }




    @Test
    @Order(3)
    void testGetCommandesInitialize() throws SQLException {
        Application.launch(App.class);
        //vider la table commande de la base de données avant lancement;

        new Commande(1, 2, 3, "09:00:00", "10:00:00", 2, (long)11122233344455.0, LocalDate.now());
        new Commande(2, 3, 4, "10:00:00", "11:00:00", 6, (long)(11122233344456.0), LocalDate.now());
        new Commande(3, 4, 5, "11:00:00", "12:00:00", 7, (long)(11122233344456.0), LocalDate.now());

        // Appelez la méthode à tester
        ArrayList<Commande> commandes = Commande.getCommandesInitialize();

        // Vérifiez que la liste retournée a bien les 3 commandes ajoutées
        assertEquals(3, commandes.size());
        assertEquals(1, commandes.get(0).getNumCommande());
        assertEquals(2, commandes.get(1).getNumCommande());
        assertEquals(3, commandes.get(2).getNumCommande());
    }


    @Test
    @Order(4)
    void testGetCommandesInitializeByAccountId() throws SQLException {

        //on utilise les commande ajouter a la BD par la fonction getCommandesInitialize pour tester celle la
        ArrayList<Commande> commandes = Commande.getCommandesInitializeByAccountId();

        // Vérifiez que la liste retournée contient bien la commande liée au compte connecter
        assertEquals(1, commandes.size());
        assertEquals(1, commandes.get(0).getNumCommande());

    }

    @Test
    @Order(5)
    void testGetCommandeById() throws SQLException {
        Commande expectedCommande=new Commande(1, 2, 3, "09:00:00", "10:00:00", 2, (long)11122233344455.0, LocalDate.now());
        Commande actualCommande = Commande.getCommandeById(expectedCommande.getNumCommande());
        assertEquals(expectedCommande.toString(), actualCommande.toString());

    }

    @Test
    @Order(6)
    void testToString() throws SQLException {
        Commande commande = new Commande(1, 2, 3.0, 4, "5:00", "6:00", 7, 8, (long)11122233344455.0, LocalDate.of(2020, 1, 1));

        // Créez la chaîne de caractères attendue
        String expectedString = "Commande{numCommande=1, articleId=2, poids=3.0, quantity=4, horaireDebut='5:00', horaireFin='6:00', idClient=7, idTournee=8, numSiret=11122233344455, dateCommande=2020-01-01}";

        // Appelez la méthode toString de l'objet Commande
        String actualString = commande.toString();

        // Vérifiez que les chaînes de caractères sont égales
        assertEquals(expectedString, actualString);
    }


}