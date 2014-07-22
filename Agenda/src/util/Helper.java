package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Helper {

    private static Scanner t;
    private static BufferedReader entrada;

    static {
        t = new Scanner(System.in);
        entrada = new BufferedReader(new InputStreamReader(System.in));
    }

    public static int leerEntero(int min, int max, String mensaje) {
        int num = 0;
        do {
                System.out.print(mensaje);
                num = t.nextInt();
        } while (num < min || num > max);
        return num;
    }

    public static int leerEntero(int min, int max, String mensaje, int restrict) {
        int num = 0;
        do {
            System.out.print(mensaje);
            num = t.nextInt();
        } while (num < min || num > max || num == restrict);
        return num;
    }

    public static double leerDoble(double min, double max, String mensaje) {
        double num = 0;
        do {
                System.out.print(mensaje);
                num = t.nextDouble();
        } while (num < min || num > max);
        return num;
    }

    public static double leerEntero(double min, double max, String mensaje, double restrict) {
        double num = 0;
        do {
            try {
                System.out.print(mensaje);
                num = t.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("La entrada debe ser un número entero");
            }
        } while (num < min || num > max || num == restrict);
        return num;
    }

    public static String leerCadena(String mensaje) {
        String cad = "";
        System.out.print(mensaje);
        try {
            cad = entrada.readLine();
        } catch (IOException ex) {
        }
        return cad;
    }

    public static String leerCadena(String mensaje, String r1, String r2) {
        String cad = "";
        do {
            System.out.print(mensaje);
            cad = t.next();
        } while (!cad.equalsIgnoreCase(r1) && !cad.equalsIgnoreCase(r2));
        return cad;
    }

    public static char leerCaracterOperacion(String mensaje) {
        String cad;
        do {
            System.out.print(mensaje);
            cad = t.next();
        } while (cad.charAt(0) != 'I' && cad.charAt(0) != 'M' && cad.charAt(0) != 'E');
        return cad.charAt(0);
    }

    public static char leerCaracterTipo(String mensaje) {
        String cad;
        do {
            System.out.print(mensaje);
            cad = t.next();
        } while (cad.charAt(0) != 'E' && cad.charAt(0) != 'S');
        return cad.charAt(0);
    }

    public static boolean obtenerConfirmacion(String msg) {
        String r;
        boolean rpta;

        rpta = false;
        r = Helper.leerCadena(msg + " (s/n)?: ", "s", "n");

        if (r.equalsIgnoreCase("s")) {
            rpta = true;
        }

        return rpta;
    }

    public static void notificar(String msg) {
        System.out.println("-------------------------------------");
        System.out.println(msg);
        System.out.println("-------------------------------------");
    }
}
