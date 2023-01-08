package com.mmn.circuitscourts.models;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProprietaireTest {

    @Test
    void getId() throws SQLException {
        Proprietaire prop = new Proprietaire(1,"martin","9 square do","0600000000");
        assertEquals(1,prop.getId());
    }

    @Test
    void setId() {
        Proprietaire prop = new Proprietaire(1,"martin","9 square do","0600000000");
        prop.setId(2);
        assertEquals(2,prop.getId());
    }

    @Test
    void testToString() throws SQLException {
        Proprietaire prop = new Proprietaire(1,"martin", "9 square do", "0600000000");
        String expectedString = "nom=martin, adresse='9 square do', numTel='0600000000'";
        String actualString = prop.toString();
        assertEquals(expectedString, actualString);
    }
}