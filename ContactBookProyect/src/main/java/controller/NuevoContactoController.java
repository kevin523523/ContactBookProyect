/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Atributo;
import java.awt.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Contacto;

/**
 *
 * @author kevin
 */
public class NuevoContactoController {   
    private TextField nombreTextField;
    private TextField apellidoTextField;
    private TextField cumpleaniosTextField;
    private TextField direccionTextField;
    private TextField emailPersonalTextField;
    private TextField emailTrabajoTextField;
    private TextField telefonoTextField;
    private TextField redesSocialesTextField;
    private Contacto nuevoContacto;

    public Contacto getNuevoContacto() {
        return nuevoContacto;
    }

    private void guardarNuevoContacto() {
        String nombre = nombreTextField.getText();
        String apellido = apellidoTextField.getText();
        String cumpleanios = cumpleaniosTextField.getText();
        String direccion = direccionTextField.getText();
        String emailPersonal = emailPersonalTextField.getText();
        String emailTrabajo = emailTrabajoTextField.getText();
        String telefono = telefonoTextField.getText();
        String redesSociales = redesSocialesTextField.getText();

        // Validacion si el nombre esta vacio
        if (nombre.isEmpty()) {
            mostrarAlerta("Error de Validación", "El nombre no puede estar vacío.");
            return;
        }
        // Validacion si el apellido esta vacio
        if (apellido.isEmpty()) {
            mostrarAlerta("Error de Validación", "El apellido no puede estar vacío.");
            return;
        }
        // Validacion del correo electrónico
        if (!emailPersonal.isEmpty() && !esFormatoCorreoValido(emailPersonal)) {
            mostrarAlerta("Error de Validación", "El formato del correo electrónico personal no es válido.");
            return;
        }
        // Validacion del correo electrónico de trabajo
        if (!emailTrabajo.isEmpty() && !esFormatoCorreoValido(emailTrabajo)) {
            mostrarAlerta("Error de Validación", "El formato del correo electrónico de trabajo no es válido.");
            return;
        }
        // Validacion del teléfono 
        if (!telefono.isEmpty() && !esNumero(telefono)) {
            mostrarAlerta("Error de Validación", "El número de teléfono debe ser numérico.");
            return;
        }
        // Crear el nuevo contacto
        nuevoContacto = new Contacto();
        nuevoContacto.setNombre(nombre);
        nuevoContacto.setApellido(apellido);
        nuevoContacto.agregarAtributo(new Atributo("Cumpleaños", cumpleanios));
        nuevoContacto.agregarAtributo(new Atributo("Dirección", direccion));
        nuevoContacto.agregarAtributo(new Atributo("Email Personal", emailPersonal));
        nuevoContacto.agregarAtributo(new Atributo("Email Trabajo", emailTrabajo));
        nuevoContacto.agregarAtributo(new Atributo("Teléfono", telefono));
        nuevoContacto.agregarAtributo(new Atributo("Redes Sociales", redesSociales));

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean esFormatoCorreoValido(String correo) {
        return correo.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
    }

    private boolean esNumero(String texto) {
        return texto.matches("\\d+");
    }

}
