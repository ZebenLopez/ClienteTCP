package org.example.clientetcpfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Conexion view.
 * <p>
 *     Clase que representa la vista de conexión
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class ConexionView extends Application {

    private static Stage stage;

    /**
     * Gets stage.
     * <p>
     *     Metodo para obtener la etapa
     * @return the stage
     */
    public static Stage getStage() {
        return stage;
    }
    /**
     * Start.
     * <p>
     *     Método que se ejecuta al iniciar la aplicación
     *     @param stage the stage
     *                  Etapa
     *                  @throws IOException the io exception
     *                  Excepción de entrada/salida
     *                  @throws IOException the io exception
     *                  <p>
     *                      Captura la vista de conexión desde el archivo FXML <br>
     *                      Configura el título y la escena de la etapa <br>
     *                      Cierra la aplicación cuando se cierra la etapa <br>
     *                      Muestra la etapa
     */
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
     * <p>
     *     Método para mostrar la vista de conexión
     *
     * @throws IOException the io exception
     */
    public static void showConexion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Conexion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The entry point of application.
     * <p>
     *     Método principal de la aplicación
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }
}