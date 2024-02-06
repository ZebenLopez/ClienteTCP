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

import java.util.List;
/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */
public class DiscoDuro {
    public static List<OSFileStore> obtenerDiscos() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        FileSystem fileSystem = systemInfo.getOperatingSystem().getFileSystem();
        return fileSystem.getFileStores();
    }

    public static long obtenerEspacioTotalDisco() {
        long totalSpace = 0;
        List<OSFileStore> discos = obtenerDiscos();
        for (OSFileStore disco : discos) {
            totalSpace += disco.getTotalSpace();
        }
        return totalSpace/1024/1024/1024;
    }

    public static long obtenerEspacioLibreDisco() {
        long usableSpace = 0;
        List<OSFileStore> discos = obtenerDiscos();
        for (OSFileStore disco : discos) {
                usableSpace += disco.getUsableSpace();
        }
        return usableSpace/1024/1024/1024;
    }

    public static double obtenerPorcentajeUsoDisco() {
        long totalSpace = obtenerEspacioTotalDisco();
        long usableSpace = obtenerEspacioLibreDisco();
        double diskUsage = (1 - (double) usableSpace / totalSpace) * 100;
        diskUsage = Math.round(diskUsage * 100.00) / 100.00;
        return diskUsage;
    }
}