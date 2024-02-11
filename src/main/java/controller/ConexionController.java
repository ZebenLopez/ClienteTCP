package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.clientetcpfx.HelloApplication;
import services.Ayuda;

import java.io.IOException;

/**
 * The type Conexion controller.
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class ConexionController {
    /**
     * The Introducir puerto.
     */
// Definición de los campos de texto para introducir la IP y el puerto
    @FXML
    public TextField introducirPuerto;
    /**
     * The Introducir ip.
     */
    @FXML
    public TextField introducirIP;

    // Variables estáticas para almacenar la IP y el puerto
    private static String ip;
    private static int puerto;

    /**
     * Boton aceptar.
     * <p>
     *     Método que se ejecuta cuando se pulsa el botón de aceptar <br>
     *     Comprueba si los campos de texto de la IP y el puerto están vacíos <br>
     *     Si están vacíos, muestra una alerta de error <br>
     *     Intenta convertir el texto del puerto a un número entero <br>
     *     Comprueba si el puerto está en el rango permitido <br>
     *     Si no está en el rango permitido, muestra una alerta de error <br>
     *     Si todo está correcto, lanza la aplicación <br>
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
// Método que se ejecuta cuando se pulsa el botón de aceptar
    public void botonAceptar(ActionEvent actionEvent) throws IOException {
        // Comprueba si los campos de texto de la IP y el puerto están vacíos
        if (introducirIP.getText().isEmpty() || introducirPuerto.getText().isEmpty()) {
            // Si están vacíos, muestra una alerta de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No puede haber campos vacíos. Introduzca la IP y el puerto.");
            alert.showAndWait();
        } else {
            try {
                // Intenta convertir el texto del puerto a un número entero
                int puertoInt = Integer.parseInt(introducirPuerto.getText());
                // Comprueba si el puerto está en el rango permitido
                if (puertoInt < 1024 || puertoInt > 65535) {
                    // Si no está en el rango permitido, muestra una alerta de error
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El puerto debe estar entre 1024 y 65535.");
                    alert.showAndWait();
                } else {
                    // Si todo está correcto, lanza la aplicación
                    HelloApplication helloApplication = new HelloApplication();
                    helloApplication.show();
                    // Almacena la IP y el puerto en las variables estáticas
                    ip = introducirIP.getText();
                    puerto = puertoInt;
                }
            } catch (NumberFormatException e) {
                // Si el puerto no es un número, muestra una alerta de error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El puerto debe ser un número.");
                alert.showAndWait();
            }
        }
    }

    /**
     * Gets ip.
     *
     * @return the ip
     */
// Método para obtener la IP almacenada
    public static String getIp() {
        return ip;
    }

    /**
     * Gets puerto.
     *
     * @return the puerto
     */
// Método para obtener el puerto almacenado
    public static int getPuerto() {
        return puerto;
    }

    public void ayuda(ActionEvent actionEvent) {
        Ayuda.abrirHelp();
    }

    public void abrirAyuda(KeyEvent keyEvent) {
        Ayuda.abrirHelp();
    }
}