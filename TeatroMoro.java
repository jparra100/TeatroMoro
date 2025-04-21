package teatromoroapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author jparral
 */
public class TeatroMoro {

    public static String nombreTeatro = "Teatro Moro";
    public static int filas = 5;
    public static int columnas = 6;
    public static double precioUnitario = 5000;

    public static int[][] asientos = new int[filas][columnas];
    public static int totalEntradas = filas * columnas;
    public static int entradasVendidas = 0;
    public static double ingresosTotales = 0;

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    reservar();
                    break;
                case 2:
                    comprar();
                    break;
                case 3:
                    modificar();
                    break;
                case 4:
                    imprimirBoleta();
                    break;
                case 5:
                    mostrarResumen();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n===== TEATRO MORO =====");
        System.out.println("1. Reservar entradas");
        System.out.println("2. Comprar entradas");
        System.out.println("3. Modificar una venta");
        System.out.println("4. Imprimir boleta");
        System.out.println("5. Ver resumen de ventas");
        System.out.println("6. Salir");
    }

    public static void reservar() {
        System.out.print("Ingrese fila (1-5): ");
        int fila = Integer.parseInt(sc.nextLine()) - 1;
        System.out.print("Ingrese asiento (1-6): ");
        int asiento = Integer.parseInt(sc.nextLine()) - 1;

        if (esAsientoValido(fila, asiento)) {
            if (asientos[fila][asiento] == 0) {
                asientos[fila][asiento] = 1;
                System.out.println("Asiento reservado correctamente.");
            } else {
                System.out.println("Asiento no disponible.");
            }
        }
    }

    public static void comprar() {
        System.out.print("Ingrese fila (1-5): ");
        int fila = Integer.parseInt(sc.nextLine()) - 1;
        System.out.print("Ingrese asiento (1-6): ");
        int asiento = Integer.parseInt(sc.nextLine()) - 1;

        if (esAsientoValido(fila, asiento)) {
            if (asientos[fila][asiento] != 2) {
                asientos[fila][asiento] = 2;
                entradasVendidas++;
                ingresosTotales += precioUnitario;
                System.out.println("Compra realizada con éxito.");
            } else {
                System.out.println("El asiento ya está comprado.");
            }
        }
    }

    public static void modificar() {
        System.out.print("Ingrese fila (1-5): ");
        int fila = Integer.parseInt(sc.nextLine()) - 1;
        System.out.print("Ingrese asiento (1-6): ");
        int asiento = Integer.parseInt(sc.nextLine()) - 1;

        if (esAsientoValido(fila, asiento)) {
            if (asientos[fila][asiento] == 2) {
                asientos[fila][asiento] = 0;
                entradasVendidas--;
                ingresosTotales -= precioUnitario;
                System.out.println("Compra anulada y asiento liberado.");
            } else {
                System.out.println("No se puede modificar ese asiento.");
            }
        }
    }

    public static void imprimirBoleta() {
        System.out.print("Ingrese fila (1-5): ");
        int fila = Integer.parseInt(sc.nextLine()) - 1;
        System.out.print("Ingrese asiento (1-6): ");
        int asiento = Integer.parseInt(sc.nextLine()) - 1;

        if (esAsientoValido(fila, asiento)) {
            if (asientos[fila][asiento] == 2) {
                System.out.println("\n------ BOLETA ------");
                System.out.println("Teatro: " + nombreTeatro);
                System.out.println("Ubicación: Fila " + (fila + 1) + ", Asiento " + (asiento + 1));
                System.out.println("Cantidad: 1 entrada");
                System.out.println("Total a pagar: $" + precioUnitario);
            } else {
                System.out.println("Ese asiento no ha sido comprado.");
            }
        }
    }

    public static void mostrarResumen() {
        System.out.println("\n--- RESUMEN DE VENTAS ---");
        System.out.println("Entradas vendidas: " + entradasVendidas);
        System.out.println("Total ingresos: $" + ingresosTotales);
        System.out.println("Asientos disponibles: " + (totalEntradas - entradasVendidas));
    }

    public static boolean esAsientoValido(int fila, int asiento) {
        return fila >= 0 && fila < filas && asiento >= 0 && asiento < columnas;
    }
}
