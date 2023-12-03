/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.contackbo;

import model.Contacto;
import java.io.BufferedReader;
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class InicioController implements Initializable {

    /*Anotacion que etiqueta una clase o miembro como accesible*/
    @FXML
    private TableView jTRegistros;
    @FXML
    private TableColumn<Contacto, String> primeraColumna;
    @FXML
    private TableColumn<Contacto, String> segundaColumna;
    @FXML
    private TableColumn<Contacto, String> terceraColumna;
    @FXML
    private TableColumn<Contacto, String> cuartaColumna;
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

    /*Metodo para cargar Columnas*/
    @FXML
    public void columnas() {
        primeraColumna = new TableColumn<>("Nombre");
        segundaColumna = new TableColumn<>("Apellido");
        terceraColumna = new TableColumn<>("Tel Celular");
        cuartaColumna = new TableColumn<>("Correo");
        jTRegistros.getColumns().addAll(primeraColumna, segundaColumna, terceraColumna, cuartaColumna);
    }

    /*Metodo para cargar filas*/
    @FXML
    public void filas() {
        primeraColumna.setCellValueFactory(new PropertyValueFactory<Contacto, String>("nombre"));
        segundaColumna.setCellValueFactory(new PropertyValueFactory<Contacto, String>("apellido"));
        terceraColumna.setCellValueFactory(new PropertyValueFactory<Contacto, String>("telefonoCelular"));
        cuartaColumna.setCellValueFactory(new PropertyValueFactory<Contacto, String>("correo"));

    }

    /*Metodos para cargar la tabla con los datos que 
     agrego el usuario*/
    @FXML
    public void cargarEnLaTabla(ActionEvent evento) {
/*
        // Validacion si el nombre esta vacio
        if (txtNombre.getText().isEmpty()) {
            mostrarAlerta("Error de Validación", "El nombre no puede estar vacío.");
        }
        // Validacion si el apellido esta vacio
        if (txtApellido.getText().isEmpty()) {
            mostrarAlerta("Error de Validación", "El apellido no puede estar vacío.");
        }
        // Validacion del correo electrónico
        if (txtCorreo.getText().isEmpty() && !esFormatoCorreoValido(txtCorreo.getText())) {
            mostrarAlerta("Error de Validación", "El formato del correo electrónico personal no es válido.");

        }
        // Validacion del teléfono 
        if (txtCelular.getText().isEmpty() && !esNumero(txtCelular.getText())) {
            mostrarAlerta("Error de Validación", "El número de teléfono debe ser numérico.");

        }
        else {
            filas();
            final ObservableList<Contacto> contacto = FXCollections.observableArrayList(
                    new Contacto(txtNombre.getText(), txtApellido.getText(), txtCelular.getText(), txtCorreo.getText()));
            jTRegistros.getItems().addAll(contacto);

        }*/
       
    }

    /*Metodo para cargar la tabla apenas arranque la apliacion*/
    @FXML
    public void cargarRegistrosALaTabla() {
        try {
            lectorArchivo = new FileReader(file);
            almacen = new BufferedReader(lectorArchivo);
            try {
                while ((linea = almacen.readLine()) != null) {
                    if (!linea.isEmpty()) {
                        pos = linea.split(";");
                        almacenFilaUno = pos[0];
                        almacenFilaDos = pos[1];
                        almacenFilaTres = pos[2];
                        almacenFilaCuatro = pos[3];
                        filas();
                        final ObservableList<Contacto> contacto = FXCollections.observableArrayList(
                                new Contacto(almacenFilaUno, almacenFilaDos, almacenFilaTres,
                                almacenFilaCuatro));
                        jTRegistros.getItems().addAll(contacto);
                    }
                }
            } catch (IOException ex) {
                
            }
        } catch (FileNotFoundException ex) {
           
        }
    }

    
    
     @FXML
    public void cargarItemsMenuBar() {
        /*
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
    
        @FXML
    public void agregarContacto() throws IOException {
        App.setRoot("Exposicion");
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mbGuardar.getMenus().clear();
        jTRegistros.getColumns().clear();
        columnas();
        cargarRegistrosALaTabla();

    }
}
