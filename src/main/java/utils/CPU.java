package utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.Locale;
/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */

public class CPU {

    public static double obtenerInfo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor processor = hal.getProcessor();

        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // Espera un segundo para que se puedan calcular las diferencias en los ticks
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double cpuUsage = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100.00;

        // Formatear la salida para que solo muestre dos decimales
        String cpuUsageFormatted = String.format(Locale.US,"%.2f", cpuUsage);

        cpuUsage = Double.parseDouble(cpuUsageFormatted);
        return cpuUsage;
    }
}