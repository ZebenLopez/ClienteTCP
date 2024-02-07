package org.example.clientetcpfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Cliente;
import services.Conexion;

import java.io.IOException;
import java.util.List;

/**
 * The type Hello application.
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class HelloApplication {
    // Definición del escenario principal
    private static final Stage primaryStage = new Stage();

    /**
     * Show.
     *
     * @throws IOException the io exception
     */
// Método para mostrar la aplicación
    public void show() throws IOException {
        Stage conexionStage = ConexionView.getStage();
        conexionStage.hide();

        // Crea un nuevo escenario
        Stage newStage = new Stage();

        // Carga la vista principal desde el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Configura el título, la escena y la redimensionabilidad del nuevo escenario
        newStage.setTitle("Enlace al servidor");
        newStage.setScene(scene);
        newStage.setResizable(false);

        // Cierra la aplicación cuando se cierra el nuevo escenario
        newStage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        // Muestra el nuevo escenario
        newStage.show();

        // Inicia la aplicación
        init();
    }

    /**
     * Init.
     */
// Método para iniciar la aplicación
    public void init() {
        new Thread(() -> {
            try {
                // Crea un nuevo cliente y una conexión
                Cliente cliente = new Cliente();
                Conexion conexion = null;
                boolean connected = false;

                // Intenta establecer la conexión hasta que sea exitosa
                while (!connected) {
                    try {
                        conexion = new Conexion(getPrimaryStage());
                        connected = true;
                    } catch (IOException e) {
                        System.out.println("Error al conectar al servidor, intentando de nuevo...");
                        Thread.sleep(5000); // Espera 5 segundos antes de intentar de nuevo
                    }
                }

                while (true) {
                    // Actualiza los datos del cliente
                    cliente.actualizarDatos();

                    // Obtiene los datos del cliente como una lista de objetos
                    List<Object> datosCliente = cliente.getCliente();

                    // Intenta enviar los datos al servidor
                    conexion.enviarJson(datosCliente);

                    // Inicia un nuevo hilo para recibir mensajes cada vez que se abre una conexión
                    new Thread(conexion::recibirMensaje).start();

                    System.out.println("Datos del cliente: " + cliente.getCliente());
                    Thread.sleep(1000); // Espera 1 segundo antes de actualizar los datos de nuevo
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Gets primary stage.
     *
     * @return the primary stage
     */
// Método para obtener el escenario principal
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}