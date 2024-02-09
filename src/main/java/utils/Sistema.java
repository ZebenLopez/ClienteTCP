package utils;

import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

/**
 * The type Sistema.
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */
public class Sistema {

    /**
     * Método que devuelve la familia y la versión del sistema operativo.
     * <p>
     *     Utiliza la librería OSHI para obtener la información del sistema operativo.
     *     Crea una nueva instancia de SystemInfo para acceder a la información del sistema.
     *     Obtiene el sistema operativo y la versión del sistema operativo.
     *     Combina la familia del sistema operativo y la versión en una cadena.
     *     Devuelve la cadena que representa la familia y la versión del sistema operativo.
     *     Si no se puede obtener la información del sistema operativo, devuelve un mensaje de error.
     *     Si se produce una excepción, devuelve un mensaje de error.
     *
     * @return Una cadena que representa la familia y la versión del sistema operativo.
     */
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