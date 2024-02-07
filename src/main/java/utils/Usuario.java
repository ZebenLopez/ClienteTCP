package utils;

import oshi.SystemInfo;

/**
 * Esta clase se utiliza para obtener información sobre el usuario del sistema.
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */
public class Usuario {

    /**
     *
     * @return Una cadena que representa el nombre del usuario del sistema.
     */
    // Método que devuelve el nombre del usuario del sistema.
    public static String obtenerInfo() {
        SystemInfo si = new SystemInfo(); // Crea una nueva instancia de SystemInfo para acceder a la información del sistema.
        // Obtener información del usuario
        String username = System.getProperty("user.name"); // Obtiene el nombre del usuario del sistema.

        return username; // Devuelve el nombre del usuario del sistema.
    }
}