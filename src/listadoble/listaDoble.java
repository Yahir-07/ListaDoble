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

    
     //Inserta un elemento en la posición indicada (0-based).
     //Si pos == 0 inserta al inicio; si pos == tamanio inserta al final.
     
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

    
     //Elimina la primera ocurrencia del valor x.
     //Retorna true si se eliminó, false si la lista está vacía o no se encontró.
     
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

    
     //Elimina y retorna el dato en la posición indicada (0-based).
     //Lanza IndexOutOfBoundsException si pos es inválido.
     
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

    
     //Elimina el último nodo (cola) de la lista.
     //Retorna true si se eliminó, false si estaba vacía.
     
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

    
     //Inserta el dato manteniendo el orden ascendente de la lista.
     
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
}



