/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listadoble;

/**
 *
 * @author girma
 */
public class nodoD {
    public int dato;
    public nodoD siguiente;
    public nodoD anterior;

    public nodoD(int dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}
