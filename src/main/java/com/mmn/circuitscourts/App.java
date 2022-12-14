package com.mmn.circuitscourts;

import com.mmn.circuitscourts.models.User;
import com.mmn.circuitscourts.views.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static User userConnected;
    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(stage);
        viewFactory.showLoginInterface();
        stage.show();
    }
}
