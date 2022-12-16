package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.Administrateur;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @author Martin
 * Classe de communication directe  avec la BD.
 * Classe génératrice qui permet de manipuler tous les dyfferents types provenants de la BD.
 * @param <T>
 */
public interface DAO<T,T1>{

        /**
         * Permet de récuperer tout le contenu d'une table.
         * @return une ArrayList qui est constituée de l'entierté de la table
         */
        public ArrayList<T> getAll() throws SQLException;

        /**
         * Permet de trouver une client, producteur, tournée..., objet <T> dans la BD via à son id.
         * @param id
         * @return retourne l'objet <T> en queston.
         */
        public T getById(T1 id) throws SQLException;

        /**
         * permet de  rajouter dans la BD un objet en particulier
         * @param t, l'objet à ajouter en quesion.
         */
        public int add(T t) throws SQLException;
        /**
         * Permet de modifier dans la BD un objet t particulier.
         * @param id permet de retrouver l'objet à modifier
         */
        public boolean update(T1 id, T t) throws SQLException;

        /**
         * Permet de supprimer un objet t de la BD
         * @param id permet de retrouver l'objet à supprimer
         */
        public boolean remove(T1 id) throws SQLException;

}
