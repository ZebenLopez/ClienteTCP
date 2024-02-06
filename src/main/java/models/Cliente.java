package models;

import utils.*;

import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */

public class Cliente {
    private String sistema;
    private String usuario;
    private double cpu;
    private double ram;
    private long espacioDisco;
    private long espacioLibreDisco;
    private double porcentajeOcupadoDisco;


    public Cliente() {
        this.sistema = Sistema.obtenerInfo();
        this.usuario = Usuario.obtenerInfo();
        this.espacioDisco = DiscoDuro.obtenerEspacioTotalDisco();
        this.espacioLibreDisco = DiscoDuro.obtenerEspacioLibreDisco();
        this.porcentajeOcupadoDisco = DiscoDuro.obtenerPorcentajeUsoDisco();

        actualizarDatos();
    }
    public void actualizarDatos() {
        this.cpu = CPU.obtenerInfo();
        this.ram = Memoria.obtenerInfo();
    }
    public void actualizarDatos2() {
        this.cpu = (CPU.obtenerInfo() * 100 );
        this.ram = Memoria.obtenerInfo();
    }
    public List<Object> getCliente() {
        return Arrays.asList(sistema, usuario, cpu, ram, espacioDisco, espacioLibreDisco, porcentajeOcupadoDisco);
    }

    public Cliente(String nombre) {
        this.sistema = Sistema.obtenerInfo();
        this.usuario = nombre;
        this.espacioDisco = DiscoDuro.obtenerEspacioTotalDisco();
        this.espacioLibreDisco = DiscoDuro.obtenerEspacioLibreDisco();
        this.porcentajeOcupadoDisco = DiscoDuro.obtenerPorcentajeUsoDisco();
        actualizarDatos2();
    }
}