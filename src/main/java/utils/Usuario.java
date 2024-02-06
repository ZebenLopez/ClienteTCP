package utils;

import oshi.SystemInfo;
/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */
public class Usuario {

    public static String obtenerInfo() {
        SystemInfo si = new SystemInfo();
        // Obtener información del usuario
        String username = System.getProperty("user.name");

        return username;
    }
}
