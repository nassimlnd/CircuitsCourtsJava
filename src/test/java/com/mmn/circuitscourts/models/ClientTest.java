package com.mmn.circuitscourts.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    void testConstructor() {
        Client client = new Client("John", "123 Main St", "555-555-1234", "john@example.com");
        assertEquals("John", client.getNom());
        assertEquals("123 Main St", client.getAdresse());
        assertEquals("555-555-1234", client.getNumTel());
        assertEquals("john@example.com", client.getEmail());
    }

    @Test
    void getId() {
        Client client = new Client(1, "John", "123 Main St", "555-555-1234", "john@example.com");
        assertEquals(1, client.getId());
    }


    @Test
    void setId() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com");
        client.setId(1);
        assertEquals(1, client.getId());
    }

    @Test
    void getEmail() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com");
        assertEquals("john@example.com", client.getEmail());
    }

    @Test
    void setEmail() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com");
        client.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", client.getEmail());
    }

    @Test
    void getAccountId() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com", 12345);
        assertEquals(12345, client.getAccountId());
    }

    @Test
    void setAccountId() {
        Client client = new Client(1,"John", "123 Main St", "555-555-1234", "john@example.com");
        client.setAccountId(12345);
        assertEquals(12345, client.getAccountId());
    }
}