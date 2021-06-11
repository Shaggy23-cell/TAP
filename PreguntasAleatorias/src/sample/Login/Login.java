package sample.Login;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Estructuras.Users;
import sample.Main;
import sample.Principal.Principal;
import sample.models.Conexion;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Login {

    Alert alert;
    private Conexion conexion;
    @FXML private JFXPasswordField textFieldPassword;

    @FXML private JFXTextField textFieldUser;

    @FXML private JFXButton buttonLogin;

    @FXML private JFXButton buttonSignUp;

    public static LinkedList<Users> users = new LinkedList<>();

    boolean found = false;

    @FXML void initialize(){
        conexion =new Conexion();
        users.add(new Users("Chris","123456"));
        users.add(new Users("Brian","123456"));
        users.add(new Users("Yazmin","123456"));




    }

    @FXML void loginAction(ActionEvent event) throws IOException, SQLException {
            login();
    }

    @FXML void signupAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../SignUp/SignUp.fxml"));
        Main.stage.setScene(new Scene(root));
    }

    @FXML void passwordAction(KeyEvent event) throws IOException, SQLException {
        if(event.getCode() == KeyCode.ENTER && !(textFieldPassword.getText().equals(""))) login();
    }

    @FXML void userAction(KeyEvent event) {if(event.getCode() == KeyCode.ENTER &&  !(textFieldUser.getText().equals(""))) textFieldPassword.requestFocus();}

    public void login() throws IOException, SQLException {

        ResultSet resultSet = conexion.consultar("SELECT * FROM users WHERE name = '"+ textFieldUser.getText() +"' AND password = '" + textFieldPassword.getText() + "'");
        if (resultSet != null) {
            int cont = 0;
            while (resultSet.next()){
                cont++;
                Parent root = FXMLLoader.load(getClass().getResource("../Principal/Principal.fxml"));
                Main.stage.setScene(new Scene(root));
                break;
            }
            if(cont == 0){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Welcome " + textFieldUser.getText());
                alert.setTitle("Welcome");
                alert.show();
            }
            else{
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Welcome " + textFieldUser.getText());
                alert.setTitle("Welcome");
                alert.show();
            }
        }

    }

}
