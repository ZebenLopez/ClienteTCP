module org.example.clientetcpfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.clientetcpfx to javafx.fxml;
    exports org.example.clientetcpfx;
}