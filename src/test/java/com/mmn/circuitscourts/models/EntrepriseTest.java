package com.mmn.circuitscourts.models;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class EntrepriseTest {

    @Test
    void testGettersEntreprise() throws SQLException {
        Proprietaire proprietaire = new Proprietaire("martin", "9 square do", "0600000000");
        Entreprise ent = new Entreprise("3 square do", proprietaire, "0601020304", "48.858222, 2.2945", 1);
        long numSiret = ent.getNumSiret();
        assertEquals("3 square do", ent.getAdresse());
        assertEquals(proprietaire, ent.getProprietaire());
        assertEquals("0601020304", ent.getNumTel());
        assertEquals("48.858222, 2.2945", ent.getCoordonneesGps());
        assertEquals(1, ent.getAccountId());
    }

    @Test
    void testSettersEntreprise() throws SQLException {
        Entreprise ent = new Entreprise("9 square do", new Proprietaire("martin", "3 square do", "0600000000"), "0601020304", "48.858222, 2.2945", 1);
        ent.setAdresse("4 square do");
        ent.setProprietaire(new Proprietaire("martino", "5 square do", "0600000001"));
        ent.setNumTel("0701020304");
        ent.setCoordonneesGps("43.296482, 5.36978");
        ent.setAccountId(2);

        assertEquals("4 square do", ent.getAdresse());
        assertEquals(new Proprietaire("martino", "5 square do", "0600000001").toString(), ent.getProprietaire().toString());
        assertEquals("0701020304", ent.getNumTel());
        assertEquals("43.296482, 5.36978", ent.getCoordonneesGps());
        assertEquals(2, ent.getAccountId());

    }
    @Test
    void TestToString() throws SQLException {
        Proprietaire proprietaire = new Proprietaire("martin", "9 square do", "0600000000");
        Entreprise ent = new Entreprise((long)11122233344455.0,"3 square do", proprietaire, "0601020304", "48.8566, 2.3522", 1);

        // Créez la chaîne de caractères attendue
        String expectedString = "Entreprise{numSiret=11122233344455, proprietaire:nom=martin, adresse='9 square do', numTel='0600000000', adresse='3 square do', numTel='0601020304', coordonneesGps='48.8566, 2.3522', accountId=1}";

        // Appelez la méthode toString de l'objet Entreprise
        String actualString = ent.toString();

        // Vérifiez que les chaînes de caractères sont égales
        assertEquals(expectedString, actualString);
    }
}