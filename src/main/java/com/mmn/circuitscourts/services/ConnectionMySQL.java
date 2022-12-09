package com.mmn.circuitscourts.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Martin
 */
public class ConnectionMySQL {
   /**
    * Connection en static pour s'assurer qu'elle soit la unique.
    */
   public static Connection connectionMySQL;

   /**
    * Crée une connection à la base de données.
    * @param url
    * @param login
    * @param pwd
    */
   private ConnectionMySQL(String url, String login, String pwd) {
      try {
         connectionMySQL = DriverManager.getConnection(url, login, pwd);
      } catch (SQLException e) {
         e.printStackTrace();
      }
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
   public static Connection getInstance(String url, String login, String pwd) {
      if(ConnectionMySQL.connectionMySQL == null){
         ConnectionMySQL connectionMySQL = new ConnectionMySQL(url, login, pwd);
         return ConnectionMySQL.connectionMySQL;
      }else return ConnectionMySQL.connectionMySQL;
   }

}
