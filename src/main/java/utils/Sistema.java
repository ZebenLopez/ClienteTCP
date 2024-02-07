package utils;

import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */
public class Sistema {

    /**
     *
     * @return Una cadena que representa la familia y la versión del sistema operativo.
     */
    // Método que devuelve la familia y la versión del sistema operativo.
    public static String obtenerInfo() {
        SystemInfo si = new SystemInfo(); // Crea una nueva instancia de SystemInfo para acceder a la información del sistema.

        String Sistema;

        // Obtener información del sistema operativo
        OperatingSystem os = si.getOperatingSystem(); // Obtiene el sistema operativo.

        // Obtener la versión y mostrar solo hasta el primer espacio
        String versionCompleta = os.getVersionInfo().toString(); // Obtiene la versión completa del sistema operativo.
        String soloVersion = versionCompleta.split(" ")[0]; // Divide la versión completa en partes y toma solo la primera parte.
        Sistema = os.getFamily() + " " + soloVersion; // Combina la familia del sistema operativo y la versión en una cadena.
        return Sistema; // Devuelve la cadena que representa la familia y la versión del sistema operativo.
    }
}