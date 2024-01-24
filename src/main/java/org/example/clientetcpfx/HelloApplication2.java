package org.example.clientetcpfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Cliente;
import services.Conexion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication2 extends Application {

    @Override
    public void init() {
        new Thread(() -> {
            Conexion conexion = new Conexion();
            try {
                // Crear un cliente con datos
                Cliente cliente = new Cliente("Windows", "Cliente", "localhost", "Intel", "8GB", "2TB");

                // Obtener los datos del cliente como una lista de cadenas
                List<String> datosCliente = cliente.getCliente();

                // Intentar conectar y enviar los datos al servidor
                conexion.conectar(datosCliente);
            } catch (Exception e) {
                // Imprimir el error si no se puede conectar al servidor
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}