package sample.AddQuestion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Estructuras.Questions;
import sample.Principal.Principal;

public class AddQuestion {

    @FXML
    private JFXTextField textFieldNumQuestion;

    @FXML
    private JFXComboBox<String> comboBoxHelp;

    @FXML
    private JFXTextField textFieldQuestion;

    @FXML
    private JFXTextField textFieldCorrectAnswer;

    @FXML
    private JFXTextField textFieldA2;

    @FXML
    private JFXTextField textFieldA3;

    @FXML
    private JFXTextField textFieldA4;

    @FXML
    private JFXButton buttonAdd;

    @FXML
    private JFXButton butonSearch;

    @FXML
    private JFXButton buttonModify;

    @FXML
    private JFXButton buttonDelete;

    @FXML void initialize(){

        comboBoxHelp.getItems().addAll("Falta agregar");
        textFieldNumQuestion.setText((Principal.questions.size()+1) + "");

    }

    @FXML
    void a2Action(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER && !(textFieldA2.getText().equals(""))) textFieldA3.requestFocus();
    }

    @FXML
    void a3Action(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER && !(textFieldA3.getText().equals(""))) textFieldA4.requestFocus();
    }

    @FXML
    void a4Action(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER && !(textFieldA4.getText().equals(""))) add();
    }

    @FXML void addAction(ActionEvent event) {
        add();
    }

    @FXML
    void correctAAction(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER && !(textFieldCorrectAnswer.getText().equals(""))) textFieldA2.requestFocus();
    }

    @FXML
    void deleteAction(ActionEvent event) {

    }

    @FXML
    void modifyAction(ActionEvent event) {

    }

    @FXML
    void questionAction(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER && !(textFieldQuestion.getText().equals(""))) textFieldCorrectAnswer.requestFocus();
    }

    @FXML
    void questionNumAction(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER && !(textFieldNumQuestion.getText().equals(""))) textFieldQuestion.requestFocus();
    }

    @FXML
    void searchAction(ActionEvent event) {

    }

    public void add(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Added");
        alert.setContentText("Added");
        alert.show();

        Principal.questions.add(new Questions(textFieldQuestion.getText(),textFieldCorrectAnswer.getText(),textFieldA2.getText(),textFieldA3.getText(),textFieldA4.getText()));

        textFieldNumQuestion.setText((Principal.questions.size()+1) + "");

    }

}
