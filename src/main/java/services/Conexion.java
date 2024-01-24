package services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Conexion {
    public void conectar(String nombre) throws InterruptedException {
        String sentence = nombre;
        String modifiedSentence;

        while (true) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = null;

            while (clientSocket == null) {
                try {
                    clientSocket = new Socket("localhost", 6789);
                } catch (IOException e) {
//                    System.out.println("Fallo al conectar, reintentando...");
                    Thread.sleep(1000);
                }
            }

            try {
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();
//                System.out.println("DEL SERVIDOR: " + modifiedSentence);
                Thread.sleep(1000);
            } catch (IOException e) {
                System.out.println("Desconectado servidor");
            }
        }
    }

    public void enviarDatos() throws IOException {
        String sentence = "Hola";
        String modifiedSentence;

        while (true) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket("localhost", 6789);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("DEL SERVIDOR: " + modifiedSentence);

        }
    }
}
