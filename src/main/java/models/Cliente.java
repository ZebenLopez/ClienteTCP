package models;

import utils.*;

import java.util.Arrays;
import java.util.List;

/**
 * The type Cliente.
 * <p>
 *     Clase que define el modelo de cliente
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class Cliente {
    private String sistema;
    private String usuario;
    private double cpu;
    private double ram;
    private long espacioDisco;
    private long espacioLibreDisco;
    private double porcentajeOcupadoDisco;

    /**
     * Constructor por defecto
     * <p>
     *     Inicializa las propiedades del cliente
     *     <p>
     *         Actualiza los datos de CPU y RAM
     *
     */
    public Cliente() {
        this.sistema = Sistema.obtenerInfo();
        this.usuario = Usuario.obtenerInfo();
        this.espacioDisco = DiscoDuro.obtenerEspacioTotalDisco();
        this.espacioLibreDisco = DiscoDuro.obtenerEspacioLibreDisco();
        this.porcentajeOcupadoDisco = DiscoDuro.obtenerPorcentajeUsoDisco();

        actualizarDatos();
    }

    /**
     * Actualizar datos.
     * <p>
     * Método para actualizar los datos de CPU y RAM
     */
    public void actualizarDatos() {
        this.cpu = CPU.obtenerInfo();
        this.ram = Memoria.obtenerInfo();
    }

    /**
     * Actualizar datos 2.
     * <p>
     * Método para actualizar los datos de CPU y RAM (con la CPU multiplicada por 100)
     */
    public void actualizarDatos2() {
        this.cpu = (CPU.obtenerInfo() * 100 );
        this.ram = Memoria.obtenerInfo();
    }

    /**
     * Método para obtener los datos del cliente
     *
     * @return the cliente
     */
    public List<Object> getCliente() {
        return Arrays.asList(sistema, usuario, cpu, ram, espacioDisco, espacioLibreDisco, porcentajeOcupadoDisco);
    }

    /**
     * Instantiates a new Cliente.
     * Constructor con parámetros
     * <p>
     *     Inicializa las propiedades del cliente con el nombre del usuario
     *     <p>
     *         Actualiza los datos de CPU y RAM
     *
     * @param nombre the nombre
     */
    public Cliente(String nombre) {
        this.sistema = Sistema.obtenerInfo();
        this.usuario = nombre;
        this.espacioDisco = DiscoDuro.obtenerEspacioTotalDisco();
        this.espacioLibreDisco = DiscoDuro.obtenerEspacioLibreDisco();
        this.porcentajeOcupadoDisco = DiscoDuro.obtenerPorcentajeUsoDisco();

        actualizarDatos2();
    }
}