package com.mmn.circuitscourts;

import com.mmn.circuitscourts.views.LoginInterface;
import com.mmn.circuitscourts.views.ProducteurDashboardInterface;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Icon init
        Image icon = new Image(getClass().getResource("images/icon.png").toExternalForm());
        stage.getIcons().add(icon);

        // Size init
        stage.setMinHeight(700);
        stage.setMinWidth(1000);

        // LoginInterface init
        LoginInterface loginInterface = new LoginInterface();
        stage.setScene(loginInterface.getScene());
        stage.setTitle(loginInterface.getTitle());

        // ProducteurDashboard init
        ProducteurDashboardInterface prodDashboard = new ProducteurDashboardInterface();

        stage.show();

    }
}
