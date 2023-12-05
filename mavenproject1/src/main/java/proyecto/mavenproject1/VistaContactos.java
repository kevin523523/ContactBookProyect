/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.mavenproject1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.ListIterator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author LuisA
 */
public class VistaContactos {

    Stage primaryStage;
    Scene escena;
    linkedList<Perfil> listaContactos;
    linkedList<String> fotos;
    Label titulo;
    private String archivo;
    VBox root = new VBox();
    HBox atributos = new HBox();
    HBox lineaBotones = new HBox();
    ListIterator it;
    TextField txtnombre = new TextField();
    TextField txtApellido = new TextField();
    TextField txtdir = new TextField();
    TextField txtmail = new TextField();
    TextField txtnum = new TextField();
    Label lblname;
    Label lblApellido;
    Label lbldir;
    Label lblmail;
    Label lblnum;
    GridPane datos = new GridPane();
    Image image;
    ImageView view;

    public VistaContactos(String archivo) {
        this.archivo = archivo;
    }

    public void start(Stage primaryStage) throws Exception {
        titulo = new Label("Contactos");
        Font fuente = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        titulo.setFont(fuente);
        //-----------------------XXXXXXX----------------------------//
        Label nombreP = new Label("Nombre");
        Label direccionP = new Label("Dirección");
        Label emailP = new Label("Correos");
        Label numeroP = new Label("Numero telefónico");
        TextField criterio = new TextField();

        atributos.getChildren().addAll(nombreP, direccionP, emailP, numeroP);
        VBox.setMargin(atributos, new Insets(10));

        //-----------------------Perfiles---------------------------//
        OperacionesArchivo operaciones = new OperacionesArchivo();
        listaContactos = operaciones.leerArchivoContactos(archivo);

        if (listaContactos != null) {
            it = listaContactos.listIterator();
        }

        //-----------------------Botones---------------------------//
        Button btnadd = new Button("Agregar");
        Button btnref = new Button("Refresh");
        Button btnnxt = new Button(">");
        Button btnprv = new Button("<");
        Button btnord = new Button("Ordenar");
        lineaBotones.getChildren().addAll(btnadd, btnref, btnprv, btnnxt, btnord, criterio);
        VBox.setMargin(lineaBotones, new Insets(10));
        lineaBotones.setAlignment(Pos.BASELINE_RIGHT);
        btnadd.setOnMouseClicked(e -> crearContacto());
        // Manejador de eventos para el botón de ordenar
        btnord.setOnMouseClicked(e -> {
            String criterioOrden = criterio.getText().trim();
            if (!criterioOrden.isEmpty()) {
                ordenar(criterioOrden);
            } else {
                System.out.println("Ingrese un criterio de ordenación antes de hacer clic en Ordenar.");
                System.out.println(listaContactos);
            }
        });

        if (listaContactos != null) {
            btnref.setOnMouseClicked(e -> refresh(it, listaContactos));
            btnnxt.setOnMouseClicked(e -> next(it));
            btnprv.setOnMouseClicked(e -> previous(it));
        }

        //------------------------Scene----------------------------//
        root.getChildren().addAll(titulo, lineaBotones, atributos);
        if (listaContactos != null) {
            cargarcontactos(it, listaContactos);
        }
        escena = new Scene(root, 500, 500);
        primaryStage.setScene(escena);
        primaryStage.show();
    }

    public void cargarcontactos(ListIterator it, linkedList link) {
        int num = 0;
        while (num < 5) {
            if (link.size() <= num) {
                return;
            }

            HBox caja = new HBox();
            VBox.setMargin(caja, new Insets(10));
            Button btnbor = new Button("X");
            CheckBox check = new CheckBox();
            Perfil perfil = (Perfil) it.next();
            Label nombre = new Label(perfil.getNombre());
            Label apellido = new Label(perfil.getApellido());
            Label direccion = new Label(perfil.getDirección());
            Label email = new Label(perfil.getEmails());
            Label numero = new Label(perfil.getNumero());
            nombre.setOnMouseClicked(e -> mostrarPerfil(perfil));
            btnbor.setOnMouseClicked(e -> borrar(perfil));
            caja.getChildren().addAll(check, nombre, apellido, direccion, email, numero, btnbor);
            root.getChildren().add(caja);
            num++;
        }
    }

