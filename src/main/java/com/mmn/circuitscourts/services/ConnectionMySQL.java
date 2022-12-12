package com.mmn.circuitscourts.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Martin
 */
public class ConnectionMySQL {
   /**
    * Connection en static pour s'assurer qu'elle soit l'unique.
    */
   public static Connection connectionMySQL;
   public static String url;
   public static String login;
   public static String password;

   /**
    * Crée une connection à la base de données.
    */
   private ConnectionMySQL() {
      try {
         XMLLoader.loadConfigData();
         connectionMySQL = DriverManager.getConnection(url, login, password);
      } catch (SQLException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * retourne une connection seulement si in y en a pas.
    * Permet de se connecter à la base de donnée avec une seule connection grace au SingleConnection patern.
    * @return
    * @throws SQLException
    */
   public static Connection getInstance() {
      if(ConnectionMySQL.connectionMySQL == null){
         ConnectionMySQL connectionMySQL = new ConnectionMySQL();
         return ConnectionMySQL.connectionMySQL;
      }else return ConnectionMySQL.connectionMySQL;
   }

}
