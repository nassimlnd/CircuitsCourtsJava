package com.mmn.circuitscourts.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testGetId() {
        Client client = new Client(1, "John", "123 Main St", "555-555-1234", "john@example.com");
        assertEquals(1, client.getId());
    }


    @Test
    void testSetId() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com");
        client.setId(1);
        assertEquals(1, client.getId());
    }

    @Test
    void testGetEmail() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com");
        assertEquals("john@example.com", client.getEmail());
    }

    @Test
    void testSetEmail() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com");
        client.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", client.getEmail());
    }

    @Test
    void testGetAccountId() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com", 12345);
        assertEquals(12345, client.getAccountId());
    }

    @Test
    void testSetAccountId() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com");
        client.setAccountId(12345);
        assertEquals(12345, client.getAccountId());
    }
}