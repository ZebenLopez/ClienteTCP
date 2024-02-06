package org.example.clientetcpfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Cliente;
import services.Conexion;
import java.io.IOException;
import java.util.List;


public class HelloApplication {
    private static final Stage primaryStage = new Stage();

    public void show() throws IOException {
        Stage conexionStage = ConexionView.getStage();
        conexionStage.hide();
        // Crea un nuevo Stage con el nuevo estilo.
        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml")); // Cargador FXML para cargar la vista principal
        Scene scene = new Scene(fxmlLoader.load()); // Crea una nueva escena con la vista principal
        newStage.setTitle("Enlace al servidor"); // Establece el título del nuevo escenario
        newStage.setScene(scene); // Establece la escena del nuevo escenario
        newStage.setResizable(false); // Hace que el nuevo escenario no sea redimensionable
        newStage.setOnCloseRequest(event -> {
            System.exit(0); // Cierra la aplicación cuando se cierra el nuevo escenario
        });
        newStage.show(); // Muestra el nuevo escenario

        init();
    }

//    public void init() {
//        new Thread(() -> {
//            try {
//                Cliente cliente = new Cliente("USBX");
//                Conexion conexion = new Conexion(getPrimaryStage());
//                while (true) {
//                    // Actualizar los datos del cliente
//                    cliente.actualizarDatos();
//
//                    // Obtener los datos del cliente como una lista de cadenas
//                    List<Object> datosCliente = cliente.getCliente();
//
//                    // Intentar conectar y enviar los datos al servidor
//                    conexion.conectar(datosCliente);
//
//                    // Iniciar un nuevo hilo para recibir mensajes cada vez que se abre una conexión
//                    new Thread(conexion::recibirMensaje).start();
//
//                    System.out.println("Datos del cliente: " + cliente.getCliente());
//                    Thread.sleep(1000);
//                }
//            } catch (ConnectException e) {
//                // Imprimir el error si no se puede conectar al servidor
//                System.out.println("##Aqui");
//                e.printStackTrace();
//            } catch (Exception e) {
//                System.out.println("##Aqui2");
//                e.printStackTrace();
//            }
//        }).start();
//    }
    public void init() {
    new Thread(() -> {
        try {
            Cliente cliente = new Cliente();
            Conexion conexion = null;
            boolean connected = false;

            // Intentar establecer la conexión hasta que sea exitosa
            while (!connected) {
                try {
                    conexion = new Conexion(getPrimaryStage());
                    connected = true;
                } catch (IOException e) {
                    System.out.println("Error al conectar al servidor, intentando de nuevo...");
                    Thread.sleep(5000); // Esperar 5 segundos antes de intentar de nuevo
                }
            }

            while (true) {
                // Actualizar los datos del cliente
                cliente.actualizarDatos();

                // Obtener los datos del cliente como una lista de cadenas
                List<Object> datosCliente = cliente.getCliente();

                // Intentar conectar y enviar los datos al servidor
                conexion.enviarJson(datosCliente);

                // Iniciar un nuevo hilo para recibir mensajes cada vez que se abre una conexión
                new Thread(conexion::recibirMensaje).start();

                System.out.println("Datos del cliente: " + cliente.getCliente());
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("##Aqui2");
            e.printStackTrace();
        }
    }).start();
}


    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}