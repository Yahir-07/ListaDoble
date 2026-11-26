
import java.util.Scanner;
import listadoble.listaDoble;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author girma
 */
public class Main {
    
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        listaDoble lista = new listaDoble();

        ejecutarPruebasAutomaticas();

        int opcion;
        do {
            System.out.println("\n=== MENU LISTA DOBLEMENTE LIGADA ===");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Insertar en posicion");
            System.out.println("4. Insertar ordenado");
            System.out.println("5. Eliminar por valor");
            System.out.println("6. Eliminar en posicion");
            System.out.println("7. Eliminar ultimo");
            System.out.println("8. Imprimir adelante y atras");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            while (!sc.hasNextInt()) {
                System.out.print("Ingresa un numero valido: ");
                sc.next();
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Dato: ");
                    lista.insertarAlInicio(sc.nextInt());
                    lista.imprimirAdelante();
                    break;
                case 2:
                    System.out.print("Dato: ");
                    lista.insertarAlFinal(sc.nextInt());
                    lista.imprimirAdelante();
                    break;
                case 3:
                    System.out.print("Posicion: ");
                    int posIns = sc.nextInt();
                    System.out.print("Dato: ");
                    int datoIns = sc.nextInt();
                    lista.insertarEnPosicion(posIns, datoIns);
                    lista.imprimirAdelante();
                    lista.imprimirAtras();
                    break;
                case 4:
                    System.out.print("Dato a insertar ordenado: ");
                    lista.insertarOrdenado(sc.nextInt());
                    lista.imprimirAdelante();
                    lista.imprimirAtras();
                    break;
                case 5:
                    System.out.print("Valor a eliminar: ");
                    boolean elimVal = lista.eliminarPorValor(sc.nextInt());
                    System.out.println("Eliminado: " + elimVal);
                    lista.imprimirAdelante();
                    lista.imprimirAtras();
                    break;
                case 6:
                    System.out.print("Posicion a eliminar: ");
                    int posElim = sc.nextInt();
                    try {
                        int valElim = lista.eliminarEnPosicion(posElim);
                        System.out.println("Valor eliminado: " + valElim);
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    lista.imprimirAdelante();
                    lista.imprimirAtras();
                    break;
                case 7:
                    boolean elimUlt = lista.eliminarUltimo();
                    System.out.println("Ultimo eliminado: " + elimUlt);
                    lista.imprimirAdelante();
                    lista.imprimirAtras();
                    break;
                case 8:
                    lista.imprimirAdelante();
                    lista.imprimirAtras();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static void ejecutarPruebasAutomaticas() {
        System.out.println("--- PRUEBAS AUTOMÁTICAS ---");
        listaDoble prueba = new listaDoble();

        prueba.insertarEnPosicion(0, 20);
        prueba.insertarEnPosicion(1, 40);
        prueba.insertarEnPosicion(1, 30);
        prueba.insertarEnPosicion(0, 10);
        prueba.imprimirAdelante();
        prueba.imprimirAtras();

        System.out.println("Eliminar valor 30: " + prueba.eliminarPorValor(30));
        System.out.println("Eliminar valor 999: " + prueba.eliminarPorValor(999));
        prueba.imprimirAdelante();

        System.out.println("Eliminar en posicion 1: " + prueba.eliminarEnPosicion(1));
        prueba.imprimirAdelante();
        prueba.imprimirAtras();

        System.out.println("Eliminar ultimo: " + prueba.eliminarUltimo());
        prueba.imprimirAdelante();
        prueba.imprimirAtras();

        listaDoble ordenada = new listaDoble();
        int[] valores = {50, 10, 30, 20, 40, 25};
        for (int v : valores) {
            ordenada.insertarOrdenado(v);
        }
        System.out.println("Lista insertada con insertarOrdenado:");
        ordenada.imprimirAdelante();
        ordenada.imprimirAtras();
        System.out.println("--- FIN PRUEBAS ---\n");
    }
    
}