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
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */

public class Conexion {

    private static String ip = ConexionController.getIp();
    private static int puerto = ConexionController.getPuerto();
    private Socket clientSocket;
    private DataOutputStream outToServer;
    private Alert alert;


    public Conexion(Stage primaryStage) throws IOException, InterruptedException {
        conectarAlServidor();
    }
    public void enviarJson(List<Object> datosCliente) throws IOException, InterruptedException {
        try {
            if (outToServer == null) {
                conectarAlServidor();
            }
            // Convertir la lista a JSON
            Gson gson = new Gson();
            String json = gson.toJson(datosCliente);

            // Enviar el JSON al servidor
            outToServer.writeBytes(json + "\n");
            outToServer.flush();
        } catch (SocketException e) {
            System.out.println("La conexión fue cerrada por el servidor. Intentando reconectar...");
            conectarAlServidor();
        } catch (IOException e) {
            System.out.println("Error al enviar los datos al servidor");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
                        mostrarAlerta("warning", "Mensaje del servidor", finalMensajeDelServidor);
                    } else {
                        mostrarAlerta("warning", "Mensaje del servidor", finalMensajeDelServidor);
                    }
                });
            }
        } catch (SocketException e) {
            System.out.println("La conexión fue cerrada por el servidor. Intentando reconectar...");
            while (clientSocket == null || clientSocket.isClosed()) {
                try {
                    conectarAlServidor();
                } catch (IOException ex) {
                    System.out.println("Error al reconectar al servidor. Intentando de nuevo en 5 segundos...");
                    try {
                        Thread.sleep(5000); // Esperar 5 segundos antes de intentar de nuevo
                    } catch (InterruptedException ie) {
                        System.out.println("Error al esperar para reconectar");
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al recibir los datos del servidor");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void conectarAlServidor() throws IOException, InterruptedException {
        while (true) {
            try {
                clientSocket = new Socket(ip, puerto);
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
                System.out.println("#########Conectao");
                if (clientSocket.isConnected()) {
                    System.out.println("Conectado al servidor");
                    Platform.runLater(() -> {
                        mostrarAlerta("information", "Conexión", "Conectado al servidor.");
                    });
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error al conectar al servidor.\nBuscando...");
                Platform.runLater(() -> {
                    mostrarAlerta("Error", "Error", "Error al conectar al servidor\nBuscando...");
                });
                Thread.sleep(20000); // Esperar 5 segundos antes de intentar de nuevo
            }
        }
    }

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

        if (alert == null || !alert.isShowing()) {
            alert = new Alert(alertType);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            Platform.runLater(alert::showAndWait);
        }
    }
}