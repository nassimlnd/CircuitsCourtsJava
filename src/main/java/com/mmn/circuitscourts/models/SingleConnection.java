package com.mmn.circuitscourts.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Martin
 */
public class SingleConnection {
   /**
    * Connection en static pour s'assurer qu'elle soit la unique.
    */
   public static Connection con;

   /**
    * Crée une connection à la base de données.
    * @param url
    * @param login
    * @param pwd
    * @throws SQLException
    */
   private SingleConnection(String url, String login, String pwd) throws SQLException {
         con = DriverManager.getConnection(url, login, pwd);
   }

   /**
    * retourne une connection seulement si in y en a pas.
    * Permet de se connecter à la base de donnée avec une seule connection grace au SingleConnection patern.
    * @param url
    * @param login
    * @param pwd
    * @return
    * @throws SQLException
    */
   public static Connection getInstance(String url, String login, String pwd) throws SQLException {
      if(SingleConnection.con == null){
         SingleConnection singleConnection = new SingleConnection(url, login, pwd);
         return SingleConnection.con;
      }else return SingleConnection.con;
   }

}
