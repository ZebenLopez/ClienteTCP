package org.example.clientetcpfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConexionView extends Application {

    private static Stage stage;
    public static Stage getStage() {
        return stage;
    }
    @Override
    public void start(Stage stage) throws IOException {
        ConexionView.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("conexion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Conexión al servidor");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            System.exit(0); // Cierra la aplicación cuando se cierra el escenario
        });
        stage.show();
    }

    public static void showConexion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Conexion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
