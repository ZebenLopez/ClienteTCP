package utils;

import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

public class Usuario {

    public static String obtenerInfo() {
        SystemInfo si = new SystemInfo();

        // Obtener información del usuario
        String username = System.getProperty("user.name");
        System.out.println("Usuario: " + username);

        return username;
    }
}
