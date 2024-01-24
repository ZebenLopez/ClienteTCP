//package utils;
//
//import models.InfoDiscoDuro;
//import oshi.SystemInfo;
//import oshi.hardware.HardwareAbstractionLayer;
//import oshi.software.os.FileSystem;
//import oshi.software.os.OSFileStore;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DiscoDuro {
//    public static List<InfoDiscoDuro> obtenerInfo() {
//        List<InfoDiscoDuro> infoDiscoDuroList = new ArrayList<>();
//
//        SystemInfo systemInfo = new SystemInfo();
//        HardwareAbstractionLayer hardware = systemInfo.getHardware();
//        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();
//
//        List<OSFileStore> fsArray = fileSystem.getFileStores();
//        for (OSFileStore fs : fsArray) {
//            if (fs.getDescription().equals("Fixed drive") && fs.getTotalSpace()/1024/1024/1024 >= 1) {
//                long totalSpace = fs.getTotalSpace();
//                long usableSpace = fs.getUsableSpace();
//                String diskUsage = String.format("%.2f", (1 - (double) usableSpace / totalSpace) * 100);
//
//                InfoDiscoDuro infoDiscoDuro = new InfoDiscoDuro(
//                        fs.getName(),
//                        fs.getDescription(),
//                        fs.getType(),
//                        totalSpace/1024/1024/1024,
//                        (totalSpace - usableSpace)/1024/1024/1024,
//                        diskUsage
//                );
//
//
//                infoDiscoDuroList.add(infoDiscoDuro);
//                System.out.println(infoDiscoDuro.toString());
//            }
//        }
//
//        return infoDiscoDuroList;
//    }
//}

package utils;

import models.InfoDiscoDuro;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DiscoDuro {
    // Este método estático obtiene información sobre los discos duros del sistema.
    public static List<InfoDiscoDuro> obtenerInfo() {
        // Se crea una lista para almacenar la información de los discos duros.
        List<InfoDiscoDuro> infoDiscoDuroList = new ArrayList<>();

        // Se crea un objeto SystemInfo para obtener información sobre el sistema.
        SystemInfo systemInfo = new SystemInfo();
        // Se obtiene la capa de abstracción de hardware del sistema.
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        // Se obtiene el sistema de archivos del sistema operativo.
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();

        // Se obtiene una lista de las tiendas de archivos del sistema de archivos.
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        // Se itera sobre cada tienda de archivos.
        for (OSFileStore fs : fsArray) {
            // Si la descripción de la tienda de archivos es "Fixed drive" y su espacio total es mayor o igual a 1GB...
            if (fs.getDescription().equals("Fixed drive") && fs.getTotalSpace()/1024/1024/1024 >= 1) {
                // Se obtiene el espacio total de la tienda de archivos.
                long totalSpace = fs.getTotalSpace();
                // Se obtiene el espacio utilizable de la tienda de archivos.
                long usableSpace = fs.getUsableSpace();
                // Se calcula el uso del disco como un porcentaje y se formatea a dos decimales.
                String diskUsage = String.format(Locale.US, "%.2f", (1 - (double) usableSpace / totalSpace) * 100);

                // Se crea un objeto InfoDiscoDuro con la información de la tienda de archivos.
                InfoDiscoDuro infoDiscoDuro = new InfoDiscoDuro(
                        fs.getName(),
                        fs.getDescription(),
                        fs.getType(),
                        totalSpace/1024/1024/1024,
                        (totalSpace - usableSpace)/1024/1024/1024,
                        diskUsage
                );

                // Se añade el objeto InfoDiscoDuro a la lista.
                infoDiscoDuroList.add(infoDiscoDuro);
                // Se imprime la representación de cadena de texto del objeto InfoDiscoDuro.
                System.out.println(infoDiscoDuro.toString());
            }
        }

        // Se devuelve la lista de objetos InfoDiscoDuro.
        return infoDiscoDuroList;
    }
}