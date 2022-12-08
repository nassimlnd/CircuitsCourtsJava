package com.mmn.circuitscourts.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class TempTestFunctionsDAO {
    public static void main(String[] args) throws SQLException {
        AdminDAO test;

        {
            ArrayList<Administrateur> array;
            try {
                test = new AdminDAO("jdbc:mysql://localhost/circuit_court?serverTimezone=Europe/Paris", "root", "");
                System.out.println("**** test getAll ****");
                ArrayList<Administrateur> resultat = test.getAll();
                for (Administrateur a : resultat) {
                    System.out.println(a.toString());
                }
                System.out.println("**** test getByID ***");
                Administrateur a = test.getById(2);
                System.out.println(a.toString());
                test.add(new Administrateur("test2", "add2"));
                System.out.println("**** test add ****");
                ArrayList<Administrateur> resultat2 = test.getAll();
                for (Administrateur a2 : resultat2) {
                    System.out.println(a2.toString());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try{
               AdminDAO test2 = new AdminDAO("jdbc:mysql://localhost/circuit_court?serverTimezone=Europe/Paris", "root", "");
               Administrateur martin1 = new Administrateur("martin", "martin");
               test2.update(7, martin1);
                System.out.println("**** test update ****");
                ArrayList<Administrateur> resultat2 = test.getAll();
                for (Administrateur a2 : resultat2) {
                    System.out.println(a2.toString());
                }
            } catch (SQLException e){
                throw new RuntimeException(e);
            }

            try {
                AdminDAO test3 = new AdminDAO("jdbc:mysql://localhost/circuit_court?serverTimezone=Europe/Paris", "root", "");
                test3.remove(11);
                ArrayList<Administrateur> resultat2 = test.getAll();
                System.out.println("**** test remove ****");
                for (Administrateur a2 : resultat2) {
                    System.out.println(a2.toString());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try{
            CommandeDAO testC = new CommandeDAO("jdbc:mysql://localhost/circuit_court?serverTimezone=Europe/Paris", "root", "");
            System.out.println("\n **** test getall commande ****");
            ArrayList<Commande> resultatC1= testC.getAll();
            for (Commande c : resultatC1) {
                System.out.println(c.toString());
            }

            Commande commande = testC.getById(1) ;
            System.out.println(" **** test getByID ***");
            System.out.println(commande.toString());

            Commande c = new Commande("annanas", 150, "15", "16", 1, 1, 1 );
            testC.add(c);
            System.out.println("\n **** test add commande ****");
            ArrayList<Commande> resultatC2= testC.getAll();
            for (Commande c2 : resultatC2) {
                System.out.println(c2.toString());
            }

                Commande c3 = new Commande("fraise", 50, "15", "16", 1, 1, 1 );
                testC.update(4, c3);
                System.out.println("**** test update ****");
                System.out.println(testC.getById(2));

                testC.remove(7);
                ArrayList<Commande> resultatC4= testC.getAll();
                for (Commande c4 : resultatC4) {
                    System.out.println(c4.toString());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}




