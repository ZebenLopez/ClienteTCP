package org.example.clientetcpfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Cliente;
import services.Conexion;
import utils.CPU;
import utils.Sistema;
import utils.Usuario;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

public class HelloApplication extends Application {

    @Override
    public void init() {
        new Thread(() -> {
            Cliente cliente = new Cliente("USBX");
            while (true) { // Bucle infinito
                Conexion conexion = null;
                try {
                    conexion = new Conexion();
                    try {
                        // Actualizar los datos del cliente
                        cliente.actualizarDatos();

                        // Obtener los datos del cliente como una lista de cadenas
                        List<Object> datosCliente = cliente.getCliente();

                        // Intentar conectar y enviar los datos al servidor
                        conexion.conectar(datosCliente);

                        // Esperar un tiempo antes de la próxima actualización
                        Thread.sleep(1000); // Esperar 5 segundos
                    } catch (Exception e) {
                        // Imprimir el error si no se puede conectar al servidor
                        e.printStackTrace();
                    }
                } catch (ConnectException e) {
                    System.out.println("No se pudo conectar al servidor.");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Datos del cliente: " + cliente.getCliente());
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