    public void refresh(ListIterator it, linkedList link) {

        root.getChildren().clear();

        int num2 = 0;
        while (num2 < 5) {
            it.previous();
            num2++;
        }

        root.getChildren().addAll(titulo, lineaBotones, atributos);
        cargarcontactos(it, listaContactos);
    }

    public void next(ListIterator it) {
        int revers = 0;
        while (revers < 4) {
            it.previous();
            revers++;
        }

        root.getChildren().clear();

        root.getChildren().addAll(titulo, lineaBotones, atributos);
        cargarcontactos(it, listaContactos);
    }

    public void previous(ListIterator it) {
        int revers = 0;
        while (revers < 6) {
            it.previous();
            revers++;
        }

        root.getChildren().clear();

        root.getChildren().addAll(titulo, lineaBotones, atributos);
        cargarcontactos(it, listaContactos);
    }

    public void crearContacto() {
        root.getChildren().clear();

        titulo.setText("Nuevo contacto");
        Font fuente = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        titulo.setFont(fuente);

        lblname = new Label("Nombre ");
        datos.add(lblname, 0, 0);
        datos.add(txtnombre, 1, 0);

        lblApellido = new Label("Apellido ");
        datos.add(lblApellido, 0, 1);
        datos.add(txtApellido, 1, 1);

        lbldir = new Label("Dirección  ");
        datos.add(lbldir, 0, 2);
        datos.add(txtdir, 1, 2);

        lblmail = new Label("email  ");
        datos.add(lblmail, 0, 3);
        datos.add(txtmail, 1, 3);

        lblnum = new Label("numero  ");
        datos.add(lblnum, 0, 4);
        datos.add(txtnum, 1, 4);

        datos.setAlignment(Pos.CENTER);

        Button btnfoto = new Button("Agregar Foto");
        fotos = new linkedList<>();
        btnfoto.setOnMouseClicked(e -> agregarFoto());

        Button btnagregar = new Button("Agregar");
        btnagregar.setOnMouseClicked(e -> agregarPerfil());

        root.getChildren().addAll(titulo, datos, btnagregar, btnfoto);
//        root.setAlignment(Pos.CENTER);
    }

