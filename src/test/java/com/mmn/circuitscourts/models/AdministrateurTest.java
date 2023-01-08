package com.mmn.circuitscourts.models;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdministrateurTest {

    @Test
    void testToString() throws SQLException {
        Administrateur admin = new Administrateur(1, "magomed", "9 square haudon", "0601020304");
        assertEquals("Administrateur{id=1, nom='magomed', adresse='9 square haudon'}", admin.toString());
    }
}