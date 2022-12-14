package com.mmn.circuitscourts.models;

public class Article {
    private int id;
    private String name;
    private String categorie;
    private double price;
    private double weight;

    public Article(String name, String categorie, double price, double weight) {
        this.name = name;
        this.categorie = categorie;
        this.price = price;
        this.weight = weight;
    }

    public Article(int id, String name, String categorie, double price, double weight) {
        this.id = id;
        this.name = name;
        this.categorie = categorie;
        this.price = price;
        this.weight = weight;
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

    public double getPrice() {
        return price;
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
}
