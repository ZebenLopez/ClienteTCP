package services;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The type Ayuda.
 *
 * @author Zebenzuí López Conde
 * @version 1.0  2ºA DAM
 */
public class Ayuda {
    /**
     * Abrir help.
     */
    public static void abrirHelp(){
        Desktop desktop = Desktop.getDesktop();
        try {
            File archivo = new File("src/main/resources/help/Ayuda.chm");
            desktop.open(archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
