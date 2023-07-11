module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens Snapp to javafx.fxml;
    exports Snapp;
    exports Snapp.Controller;
    opens Snapp.Controller to javafx.fxml;
}