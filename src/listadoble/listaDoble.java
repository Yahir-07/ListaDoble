/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package listadoble;

import java.util.Scanner;

/**
 *
 * @author girma
 */
public class listaDoble {
    private nodoD cabeza;
    private nodoD cola;
    private int tamanio;

    public listaDoble() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void insertarAlInicio(int dato) {
        nodoD nuevo = new nodoD(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        tamanio++;
    }

    public void insertarAlFinal(int dato) {
        nodoD nuevo = new nodoD(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        tamanio++;
    }

    /**
     * Inserta un elemento en la posición indicada (0-based).
     * Si pos == 0 inserta al inicio; si pos == tamanio inserta al final.
     */
    public boolean insertarEnPosicion(int pos, int dato) {
        if (pos < 0 || pos > tamanio) {
            System.out.println("Error: posición fuera de rango (" + pos + ").");
            return false;
        }

        if (pos == 0) {
            insertarAlInicio(dato);
            return true;
        }

        if (pos == tamanio) {
            insertarAlFinal(dato);
            return true;
        }

        nodoD actual = cabeza;
        for (int i = 0; i < pos; i++) {
            actual = actual.siguiente;
        }

        nodoD nuevo = new nodoD(dato);
        nodoD prev = actual.anterior;

        prev.siguiente = nuevo;
        nuevo.anterior = prev;
        nuevo.siguiente = actual;
        actual.anterior = nuevo;

        tamanio++;
        return true;
    }

    /**
     * Elimina la primera ocurrencia del valor x.
     * Retorna true si se eliminó, false si la lista está vacía o no se encontró.
     */
    public boolean eliminarPorValor(int x) {
        if (estaVacia()) {
            return false;
        }

        nodoD actual = cabeza;
        while (actual != null && actual.dato != x) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            return false; // No encontrado
        }

        // Caso 1: único elemento en la lista
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        }
        // Caso 2: es la cabeza
        else if (actual == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }
        // Caso 3: es la cola
        else if (actual == cola) {
            cola = cola.anterior;
            cola.siguiente = null;
        }
        // Caso 4: nodo intermedio
        else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }

        actual.siguiente = null;
        actual.anterior = null;
        tamanio--;
        return true;
    }

    /**
     * Elimina y retorna el dato en la posición indicada (0-based).
     * Lanza IndexOutOfBoundsException si pos es inválido.
     */
    public int eliminarEnPosicion(int pos) {
        if (pos < 0 || pos >= tamanio) {
            throw new IndexOutOfBoundsException("Posición inválida: " + pos + " (tamaño: " + tamanio + ")");
        }

        nodoD eliminado;

        if (tamanio == 1) {
            eliminado = cabeza;
            cabeza = null;
            cola = null;
        } else if (pos == 0) {
            eliminado = cabeza;
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            eliminado.siguiente = null;
        } else if (pos == tamanio - 1) {
            eliminado = cola;
            cola = cola.anterior;
            cola.siguiente = null;
            eliminado.anterior = null;
        } else {
            eliminado = cabeza;
            for (int i = 0; i < pos; i++) {
                eliminado = eliminado.siguiente;
            }
            eliminado.anterior.siguiente = eliminado.siguiente;
            eliminado.siguiente.anterior = eliminado.anterior;
            eliminado.siguiente = null;
            eliminado.anterior = null;
        }

        tamanio--;
        return eliminado.dato;
    }

    /**
     * Elimina el último nodo (cola) de la lista.
     * Retorna true si se eliminó, false si estaba vacía.
     */
    public boolean eliminarUltimo() {
        if (estaVacia()) {
            return false;
        }

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.anterior;
            cola.siguiente = null;
        }

        tamanio--;
        return true;
    }

    /**
     * Inserta el dato manteniendo el orden ascendente de la lista.
     */
    public void insertarOrdenado(int dato) {
        if (estaVacia() || dato <= cabeza.dato) {
            insertarAlInicio(dato);
            return;
        }

        if (dato >= cola.dato) {
            insertarAlFinal(dato);
            return;
        }

        nodoD actual = cabeza.siguiente;
        while (actual != null && actual.dato < dato) {
            actual = actual.siguiente;
        }

        nodoD nuevo = new nodoD(dato);
        nodoD prev = actual.anterior;

        prev.siguiente = nuevo;
        nuevo.anterior = prev;
        nuevo.siguiente = actual;
        actual.anterior = nuevo;

        tamanio++;
    }

    public void imprimirAdelante() {
        if (estaVacia()) {
            System.out.println("[Lista vacía]");
            return;
        }
        StringBuilder sb = new StringBuilder("Adelante: [");
        nodoD actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(" <-> ");
            }
            actual = actual.siguiente;
        }
        sb.append("] (tamaño: ").append(tamanio).append(")");
        System.out.println(sb.toString());
    }

    public void imprimirAtras() {
        if (estaVacia()) {
            System.out.println("[Lista vacía]");
            return;
        }
        StringBuilder sb = new StringBuilder("Atrás:    [");
        nodoD actual = cola;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.anterior != null) {
                sb.append(" <-> ");
            }
            actual = actual.anterior;
        }
        sb.append("] (tamaño: ").append(tamanio).append(")");
        System.out.println(sb.toString());
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        listaDoble lista = new listaDoble();

        ejecutarPruebasAutomaticas();

        int opcion;
        do {
            System.out.println("\n=== MENU LISTA DOBLEMENTE LIGADA ===");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Insertar en posicion (insertarEnPosicion)");
            System.out.println("4. Insertar ordenado (insertarOrdenado)");
            System.out.println("5. Eliminar por valor (eliminarPorValor)");
            System.out.println("6. Eliminar en posicion (eliminarEnPosicion)");
            System.out.println("7. Eliminar ultimo (eliminarUltimo)");
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



