package com.mmn.circuitscourts;

import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

public class MainApp {

    public static void main(String[] args) throws SQLException, IOException {
        Application.launch(App.class);

        File directory = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "data");
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                file.delete();
            }
            directory.delete();
        }
    }
}
