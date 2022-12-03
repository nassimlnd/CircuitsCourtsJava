module com.mmn.circuitscourts {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mmn.circuitscourts to javafx.fxml;
    exports com.mmn.circuitscourts;
}