package com.mmn.circuitscourts.models;
import com.mmn.circuitscourts.services.MarketplaceDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class Article {
    private int id;
    private String name;
    private String categorie;
    private String description;
    private double price;
    private double weight;
    private int imageId;
    private int numSiret;

    public Article(String name, String categorie, String description, double price, double weight, int imageId, int numSiret) {
        this.name = name;
        this.categorie = categorie;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.imageId = imageId;
        this.numSiret = numSiret;

        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        try {
            this.id = marketplaceDAO.add(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MarketplaceDAO article = new MarketplaceDAO();
    public Article(int id, String name, String categorie, String description, double price, double weight, int imageId, int numSiret) {
        this.id = id;
        this.name = name;
        this.categorie = categorie;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.imageId = imageId;
        this.numSiret = numSiret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getNumSiret() {
        return numSiret;
    }

    public void setNumSiret(int numSiret) {
        this.numSiret = numSiret;
    }

    public static ArrayList<Article> getAll() {
        MarketplaceDAO marketplaceDAO = new MarketplaceDAO();
        try {
            return marketplaceDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
