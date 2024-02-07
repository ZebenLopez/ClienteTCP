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

/**
 * The type Conexion.
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class Conexion {

    // Variables estáticas para la dirección IP y el puerto del servidor
    private static String ip = ConexionController.getIp();
    private static int puerto = ConexionController.getPuerto();

    // Variables para el socket del cliente, el flujo de salida al servidor y la alerta
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private Alert alert;

    /**
     * Instantiates a new Conexion.
     *
     * @param primaryStage the primary stage
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
// Constructor de la clase Conexion
    public Conexion(Stage primaryStage) throws IOException, InterruptedException {
        // Intenta conectar al servidor al crear una instancia de la clase
        conectarAlServidor();
    }

    /**
     * Enviar json.
     *
     * @param datosCliente the datos cliente
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
// Método para enviar datos al servidor en formato JSON
    public void enviarJson(List<Object> datosCliente) throws IOException, InterruptedException {
        try {
            // Si el flujo de salida al servidor no está inicializado, intenta conectar al servidor
            if (outToServer == null) {
                conectarAlServidor();
            }

            // Convierte la lista de datos del cliente a formato JSON
            Gson gson = new Gson();
            String json = gson.toJson(datosCliente);

            // Envia el JSON al servidor
            outToServer.writeBytes(json + "\n");
            outToServer.flush();
        } catch (SocketException e) {
            // Si la conexión fue cerrada por el servidor, intenta reconectar
            System.out.println("La conexión fue cerrada por el servidor. Intentando reconectar...");
            conectarAlServidor();
        } catch (IOException e) {
            // Maneja cualquier otro error de entrada/salida
            System.out.println("Error al enviar los datos al servidor");
        } catch (InterruptedException e) {
            // Maneja cualquier interrupción del hilo
            throw new RuntimeException(e);
        }
    }

    /**
     * Recibir mensaje.
     */
// Método para recibir mensajes del servidor
    public void recibirMensaje() {
        try {
            // Si el socket del cliente no está inicializado o está cerrado, intenta conectar al servidor
            if (clientSocket == null || clientSocket.isClosed()) {
                conectarAlServidor();
            }

            // Crea un lector para leer los mensajes del servidor
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String mensajeDelServidor;

            // Lee y muestra los mensajes del servidor hasta que no haya más
            while ((mensajeDelServidor = inFromServer.readLine()) != null) {
                System.out.println("Mensaje del servidor: " + mensajeDelServidor);
                String finalMensajeDelServidor = mensajeDelServidor;
                Platform.runLater(() -> {
                    mostrarAlerta("warning", "Mensaje del servidor", finalMensajeDelServidor);
                });
            }
        } catch (SocketException e) {
            // Si la conexión fue cerrada por el servidor, intenta reconectar
            System.out.println("La conexión fue cerrada por el servidor. Intentando reconectar...");
            while (clientSocket == null || clientSocket.isClosed()) {
                try {
                    conectarAlServidor();
                } catch (IOException ex) {
                    System.out.println("Error al reconectar al servidor. Intentando de nuevo en 5 segundos...");
                    try {
                        Thread.sleep(5000); // Espera 5 segundos antes de intentar de nuevo
                    } catch (InterruptedException ie) {
                        System.out.println("Error al esperar para reconectar");
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } catch (IOException e) {
            // Maneja cualquier otro error de entrada/salida
            System.out.println("Error al recibir los datos del servidor");
        } catch (InterruptedException e) {
            // Maneja cualquier interrupción del hilo
            throw new RuntimeException(e);
        }
    }

    // Método para conectar al servidor
    private void conectarAlServidor() throws IOException, InterruptedException {
        while (true) {
            try {
                // Intenta conectar al servidor
                clientSocket = new Socket(ip, puerto);
                outToServer = new DataOutputStream(clientSocket.getOutputStream());

                // Si la conexión es exitosa, muestra un mensaje y sale del bucle
                if (clientSocket.isConnected()) {
                    System.out.println("Conectado al servidor");
                    Platform.runLater(() -> {
                        mostrarAlerta("information", "Conexión", "Conectado al servidor.");
                    });
                    break;
                }
            } catch (IOException e) {
                // Si hay un error al conectar al servidor, muestra un mensaje y espera 5 segundos antes de intentar de nuevo
                System.out.println("Error al conectar al servidor. Buscando...");
                Platform.runLater(() -> {
                    mostrarAlerta("Error", "Error", "Error al conectar al servidor. Buscando...");
                });
                Thread.sleep(20000); // Espera 5 segundos antes de intentar de nuevo
            }
        }
    }

    // Método para mostrar alertas al usuario
    private void mostrarAlerta(String info, String titulo, String mensaje) {
        Alert.AlertType alertType;
        switch (info.toLowerCase()) {
            case "information":
                alertType = Alert.AlertType.INFORMATION;
                break;
            case "warning":
                alertType = Alert.AlertType.WARNING;
                break;
            case "error":
                alertType = Alert.AlertType.ERROR;
                break;
            case "confirmation":
                alertType = Alert.AlertType.CONFIRMATION;
                break;
            default:
                throw new IllegalArgumentException("Tipo de alerta no reconocido: " + info);
        }

        // Si no hay una alerta mostrándose actualmente, crea una nueva y la muestra
        if (alert == null || !alert.isShowing()) {
            alert = new Alert(alertType);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            Platform.runLater(alert::showAndWait);
        }
    }
}