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
    
    public static Contacto leerContactoPorConsola(){
        Contacto contacto;
        String nombre;
        String telefonos;
        StringTokenizer token;
        nombre = Helper.leerCadena("Nombre: ");
        telefonos = Helper.leerCadena("Telefonos: ");
        token = new StringTokenizer(telefonos, " ");
        System.out.println(telefonos);
        String arrayTelefonos[]= {token.nextToken(), token.nextToken(), token.nextToken()};
        contacto = new Contacto(nombre, arrayTelefonos );
        return contacto;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + " Tel1: " + this.telefonos[0] + " Tel2: " + this.telefonos[1] + " Tel3: " + this.telefonos[2];
    }     
    
    public static void main(String[] args) {
        leerContactoPorConsola();
        
    }
}
