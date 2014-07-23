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
    public static final int NUMERO_REGISTROS;
    private static RandomAccessFile flujoInputOutput;

    static {
        PATH = "D:/";
        NOMBRE_ARCHIVO = "data.dat";
        TAMAÑO_REGISTRO = 47;
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
            escribeCadena(con.getNombre());
            escribeCadena(con.getTelefonos()[0]);
            escribeCadena(con.getTelefonos()[1]);
            escribeCadena(con.getTelefonos()[2]);
        } catch (Exception ex) {
            Helper.notificar("Error al escribir en el archivo");
        }
    }

    private static void escribeContacto(Contacto con) {
        try {
            escribeCadena(con.getNombre());
            escribeCadena(con.getTelefonos()[0]);
            escribeCadena(con.getTelefonos()[1]);
            escribeCadena(con.getTelefonos()[2]);
        } catch (Exception ex) {
            Helper.notificar("Error al escribir en el archivo");
        }
    }

    public static Contacto leerContacto(int nrr) {
        Contacto contacto;
        String nombre, tel1, tel2, tel3;
        contacto = null;
        try {
            flujoInputOutput.seek(TAMAÑO_REGISTRO * nrr);
            nombre = leerCadena(20);
            tel1 = leerCadena(9);
            tel2 = leerCadena(9);
            tel3 = leerCadena(9);
            String telefonos[] = {tel1, tel2, tel3};
            contacto = new Contacto(nombre, telefonos);
        } catch (IOException ex) {
            ex.printStackTrace();
            Helper.notificar("Error al manipular el archivo");
        }
        return contacto;
    }

    public static int generarNRR(Contacto con) {
        int nrr;
        nrr = con.hashCode();
        System.out.println("Existe colision: " + existeColision(nrr));
        if (existeColision(nrr)) {
            System.out.println("Entro a tratar colision");
            nrr = tratarColision(nrr);
            System.out.println("SAlio de tratar colision");
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
        while (existeColision(i)) {
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
        System.out.println("Nuevo nrr");
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
            } else if (flag.equals(contactoBuscado)) {
                break;
            }
            i++;
            if (i == TAMAÑO_REGISTRO) {
                i = 0;
            }

        } while (flag != null && i != nrr);
        if (i == nrr) {
            flag = null;
        }
        contactoDevuelto = flag;
        return contactoDevuelto;
    }

    public static int obtenerNRR(String nombre) {
        Contacto contactoBuscado, flag;
        int nrrFlag, i ,nrr;
        contactoBuscado = new Contacto();
        contactoBuscado.setNombre(nombre);
        nrrFlag = contactoBuscado.hashCode();
        i = nrrFlag;
        nrr = nrrFlag;
        do {
            flag = leerContacto(i);
            if (flag.getNombre().trim().equals("")) {
                flag = null;
                nrr = -1;
            } else if (flag.equals(contactoBuscado)) {
                nrr = i;
                nrrFlag = -1;
                break;
            }
            i++;
            if (i == TAMAÑO_REGISTRO) {
                i = 0;
            }

        } while (flag != null && i != nrrFlag);
        if (i == nrrFlag) {
            nrr = -1;
        }
        return nrr;
    }

    public static void cerrarConexion() {
        try {
            flujoInputOutput.close();
            flujoInputOutput = null;
        } catch (IOException ex) {
        }
    }
    
    public static String leerCadena(int len){
        String cadena;
        cadena = "";
        char car;
        
        for (int i = 0; i < len; i++) {
            try {
                car = (char)flujoInputOutput.readByte();
                cadena += car;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return cadena;
    }
    
    public static void escribeCadena(String cadena){
        for (int i = 0; i < cadena.length(); i++) {
            try {
                flujoInputOutput.writeByte(cadena.charAt(i));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
