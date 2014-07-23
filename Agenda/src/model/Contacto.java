/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.StringTokenizer;
import util.Helper;

/**
 *
 * @author Hugo
 */
public class Contacto {

    private String nombre;
    private String[] telefonos;

    public Contacto() {
        this.nombre = "                    ";
        this.telefonos = new String[3];
        for (int i = 0; i < telefonos.length; i++) {
            this.telefonos[i] = "         ";
        }
    }

    public Contacto(String nombre, String[] telefonos) {
        this.nombre = Helper.formateaCadenaConEspacios(nombre, 20);
        this.telefonos = new String[3];
        this.telefonos[0] = Helper.formateaCadenaConEspacios(telefonos[0], 9);
        this.telefonos[1] = Helper.formateaCadenaConEspacios(telefonos[1], 9);
        this.telefonos[2] = Helper.formateaCadenaConEspacios(telefonos[2], 9);
//        System.out.println("Nombre: " + this.nombre);
//        for (String tel : this.telefonos) {
//            System.out.println("Tel: "+tel+" Length: "+ tel.length());
//        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String[] getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String[] telefonos) {
        this.telefonos = telefonos;
    }

    public static Contacto leerContactoPorConsola() {
        Contacto contacto;
        String nombre;
        String telefono1,telefono2,telefono3;
        //StringTokenizer token;
        nombre = Helper.leerCadena("Nombre: ");
        telefono1 = Helper.leerCadena("Telefono 1: ");
        telefono2 = Helper.leerCadena("Telefono 2: ");
        telefono3 = Helper.leerCadena("Telefono 3: ");
        //token = new StringTokenizer(telefonos, " ");
       // String arrayTelefonos[] = {token.nextToken(), token.nextToken(), token.nextToken()};
        String arrayTelefonos[] = {telefono1,telefono2,telefono3};
        contacto = new Contacto(nombre, arrayTelefonos);        
        return contacto;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + " Tel1: " + this.telefonos[0] + " Tel2: " + this.telefonos[1] + " Tel3: " + this.telefonos[2];
    }

    @Override
    public int hashCode() {
        int indice;
        int numero;
        char car;
        numero = 0;
        for (int i = 0; i < this.nombre.trim().length(); i++) {
            car = this.nombre.charAt(i);
            numero = numero + car;
        }
        indice = numero % 101;
        return indice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contacto) {
            Contacto contacto = (Contacto) obj;
            return this.nombre.trim().equals(contacto.getNombre().trim());
        }else{
            return false;
        }
    }
}
