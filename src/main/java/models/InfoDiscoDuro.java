package models;

/**
 * The type Info disco duro.
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class InfoDiscoDuro {
    private String nombre;
    private String descripcion;
    private String tipo;
    private long espacioTotal;
    private long espacioOcupado;
    private String usoDisco;

    /**
     * Instantiates a new Info disco duro.
     * Constructor que inicializa las propiedades de la información del disco duro
     *
     *
     * @param nombre          nombre
     * @param descripcion     descripcion
     * @param tipo            tipo
     * @param espacioTotal    espacio total
     * @param espacioOcupado  espacio ocupado
     * @param usoDisco        uso disco
     */
    public InfoDiscoDuro(String nombre, String descripcion, String tipo, long espacioTotal, long espacioOcupado, String usoDisco) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.espacioTotal = espacioTotal;
        this.espacioOcupado = espacioOcupado;
        this.usoDisco = usoDisco;
    }
    /**
     * Método toString que devuelve una representación en cadena de la información del disco duro
     * @return the nombre
     */
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