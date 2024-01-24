package services;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class Conexion {
    private Socket clientSocket;
    private DataOutputStream outToServer;

    public Conexion() throws IOException {
        clientSocket = new Socket("localhost", 6789);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
    }

    public void conectar(List<Object> datosCliente) {
        try {
            // Convertir la lista a JSON
            Gson gson = new Gson();
            String json = gson.toJson(datosCliente);

            // Enviar el JSON al servidor
            outToServer.writeBytes(json + "\n");
            outToServer.flush();
        } catch (SocketException e) {
            System.out.println("La conexión fue cerrada por el servidor. Intentando reconectar...");
            // Aquí puedes intentar reconectar o manejar el error de alguna otra manera
        } catch (IOException e) {
            System.out.println("Error al enviar los datos al servidor");
        }
    }
}