    public void agregarPerfil() {
        if (listaContactos == null) {
            listaContactos = new linkedList();
        }

        String nombre = txtnombre.getText();
        String apellido = txtApellido.getText();
        String direccion = txtdir.getText();
        String email = txtmail.getText();
        String numero = txtnum.getText();

        try {
            OutputStream fos = new FileOutputStream("c" + archivo + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Perfil contacto = new Perfil(nombre, apellido, direccion, email, numero, fotos);
            listaContactos.add(contacto);
            oos.writeObject(listaContactos);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (listaContactos != null) {
            it = listaContactos.listIterator();
        }

        txtnombre.clear();
        txtApellido.clear();
        txtdir.clear();
        txtmail.clear();
        txtnum.clear();

        datos.getChildren().clear();

        refresh(it, listaContactos);
    }

    public void mostrarPerfil(Perfil perfil) {
        root.getChildren().clear();

        titulo = new Label("Perfil");
        Font fuente = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        titulo.setFont(fuente);

        lblname = new Label(perfil.getNombre());
        datos.add(lblname, 0, 0);

        lblApellido = new Label(perfil.getApellido());
        datos.add(lblApellido, 0, 1);

        lbldir = new Label(perfil.getDirección());
        datos.add(lbldir, 0, 2);

        lblmail = new Label(perfil.getEmail());
        datos.add(lblmail, 0, 3);

        lblnum = new Label(perfil.getNumero());
        datos.add(lblnum, 0, 4);

        HBox imagenes = new HBox();

        fotos = perfil.getFotos();
        System.out.println(fotos.toString());
        ListIterator fotoit = fotos.listIterator();
        image = new Image("file:C:\\Users\\LuisA\\OneDrive\\Escritorio\\images\\" + fotoit.next());
        view = new ImageView(image);

        view.setFitHeight(100);
        view.setFitWidth(100);

        Button btnnxtft = new Button(">");
        btnnxtft.setOnMouseClicked(e -> nextFoto(fotoit));
        Button btnprvft = new Button("<");
        btnprvft.setOnMouseClicked(e -> previousFoto(fotoit));

        imagenes.getChildren().addAll(btnprvft, view, btnnxtft);

        Button btnedit = new Button("Editar");
        btnedit.setOnMouseClicked(e -> editar(perfil));

        datos.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titulo, imagenes, datos, btnedit);
    }

    private void editar(Perfil perfil) {
        root.getChildren().clear();
        datos.getChildren().clear();

        txtnombre = new TextField();
        lblname.setText("Nombre ");
        datos.add(lblname, 0, 0);
        datos.add(txtnombre, 1, 0);

        lblApellido.setText("Apellido ");
        datos.add(lblname, 0, 1);
        datos.add(txtnombre, 1, 1);

        txtdir = new TextField();
        lbldir.setText("Dirección  ");
        datos.add(lbldir, 0, 2);
        datos.add(txtdir, 1, 2);

        txtmail = new TextField();
        lblmail.setText("email  ");
        datos.add(lblmail, 0, 3);
        datos.add(txtmail, 1, 3);

        txtnum = new TextField();
        lblnum.setText("numero  ");
        datos.add(lblnum, 0, 4);
        datos.add(txtnum, 1, 4);

        Button btnsave = new Button("Guardar");
        btnsave.setOnMouseClicked(e -> guardar(perfil));

        root.getChildren().addAll(titulo, datos, btnsave);
    }

    private void guardar(Perfil perfil) {
        listaContactos.remove(perfil);

        String nombre = txtnombre.getText();
        String apellido = txtApellido.getText();
        String direccion = txtdir.getText();
        String email = txtmail.getText();
        String numero = txtnum.getText();
        linkedList fotos = perfil.getFotos();

        try {
            OutputStream fos = new FileOutputStream("c" + archivo + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Perfil contacto = new Perfil(nombre, apellido, direccion, email, numero, fotos);
            listaContactos.add(contacto);
            oos.writeObject(listaContactos);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (listaContactos != null) {
            it = listaContactos.listIterator();
        }

        txtnombre.clear();
        txtdir.clear();
        txtmail.clear();
        txtnum.clear();

        datos.getChildren().clear();

        refresh(it, listaContactos);
    }

    private void borrar(Perfil perfil) {

        try {
            OutputStream fos = new FileOutputStream("c" + archivo + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            listaContactos.remove(perfil);
            oos.writeObject(listaContactos);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        refresh(it, listaContactos);
    }

    private void agregarFoto() {
        FileChooser f = new FileChooser();
        f.setInitialDirectory(new File("C:\\Users\\LuisA\\OneDrive\\Escritorio\\images"));
        File selectedFile = f.showOpenDialog(primaryStage);
//        System.out.println(selectedFile.getName());

//        Path from = Paths.get(selectedFile.toURI());
//        Path to = Paths.get("C:\\Users\\LuisA\\OneDrive\\Escritorio\\images");
        String path = "C:\\Users\\LuisA\\OneDrive\\Escritorio\\images";
        selectedFile.renameTo(new File(path));

        fotos.add(selectedFile.getName());
        System.out.println(fotos.toString());
    }

    private void nextFoto(ListIterator it) {
        image = new Image("file:C:\\Users\\LuisA\\OneDrive\\Escritorio\\images\\" + it.next());
        view.setImage(image);
    }

    private void previousFoto(ListIterator it) {
        image = new Image("file:C:\\Users\\LuisA\\OneDrive\\Escritorio\\images\\" + it.previous());
        view.setImage(image);
    }

    public void ordenar(String criterio) {
        System.out.println(listaContactos);
        if ("nombre".equalsIgnoreCase(criterio)) {
            Collections.sort(listaContactos, Perfil.compareByApellidoYNombre());
        } else if ("fotos".equalsIgnoreCase(criterio)) {
            Collections.sort(listaContactos, Perfil.compareByCantidadFotos());
        } else if ("pais".equalsIgnoreCase(criterio)) {
            Collections.sort(listaContactos, Perfil.compareByCountry());
        }
        cargarcontactos(it, listaContactos);
        //refresh(it, listaContactos);
    }

}
