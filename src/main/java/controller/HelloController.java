package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import services.Conexion;

/**
 * The type Hello controller.
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class HelloController {
    // Definición de la conexión
    private Conexion conexion;

    /**
     * The Introducir ip.
     */
// Definición de los campos de texto para introducir la IP y el puerto
    @FXML
    public TextField introducirIP;
    /**
     * The Introducir puerto.
     */
    @FXML
    public TextField introducirPuerto;

    /**
     * Sets conexion.
     * <p>
     *     Método para establecer la conexión
     *
     * @param conexion the conexion
     */
// Método para establecer la conexión
    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Salir.
     * <p>
     *     Método que se ejecuta cuando se pulsa el botón de conectar
     *
     * @param actionEvent the action event
     */
// Método que se ejecuta cuando se pulsa el botón de salir
    public void salir(ActionEvent actionEvent) {
        // Cierra la aplicación
        System.exit(0);
    }
}