package proyecto.mavenproject1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ListIterator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public Stage stage;
    TextField txtusuario;
    VBox root = new VBox();
    VBox lineaBotones = new VBox();
    GridPane datos = new GridPane();
    Label label = new Label("Gestión de contactos");

    @Override
    public void start(Stage stage) {
        Font fuente = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        label.setFont(fuente);
        label.setAlignment(Pos.TOP_CENTER);

        //Creacion Botones
        Button btnlog = new Button("Log In");
        Button btnreg = new Button("Register");

        datos.setVgap(10); // Espaciado vertical entre filas
        datos.setHgap(10); // Espaciado horizontal entre columnas
        datos.setAlignment(Pos.CENTER);
        lineaBotones.setAlignment(Pos.CENTER);
        
        // Configurar márgenes para los elementos (opcional)
        GridPane.setMargin(btnlog, new Insets(5));
        GridPane.setMargin(btnreg, new Insets(5));

        Label lblusuario = new Label("Usuario:  ");
        txtusuario = new TextField();
        datos.add(lblusuario, 0, 0);
        datos.add(txtusuario, 1, 0);

        lineaBotones.getChildren().addAll(btnlog, btnreg);

        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label);
        root.getChildren().add(datos);
        root.getChildren().add(lineaBotones);
        btnlog.setOnMouseClicked(e -> mostrarContactos());
        btnreg.setOnMouseClicked(e -> registrarUsuario());

        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("Gestión de Contactos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void nextImage(ListIterator it) {

//        FileChooser f = new FileChooser();
//        f.setInitialDirectory(new File("C:\\Users\\LuisA\\OneDrive\\Escritorio\\images"));
//        File selectedFile = f.showOpenDialog(stage);
//        
//        
//        Path from = Paths.get(selectedFile.toURI());
//        System.out.println(selectedFile.toURI());
//        System.out.println(Paths.get("C:\\Users\\LuisA\\OneDrive\\Escritorio\\images"));
//        Path to = Paths.get("C:\\Users\\LuisA\\OneDrive\\Escritorio\\images");
//        
//        selectedFile.renameTo(new File("C:\\Users\\LuisA\\OneDrive\\Escritorio\\images\\newjpg.jpg"));        
    }

    public void mostrarContactos() {
        try {
            VistaContactos VentanaContactos = new VistaContactos(txtusuario.getText());
            VentanaContactos.start(new Stage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void registrarUsuario() {
        // Obtiene el código ingresado en el campo txtcodigo
        String usuario = txtusuario.getText();

        // Verifica que el código no esté vacío
        if (!usuario.isEmpty()) {
            // Define un nombre de archivo basado en el código
            Usuario user = new Usuario(usuario);
            String archivo = "U" + usuario + ".txt";
            try {
                // Abre un archivo para escribir el objeto
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
                // Escribe el código en el archivo
                oos.writeObject(user);
                oos.flush();
                oos.close();
                // Muestra una alerta de éxito
                mostrarAlerta("Éxito", "El código se ha grabado en el archivo: " + archivo, Alert.AlertType.INFORMATION);
            } catch (IOException ex) {
                // Muestra una alerta en caso de error
                mostrarAlerta("Error", "Hubo un error al grabar el código en el archivo.", Alert.AlertType.ERROR);
            }
        } else {
            // Muestra una alerta si el campo está vacío
            mostrarAlerta("Error", "El campo de código está vacío.", Alert.AlertType.ERROR);
        }
    }

    // Método auxiliar para mostrar una alerta
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
