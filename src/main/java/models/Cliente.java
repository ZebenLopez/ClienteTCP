package models;

import java.util.List;

public class Cliente {

    private String sistemaOperativo = "Windows";
    private String nombre = "Lopez";
    private String ip = "localhost";
    private String cpu = "Intel";
    private String ram = "8GB";
    private String discoDuro = "1TB";

    public Cliente(String sistemaOperativo, String nombre, String ip, String cpu, String ram, String discoDuro) {
        this.sistemaOperativo = sistemaOperativo;
        this.nombre = nombre;
        this.ip = ip;
        this.cpu = cpu;
        this.ram = ram;
        this.discoDuro = discoDuro;
    }

    public Cliente() {

    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(String discoDuro) {
        this.discoDuro = discoDuro;
    }

    public List<String> getCliente() {
        return List.of(
                this.sistemaOperativo,
                this.nombre,
                this.ip,
                this.cpu,
                this.ram,
                this.discoDuro
        );
    }
}