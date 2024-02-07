package org.example.clientetcpfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Conexion view.
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class ConexionView extends Application {

    // Definición de la etapa de la aplicación
    private static Stage stage;

    /**
     * Gets stage.
     *
     * @return the stage
     */
// Método para obtener la etapa de la aplicación
    public static Stage getStage() {
        return stage;
    }

    // Método que se ejecuta al iniciar la aplicación
    @Override
    public void start(Stage stage) throws IOException {
        ConexionView.stage = stage;

        // Carga la vista de conexión desde el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("conexion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Configura el título y la escena de la etapa
        stage.setTitle("Conexión al servidor");
        stage.setScene(scene);

        // Cierra la aplicación cuando se cierra la etapa
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        // Muestra la etapa
        stage.show();
    }

    /**
     * Show conexion.
     *
     * @throws IOException the io exception
     */
// Método para mostrar la vista de conexión
    public static void showConexion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Conexion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
// Método principal que lanza la aplicación
    public static void main(String[] args) {
        launch();
    }
}