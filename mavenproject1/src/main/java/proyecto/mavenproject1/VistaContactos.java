/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.mavenproject1;



import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ListIterator;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
    linkedList<String> conocidos;
    linkedList<String> identificadores;
    Label titulo;
    private String archivo;
    VBox root = new VBox();
    HBox numbers;
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
    Label lblpais;
    Label lblcntct;
    GridPane datos = new GridPane();
    ListIterator fotoit;
    ListIterator contctit;
    Image image;
    ImageView view;
    Button btnagregar;
    Button btnfoto;
    ComboBox<String> pais;
    ComboBox<String> contactos;


    
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
        atributos.getChildren().addAll(nombreP, direccionP, emailP, numeroP);
        VBox.setMargin(atributos, new Insets(10));
        
        //-----------------------Perfiles---------------------------//
        OperacionesArchivo operaciones = new OperacionesArchivo();
        listaContactos = operaciones.leerArchivoContactos(archivo);
        
        if (listaContactos != null){
            it = listaContactos.listIterator();
        }
        
        //-----------------------Botones---------------------------//
        Button btnadd = new Button("Agregar");
        Button btnref = new Button("Refresh");
        Button btnnxt = new Button(">");
        Button btnprv = new Button("<");

        lineaBotones.getChildren().addAll(btnadd, btnref, btnprv, btnnxt);

        Button btnord = new Button("Ordenar");
        lineaBotones.getChildren().addAll(btnadd, btnprv, btnnxt, btnord, criterio);

        VBox.setMargin(lineaBotones, new Insets(10));
        lineaBotones.setAlignment(Pos.BASELINE_RIGHT);
        btnadd.setOnMouseClicked(e -> crearContacto());
        if (listaContactos != null){
            btnref.setOnMouseClicked(e -> refresh(it, listaContactos));
            btnnxt.setOnMouseClicked(e -> next(it));
            btnprv.setOnMouseClicked(e -> previous(it));
        }
        
        //------------------------Scene----------------------------//
        root.getChildren().addAll(titulo, lineaBotones, atributos);
        if (listaContactos != null)
            cargarcontactos(it, listaContactos);
        escena = new Scene(root,500,500);
        primaryStage.setScene(escena);
        primaryStage.show();
    }

    


    public void cargarcontactos(ListIterator it, linkedList link) { 

        int num = 0;
        while (num < 5){
            if (link.size() <= num)
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
            Label email = new Label(perfil.getEmail());
            Label numero = new Label(perfil.getNumero());
            nombre.setOnMouseClicked(e -> mostrarPerfil(perfil));
            btnbor.setOnMouseClicked(e -> borrar(perfil));
            caja.getChildren().addAll(check, nombre, apellido, direccion, email, numero, btnbor);
            root.getChildren().add(caja);
            num++;
        }
    }
    
    public void refresh(ListIterator it, linkedList link){

        root.getChildren().clear();

        int num2 = 0;
        while (num2 < 5){
            it.previous();
            num2++;
        }
        
        root.getChildren().addAll(titulo, lineaBotones, atributos);
        cargarcontactos(it, listaContactos);
    }
    
    public void next(ListIterator it){
        int revers = 0;
        while (revers < 4){
            it.previous();
            revers++;
        }
        
        
        root.getChildren().clear();
        
        root.getChildren().addAll(titulo, lineaBotones, atributos);
        cargarcontactos(it, listaContactos);
    }
    
    public void previous(ListIterator it){
        int revers = 0;
        while (revers < 6){
            it.previous();
            revers++;
        }
        
        
        root.getChildren().clear();
        
        root.getChildren().addAll(titulo, lineaBotones, atributos);
        cargarcontactos(it, listaContactos);
    }
    
    public void crearContacto(){
        root.getChildren().clear();
        
        titulo.setText("Nuevo contacto");
        Font fuente = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        titulo.setFont(fuente);
        
        
        lblname = new Label("Nombre ");
        datos.add(lblname,0,0);
        datos.add(txtnombre,1,0);
        
        lblApellido = new Label("Apellido ");
        datos.add(lblApellido,0,1);
        datos.add(txtApellido,1,1);
        
        lbldir = new Label("Dirección  ");
        datos.add(lbldir,0,2);
        datos.add(txtdir,1,2);
        
        lblmail = new Label("email  ");
        datos.add(lblmail,0,3);
        datos.add(txtmail,1,3);
        
        
        lblnum = new Label("numero  ");
        datos.add(lblnum,0,4);
        datos.add(txtnum,1,4);
        

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
        root.setAlignment(Pos.CENTER);

        Button btnnxtft = new Button(">");
        btnnxtft.setOnMouseClicked(e -> nextFoto(fotoit));
        Button btnprvft = new Button("<");
        btnprvft.setOnMouseClicked(e -> previousFoto(fotoit));
        
        
        fotos = new linkedList<>();
        identificadores = new linkedList<>();
        
        

        titulo.setText("Nuevo contacto");
        Font fuente = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        titulo.setFont(fuente);
        titulo.setAlignment(Pos.TOP_CENTER);

        lblname = new Label("Nombre: ");
        datos.add(lblname, 0, 0);
        datos.add(txtnombre, 1, 0);

        lblApellido = new Label("Apellido: ");
        datos.add(lblApellido, 0, 1);
        datos.add(txtApellido, 1, 1);

        lbldir = new Label("Dirección:  ");
        datos.add(lbldir, 0, 2);
        datos.add(txtdir, 1, 2);

        lblmail = new Label("Email:  ");
        datos.add(lblmail, 0, 3);
        datos.add(txtmail, 1, 3);
        
        lblnum = new Label("Numero telefónico:  ");
        datos.add(lblnum, 0, 4);
        datos.add(txtnum, 1, 4);


        datos.setAlignment(Pos.CENTER);
        
        Button btnfoto = new Button("Agregar Foto");
        fotos = new linkedList<>();
        btnfoto.setOnMouseClicked(e -> agregarFoto());


        btnfoto = new Button("Agregar Foto");
        btnfoto.setOnMouseClicked(e -> agregarFoto(fotos));
        
        //Lista imagenes
        HBox imagenes = new HBox();
        view = new ImageView();
        view.setFitHeight(100);
        view.setFitWidth(100);
        if (!fotos.isEmpty()){
            fotoit = fotos.listIterator();
            image = new Image("file:C:\\images\\" + fotoit.next());
            view.setImage(image);
        }
        imagenes.getChildren().addAll(btnprvft, view, btnnxtft);
        
        
        pais = new ComboBox<>();
        ObservableList<String> ob = pais.getItems();
        ob.add("Argentina");
        ob.add("Brasil");
        ob.add("Colombia");
        ob.add("Dinamarca");
        ob.add("Ecuador");
        ob.add("Francia");
        ob.add("Guatemala");
        
        contactos = new ComboBox<>();
        ObservableList<String> cb = contactos.getItems();
        if (listaContactos != null){
            for (Perfil contacto : listaContactos){
                cb.add(contacto.getNombre());
            }
        }
        
        
        Button btnborrar = new Button("X");
        btnborrar.setOnMouseClicked(e -> borrarFoto(fotos, fotoit));


        btnagregar = new Button("Agregar");
        btnagregar.setOnMouseClicked(e -> agregarPerfil());


        
        root.getChildren().addAll(titulo, datos, btnagregar, btnfoto);

        root.getChildren().addAll(titulo, datos, btnfoto, imagenes, btnborrar, pais, contactos, btnagregar);

    }
    
    public void agregarPerfil(){
        if (listaContactos == null){
            listaContactos = new linkedList();        
        }
        
        if (identificadores == null) {
            identificadores = new linkedList();
        }
        
        if (conocidos == null) {
            conocidos = new linkedList();
        }
        
        String nombre = txtnombre.getText();
        String apellido = txtApellido.getText();
        String direccion = txtdir.getText();
        String email = txtmail.getText();
        String numero = txtnum.getText();

        

        String nacionalidad = pais.valueProperty().get();
        String amigo = contactos.valueProperty().get();
        if (amigo != null)
            conocidos.add(amigo);


        try {
            OutputStream fos = new FileOutputStream("c" + archivo + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Perfil contacto = new Perfil(nombre, apellido, direccion, email, numero, nacionalidad, conocidos, identificadores, fotos);
            listaContactos.add(contacto);
            oos.writeObject(listaContactos);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if (listaContactos != null){
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
    
    public void mostrarPerfil(Perfil perfil){
        root.getChildren().clear();
        
        titulo = new Label("Perfil");
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
        
        
        conocidos = perfil.getContactos();

        HBox nose = new HBox();
        lblcntct = new Label();
        if (!conocidos.isEmpty()){
            contctit = conocidos.listIterator();
            String a = (String) contctit.next();
            lblcntct.setText(a);
            lblcntct.setOnMouseClicked(e -> abrirContacto(a));
        }
        
        Button btnnxtct = new Button(">");
        btnnxtft.setOnMouseClicked(e -> nextContact(contctit));
        Button btnprvct = new Button("<");
        btnprvft.setOnMouseClicked(e -> previousContact(contctit));

        nose.getChildren().addAll(btnprvct, lblcntct, btnnxtct);

        Button btnedit = new Button("Editar");
        btnedit.setOnMouseClicked(e -> editar(perfil));
        
        datos.setAlignment(Pos.CENTER);
        
    
        root.getChildren().addAll(titulo, imagenes, datos, btnedit);
    }   

        root.getChildren().addAll(titulo, imagenes, nose, datos, btnedit);
    }

    private void editar(Perfil perfil) {
        root.getChildren().clear();
        datos.getChildren().clear();

        
        txtnombre = new TextField();
        lblname.setText("Nombre ");
        datos.add(lblname,0,0);
        datos.add(txtnombre,1,0);
        
        lblApellido.setText("Apellido ");
        datos.add(lblname,0,1);
        datos.add(txtnombre,1,1);
        
        txtdir = new TextField();
        lbldir.setText("Dirección  ");
        datos.add(lbldir,0,2);
        datos.add(txtdir,1,2);
        
        txtmail = new TextField();
        lblmail.setText("email  ");
        datos.add(lblmail,0,3);
        datos.add(txtmail,1,3);
        
        txtnum = new TextField();
        lblnum.setText("numero  ");
        datos.add(lblnum,0,4);
        datos.add(txtnum,1,4);
        
        Button btnsave = new Button("Guardar");
        btnsave.setOnMouseClicked(e -> guardar(perfil));
        
        root.getChildren().addAll(titulo, datos, btnsave);


        txtnombre = new TextField(perfil.getNombre());
        lblname.setText("Nombre: ");
        datos.add(lblname, 0, 0);
        datos.add(txtnombre, 1, 0);

        txtApellido = new TextField(perfil.getApellido());
        lblApellido.setText("Apellido: ");
        datos.add(lblApellido, 0, 1);
        datos.add(txtApellido, 1, 1);

        txtdir = new TextField(perfil.getDirección());
        lbldir.setText("Dirección:  ");
        datos.add(lbldir, 0, 2);
        datos.add(txtdir, 1, 2);

        txtmail = new TextField(perfil.getEmail());
        lblmail.setText("Email:  ");
        datos.add(lblmail, 0, 3);
        datos.add(txtmail, 1, 3);

        txtnum = new TextField(perfil.getNombre());
        lblnum.setText("Número telefonico:  ");
        datos.add(lblnum, 0, 4);
        datos.add(txtnum, 1, 4);
        
        btnfoto = new Button("Agregar Foto");
        btnfoto.setOnMouseClicked(e -> agregarFoto(fotos));
        
        HBox imagenes = new HBox();
        view = new ImageView();
        view.setFitHeight(100);
        view.setFitWidth(100);
        if (!perfil.getFotos().isEmpty()){
            fotoit = perfil.getFotos().listIterator();
            image = new Image("file:C:\\images\\" + fotoit.next());
            view.setImage(image);
        }
        Button btnnxtft = new Button(">");
        btnnxtft.setOnMouseClicked(e -> nextFoto(fotoit));
        Button btnprvft = new Button("<");
        btnprvft.setOnMouseClicked(e -> previousFoto(fotoit));
        imagenes.getChildren().addAll(btnprvft, view, btnnxtft);
        
        Button btnborrar = new Button("X");
        btnborrar.setOnMouseClicked(e -> borrarFoto(fotos, fotoit));
        
        pais = new ComboBox<>();
        ObservableList<String> ob = pais.getItems();
        ob.add("Argentina");
        ob.add("Brasil");
        ob.add("Colombia");
        ob.add("Dinamarca");
        ob.add("Ecuador");
        ob.add("Francia");
        ob.add("Guatemala");
        
        

        Button btnsave = new Button("Guardar");
        btnsave.setOnMouseClicked(e -> guardar(perfil));

        root.getChildren().addAll(titulo, datos, btnfoto,  imagenes, btnborrar, pais, btnsave);

    }

    private void guardar(Perfil perfil) {
        listaContactos.remove(perfil);
        
        String nombre = txtnombre.getText();
        String apellido = txtApellido.getText();
        String direccion = txtdir.getText();
        String email = txtmail.getText();
        String numero = txtnum.getText();

        linkedList fotos = perfil.getFotos();
        
        

        String nacionalidad = pais.valueProperty().get();
        fotos = perfil.getFotos();


        try {
            OutputStream fos = new FileOutputStream("c" + archivo + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Perfil contacto = new Perfil(nombre, apellido, direccion, email, numero, nacionalidad, conocidos, identificadores, fotos);
            listaContactos.add(contacto);
            oos.writeObject(listaContactos);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if (listaContactos != null){
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
        
        Comparator<Perfil> comparadorApellido_Nombre = new Comparator<Perfil>() {
            @Override
            public int compare(Perfil perfil1, Perfil perfil2) {
                // Comparar por apellido y luego por nombre
                int comparacionApellido = perfil1.getApellido().compareTo(perfil2.getApellido());
                if (comparacionApellido != 0) {
                    return comparacionApellido;
                }
                return perfil1.getNombre().compareTo(perfil2.getNombre());
            }
        };
        
        Comparator<Perfil> comparadorCantidadFotos = new Comparator<Perfil>() {
            @Override
            public int compare(Perfil perfil1, Perfil perfil2) {
                if (perfil1.getFotos().size() == perfil2.getFotos().size()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        
        // Comparador para ordenar por pais de residencia
        Comparator<Perfil> comparadorPais = new Comparator<Perfil>() {
            @Override
            public int compare(Perfil perfil1, Perfil perfil2) {
                if (perfil1.getPais() == perfil2.getPais()) {
                    return 0;
                }
                return -1;
            }
        };
        
        if ("nombre".equalsIgnoreCase(criterio)) {
            Collections.sort(listaContactos, comparadorApellido_Nombre);
        } else if ("fotos".equalsIgnoreCase(criterio)) {
            Collections.sort(listaContactos, comparadorCantidadFotos);
        } else if ("pais".equalsIgnoreCase(criterio)) {
            Collections.sort(listaContactos,comparadorPais);
        }
        
        System.out.println(listaContactos);
        refresh(it, listaContactos);
    }

    private void abrirContacto(String a) {
        for (Perfil b : listaContactos){
            if (a == b.getNombre()){
                datos.getChildren().clear();
                mostrarPerfil(b);
            }
        }
    }

    private void nextContact(ListIterator contctit) {
        if (contctit != null){
            String a = (String) contctit.next();
            lblcntct.setText(a);
            lblcntct.setOnMouseClicked(e -> abrirContacto(a));
        }
    }

    private void previousContact(ListIterator contctit) {
        if (contctit != null){
            String a = (String) contctit.previous();
            lblcntct.setText(a);
            lblcntct.setOnMouseClicked(e -> abrirContacto(a));
        }
    }
}
