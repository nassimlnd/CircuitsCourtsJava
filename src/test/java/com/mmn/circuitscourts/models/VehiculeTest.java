package com.mmn.circuitscourts.models;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class VehiculeTest {

    @Test
    public void testSetters() throws SQLException {
        // Créer un nouveau véhicule
        Vehicule v = new Vehicule("AA-123-AA", 1000, (long)11122233344455.0);

        // Modifier les valeurs
        v.setNumImmat("BB-123-BB");
        v.setPoidsMax(2000);

        // Vérifier que les valeurs ont bien été modifiées
        assertEquals("BB-123-BB", v.getNumImmat());
        assertEquals(2000, v.getPoidsMax());
    }

    @Test
    public void testGetters() throws SQLException {
        // Créer un nouveau véhicule avec des valeurs spécifiques
        Vehicule v = new Vehicule("AA-123-AA", 1000, (long)11122233344455.0);

        // Vérifier que les getters retournent les bonnes valeurs
        assertEquals("AA-123-AA", v.getNumImmat());
        assertEquals(1000, v.getPoidsMax(), 0);
        assertEquals((long)11122233344455.0, v.getNumSiret());
    }
}