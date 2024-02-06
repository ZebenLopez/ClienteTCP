package services;

import com.google.gson.Gson;
import controller.ConexionController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class Conexion {

    private static String ip = ConexionController.getIp();
    private static int puerto = ConexionController.getPuerto();
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private boolean isRunning = false;


    public Conexion(Stage primaryStage) throws IOException {
        try {
            clientSocket = new Socket(ip, puerto);
//        clientSocket = new Socket("localhost", 6789);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("#########Conectao");
            if (clientSocket.isConnected()) {
                System.out.println("Conectado al servidor");
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Conexión");
                    alert.setHeaderText(null);
                    alert.setContentText("Conectado al servidor.");
                    alert.showAndWait();
                });
            }
        } catch (IOException e) {
            System.out.println("Error al conectar al servidor.\nBuscando...");
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al conectar al servidor\nBuscando...");
                alert.showAndWait();
                primaryStage.close();
            });
        }
    }

//    public void conectar(List<Object> datosCliente) {
//        try {
//            // Convertir la lista a JSON
//            Gson gson = new Gson();
//            String json = gson.toJson(datosCliente);
//
//            // Enviar el JSON al servidor
//            outToServer.writeBytes(json + "\n");
//            outToServer.flush();
//        } catch (SocketException e) {
//            System.out.println("La conexión fue cerrada por el servidor. Intentando reconectar...");
//        } catch (IOException e) {
//            System.out.println("Error al enviar los datos al servidor");
//        }
//    }
    public void conectar(List<Object> datosCliente) {
    try {
        if (outToServer == null) {
            // Intentar reconectar con el servidor
            conectarAlServidor();
            try {
                clientSocket = new Socket(ip, puerto);
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                System.out.println("Error al reconectar al servidor");
                return;
            }
        }
        // Convertir la lista a JSON
        Gson gson = new Gson();
        String json = gson.toJson(datosCliente);

        // Enviar el JSON al servidor
        outToServer.writeBytes(json + "\n");
        outToServer.flush();
        isRunning = true;
    } catch (SocketException e) {
        System.out.println("La conexión fue cerrada por el servidor. Intentando reconectar...");
    } catch (IOException e) {
        System.out.println("Error al enviar los datos al servidor");
    }
}

    public void recibirMensaje() {
        try {
            if (clientSocket == null || clientSocket.isClosed()) {
                conectarAlServidor();
            }
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String mensajeDelServidor;

            while ((mensajeDelServidor = inFromServer.readLine()) != null) {
                System.out.println("#######Mensaje del servidor: " + mensajeDelServidor);
                String finalMensajeDelServidor = mensajeDelServidor;
                Platform.runLater(() -> {
                    System.out.println("Antes de verificar Platform.isImplicitExit()");
                    if (!Platform.isImplicitExit()) {
                        System.out.println("Dentro de Platform.isImplicitExit()");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Mensaje del servidor");
                        alert.setHeaderText(null);
                        alert.setContentText(finalMensajeDelServidor);
                        alert.showAndWait();
                        System.out.println("Después de mostrar la alerta");
                    } else {
                        System.out.println("Se salta el platform.runLater()");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Mensaje del servidor");
                        alert.setHeaderText(null);
                        alert.setContentText(finalMensajeDelServidor);
                        alert.show();
                    }
                });
            }
        } catch (IOException e) {
            System.out.println("Error al recibir los datos del servidor");
            // Aquí puedes intentar reconectar o manejar el error de alguna otra manera
            try {
                Thread.sleep(2000); // Esperar 2 segundos antes de intentar reconectar
            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
                System.out.println("Error al recibir los datos del servidor");
            }
        }
    }

    private void conectarAlServidor() throws IOException {
        clientSocket = new Socket(ip, puerto);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        System.out.println("Conectado al servidor");
    }

}