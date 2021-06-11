package sample.AddQuestion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Config.Config;
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

    String[][] idioms = new String[4][11];

    @FXML void initialize(){

        addIdioms();
        changeIdiom(Config.idiom);

        comboBoxHelp.getItems().addAll("¿","?","ñ");
        textFieldNumQuestion.setText((Principal.questions.size()+1) + "");
        comboBoxHelp.setOnAction(event -> {

            textFieldQuestion.setText(textFieldQuestion.getText() + comboBoxHelp.getSelectionModel().getSelectedItem());

        });

    }

    public void addIdioms(){

        idioms[0][0] = "#Question";
        idioms[0][1] = "Help question";
        idioms[0][2] = "Question";
        idioms[0][3] = "Correct Answer";
        idioms[0][4] = "Answer 2";
        idioms[0][5] = "Answer 3";
        idioms[0][6] = "Answer 4";
        idioms[0][7] = "Add";
        idioms[0][8] = "Search";
        idioms[0][9] = "Modify";
        idioms[0][10] = "Delete";

        idioms[1][0] = "#Pregunta";
        idioms[1][1] = "Ayuda de pregunta";
        idioms[1][2] = "Pregunta";
        idioms[1][3] = "Pregunta correcta";
        idioms[1][4] = "Respuesta 2";
        idioms[1][5] = "Respuesta 3";
        idioms[1][6] = "Respuesta 4";
        idioms[1][7] = "Agregar";
        idioms[1][8] = "Buscar";
        idioms[1][9] = "Modificar";
        idioms[1][10] = "Borrar";

        idioms[2][0] = "#問題";
        idioms[2][1] = "問題幫助";
        idioms[2][2] = "問題";
        idioms[2][3] = "正確的問題";
        idioms[2][4] = "回答2";
        idioms[2][5] = "回答3";
        idioms[2][6] = "回答4";
        idioms[2][7] = "添加";
        idioms[2][8] = "搜索";
        idioms[2][9] = "調整";
        idioms[2][10] = "擦除";

        idioms[3][0] = "سؤال#";
        idioms[3][1] = "سؤال المساعدة";
        idioms[3][2] = "سؤال";
        idioms[3][3] = "اجابة صحيحة";
        idioms[3][4] = "يسأل2";
        idioms[3][5] = "يسأل3";
        idioms[3][6] = "يسأل4";
        idioms[3][7] = "يضيف";
        idioms[3][8] = "بحث";
        idioms[3][9] = "تعديل";
        idioms[3][10] = "حذف";

    }
    public void changeIdiom(int num){

        textFieldNumQuestion.setPromptText(idioms[num][0]);
        comboBoxHelp.setPromptText(idioms[num][1]);
        textFieldQuestion.setPromptText(idioms[num][2]);
        textFieldCorrectAnswer.setPromptText(idioms[num][3]);
        textFieldA2.setPromptText(idioms[num][4]);
        textFieldA3.setPromptText(idioms[num][5]);
        textFieldA4.setPromptText(idioms[num][6]);
        buttonAdd.setText(idioms[num][7]);
        butonSearch.setText(idioms[num][8]);
        buttonModify.setText(idioms[num][9]);
        buttonDelete.setText(idioms[num][10]);

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

        Principal.questions.remove(Integer.parseInt(textFieldNumQuestion.getText())-1);

    }

    @FXML
    void modifyAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modify");
        alert.setContentText("Well");
        alert.setHeaderText("Modified");
        alert.show();

        Principal.questions.remove(Integer.parseInt(textFieldNumQuestion.getText())-1);
        Principal.questions.add(Integer.parseInt(textFieldNumQuestion.getText())-1,new Questions(textFieldQuestion.getText(),textFieldCorrectAnswer.getText(),textFieldA2.getText(),textFieldA3.getText(),textFieldA4.getText()));

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

        if(Integer.parseInt(textFieldNumQuestion.getText()) <= Principal.questions.size()){

            textFieldQuestion.setText(Principal.questions.get(Integer.parseInt(textFieldNumQuestion.getText())-1).getQuestion());
            textFieldCorrectAnswer.setText(Principal.questions.get(Integer.parseInt(textFieldNumQuestion.getText())-1).getCorrectAnswer());
            textFieldA2.setText(Principal.questions.get(Integer.parseInt(textFieldNumQuestion.getText())-1).getAnswer2());
            textFieldA3.setText(Principal.questions.get(Integer.parseInt(textFieldNumQuestion.getText())-1).getAnswer3());
            textFieldA4.setText(Principal.questions.get(Integer.parseInt(textFieldNumQuestion.getText())-1).getAnswer4());

        }

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
