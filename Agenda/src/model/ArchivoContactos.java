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
        NUMERO_REGISTROS = 101;
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

    private static Contacto leerContacto(int nrr) {
        Contacto contacto;
        String nombre, tel1, tel2, tel3;
        try {
            flujoInputOutput.seek(TAMAÑO_REGISTRO * nrr);
            nombre = flujoInputOutput.readUTF();
            tel1 = flujoInputOutput.readUTF();
            tel2 = flujoInputOutput.readUTF();
            tel3 = flujoInputOutput.readUTF();
            String telefonos[] = {tel1, tel2, tel3};
            contacto = new Contacto(nombre, telefonos);
        } catch (IOException ex) {
            contacto = null;
            Helper.notificar("Error al manipular el archivo");
        }
        return contacto;
    }

    private static int generarNRR(Contacto con) {
        int nrr;
        nrr = con.hashCode();
        if (existeColision(nrr)) {
            nrr = tratarColision(nrr);
        }
        return nrr;
    }

    private static boolean existeColision(int nrr) {
        boolean rpta;
        Contacto contacto;
        rpta = false;
        contacto = leerContacto(nrr);
        if (!contacto.getNombre().trim().equals("")) {
            rpta = true;
        }
        return rpta;

    }

    private static int tratarColision(int nrr) {
        int nuevoNRR, i;
        nuevoNRR = 0;
        i = nrr + 1;
        while (!existeColision(i)) {
            if (i == nrr) {
                i = TAMAÑO_REGISTRO;
                break;
            }
            i++;
            if (i == TAMAÑO_REGISTRO) {
                i = 0;
            }
        }
        nuevoNRR = i;
        return nuevoNRR;
    }

    public static Contacto obtenerContacto(String nombre) {
        Contacto contactoDevuelto, contactoBuscado, flag;
        int nrr, i;
        contactoBuscado = new Contacto();
        contactoBuscado.setNombre(nombre);
        nrr = contactoBuscado.hashCode();
        flag = null;
        i = nrr;
        do {
            flag = leerContacto(i);
            if (flag.getNombre().trim().equals("")) {
                flag = null;
            }else if(flag.equals(contactoBuscado)){
                break;
            }
            i++;
            if (i == TAMAÑO_REGISTRO) {
                i = 0;
            }
            
        } while (flag != null && i!=nrr);
        if (i==nrr) {
            flag = null;
        }
        contactoDevuelto = flag;
        return contactoDevuelto;
    }
    
    public static int obtenerNRR(String nombre) {
        Contacto contactoBuscado, flag;
        int nrr, i;
        contactoBuscado = new Contacto();
        contactoBuscado.setNombre(nombre);
        nrr = contactoBuscado.hashCode();
        i = nrr;
        do {
            flag = leerContacto(i);
            if (flag.getNombre().trim().equals("")) {
                flag = null;
            }else if(flag.equals(contactoBuscado)){
                break;
            }
            i++;
            if (i == TAMAÑO_REGISTRO) {
                i = 0;
            }
            
        } while (flag != null && i!=nrr);
        if (i==nrr) {
            i = -1;
        }
        return i;
    }
}
