package utils;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.Locale;

/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */
public class Memoria {

    /**
     *
     * @return El porcentaje de uso de la memoria del sistema.
     */
    //    Este método devuelve el espacio total de todos los discos en el sistema en gigabytes.
    public static double obtenerInfo() {
        SystemInfo systemInfo = new SystemInfo(); // Crea una nueva instancia de SystemInfo para acceder a la información del sistema.
        HardwareAbstractionLayer hardware = systemInfo.getHardware(); // Obtiene la capa de abstracción de hardware del sistema.

        GlobalMemory memory = hardware.getMemory(); // Obtiene la memoria global del sistema.
        long availableMemory = memory.getAvailable(); // Obtiene la memoria disponible del sistema.
        long totalMemory = memory.getTotal(); // Obtiene la memoria total del sistema.

        double memoryUsage = (1 - (double) availableMemory / totalMemory) * 100; // Calcula el porcentaje de uso de la memoria.
        String formattedMemoryUsage = String.format(Locale.US, "%.2f", memoryUsage); // Formatea el porcentaje de uso de la memoria a dos decimales.
        memoryUsage = Double.parseDouble(formattedMemoryUsage); // Convierte el porcentaje de uso de la memoria a un double.
        return memoryUsage; // Devuelve el porcentaje de uso de la memoria.
    }
}