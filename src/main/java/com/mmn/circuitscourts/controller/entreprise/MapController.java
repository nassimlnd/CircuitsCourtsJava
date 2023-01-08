package com.mmn.circuitscourts.controller.entreprise;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import com.mmn.circuitscourts.App;
import com.mmn.circuitscourts.models.Client;
import com.mmn.circuitscourts.models.Commande;
import com.mmn.circuitscourts.models.Entreprise;
import com.mmn.circuitscourts.models.Tournee;
import com.mmn.circuitscourts.services.CommandeDAO;
import javafx.geometry.Point2D;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;



public class MapController {
    public static int idT = 0;

    @FXML
    VBox vbox;

    public Tournee getTournee() {
        try {
            return Tournee.tourneeDAO.getById(idT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() throws SQLException, IOException, ParseException {
        VBox container = new VBox();
        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        double latitude = 0;
        double longitude = 0;
        MapView mapView = new MapView();
        MapPoint firstMapPoint = new MapPoint(46.227638, 2.213749);
        MapPoint mapPoint = new MapPoint(latitude, longitude);

        //coordonnées du points dans le map layer.
        mapView.setZoom(5);
        mapView.flyTo(0, firstMapPoint, 0.1);

        int finalCodePostal = 0;
        String finalString = null;

        ArrayList<MapPoint> points = new ArrayList<>();


        CommandeDAO cmd = new CommandeDAO();
        ArrayList<Commande> commandes = cmd.getAllByTournee(idT);
        System.out.println(commandes);

        for (Commande commande : commandes) {
            Client temp = Client.client.getById(commande.getIdClient());
            // les adresses dans la bd sont séparés avec des autres info par ':'
            String splitAdresse = temp.getAdresse().split(":")[0];
            System.out.println(splitAdresse);
            finalCodePostal = Integer.parseInt(temp.getAdresse().split(":")[1]);
            // on re split avec ' ' pour supprimer les espaces.
            // le finalstring est la version concaténé dans l'url
            finalString = "";
            String[] secondSplit = splitAdresse.split(" ");
            for (String s : secondSplit) {
                finalString += "%" + s;
            }

            try {
                URL url = new URL("https://wxs.ign.fr/essentiels/geoportail/geocodage/rest/0.1/search?q=" + finalString + "&index=address&limit=1&returntruegeometry=false&postcode=" + finalCodePostal);
                URLConnection connection = url.openConnection();

                InputStream inputStream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = reader.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject data = (JSONObject) jsonParser.parse(line);
                JSONArray jsonArray = (JSONArray) data.get("features");
                String result = jsonArray.get(0).toString();
                JSONObject object = (JSONObject) jsonParser.parse(result);
                JSONObject coordinates = (JSONObject) object.get("geometry");
                JSONArray test = (JSONArray) coordinates.get("coordinates");
                latitude = (Double) test.get(1);
                longitude = (Double) test.get(0);
            } catch (Exception e) {
                e.printStackTrace();

            }
                MapPoint pt = new MapPoint(latitude, longitude);
                System.out.println(pt);
                MapLayer mp = new CustomPin(pt);
                System.out.println(mp);
                points.add(pt);
        }
        for (MapPoint pt: points) {
           MapLayer point = new CustomPin(pt);
           mapView.addLayer(point);

        }

        //récupère les coordonnées gps de l'entreprise pour créer son MapPoint.
        long numSiret = Tournee.tourneeDAO.getById(idT).getNumSiret();
        String coords = Entreprise.entrepriseDAO.getById(numSiret).getCoordonneesGps();
        double latitudeEntreprise = Double.parseDouble(coords.split(",")[0]);
        double longitudeEntreprise = Double.parseDouble(coords.split(",")[1]);
        MapPoint entreprise  = new MapPoint(latitudeEntreprise, longitudeEntreprise);
        MapLayer mapLayerEntreprise = new CustomPinEntreprise(entreprise);
        mapView.addLayer(mapLayerEntreprise);

        container.getChildren().add(mapView);
        vbox.getChildren().add(container);
    }

    /**
     * classe interne pour la customisation des points des commandes sur la carte.
     */
    class CustomPin extends MapLayer {
        private static final int PIN_WIDTH = 30, PIN_HEIGHT = 30;
        private final MapPoint mapPoint;
        private final ImageView imageView;

        public CustomPin(MapPoint mapPoint){
            this.mapPoint = mapPoint;
            Image image = new Image(String.valueOf(App.class.getResource("images/localisation.png")), PIN_WIDTH, PIN_HEIGHT, false, false);
            this.imageView = new ImageView(image);
            this.getChildren().add(this.imageView);
        }

        @Override
        protected void layoutLayer() {
            Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
            imageView.setTranslateX(point2d.getX() - (PIN_WIDTH / 2));
            imageView.setTranslateY(point2d.getY() - PIN_HEIGHT);
        }

    }

    /**
     * classe interne pour la customisation des points des Entreprises sur la carte.
     */
    class CustomPinEntreprise extends MapLayer {
        private static final int PIN_WIDTH = 30, PIN_HEIGHT = 30;
        private final MapPoint mapPoint;
        private final ImageView imageView;

        public CustomPinEntreprise(MapPoint mapPoint){
            this.mapPoint = mapPoint;
            Image image = new Image(String.valueOf(App.class.getResource("images/entreprise.png")), PIN_WIDTH, PIN_HEIGHT, false, false);
            this.imageView = new ImageView(image);
            this.getChildren().add(this.imageView);
        }

        @Override
        protected void layoutLayer() {
            Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
            imageView.setTranslateX(point2d.getX() - (PIN_WIDTH / 2));
            imageView.setTranslateY(point2d.getY() - PIN_HEIGHT);
        }

    }
}

