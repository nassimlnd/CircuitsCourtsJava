package com.mmn.circuitscourts.tools;

import com.github.javafaker.Faker;
import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Proprietaire;

import java.util.Locale;

public class EntrepriseGenerator {

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            Faker faker = new Faker(new Locale("fr"));
            String fullName = faker.name().fullName();
            String adresse = faker.address().streetAddress();
            String codePostal = faker.address().zipCode();
            String city = faker.address().city();
            String fullAdresse = adresse+":"+codePostal+":"+city;
            String numTel = faker.phoneNumber().cellPhone();
            String latitude = faker.address().latitude();
            String longitude = faker.address().longitude();
            String gps = latitude.replace(",", ".") + ", " + longitude.replace(",", ".");
            numTel = numTel.replace(" ", "");
            Entreprise entreprise = new Entreprise(fullAdresse, new Proprietaire(fullName, fullAdresse, numTel), numTel, gps);
        }
    }

}
