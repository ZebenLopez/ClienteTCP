package utils;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.Locale;

public class Memoria {
    public static double obtenerInfo() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();

        GlobalMemory memory = hardware.getMemory();
        long availableMemory = memory.getAvailable();
        long totalMemory = memory.getTotal();

        double memoryUsage = (1 - (double) availableMemory / totalMemory) * 100;
        String formattedMemoryUsage = String.format(Locale.US, "%.2f", memoryUsage);
        memoryUsage = Double.parseDouble(formattedMemoryUsage);
        return memoryUsage;
    }
}
