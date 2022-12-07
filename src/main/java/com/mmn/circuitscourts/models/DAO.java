package com.mmn.circuitscourts.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Martin
 * Classe de communication directe  avec la BD.
 * Classe génératrice qui permet de manipuler tous les dyfferents types provenants de la BD.
 * @param <T>
 */
public interface DAO <T>{

        /**
         * Permet de récuperer tout le contenu d'une table.
         * @param table, désigne la table en question
         * @return une ArrayList  qui est constituée de l'entierté de la table
         */
        public ArrayList<T> getAll(String table) throws SQLException;

        /**
         * Permet de trouver une client, producteur, tournée..., objet <T> dans la BD via à son id.
         * @param id
         * @return retourne l'objet <T> en queston.
         */
        public T getById(String id, String table);

        /**
         * Permet d'enregistrer dans la BD un objet t particulier.
         * @param t
         */
        public void update(T t);

        /**
         * Permet de supprimer un objet t de la BD
         * @param t
         */
        public void remove(T t);
    }
