module org.example.clientetcpfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires com.github.oshi;


    opens org.example.clientetcpfx to javafx.fxml;
    opens models to com.google.gson;
    exports org.example.clientetcpfx;
}