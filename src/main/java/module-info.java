module com.mmn.circuitscourts {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires java.sql;
    //requires java.sql;


    opens com.mmn.circuitscourts to javafx.fxml;
    opens com.mmn.circuitscourts.controller to javafx.fxml;
    exports com.mmn.circuitscourts;
    exports com.mmn.circuitscourts.controller;
    exports com.mmn.circuitscourts.controller.admin;
    opens com.mmn.circuitscourts.controller.admin to javafx.fxml;
    exports com.mmn.circuitscourts.controller.producteur;
    opens com.mmn.circuitscourts.controller.producteur to javafx.fxml;
    opens com.mmn.circuitscourts.models to javafx.base;
}