package com.mmn.circuitscourts.tools;

import com.github.javafaker.Faker;
import com.mmn.circuitscourts.models.Client;

import java.util.Locale;

public class ClientGenerator {

    public static void main(String[] args) {

        for (int i = 0; i < 200; i++) {
            Faker faker = new Faker(new Locale("fr"));
            String fullName = faker.name().fullName();
            String adresse = faker.address().streetAddress();
            String codePostal = faker.address().zipCode();
            String city = faker.address().city();
            String fullAdresse = adresse + ":" + codePostal + ":" + city;
            String numTel = faker.phoneNumber().cellPhone();
            String email = faker.name().username() + "@email.fr";
            numTel = numTel.replace(" ", "");
            new Client(fullName, fullAdresse, numTel, email);
        }
    }
}
