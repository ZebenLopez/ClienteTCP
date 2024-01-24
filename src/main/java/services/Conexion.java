package services;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class Conexion {
    public void conectar(List<String> datosCliente) throws InterruptedException {
        String datosClienteJson = new Gson().toJson(datosCliente);

        while (true) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = null;

            while (clientSocket == null) {
                try {
                    clientSocket = new Socket("localhost", 6789);
                    System.out.println("Conectado al servidor"  );
                } catch (IOException e) {
                    System.out.println("Intentando conectar...");
                    Thread.sleep(1000);
                }
            }

            try {
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                outToServer.writeBytes(datosClienteJson + '\n');
                Thread.sleep(1000);
            } catch (IOException e) {
                System.out.println("Desconectado servidor");
            }
        }
    }
}