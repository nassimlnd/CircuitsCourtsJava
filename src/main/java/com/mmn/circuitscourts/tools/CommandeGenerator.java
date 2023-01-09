package com.mmn.circuitscourts.tools;

import com.github.javafaker.Faker;
import com.mmn.circuitscourts.models.Article;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Entreprise;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class CommandeGenerator {

    public static void main(String[] args) {
        ArrayList<Article> articles = null;
        ArrayList<Entreprise> entreprises = null;
        ArrayList<Client> clients = null;
        try {
            articles = Article.article.getAll();
            entreprises = Entreprise.entrepriseDAO.getAll();
            clients = Client.client.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 200; i++) {
            Random random = new Random();
            Article article = articles.get(random.nextInt(articles.size()));
            int quantity = random.nextInt(30);
            double weight = article.getWeight() * quantity;
            Commande commande = new Commande(article.getId(), weight, quantity, "10:00:00", "20:00:00", clients.get(random.nextInt(clients.size())).getId(), entreprises.get(random.nextInt(entreprises.size())).getNumSiret(), LocalDate.now());
        }

    }
}
