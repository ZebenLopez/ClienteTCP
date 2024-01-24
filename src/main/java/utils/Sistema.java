package utils;

import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

public class Sistema {
    public static String obtenerInfo() {
        SystemInfo si = new SystemInfo();

        String Sistema;


        // Obtener información del sistema operativo
        OperatingSystem os = si.getOperatingSystem();
        System.out.println("Sistema Operativo: " + os.getFamily());

        // Obtener la versión y mostrar solo hasta el primer espacio
        String versionCompleta = os.getVersionInfo().toString();
        String soloVersion = versionCompleta.split(" ")[0];
        System.out.println("Versión: " + soloVersion);

        Sistema = os.getFamily() + " " + soloVersion;
        return Sistema;

//        // Obtener información del usuario
//        String username = System.getProperty("user.name");
//        System.out.println("Usuario: " + username);
    }
}
