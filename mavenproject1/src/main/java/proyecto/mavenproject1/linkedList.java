/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.mavenproject1;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author LuisA
 */
public class linkedList<E> implements List<E>, Serializable {

    private int size = 0;

    private node<E> first = null;

    private node<E> last = null;

    public linkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public class node<E> implements Serializable {

        node<E> next, previous;
        public E content;
        private int index;

        public node(E content) {
            this.next = null;
            this.previous = null;
            this.content = content;
            this.index = 0;
        }
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
        Iterator<E> it = new Iterator<E>() {

            node<E> cursor = first;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                if (cursor == last) {
                    cursor = null;
                    return last.content;
                }
                cursor = cursor.next;

                return cursor.previous.content;
            }
        };

        return it;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;

        for (E element : this) {
            array[index++] = element;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        //Revisando si hay contenido
        if (e == null) {
            throw new NullPointerException("No hay nada que ingresar.");
        }

        node newNode = new node(e);
        //Revisando si la lista esta vacía
        if (isEmpty() == true) {
            first = newNode;
            last = newNode;
            newNode.next = newNode;
            newNode.previous = newNode;
            size++;
            return true;
        } else {
            node tmp = first;
            while (tmp.next != first) {
                tmp = tmp.next;
            }

            //Ingresando el nodo
            newNode.index = tmp.index++;
            tmp.next = newNode;
            last = newNode;
            newNode.previous = tmp;
            newNode.next = first;
            first.previous = newNode;
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        //Check if linkedList is empty
        if (isEmpty() == true) {
            throw new NullPointerException("There is nothing to remove.");
        } else {
            node current = first;

            //Buscando el nodo a eliminar
            while (current.content != o) {
                current = current.next;
                if (current == first) {
                    return false;
                }
            }

            node nNode = current.next;

            //Cambiando la cabeza ó la cola
            if (current == first) {
                first = current.next;
            }

            if (current == last);
            last = current.previous;

            //Eliminando el nodo
            current.content = null;
            current.index = 0;
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;

            //Ajustando los indices
            while (nNode != first) {
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
        if (isEmpty()) {
            return;
        }

        last = null;
    }

    @Override
    public E get(int index) {
        node current = first;

        //Buscando el nodo
        while (current.index != index) {
            current = current.next;
        }

        return (E) current.content;
    }

    @Override
    public E set(int index, E element) {
        node current = first;

        //Buscando el nodo
        while (current.index != index) {
            current = current.next;
        }

        current.content = element;
        return (E) current.content;
    }

    @Override
    public void add(int index, E element) {
        if (index > size) {
            throw new NullPointerException("No existe el indice.");
        }

        node current = first;

        //Buscando el nodo
        while (current.index != index) {
            current = current.next;
        }

        node newNode = new node(element);

        //Cambiando la cabeza ó la cola
        if (index == 0) {
            first = newNode;
        }

        //Reemplazando el nodo con el nuevo nodo
        current.previous.next = newNode;
        newNode.previous = current.previous;
        current.previous = newNode;
        newNode.next = current;
        newNode.index = current.index;

        //Ajustando los indices
        while (current != first) {
            current.index++;
            current = current.next;
        }

        size++;
    }

    @Override
    public E remove(int index) {
        if (index > size) {
            throw new NullPointerException("No existe el indice.");
        }

        node current = first;

        //Buscando el nodo
        while (current.index != index) {
            current = current.next;
        }

        E e = (E) current.content;
        node nNode = current.next;

        //Cambiando la cabeza ó la cola
        if (current == first) {
            first = current.next;
        }

        if (current == last);
        last = current.previous;

        //Eliminando el nodo
        current.content = null;
        current.index = 0;
        current.previous.next = current.next;
        current.next.previous = current.previous;

        //Ajustando los indices
        while (nNode != first) {
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
        if (isEmpty()) {
            return null;
        }
        return new ListIterator<E>() {

            node<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
//                if(node == last){
//                    
//                    node = first;
//                    
//                    return last.content;
//                }
                node = node.next;

                return node.previous.content;
            }

            @Override
            public boolean hasPrevious() {
                return node != null;
            }

            @Override
            public E previous() {
//                if(node == first){
//                    
//                    node = last;
//                    
//                    return first.content;
//                }
                node = node.previous;

                return node.previous.content;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void remove() {
                if (this.node.previous != null) {
                    this.node.previous.next = this.node.next;
                }
                if (this.node.next != null) {
                    this.node.next.previous = this.node.previous;
                }
            }

            @Override
            public void set(E e) {
                if (node != null) {
                    node.content = e;
                } else {
                    throw new IllegalStateException("No se ha llamado a next() o previous() o ya se ha eliminado el elemento.");
                }
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {

        String s = "";

        for (node<E> v = this.first; v != this.last; v = v.next) {
            s += v.content.toString() + " ";
        }
//        s += last.content.toString();
        return s;
    }
}
