package proyecto.mavenproject1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author LuisA
 */
public class Perfil implements Serializable, Comparable<Perfil> {

    public String nombre;
    public String apellido;
    public String dirección;
    public String email;
    public String numero;
    public String identificador;
    public linkedList<String> fotos;
    public boolean favorito;
    public String pais;
    public String tipo;


    public Perfil(String nombre, String apellido, String dirección, String email, String numero, linkedList<String> fotos){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dirección = dirección;
        this.email = email;
        this.numero = numero;
        this.fotos = fotos;
        this.favorito = false;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getEmails() {
        return email;
    }

    public void setEmails(String emails) {
        this.email = emails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public linkedList<String> getFotos() {
        return fotos;
    }

    public void setFotos(linkedList<String> fotos) {
        this.fotos = fotos;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString() {
        String s = "nombre: " + nombre + ", email: " + email + ", numero: " + numero + ", identificador: " + identificador + ", fotos:" + fotos.toString() + ", favorito: " + favorito;
        return s;
    }

    @Override
    public int compareTo(Perfil otroPerfil) {
        return nombre.compareTo(otroPerfil.getNombre());
    }

    // Comparador para ordenar por apellido y nombre
    public static Comparator<Perfil> compareByApellidoYNombre() {
        Comparator<Perfil> comparadorApellido_Nombre = new Comparator<Perfil>() {
            @Override
            public int compare(Perfil perfil1, Perfil perfil2) {
                if ((perfil1.getNombre() == perfil2.getNombre()) && (perfil1.getApellido() == perfil2.getApellido())) {
                    return 0;
                }
                return -1;
            }
        };
        return comparadorApellido_Nombre;
    }

    // Comparador para ordenar por pais de residencia
    public static Comparator<Perfil> compareByCountry() {
        Comparator<Perfil> comparadorPais = new Comparator<Perfil>() {
            @Override
            public int compare(Perfil perfil1, Perfil perfil2) {
                if (perfil1.getPais() == perfil2.getPais()) {
                    return 0;
                }
                return -1;
            }
        };
        return comparadorPais;
    }

    // Comparador para ordenar por cantidad de fotos
    public static Comparator<Perfil> compareByCantidadFotos() {
        Comparator<Perfil> comparadorCantidadFotos = new Comparator<Perfil>() {
            @Override
            public int compare(Perfil perfil1, Perfil perfil2) {
                if (perfil1.getFotos().size() == perfil2.getFotos().size()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        return comparadorCantidadFotos;
    }

}
