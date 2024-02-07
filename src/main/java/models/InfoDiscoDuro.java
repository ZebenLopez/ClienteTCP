package models;

/**
 * The type Info disco duro.
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class InfoDiscoDuro {
    // Definición de las propiedades de la información del disco duro
    private String nombre;
    private String descripcion;
    private String tipo;
    private long espacioTotal;
    private long espacioOcupado;
    private String usoDisco;

    /**
     * Instantiates a new Info disco duro.
     *
     * @param nombre         the nombre
     * @param descripcion    the descripcion
     * @param tipo           the tipo
     * @param espacioTotal   the espacio total
     * @param espacioOcupado the espacio ocupado
     * @param usoDisco       the uso disco
     */
// Constructor que inicializa las propiedades de la información del disco duro
    public InfoDiscoDuro(String nombre, String descripcion, String tipo, long espacioTotal, long espacioOcupado, String usoDisco) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.espacioTotal = espacioTotal;
        this.espacioOcupado = espacioOcupado;
        this.usoDisco = usoDisco;
    }

    // Método toString que devuelve una representación en cadena de la información del disco duro
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