/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.contackbo;

import datos.CONSTANTES;
import model.Contacto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author jucazuse
 */
public class ContactosController implements Initializable {

    /*Anotacion que etiqueta una clase o miembro como accesible*/
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private RadioButton rbMasculino;
    @FXML
    private RadioButton rbFemenino;
    @FXML
    private ToggleGroup grupoSexo;
    @FXML
    private ComboBox cbCiudad;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtTelefonoFijo;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtCorreo;
    @FXML
    private String separadorArchivo = System.getProperty("file.separator");
    @FXML
    private String rutaDondeSeCreaArchivo = System.getProperty("user.dir");
    @FXML
    private File file = new File(rutaDondeSeCreaArchivo.concat(separadorArchivo.concat("Agenda.txt")));
    @FXML
    private String linea;
    @FXML
    private String[] pos;
    @FXML
    private FileReader lectorArchivo;
    @FXML
    private BufferedReader almacen;
    @FXML
    private String almacenFilaUno;
    @FXML
    private String almacenFilaDos;
    @FXML
    private String almacenFilaTres;
    @FXML
    private String almacenFilaCuatro;
    @FXML
    private File imagen;
    @FXML
    private Label lbImagen;
    @FXML
    private Stage primaryStage;
    @FXML
    private FileWriter fichero;
    @FXML
    private ImageView visor;
    @FXML
    private InputStream inputStream;
    @FXML
    private Image image;
    @FXML
    private MenuBar mbGuardar;
    @FXML
    private String subString;
    @FXML
    private int longitudCadena;
    @FXML
    private File rutaImagen;
    @FXML
    private File imagenItemGuardar;
    @FXML
    private File imagenItemReporte;
    @FXML
    private Button btnGuardar;

    /*Metodo para llenar comboBox*/
    @FXML
    private void llenarComboBox() {
        /*con esto borramos los Items que trae por defecto los 
         choice box*/
        cbCiudad.getItems().clear();
        cbCiudad.setItems(FXCollections.observableArrayList("Ambato", "Babahoyo", "Cuenca", "Daule", "Durán", "Esmeraldas", "Guayaquil", "Ibarra", "Machala", "Manta", "Milagro", "Palestina", "Quito"));
    }

