package utils;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.Locale;

/**
 * The type Memoria.
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */
public class Memoria {

    /**
     * Este método devuelve el porcentaje de uso de la memoria del sistema.
     * <p>
     *     Para obtener el porcentaje de uso de la memoria del sistema, se utiliza la librería OSHI.
     *     Se crea una nueva instancia de SystemInfo para acceder a la información del sistema.
     *     A continuación, se obtiene la capa de abstracción de hardware del sistema.
     *     Se obtiene la memoria global del sistema y se almacena la memoria disponible y la memoria total.
     *     Se calcula el porcentaje de uso de la memoria y se formatea a dos decimales.
     *     Finalmente, se devuelve el porcentaje de uso de la memoria.
     *
     * @return El porcentaje de uso de la memoria del sistema.
     */
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