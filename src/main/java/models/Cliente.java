package models;

import utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cliente {
    private String sistema;
    private String usuario;
    private String cpu;
    private String ram;
    private List<String> disco;
    private String perifericos;

    public Cliente(String perifericos) {
        this.sistema = Sistema.obtenerInfo();
        this.usuario = Usuario.obtenerInfo();
        this.disco = obtenerInfoDisco();
        this.perifericos = perifericos;
        actualizarDatos();
    }

    private List<String> obtenerInfoDisco() {
        List<InfoDiscoDuro> infoDiscoDuroList = DiscoDuro.obtenerInfo();
        List<String> discoInfo = new ArrayList<>();
        for (InfoDiscoDuro info : infoDiscoDuroList) {
            discoInfo.add(info.toString());
        }
        return discoInfo;
    }

    public void actualizarDatos() {
        this.cpu = CPU.obtenerInfo();
        this.ram = Memoria.obtenerInfo();
    }

    public List<Object> getCliente() {
        return Arrays.asList(sistema, usuario, cpu, ram, disco, perifericos);
    }
}