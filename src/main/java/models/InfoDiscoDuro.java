package models;
/**
 *
 * @author Zebenzuí López Conde
 * @version 1.0
 * 2ºA DAM
 */

public class InfoDiscoDuro {
    private String nombre;
    private String descripcion;
    private String tipo;
    private String etiqueta;
    private long espacioTotal;
    private long espacioOcupado;
    private String usoDisco;

    // Constructor y getters/setters
    public InfoDiscoDuro(String nombre, String descripcion, String tipo, long espacioTotal, long espacioOcupado, String usoDisco) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.espacioTotal = espacioTotal;
        this.espacioOcupado = espacioOcupado;
        this.usoDisco = usoDisco;
    }

    @Override
    public String toString() {
        return  nombre + ',' +
                descripcion + ',' +
                tipo + ',' +
                espacioTotal + "GB" + ',' +
                espacioOcupado + "GB" + ',' +
                usoDisco + '%';
    }
}