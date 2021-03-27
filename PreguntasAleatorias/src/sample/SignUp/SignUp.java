package sample.SignUp;

import com.jfoenix.controls.JFXButton;
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
import sample.Login.Login;
import sample.Main;

import java.io.IOException;

public class SignUp {

    @FXML
    private JFXTextField textFieldUser;

    @FXML
    private JFXTextField textFieldPassword;

    @FXML
    private JFXTextField textFieldRPassword;

    @FXML private JFXButton buttonSignUp;

    boolean found = false;
    Alert alert;

    @FXML void passwordAction(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER &&  !(textFieldPassword.getText().equals(""))) textFieldRPassword.requestFocus();
    }

    @FXML void rPasswordAction(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER && !(textFieldRPassword.getText().equals(""))) signUp();
    }

    @FXML void signUpAction(ActionEvent event) throws IOException {
        signUp();
    }

    @FXML void userAction(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER &&  !(textFieldUser.getText().equals(""))) textFieldPassword.requestFocus();
    }

    public void signUp() throws IOException {

        if(textFieldPassword.getText().equals(textFieldRPassword.getText())){

            for(int x = 0; x < Login.users.size(); x++){

                if(textFieldUser.getText().equals(Login.users.get(x).getUser())){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("User used");
                    alert.setTitle("Error");
                    alert.show();
                    found = true;
                    break;
                }

            }

            if(!found){

                Login.users.add(new Users(textFieldUser.getText(),textFieldPassword.getText()));

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Well");
                alert.setTitle("Correct");
                alert.show();

                Parent root = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
                Main.stage.setScene(new Scene(root));

            }

        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The passwords are not same");
            alert.setTitle("Wrong");
            alert.show();
        }

    }

}
