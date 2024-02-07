package utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.Locale;

/**
 * Clase CPU para obtener la información de uso de la CPU del sistema.
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */
public class CPU {

    /**
     * Método para obtener el uso de la CPU.
     *
     * @return el uso de la CPU como un porcentaje.
     */
    public static double obtenerInfo() {
        // Creación de un objeto SystemInfo para acceder a la información del sistema
        SystemInfo si = new SystemInfo();

        // Creación de un objeto HardwareAbstractionLayer para acceder a la información del hardware
        HardwareAbstractionLayer hal = si.getHardware();

        // Creación de un objeto CentralProcessor para acceder a la información del procesador
        CentralProcessor processor = hal.getProcessor();

        // Obtención de los ticks del sistema CPU para calcular el uso de la CPU
        long[] prevTicks = processor.getSystemCpuLoadTicks();

        // Espera un segundo para que se puedan calcular las diferencias en los ticks
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cálculo del uso de la CPU entre los ticks y conversión a porcentaje
        double cpuUsage = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100.00;

        // Formateo de la salida para que solo muestre dos decimales
        String cpuUsageFormatted = String.format(Locale.US,"%.2f", cpuUsage);

        // Conversión del uso de la CPU formateado a double y retorno
        cpuUsage = Double.parseDouble(cpuUsageFormatted);
        return cpuUsage;
    }
}