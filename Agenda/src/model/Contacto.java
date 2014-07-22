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
        for (String tel : telefonos) {
            tel = "         ";
        }
    }

    public Contacto(String nombre, String[] telefonos) {
        if(nombre.length()>20) nombre = nombre.substring(0,20);
        if(telefonos[0].length()>9) telefonos[0] = telefonos[0].substring(0, 9);
        if(telefonos[1].length()>9) telefonos[1] = telefonos[1].substring(0, 9);
        if(telefonos[2].length()>9) telefonos[2] = telefonos[2].substring(0, 9);
        this.nombre = nombre;
        this.telefonos = new String[3];
        this.telefonos[0] = telefonos[0];
        this.telefonos[1] = telefonos[1];
        this.telefonos[2] = telefonos[2];
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
    
}
