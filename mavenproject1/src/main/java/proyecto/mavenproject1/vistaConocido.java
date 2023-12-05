/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.mavenproject1;

import java.util.ListIterator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class vistaConocido {
    
    Stage primaryStage;
    Scene escena;
    Label titulo;
    VBox root = new VBox();
    Perfil perfil;
    Label lblcntct;
    Label lblname;
    Label lblApellido;
    Label lbldir;
    Label lblmail;
    Label lblnum;
    Label lblpais;
    GridPane datos = new GridPane();
    Image image;
    ImageView view;
    ListIterator fotoit;
    ListIterator contctit;
    linkedList<String> fotos;
    linkedList<String> conocidos;
    
    public vistaConocido(Perfil perfil, Label lblcntct) {
        this.perfil = perfil;
        this.lblcntct = lblcntct;
    }
    
    public void start(Stage secondaryStage) throws Exception {
        root.getChildren().clear();
        
        titulo = new Label("Conocido");
        Font fuente = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        titulo.setFont(fuente);
        

        lblname = new Label(perfil.getNombre());
        datos.add(lblname,0,0);
        
        lblApellido = new Label(perfil.getApellido());
        datos.add(lblApellido,0,1);
        
        lbldir = new Label(perfil.getDirección());
        datos.add(lbldir,0,2);
        
        lblmail = new Label(perfil.getEmail());
        datos.add(lblmail,0,3);
        
        lblnum = new Label(perfil.getNumero());
        datos.add(lblnum,0,4);
        
        HBox imagenes = new HBox();
        
        lblpais = new Label(perfil.getPais());
        datos.add(lblpais, 0, 5);
        
        fotos = perfil.getFotos();
        System.out.println(fotos.toString());
        fotoit = fotos.listIterator();
        image = new Image("file:C:\\images\\" + fotoit.next());
        view = new ImageView(image);
        
        view.setFitHeight(100);
        view.setFitWidth(100);
        
        Button btnnxtft = new Button(">");
        btnnxtft.setOnMouseClicked(e -> nextFoto(fotoit));
        Button btnprvft = new Button("<");
        btnprvft.setOnMouseClicked(e -> previousFoto(fotoit));
        
        imagenes.getChildren().addAll(btnprvft, view, btnnxtft);
        
        
        conocidos = perfil.getContactos();

        datos.setAlignment(Pos.CENTER);
        

        root.getChildren().addAll(titulo, imagenes, datos);
        
        var scene = new Scene(root, 640, 480);
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }
    
    private void nextFoto(ListIterator it) {
        image = new Image("file:C:\\" + it.next());
        view.setImage(image);
    }

    private void previousFoto(ListIterator it) {
        image = new Image("file:C:\\" + it.previous());
        view.setImage(image);
    }
}
