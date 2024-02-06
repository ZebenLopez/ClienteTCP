package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import services.Conexion;

/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */

public class HelloController {
    private Conexion conexion;
    @FXML
    public TextField introducirIP;
    @FXML
    public TextField introducirPuerto;

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }




    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }
}