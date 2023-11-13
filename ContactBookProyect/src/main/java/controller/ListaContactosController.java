/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.TextField;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.scene.control.Alert;
import model.Contacto;

/**
 *
 * @author kevin
 */
public class ListaContactosController extends LinkedList<Object> {

    private TextField nombreTextField;
    private TextField apellidoTextField;
    private TextField cumpleaniosTextField;
    private TextField direccionTextField;
    private TextField emailPersonalTextField;
    private TextField emailTrabajoTextField;
    private TextField telefonoTextField;
    private TextField redesSocialesTextField;
    private LinkedList<Contacto> listaContactos;

    public void inicializarListaContactos(LinkedList<Contacto> contactos) {
        if (isEmpty()) {
            mostrarAlerta("Error de Inicialización", "La lista de contactos se encuentra vacía");
        }
        for (Iterator<Contacto> iterator = contactos.iterator(); iterator.hasNext();) {
            Contacto next = iterator.next();
            nombreTextField.setText(String.valueOf(contactos.get(modCount)));
            apellidoTextField.setText(String.valueOf(contactos.get(modCount)));
            cumpleaniosTextField.setText(String.valueOf(contactos.get(modCount)));
            direccionTextField.setText(String.valueOf(contactos.get(modCount)));
            emailPersonalTextField.setText(String.valueOf(contactos.get(modCount)));
            emailTrabajoTextField.setText(String.valueOf(contactos.get(modCount)));
            telefonoTextField.setText(String.valueOf(contactos.get(modCount)));
            redesSocialesTextField.setText(String.valueOf(contactos.get(modCount)));
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
