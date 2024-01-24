package org.example.clientetcpfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Conexion;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class HelloApplication2 extends Application {

    @Override
    public void init() throws Exception {
        new Thread(()->{
            Conexion conexion = new Conexion();
            try {
                conexion.conectar("Marcos");
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Conexión perdida");
            }
        }).start();

    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication2.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException { launch();
    }
}