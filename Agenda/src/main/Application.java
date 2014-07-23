/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import model.ArchivoContactos;
import model.Contacto;
import util.Helper;

/**
 *
 * @author Hugo
 */
public class Application {

    public Application() {
    }

    public static void main(String[] args) {
        Application ap;
        int opcion;
        ap = new Application();

        ap.imprimeCabezera();

        do {
            ap.imprimeOpciones();
            opcion = Helper.leerEntero(0, 5, "Opcion: ");

            switch (opcion) {
                case 1:
                    ap.nuevoContacto();
                    break;
                case 2:
                    ap.editarContacto();
                    break;
                case 3:
                    ap.eliminarContacto();
                    break;
                case 4:
                    ap.buscarContacto();
                    break;
                case 5:
                    ap.listarContactos();
                    break;
            }
        } while (opcion != 0);
    }

    public void imprimeCabezera() {
        System.out.println("-------------------------------------------");
        System.out.println("         Agenda Telefonica v1.0");
    }

    public void imprimeOpciones() {
        System.out.println("-------------------------------------------");
        System.out.println("Menu Principal");
        System.out.println("\t[1] Nuevo Contacto");
        System.out.println("\t[2] Editar Contacto");
        System.out.println("\t[3] Eliminar Contacto");
        System.out.println("\t[4] Buscar Contacto");
        System.out.println("\t[5] Listar Contactos");
        System.out.println("\t[0] Salir");
    }

    public void nuevoContacto() {
        Contacto contacto;
        int nrr;
        contacto = Contacto.leerContactoPorConsola();
        nrr = ArchivoContactos.generarNRR(contacto);
        System.out.println("Nrr: " + nrr);
        if (nrr == ArchivoContactos.NUMERO_REGISTROS) {
            Helper.notificar("El archivo de contactos esta lleno");
        } else {
            ArchivoContactos.escribeContacto(nrr, contacto);
            Helper.notificar("Contacto registrado");
        }

    }

    private void editarContacto() {
        int nrr;
        String nombre;
        String edicion;
        Contacto contacto;
        contacto = null;
        nombre = Helper.leerCadena("Nombre: ");
        nrr = ArchivoContactos.obtenerNRR(nombre);

        if (nrr == -1) {
            Helper.notificar("No se encontró el registro");
        }else{
            contacto = ArchivoContactos.leerContacto(nrr);
            
            System.out.println("Editando Teléfonos del Contacto......");
            System.out.println("Para Eliminar Tecle 'E' ");
            
            edicion=Helper.leerCadena("Telefono 1:: "+contacto.getTelefonos()[0]+"----> Nueva Edicion::");
            if(!edicion.equals("")&&!edicion.equals("E")) contacto.getTelefonos()[0]=edicion;
            if(edicion.equals("E"))contacto.getTelefonos()[0]="         ";
            
            edicion=Helper.leerCadena("Telefono 2:: "+contacto.getTelefonos()[1]+"----> Nueva Edicion::");
            if(!edicion.equals("")&&!edicion.equals("E")) contacto.getTelefonos()[1]=edicion;
            if(edicion.equals("E"))contacto.getTelefonos()[1]="         ";
            
            edicion=Helper.leerCadena("Telefono 3:: "+contacto.getTelefonos()[2]+"----> Nueva Edicion::");
            if(!edicion.equals("")&&!edicion.equals("E")) contacto.getTelefonos()[2]=edicion;
            if(edicion.equals("E"))contacto.getTelefonos()[2]="         ";
            
            ArchivoContactos.escribeContacto(nrr, contacto);
            
            Helper.notificar("- -Se editaron teléfonos del contacto - -");
            
        }

    }

    public void eliminarContacto() {
        int nrr;
        String nombre;
        Contacto contacto;
        contacto = null;
        nombre = Helper.leerCadena("Nombre: ");
        nrr = ArchivoContactos.obtenerNRR(nombre);

        if (nrr == -1) {
            Helper.notificar("No se encontro el registro");
        } else {
            contacto = new Contacto();
            ArchivoContactos.escribeContacto(nrr, contacto);
            Helper.notificar("Se elimino registro");
        }
    }

    public void buscarContacto() {
        int nrr;
        String nombre;
        Contacto contacto;
        contacto = null;
        nombre = Helper.leerCadena("Nombre: ");
        nrr = ArchivoContactos.obtenerNRR(nombre);
        if (nrr == -1) {
            Helper.notificar("No se encontro el registro");
        } else {
            contacto = ArchivoContactos.leerContacto(nrr);
            mostrarContacto(contacto);
        }
    }

    public void listarContactos() {
        Contacto c;
        System.out.println("+--------------------+---------+");
        System.out.println("|       Nombre       | Telefono|");
        System.out.println("+--------------------+---------+");
        for (int i = 0; i < ArchivoContactos.NUMERO_REGISTROS; i++) {
            c = ArchivoContactos.leerContacto(i);
            if (!c.getNombre().trim().equals("")) {
                System.out.printf("|%20s|%9s|\n", c.getNombre(), c.getTelefonos()[0]);
                System.out.printf("|%20s|%9s|\n", " ", c.getTelefonos()[1]);
                System.out.printf("|%20s|%9s|\n", " ", c.getTelefonos()[2]);
                System.out.println("+--------------------+---------+");
            }
        }
    }

    public void mostrarContacto(Contacto con) {
        System.out.println("+--------------------+---------+");
        System.out.println("|       Nombre       | Telefono|");
        System.out.println("+--------------------+---------+");
        System.out.printf("|%20s|%9s|\n", con.getNombre(), con.getTelefonos()[0]);
        System.out.printf("|%20s|%9s|\n", " ", con.getTelefonos()[1]);
        System.out.printf("|%20s|%9s|\n", " ", con.getTelefonos()[2]);
        System.out.println("+--------------------+---------+");

    }
}    
