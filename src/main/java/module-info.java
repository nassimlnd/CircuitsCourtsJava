module com.mmn.circuitscourts {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.io;
    //requires java.sql;
    requires com.gluonhq.maps;
    requires json.simple;



    opens com.mmn.circuitscourts to javafx.fxml;
    opens com.mmn.circuitscourts.controller to javafx.fxml;
    exports com.mmn.circuitscourts;
    exports com.mmn.circuitscourts.controller;
    exports com.mmn.circuitscourts.controller.admin;
    opens com.mmn.circuitscourts.controller.admin to javafx.fxml;
    exports com.mmn.circuitscourts.controller.entreprise;
    opens com.mmn.circuitscourts.controller.entreprise to javafx.fxml;
    opens com.mmn.circuitscourts.models to javafx.base;
    opens com.mmn.circuitscourts.controller.client to javafx.fxml;
}