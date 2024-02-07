package utils;

import models.InfoDiscoDuro;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

import java.util.List;

/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class DiscoDuro {

    /**
     *
     * @return Una lista de OSFileStore que representa todos los discos en el sistema.
     */
    // Este método devuelve una lista de todos los discos en el sistema.
    public static List<OSFileStore> obtenerDiscos() {
        SystemInfo systemInfo = new SystemInfo(); // Crea una nueva instancia de SystemInfo para acceder a la información del sistema.
        HardwareAbstractionLayer hardware = systemInfo.getHardware(); // Obtiene la capa de abstracción de hardware del sistema.
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem(); // Obtiene el sistema de archivos del sistema operativo.
        return fileSystem.getFileStores(); // Devuelve una lista de todos los discos en el sistema.
    }

    /**
     *
     * @return El espacio total de todos los discos en el sistema en gigabytes.
     */
    // Este método devuelve el espacio total de todos los discos en el sistema en gigabytes.
    public static long obtenerEspacioTotalDisco() {
        long totalSpace = 0; // Inicializa la variable totalSpace en 0.
        List<OSFileStore> discos = obtenerDiscos(); // Obtiene una lista de todos los discos en el sistema.
        for (OSFileStore disco : discos) { // Para cada disco en la lista de discos...
            totalSpace += disco.getTotalSpace(); // ...añade el espacio total del disco a totalSpace.
        }
        return totalSpace/1024/1024/1024; // Devuelve totalSpace convertido a gigabytes.
    }

    /**
     *
     * @return El espacio libre de todos los discos en el sistema en gigabytes.
     */
    // Este método devuelve el espacio libre de todos los discos en el sistema en gigabytes.
    public static long obtenerEspacioLibreDisco() {
        long usableSpace = 0; // Inicializa la variable usableSpace en 0.
        List<OSFileStore> discos = obtenerDiscos(); // Obtiene una lista de todos los discos en el sistema.
        for (OSFileStore disco : discos) { // Para cada disco en la lista de discos...
            usableSpace += disco.getUsableSpace(); // ...añade el espacio utilizable del disco a usableSpace.
        }
        return usableSpace/1024/1024/1024; // Devuelve usableSpace convertido a gigabytes.
    }

    /**
     *
     * @return El porcentaje de uso de todos los discos en el sistema.
     */
    // Este método devuelve el porcentaje de uso de todos los discos en el sistema.
    public static double obtenerPorcentajeUsoDisco() {
        long totalSpace = obtenerEspacioTotalDisco(); // Obtiene el espacio total de todos los discos en el sistema.
        long usableSpace = obtenerEspacioLibreDisco(); // Obtiene el espacio utilizable de todos los discos en el sistema.
        double diskUsage = (1 - (double) usableSpace / totalSpace) * 100; // Calcula el porcentaje de uso del disco.
        diskUsage = Math.round(diskUsage * 100.00) / 100.00; // Redondea el porcentaje de uso del disco a dos decimales.
        return diskUsage; // Devuelve el porcentaje de uso del disco.
    }
}