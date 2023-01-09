package com.mmn.circuitscourts.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testGetId() {
        Client client = new Client(1, "martino", "9 square do", "06000000", "martino@gmail.com");
        assertEquals(1, client.getId());
    }


    @Test
    void testSetId() {
        Client client = new Client(1, "martino", "9 square do", "06000000", "martino@gmail.com");
        client.setId(1);
        assertEquals(1, client.getId());
    }

    @Test
    void testGetEmail() {
        Client client = new Client(1, "martino", "9 square do", "06000000", "martino@gmail.com");
        assertEquals("martino@gmail.com", client.getEmail());
    }

    @Test
    void testSetEmail() {
        Client client = new Client(1, "martino", "9 square do", "06000000", "martino@gmail.com");
        client.setEmail("martino@gmail.com");
        assertEquals("martino@gmail.com", client.getEmail());
    }

    @Test
    void testGetAccountId() {
        Client client = new Client(1, "martino", "9 square do", "06000000", "martino@gmail.com",2);
        assertEquals(2, client.getAccountId());
    }

    @Test
    void testSetAccountId() {
        Client client = new Client(1, "martino", "9 square do", "06000000", "martino@gmail.com");
        client.setAccountId(2);
        assertEquals(2, client.getAccountId());
    }
}