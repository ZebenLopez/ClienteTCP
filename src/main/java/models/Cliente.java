package models;

import utils.*;

import java.util.Arrays;
import java.util.List;

/**
 * The type Cliente.
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class Cliente {
    // Definición de las propiedades del cliente
    private String sistema;
    private String usuario;
    private double cpu;
    private double ram;
    private long espacioDisco;
    private long espacioLibreDisco;
    private double porcentajeOcupadoDisco;

    /**
     * Instantiates a new Cliente.
     */
// Constructor por defecto
    public Cliente() {
        // Inicialización de las propiedades del cliente
        this.sistema = Sistema.obtenerInfo();
        this.usuario = Usuario.obtenerInfo();
        this.espacioDisco = DiscoDuro.obtenerEspacioTotalDisco();
        this.espacioLibreDisco = DiscoDuro.obtenerEspacioLibreDisco();
        this.porcentajeOcupadoDisco = DiscoDuro.obtenerPorcentajeUsoDisco();

        // Actualización de los datos de CPU y RAM
        actualizarDatos();
    }

    /**
     * Actualizar datos.
     */
// Método para actualizar los datos de CPU y RAM
    public void actualizarDatos() {
        this.cpu = CPU.obtenerInfo();
        this.ram = Memoria.obtenerInfo();
    }

    /**
     * Actualizar datos 2.
     */
// Método para actualizar los datos de CPU y RAM (con la CPU multiplicada por 100)
    public void actualizarDatos2() {
        this.cpu = (CPU.obtenerInfo() * 100 );
        this.ram = Memoria.obtenerInfo();
    }

    /**
     * Gets cliente.
     *
     * @return the cliente
     */
// Método para obtener una lista con las propiedades del cliente
    public List<Object> getCliente() {
        return Arrays.asList(sistema, usuario, cpu, ram, espacioDisco, espacioLibreDisco, porcentajeOcupadoDisco);
    }

    /**
     * Instantiates a new Cliente.
     *
     * @param nombre the nombre
     */
// Constructor con parámetro nombre
    public Cliente(String nombre) {
        // Inicialización de las propiedades del cliente
        this.sistema = Sistema.obtenerInfo();
        this.usuario = nombre;
        this.espacioDisco = DiscoDuro.obtenerEspacioTotalDisco();
        this.espacioLibreDisco = DiscoDuro.obtenerEspacioLibreDisco();
        this.porcentajeOcupadoDisco = DiscoDuro.obtenerPorcentajeUsoDisco();

        // Actualización de los datos de CPU y RAM (con la CPU multiplicada por 100)
        actualizarDatos2();
    }
}