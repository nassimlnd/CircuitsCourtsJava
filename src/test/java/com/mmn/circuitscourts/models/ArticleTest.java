package com.mmn.circuitscourts.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
    class ArticleTest {
        @Test
        public void testConstructor() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            assertEquals("name", a.getName());
            assertEquals("categorie", a.getCategorie());
            assertEquals("description", a.getDescription());
            assertEquals(10.0, a.getPrice(), 0.01);
            assertEquals(5.0, a.getWeight(), 0.01);
            assertEquals(123, a.getImageId());
            assertEquals((long) (111222333.44455*100000), a.getNumSiret());
        }

        @Test
        public void testGetId() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            a.setId(1);
            assertEquals(1, a.getId());
        }

        @Test
        public void testSetId() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            a.setId(1);
            assertEquals(1, a.getId());
        }

        @Test
        public void testGetName() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            assertEquals("name", a.getName());
        }

        @Test
        public void testSetName() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            a.setName("new name");
            assertEquals("new name", a.getName());
        }

        @Test
        public void testGetCategorie() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            assertEquals("categorie", a.getCategorie());
        }

        @Test
        public void testSetCategorie() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            a.setCategorie("new categorie");
            assertEquals("new categorie", a.getCategorie());
        }

        @Test
        public void testGetDescription() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            assertEquals("description", a.getDescription());
        }

        @Test
        public void testSetDescription() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            a.setDescription("new description");
            assertEquals("new description", a.getDescription());
        }

        @Test
        public void testGetPrice() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            assertEquals(10.0, a.getPrice(), 0.01);
        }

        @Test
        public void testSetPrice() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            a.setPrice(20.0);
            assertEquals(20.0, a.getPrice(), 0.01);
        }

        @Test
        public void testGetWeight() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            assertEquals(5.0, a.getWeight(), 0.01);
        }

        @Test
        public void testSetWeight() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            a.setWeight(10.0);
            assertEquals(10.0, a.getWeight(), 0.01);
        }

        @Test
        public void testGetImageId() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            assertEquals(123, a.getImageId());
        }

        @Test
        public void testSetImageId() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            a.setImageId(456);
            assertEquals(456, a.getImageId());
        }

        @Test
        public void testGetNumSiret() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            assertEquals((long) (111222333.44455*100000), a.getNumSiret());
        }

        @Test
        public void testSetNumSiret() {
            Article a = new Article(1,"name", "categorie", "description", 10.0, 5.0, 123, (long) (111222333.44455*100000));
            a.setNumSiret((long) (111222333.44455*100000));
            assertEquals((long) (111222333.44455*100000), a.getNumSiret());
        }

        @Test
        public void testGetAll() {
            // This test assumes that there are already some articles in the database
            List<Article> articles = Article.getAll();
            assertTrue(articles.size() > 0);
        }


    }


