/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.mavenproject1;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
/**
 *
 * @author LuisA
 */
public class Nodes<E> implements List<E>{

    private int size = 0;
    private int index;
    
    private Nodes<E> first = null;
    private Nodes<E> last = null;
    private Nodes<E> next;
    private Nodes<E> previous;

    private E content;
    
    public Nodes(E content) {
        
        this.next = null;
        this.previous = null;
        this.content = content;
        this.index = 0;
    }

    Nodes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        //Check if e is null
        if (e == null){
            throw new NullPointerException("value is null.");
        }
        
        Nodes newNode = new Nodes(e);
        //Check if linkedList is empty
        if (isEmpty() == true){
            first = newNode;
            last = newNode;
            newNode.next = newNode;
            newNode.previous = newNode;
            newNode.index = 0;
            size++;
            return true;
        }
        else{
            newNode.previous = last;
            last = newNode;
            newNode.next = first;
            newNode.index = newNode.previous.index + 1;
            size++;
            return true;
        }  
    }

    @Override
    public boolean remove(Object o) {
        //Check if linkedList is empty
        if (isEmpty() == true){
            throw new NullPointerException("There is nothing to remove.");
        }
        else{
            Nodes Node = first;
            
            //Buscando el nodo a eliminar
            while(Node.content != o){
                Node = Node.next;
                if (Node == first)
                    return false;
            }
            
            Nodes nNode = Node.next;
            
            //Cambiando la cabeza ó la cola
            if (Node == first)
                first = Node.next;
            
            if (Node == last);
                last = Node.previous;
            
            //Eliminando el nodo
            Node.content = null;
            Node.index = 0;
            Node.previous.next = Node.next;
            Node.next.previous = Node.previous;
            size--;
            
            //Ajustando los indices
            while(nNode != first){
                nNode.index--;
                nNode = nNode.next;
            }
            
            return true;
        }  
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) {
        Nodes Node = first;
        
        //Buscando el nodo
        while (Node.index != index){
            Node = Node.next;
        }
        
        return (E) Node.content;
    }

    @Override
    public E set(int index, E element) {
        Nodes Node = first;
        
        //Buscando el nodo
        while (Node.index != index){
            Node = Node.next;
        }
        
        Node.content = element;
        return (E) Node.content;
    }

    @Override
    public void add(int index, E element) {
        if (index > size)
            throw new NullPointerException("No existe el indice.");
        
        Nodes Node = first;
        
        //Buscando el nodo
        while (Node.index != index){
            Node = Node.next;
        }

        Nodes newNode = new Nodes(element);
        
        //Cambiando la cabeza ó la cola
        if (Node == first)
            first = newNode;
        
        //Reemplazando el nodo con el nuevo nodo
        Node.previous.next = newNode;
        newNode.previous = Node.previous;
        Node.previous = newNode;
        newNode.next = Node;
        newNode.index = Node.index;
        
        //Ajustando los indices
        while(Node != first){
            Node.index++;
            Node = Node.next;
        }
        
        size++;
    }

    @Override
    public E remove(int index) {
        if (index > size)
            throw new NullPointerException("No existe el indice.");
        
        Nodes Node = first;
        
        //Buscando el nodo
        while (Node.index != index){
            Node = Node.next;
        }
        
        E e = (E) Node.content;
        Nodes nNode = Node.next;
            
        //Cambiando la cabeza ó la cola
        if (Node == first)
            first = Node.next;
            
        if (Node == last);
            last = Node.previous;
            
        //Eliminando el nodo
        Node.content = null;
        Node.index = 0;
        Node.previous.next = Node.next;
        Node.next.previous = Node.previous;
            
        //Ajustando los indices
        while(nNode != first){
            nNode.index--;
            nNode = nNode.next;
        }
        
        size--;
        return e;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
