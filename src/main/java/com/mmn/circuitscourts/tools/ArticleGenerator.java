package com.mmn.circuitscourts.tools;

import com.github.javafaker.Faker;
import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Entreprise;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class ArticleGenerator {

    public static void main(String[] args) {
        ArrayList<Entreprise> entreprises = null;
        try {
            entreprises = Entreprise.entrepriseDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 25; i++) {
            ArrayList<String> names = new ArrayList<>(Arrays.asList("Pommes", "Rhubarbe", "Fraise", "Kiwi", "Citron", "Orange sanguine", "Orange", "Banane", "Clémentine"));
            Faker faker = new Faker(new Locale("fr"));
            for (String name : names) {
                Random random = new Random();
                new Article(name, "Fruits", faker.lorem().fixedString(20), 1, 1, 1, entreprises.get(random.nextInt(entreprises.size())).getNumSiret());
            }
        }

    }
}
