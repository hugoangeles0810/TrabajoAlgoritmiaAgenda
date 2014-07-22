/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import util.Helper;

/**
 *
 * @author Hugo
 */
public class ArchivoContactos {

    private static final String PATH;
    private static final String NOMBRE_ARCHIVO;
    private static final int TAMAÑO_REGISTRO;
    private static final int NUMERO_REGISTROS;
    private static RandomAccessFile flujoInputOutput;

    static {
        PATH = "D:/";
        NOMBRE_ARCHIVO = "data.dat";
        TAMAÑO_REGISTRO = 55;
        NUMERO_REGISTROS = 1;
        try {
            flujoInputOutput = new RandomAccessFile(PATH + NOMBRE_ARCHIVO, "rw");
        } catch (FileNotFoundException ex) {
            Helper.notificar("No se encontro el archivo de contactos");
        }
    }

    public static void preprocesarArchivo() {
        Contacto con;
        con = new Contacto();
        for (int i = 0; i < NUMERO_REGISTROS; i++) {
            escribeContacto(con);
        }
    }

    public static void escribeContacto(int nrr, Contacto con) {
        try {
            flujoInputOutput.seek(TAMAÑO_REGISTRO * nrr);
        } catch (IOException ex) {
            Helper.notificar("Error al desplazarse por el archivo");
        }
        try {
            flujoInputOutput.writeChars(con.getNombre());
            flujoInputOutput.writeChars(con.getTelefonos()[0]);
            flujoInputOutput.writeChars(con.getTelefonos()[1]);
            flujoInputOutput.writeChars(con.getTelefonos()[2]);
        } catch (IOException ex) {
            Helper.notificar("Error al escribir en el archivo");
        }
    }
    
    private static void escribeContacto(Contacto con) {
        try {
            flujoInputOutput.writeUTF(con.getNombre());
            flujoInputOutput.writeUTF(con.getTelefonos()[0]);
            flujoInputOutput.writeUTF(con.getTelefonos()[1]);
            flujoInputOutput.writeUTF(con.getTelefonos()[2]);
        } catch (IOException ex) {
            Helper.notificar("Error al escribir en el archivo");
        }
    }
}
