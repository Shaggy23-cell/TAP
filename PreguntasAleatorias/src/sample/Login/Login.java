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

import java.io.IOException;
import java.util.LinkedList;

public class Login {

    @FXML private JFXPasswordField textFieldPassword;

    @FXML private JFXTextField textFieldUser;

    @FXML private JFXButton buttonLogin;

    @FXML private JFXButton buttonSignUp;

    public static LinkedList<Users> users = new LinkedList<>();

    boolean found = false;

    @FXML void initialize(){

        users.add(new Users("Chris","123456"));

    }

    @FXML void loginAction(ActionEvent event) throws IOException {
            login();
    }

    @FXML void signupAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../SignUp/SignUp.fxml"));
        Main.stage.setScene(new Scene(root));
    }

    @FXML void passwordAction(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER && !(textFieldPassword.getText().equals(""))) login();
    }

    @FXML void userAction(KeyEvent event) {if(event.getCode() == KeyCode.ENTER &&  !(textFieldUser.getText().equals(""))) textFieldPassword.requestFocus();}

    public void login() throws IOException {

        for(int x = 0; x < users.size(); x++){

            if(textFieldUser.getText().equals(users.get(x).getUser()) && textFieldPassword.getText().equals(users.get(x).getPassword())){
                Parent root = FXMLLoader.load(getClass().getResource("../Principal/Principal.fxml"));
                Main.stage.setScene(new Scene(root));
                found = true;
                break;
            }

        }
        if(!(found)){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect password or user");
            alert.setTitle("Error");
            alert.show();

        }

    }

}
