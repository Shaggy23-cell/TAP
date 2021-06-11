package sample.Login;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Fila;
import sample.Main;
import sample.models.Conexion;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Login {
    Parent parent;
    Alert alert;
    @FXML TextField txtUser;
    @FXML PasswordField txtPassword;
    @FXML Label lblEliminar;
    @FXML private AnchorPane anchorPane;
    private Conexion conexion;


    @FXML void initialize(){

        conexion = new Conexion();

    }
    public  void login() throws IOException, SQLException {
        //Parent root = FXMLLoader.load(getClass().getResource("../Alumnos/Alumnos.fxml"));
      //  Main.stage.setScene(new Scene(root));
       /* changeScreen("../Alumnos/Alumnos.fxml");
        Main.stage.setHeight(400);
        Main.stage.setWidth(600);*/

        ResultSet resultSet = conexion.consultar("SELECT * FROM users WHERE name = '"+ txtUser.getText() +"' AND password = '" + txtPassword.getText() + "'");

        if (resultSet != null) {
            int cont = 0;

            while (resultSet.next()){
                cont++;
                Parent root = FXMLLoader.load(getClass().getResource("../Alumnos/Alumnos.fxml"));
                Main.stage.setScene(new Scene(root));


                break;
            }

            if(cont == 0){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Welcome " + txtUser.getText());
                alert.setTitle("Welcome");
                alert.show();
            }
            else{
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Welcome " + txtUser.getText());
                alert.setTitle("Welcome");
                alert.show();
            }

        }
    }

    public void crear() throws SQLException {
        if(!txtUser.getText().trim().equals("")&&!txtPassword.getText().trim().equals("")){


            String user= txtUser.getText();
            String password=txtPassword.getText();

            conexion.insmodel("INSERT INTO `users`(`name`, `password`) VALUES ('"+user+"','"+password+"')");

            txtUser.setText(""); txtPassword.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setContentText("Nuevo usuario registrado");
            alert.show();


        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerta");
            alert.setContentText("Favor de llenar todos los campos para registrarte");
            alert.show();
        }
    }

 public void Eliminar(){
        if(!txtUser.getText().trim().equals("")&&!txtPassword.getText().trim().equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar usuario");
            alert.setContentText("¿Desea eliminar el usuario?");
            Optional<ButtonType> resultado=alert.showAndWait();
            if(resultado.get()== ButtonType.OK){
                String u=txtUser.getText();
                conexion.insmodel("DELETE FROM `users` WHERE name='"+u+"'");
                txtUser.setText(""); txtPassword.setText("");
        }
     }else{
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alerta");
            al.setContentText("Favor de llenar todos los campos para registrarte");
            al.show();
        }
 }

    public void changeScreen(String url) throws IOException {

        parent = FXMLLoader.load(getClass().getResource(url));
        parent.translateXProperty().set(anchorPane.getWidth());
        anchorPane.getChildren().add(parent);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), new KeyValue(parent.translateXProperty(),0, Interpolator.EASE_IN)));
        timeline.setOnFinished(event -> {if(anchorPane.getChildren().size() == 2) anchorPane.getChildren().remove(0);});
        timeline.play();

    }

}