    @FXML
    private void guardar() throws IOException {
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String sexo = ((RadioButton) grupoSexo.getSelectedToggle()).getText();
        String direccion = txtDireccion.getText();
        String correo = txtCorreo.getText();
        String celular = txtCelular.getText();
        String telefonoFijo = txtTelefonoFijo.getText();
        String ciudad = cbCiudad.getValue().toString();

        try (BufferedWriter outputStream
                = new BufferedWriter(new FileWriter(CONSTANTES.ARCHIVOS + "contactos.txt", true))) {
            Contacto contacto = new Contacto(nombre, apellido, sexo, ciudad, direccion, celular,telefonoFijo,correo);
            contactos.add(contacto);
            outputStream.write(nombre + ";" + apellido+ ";" + sexo+ ";"+ciudad+ ";"+direccion+ ";" +celular+ ";" +telefonoFijo+ ";"+correo+"\n");
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file out.txt." + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException." + e.getMessage());
        }       
        App.setRoot("Inicio");
        }


    /*Metodo para que solo se pueda escoger un radio boton*/
    @FXML
    private void BotonGrupo() {
        grupoSexo = new ToggleGroup();
        rbFemenino.setToggleGroup(grupoSexo);
        rbFemenino.setSelected(true);
        rbMasculino.setToggleGroup(grupoSexo);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /*Metodo para que el usuario escoja una imagen del contacto
     agregado*/
    @FXML
    public void elegirImagen(ActionEvent elegirImagen) {
        FileChooser elegir = new FileChooser();
        FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("Add Files(*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extensionPNG = new FileChooser.ExtensionFilter("Add Files(*.png)", "*.png");
        FileChooser.ExtensionFilter extensionJPEG = new FileChooser.ExtensionFilter("Add Files(*.jpeg)", "*.jpeg");
        elegir.getExtensionFilters().add(extension);
        elegir.getExtensionFilters().add(extensionPNG);
        elegir.getExtensionFilters().add(extensionJPEG);
        rutaImagen = elegir.showOpenDialog(primaryStage);
        try {
            if (rutaImagen == null) {
                mostrarAlerta("Error", "Usted no ha seleccionado ninguna imagen");
            } else {
                inputStream = new FileInputStream(rutaImagen);
                image = new Image(inputStream);
                visor = new ImageView(image);
                visor.setFitWidth(128);
                visor.setPreserveRatio(true);
                lbImagen.setGraphic(visor);
            }

        } catch (FileNotFoundException ex) {

        }
    }

    @FXML
    public void validarCampos() {
        if (!txtNombre.getText().matches("[ /s a-zA-z]*")) {
            longitudCadena = txtNombre.getText().length();
            subString = txtNombre.getText().substring(0, longitudCadena - 1);
            txtNombre.setText(subString);
        }
        if (!txtApellido.getText().matches("[ /s a-zA-z]*")) {
            longitudCadena = txtApellido.getText().length();
            subString = txtApellido.getText().substring(0, longitudCadena - 1);
            txtApellido.setText(subString);
        }
        if (!txtCelular.getText().matches("[ 0-9 ]*")) {
            longitudCadena = txtCelular.getText().length();
            subString = txtCelular.getText().substring(0, longitudCadena - 1);
            txtCelular.setText(subString);
        }
        if (!txtTelefonoFijo.getText().matches("[ 0-9 ]*")) {
            longitudCadena = txtTelefonoFijo.getText().length();
            subString = txtTelefonoFijo.getText().substring(0, longitudCadena - 1);
            txtTelefonoFijo.setText(subString);
        }

    }

    /*Metodo para cargar la imagen de muestra apenas carga el
     programa*/
    @FXML
    public void imagenPrincipio() {/*
        try {
            imagen = new File(rutaDondeSeCreaArchivo.concat(separadorArchivo.concat("sinImagen.png")));
            inputStream = new FileInputStream(imagen);
            image = new Image(inputStream);
            visor = new ImageView(image);
            visor.setFitWidth(128);
            visor.setPreserveRatio(true);
            lbImagen.setGraphic(visor);
        } catch (FileNotFoundException ex) {
            
        }
         */
    }

    @FXML
    public void cargarItemsMenuBar() {/*
        Menu menuArchivo = new Menu("Archivo");
        MenuItem menuGuardar = new MenuItem("Guardar");
        MenuItem menuReporte = new MenuItem("Reporte");
        imagenItemGuardar = new File(rutaDondeSeCreaArchivo.concat(separadorArchivo.concat("guardar.png")));
        imagenItemReporte = new File(rutaDondeSeCreaArchivo.concat(separadorArchivo.concat("Reporte.png")));
        try {
            inputStream = new FileInputStream(imagenItemGuardar);
            image = new Image(inputStream);
            visor = new ImageView(image);
            menuGuardar.setGraphic(visor);
        } catch (FileNotFoundException ex) {

        }
        try {
            inputStream = new FileInputStream(imagenItemReporte);
            image = new Image(inputStream);
            visor = new ImageView(image);
            menuReporte.setGraphic(visor);
        } catch (FileNotFoundException ex) {

        }

        menuGuardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    if (txtNombre.getText().isEmpty()) {
                        mostrarAlerta("Error", "El campo nombre es necesario llenarlo");
                    }
                    if (txtApellido.getText().isEmpty()) {
                        mostrarAlerta("Error", "El campo nombre es necesario llenarlo");
                    }
                    if (txtCelular.getText().isEmpty()) {
                        mostrarAlerta("Error", "El campo nombre es necesario llenarlo");
                    }
                    if (txtCorreo.getText().isEmpty()) {
                        mostrarAlerta("Error", "El campo nombre es necesario llenarlo");
                    } else {
                        fichero = new FileWriter(file, true);
                        PrintWriter pw = new PrintWriter(fichero);
                        pw.print(txtNombre.getText());
                        pw.print(";");
                        pw.print(txtApellido.getText());
                        pw.print(";");
                        pw.print(txtCelular.getText());
                        pw.print(";");
                        pw.print(txtCorreo.getText());
                        pw.println();
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtCelular.setText("");
                        txtTelefonoFijo.setText("");
                        txtCorreo.setText("");
                    }
                } catch (Exception e) {
                    System.out.println("Error en el primer catch de guardar archivo: " + e);
                } finally {
                    try {
                        if (null != fichero) {
                            fichero.close();
                        }
                    } catch (Exception e2) {
                        System.out.println("Error en el segundo catch de guardar archivo: " + e2);
                    }
                }
            }
        });

        menuArchivo.getItems().add(menuGuardar);
        menuArchivo.getItems().add(menuReporte);
        mbGuardar.getMenus().add(menuArchivo);
         */
    }

    @FXML
    public void validarExistenciaArchivo() {
        if (file.exists() == false) {
            try {
                fichero = new FileWriter(file, true);
            } catch (IOException ex) {

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mbGuardar.getMenus().clear();
        BotonGrupo();
        llenarComboBox();

    }
}
