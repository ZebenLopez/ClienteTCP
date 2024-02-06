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
    public static String obtenerInfo() {
        SystemInfo si = new SystemInfo();

        String Sistema;


        // Obtener información del sistema operativo
        OperatingSystem os = si.getOperatingSystem();

        // Obtener la versión y mostrar solo hasta el primer espacio
        String versionCompleta = os.getVersionInfo().toString();
        String soloVersion = versionCompleta.split(" ")[0];
        Sistema = os.getFamily() + " " + soloVersion;
        return Sistema;
    }
}
