package com.mmn.circuitscourts.controller.entreprise;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import com.mmn.circuitscourts.App;
//import com.mmn.circuitscourts.TestApi;
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
import javafx.scene.shape.Line;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
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

        /* Création de la carte Gluon JavaFX */
        MapView mapView = new MapView();


        /* Création du point avec latitude et longitude */
        MapPoint mapPoint = new MapPoint(47.343553, 0.709106);
        MapPoint endPoint = new MapPoint(47.282405853271484, 0.7068501114845276);
        MapPoint thirdPoint = new MapPoint(47.38065472693533, 0.6925432738020421);

        //coordonnées du points dans le map layer.
        mapView.setZoom(5);
        mapView.flyTo(0, mapPoint, 0.1);



        ArrayList<MapPoint> points = initPoints();

        initItinerary(points, mapView);

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

    public ArrayList<MapPoint> initPoints() {
        int finalCodePostal = 0;
        String finalString = null;
        ArrayList<MapPoint> points = new ArrayList<>();
        CommandeDAO cmd = new CommandeDAO();
        ArrayList<Commande> commandes = null;
        try {
            commandes = cmd.getAllByTournee(idT);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(commandes);

        for (Commande commande : commandes) {
            Client temp = null;
            try {
                temp = Client.client.getById(commande.getIdClient());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
                MapPoint pt = new MapPoint((Double) test.get(1), (Double) test.get(0));
                System.out.println(pt);
                MapLayer mp = new CustomPin(pt);
                System.out.println(mp);
                points.add(pt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return points;
    }

    public void initItinerary(ArrayList<MapPoint> mapPoints, MapView mapView) {
        try {

            for (int i = 0; i < mapPoints.size(); i++) {
                if (i != mapPoints.size() - 1) {
                    URL url1 = new URL("https://wxs.ign.fr/calcul/geoportail/itineraire/rest/1.0.0/route?resource=bdtopo-osrm&start=" + mapPoints.get(i).getLongitude() + "," + mapPoints.get(i).getLatitude() + "&end=" + mapPoints.get(i+1).getLongitude() + "," + mapPoints.get(i+1).getLatitude() +"&profile=car&optimization=fastest&constraints=%7B%22constraintType%22%3A%22banned%22%2C%22key%22%3A%22wayType%22%2C%22operator%22%3A%22%3D%22%2C%22value%22%3A%22autoroute%22%7D&getSteps=true&getBbox=true&distanceUnit=kilometer&timeUnit=hour&crs=EPSG%3A4326");
                    URLConnection connection1 = url1.openConnection();

                    InputStream inputStream1 = connection1.getInputStream();

                    BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream1));
                    String line1 = reader1.readLine();

                    JSONParser jsonParser1 = new JSONParser();
                    JSONObject data1 = (JSONObject) jsonParser1.parse(line1);
                    JSONObject geometry1 = (JSONObject) data1.get("geometry");
                    JSONArray coordinates1 = (JSONArray) geometry1.get("coordinates");

                    ArrayList<MapPoint> mapPoints1 = new ArrayList<>();


                    for (int j = 0; j < coordinates1.toArray().length; j++) {
                        JSONArray jsonArray = (JSONArray) coordinates1.get(j);
                        mapPoints1.add(new MapPoint((Double) jsonArray.get(1), (Double) jsonArray.get(0)));
                    }

                    for (int k = 0; k < mapPoints1.size(); k++) {
                        if (k != mapPoints1.size() - 1) {
                            MapLayer mapLayer1 = new LineLayer(mapPoints1.get(k), mapPoints1.get(k+1));
                            mapView.addLayer(mapLayer1);
                        }
                    }
                }
            }
            //URL url1 = new URL("https://wxs.ign.fr/calcul/geoportail/itineraire/rest/1.0.0/route?resource=bdtopo-osrm&start=" + endPoint.getLongitude() + "," + endPoint.getLatitude() + "&end=" + thirdPoint.getLongitude() + "," + thirdPoint.getLatitude() +"&profile=car&optimization=fastest&constraints=%7B%22constraintType%22%3A%22banned%22%2C%22key%22%3A%22wayType%22%2C%22operator%22%3A%22%3D%22%2C%22value%22%3A%22autoroute%22%7D&getSteps=true&getBbox=true&distanceUnit=kilometer&timeUnit=hour&crs=EPSG%3A4326");

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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

    private class LineLayer extends MapLayer {

        private final MapPoint point1;
        private final MapPoint point2;
        private final Line line;

        public LineLayer(MapPoint point1, MapPoint point2) {
            this.point1 = point1;
            this.point2 = point2;

            this.line = new Line(point1.getLatitude(), point2.getLatitude(), point1.getLongitude(), point2.getLongitude());
            this.line.setStrokeWidth(5);

            this.getChildren().add(line);
        }

        @Override
        protected void layoutLayer() {
            Point2D point2D1 = this.getMapPoint(point1.getLatitude(), point1.getLongitude());
            Point2D point2D2 = this.getMapPoint(point2.getLatitude(), point2.getLongitude());

            line.setStartX(point2D1.getX());
            line.setStartY(point2D1.getY());
            line.setEndX(point2D2.getX());
            line.setEndY(point2D2.getY());
        }
    }
}

