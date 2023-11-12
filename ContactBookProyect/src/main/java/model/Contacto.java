/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.LinkedList;

/**
 *
 * @author kevin
 */
public class Contacto {

    private String nombre;
    private String apellido;
    private LinkedList<Atributo> atributos;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LinkedList<Atributo> getAtributos() {
        return atributos;
    }

    public void agregarAtributo(Atributo atributo) {
        atributos.add(atributo);
    }

    public void eliminarAtributo(Atributo atributo) {
        atributos.remove(atributo);
    }

}
