package proyecto.mavenproject1;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LuisA
 */
public class arrayList<E> implements List<E>{

    private E[] elementos;
    private int capacidad = 100;
    private int size;
    
    public arrayList(){
        elementos = (E[]) new Object[capacidad];
        size = 0;
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean estaLleno(){
        return size == capacidad;
    }

    public void aumentarCapacidad(){
        capacidad = capacidad*2;//se duplica para mayor seguridad la capacidad
        E[] nuevosElementos = (E[]) new Object[capacidad];      
        for (int i = 0; i < size; i++) {//esto es para copiar los elementos del antiguo arreglo al nuebo
            nuevosElementos[i] = elementos[i];
        }       
        this.elementos = nuevosElementos;
    }
    
    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator(){
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public E next() {
                E e = elementos[i];
                i++;
                return e;
            }
            
        };
        return it;
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        //verificar si el elemento esta vacio
        if (e == null) {
            throw new NullPointerException("No hay nada que ingresar.");
        
        //verificar si el arreglo esta vacio
        }if (isEmpty()) {
            elementos[size] = e;
            size++;
            return true;
        }
        
        //verificar si el arreglo esta lleno
        if (estaLleno()) {
            aumentarCapacidad();
        }
        
        //desplazando los elementos para agregar el nuevo
        for (int i = size; i > 0; i--) {
            elementos[i] = elementos[i-1];
        }
        elementos[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public E get(int index) {
        
        if(index < 0) try {
            throw new Exception("No se admiten indices negativos.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if(index > size - 1) try {
            throw new Exception("Indice no existente");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return elementos[index];
    }

    @Override
    public E set(int index, E element) {
        
        if(index < 0) try {
            throw new Exception("No se admiten indices negativos.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if(index > size - 1) try {
            throw new Exception("Indice no existente");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return elementos[index];
    }

    @Override
    public void add(int index, E element) {
        //Revisar si el array está vacío
        if (isEmpty()) {
            elementos[size] = element;
            size++;
            
        //Revisar si el array está lleno
        }if (estaLleno()) {
            aumentarCapacidad();
        }
        
        size++;
        //Reorganizando el array
        for (int i = size; i > index; i--) {
            elementos[i] = elementos [i-1];
        }
        elementos[index] = element;
    }

    @Override
    public E remove(int index) {
        //Confirmar que el indice está en el arreglo
        if (index >= size || index < 0) {
            throw new NullPointerException("El indice no existe.");
        }
        
        //Revisar que el arrglo no esté vacío
        if (isEmpty()) {
            throw new NullPointerException("El arreglo está vacío.");
        }
        
        //Reorganizando el arrglo
        E removido = elementos[index];
        for (int i = index; i < size; i++) {
            elementos[i] = elementos [i+1];
        }
        size--;
        return removido;
    }

    @Override
    public int indexOf(Object o) {
        int accu= 0;
        for(int i = 0; i < size; i++){
            if(!(get(i).equals(elementos))){
                accu += 1;
            }            
        }
        return accu;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString(){
        //Revisar si el arreglo está vacío
        if(isEmpty()){
            throw new NullPointerException("El arreglo está vacío.");
        }
        
        String result = "";
        for(int i=0; i < size; i++){
            if(i == 0){
                result += elementos[i].toString() + ", ";
            }
        }
        return result;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
