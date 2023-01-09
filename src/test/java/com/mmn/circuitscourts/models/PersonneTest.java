package com.mmn.circuitscourts.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonneTest {

    @Test
    public void testGetId() {
        // Créez un objet Personne à tester
        Personne personne = new Personne(1, "martino", "9 square do", "0600000000");

        // Vérifiez que la méthode getId renvoie la valeur attendue
        assertEquals(1, personne.getId());
    }

    @Test
    public void testSetId() {
        // Créez un objet Personne à tester
        Personne personne = new Personne(1, "martino ", "9 square do", "0600000000");

        // Modifiez la valeur de l'ID avec la méthode setId
        personne.setId(2);

        // Vérifiez que la valeur de l'ID a bien été modifiée
        assertEquals(2, personne.getId());
    }

    @Test
    public void testGetNom() {
        // Créez un objet Personne à tester
        Personne personne = new Personne(1, "martino", "9 square do", "0600000000");

        // Vérifiez que la méthode getNom renvoie la valeur attendue
        assertEquals("martino", personne.getNom());
    }

    @Test
    public void testSetNom() {
        // Créez un objet Personne à tester
        Personne personne = new Personne(1, "martino", "9 square do", "0600000000");

        // Modifiez la valeur du nom avec la méthode setNom
        personne.setNom("martin");

        // Vérifiez que la valeur du nom a bien été modifiée
        assertEquals("martin", personne.getNom());
    }

    @Test
    public void testGetAdresse() {
        // Créez un objet Personne à tester
        Personne personne = new Personne(1, "martino", "9 square do", "0600000000");

        // Vérifiez que la méthode getAdresse renvoie la valeur attendue
        assertEquals("9 square do", personne.getAdresse());
    }

    @Test
    public void testSetAdresse() {
        // Créez un objet Personne à tester
        Personne personne = new Personne(1, "martino", "9 square do", "0600000000");

        // Modifiez la valeur de l'adresse avec la méthode setAdresse
        personne.setAdresse("3 square do");

        // Vérifiez que la valeur de l'adresse a bien été modifiée
        assertEquals("3 square do", personne.getAdresse());
    }

    @Test
    public void testGetNumTel() {
        // Créez un objet Personne à tester
        Personne personne = new Personne(1, "martino", "9 square do", "0600000000");

        // Vérifiez que la méthode getNumTel renvoie la valeur attendue
        assertEquals("0600000000", personne.getNumTel());
    }

    @Test
    public void testSetNumTel() {
        // Créez un objet Personne à tester
        Personne personne = new Personne(1, "martino", "9 square do", "0600000000");

        // Modifiez la valeur du numéro de téléphone avec la méthode setNumTel
        personne.setNumTel("0600000001");

        // Vérifiez que la valeur du numéro de téléphone a bien été modifiée
        assertEquals("0600000001", personne.getNumTel());
    }
}