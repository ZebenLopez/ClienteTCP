package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.clientetcpfx.HelloApplication;

import java.io.IOException;
/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */

public class ConexionController {
    @FXML
    public TextField introducirPuerto;
    @FXML
    public TextField introducirIP;

    private static String ip;
    private static int puerto;

    public void botonAceptar(ActionEvent actionEvent) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.show();

        ip = introducirIP.getText();
        puerto = Integer.parseInt(introducirPuerto.getText());
    }

    public static String getIp() {
        return ip;
    }
    public static int getPuerto() {
        return puerto;
    }
}
