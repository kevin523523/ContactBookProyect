/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.mavenproject1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author LuisA
 */
public class crearContacto {

    private String archivo;
    TextField txtnombre;
    TextField txtdir;
    TextField txtmail;
    TextField txtnum;
    Button btnagregar = new Button("Agregar");
    VistaContactos VentanaContactos;
    ListIterator it;

    public crearContacto(String archivo, ListIterator it) {
        this.archivo = archivo;
        this.it = it;
    }

    public void start(Stage stage) {
        VBox root = new VBox();
        Label lblTitulo = new Label("Nuevo contacto");
        lblTitulo.setAlignment(Pos.CENTER_RIGHT);
        Font fuente = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        lblTitulo.setFont(fuente);
        
        root.setAlignment(Pos.CENTER);
        
        GridPane datos = new GridPane();
        
        Label lblname = new Label("Nombre: ");
        txtnombre = new TextField();
        datos.add(lblname,0,0);
        datos.add(txtnombre,1,0);
        
        Label lblsurname = new Label("Apellido: ");
        txtnombre = new TextField();
        datos.add(lblsurname,0,1);
        datos.add(txtnombre,1,1);
        
        Label lbldir = new Label("Dirección:  ");
        txtdir = new TextField();
        datos.add(lbldir,0,2);
        datos.add(txtdir,1,2);
        
        Label lblmail = new Label("Email:  ");
        txtmail = new TextField();
        datos.add(lblmail,0,3);
        datos.add(txtmail,1,3);
        
        
        Label lblnum = new Label("Número:  ");
        txtnum = new TextField();
        datos.add(lblnum,0,4);
        datos.add(txtnum,1,4);
        
        datos.setAlignment(Pos.CENTER);

        GridPane.setHalignment(lblname, HPos.CENTER);
        GridPane.setHalignment(lbldir, HPos.CENTER);
        GridPane.setHalignment(lblmail, HPos.CENTER);
        GridPane.setHalignment(lblnum, HPos.CENTER);

        GridPane.setValignment(lblname, VPos.CENTER);
        GridPane.setValignment(lbldir, VPos.CENTER);
        GridPane.setValignment(lblmail, VPos.CENTER);
        GridPane.setValignment(lblnum, VPos.CENTER);

        HBox botones = new HBox();

        btnagregar.setOnMouseClicked(e -> agregarPerfil());
        botones.getChildren().add(btnagregar);

        botones.setAlignment(Pos.CENTER);

        root.getChildren().addAll(lblTitulo, datos, botones);
        
        
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public void agregarPerfil() {
        linkedList<Perfil> contactos = new linkedList<>();

        try {
            InputStream fis = new FileInputStream("c" + archivo + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            contactos = (linkedList<Perfil>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        /*String nombre = txtnombre.getText();
>>>>>>> Stashed changes
        String direccion = txtdir.getText();
        String email = txtmail.getText();
        String numero = txtnum.getText();*/

//        try {
//            OutputStream fos = new FileOutputStream("c" + archivo + ".ser");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            Perfil contacto = new Perfil(nombre, direccion, email, numero);
//            contactos.add(contacto);
//            oos.writeObject(contactos);
//            oos.flush();
//            oos.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        Stage stage = (Stage) btnagregar.getScene().getWindow();
        stage.close();
    }
}
