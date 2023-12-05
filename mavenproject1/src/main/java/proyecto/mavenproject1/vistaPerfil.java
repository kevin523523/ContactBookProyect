package proyecto.mavenproject1;


import datos.CONSTANTES;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import proyecto.mavenproject1.Perfil;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LuisA
 */
public class vistaPerfil {
    
    Perfil perfil;
    Label lblTitulo = new Label();
    VBox root = new VBox();
    GridPane datos = new GridPane();
    
    public vistaPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    public void start(Stage stage) {
        lblTitulo = new Label("Perfil");
        Font fuente = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        lblTitulo.setFont(fuente);
        

        Label lblname = new Label(perfil.getNombre());
        datos.add(lblname,0,0);
        
        Label lbldir = new Label(perfil.getDirección());
        datos.add(lbldir,0,1);
        
        Label lblmail = new Label(perfil.getEmail());
        datos.add(lblmail,0,2);
        
        
        Label lblnum = new Label(perfil.getNumero());
        datos.add(lblnum,0,3);

        Button btnedit = new Button("Editar");
        btnedit.setOnMouseClicked(e -> editar());
        
        datos.setAlignment(Pos.CENTER);


        root.getChildren().addAll(lblTitulo, datos);
        
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private void editar() {
        root.getChildren().clear();
        
        Label lblname = new Label("Nombre ");
        TextField txtnombre = new TextField();
        datos.add(lblname,0,0);
        datos.add(txtnombre,1,0);
        
        Label lbldir = new Label("Dirección  ");
        TextField txtdir = new TextField();
        datos.add(lbldir,0,1);
        datos.add(txtdir,1,1);
        
        Label lblmail = new Label("email  ");
        TextField txtmail = new TextField();
        datos.add(lblmail,0,2);
        datos.add(txtmail,1,2);
        
        
        Label lblnum = new Label("numero  ");
        TextField txtnum = new TextField();
        datos.add(lblnum,0,3);
        datos.add(txtnum,1,3);
        
        root.getChildren().addAll(lblTitulo, datos);
    }
}
