package sample.Question;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.Config.Config;
import sample.Estructuras.QuestionAnswered;
import sample.Login.Login;
import sample.Principal.Principal;

import java.util.Random;

public class Question {

    @FXML
    private Text txtPregunta;

    @FXML
    private JFXButton btnR1;

    @FXML
    private JFXButton btnR2;

    @FXML
    private JFXButton btnR3;

    @FXML
    private JFXButton btnR4;

    @FXML
    private JFXButton btnAnterior;

    @FXML
    private JFXButton btnAleatiora;

    @FXML
    private JFXButton btnSiguiente;

    Random random = new Random();
    int numQuestion = 1,numButton;
    int[] answer = new int[4];
    String[][] idioms = new String[4][8];
    Alert alert;

    @FXML void initialize(){

        addIdioms();
        changeIdiom(Config.idiom);



    }

    public void addIdioms(){

        idioms[0][0] = "Here the question will appear";
        idioms[0][1] = "Answer 1";
        idioms[0][2] = "Answer 2";
        idioms[0][3] = "Answer 3";
        idioms[0][4] = "Answer 4";
        idioms[0][5] = "Previous";
        idioms[0][6] = "Random";
        idioms[0][7] = "Following";

        idioms[1][0] = "Aquí aparecerá la pregunta";
        idioms[1][1] = "Respuesta 1";
        idioms[1][2] = "Respuesta 2";
        idioms[1][3] = "Respuesta 3";
        idioms[1][4] = "Respuesta 4";
        idioms[1][5] = "Anterior";
        idioms[1][6] = "Aleatorio";
        idioms[1][7] = "Siguiente";

        idioms[2][0] = "這個問題會出現在這裡";
        idioms[2][1] = "答案1";
        idioms[2][2] = "答案2";
        idioms[2][3] = "答案3";
        idioms[2][4] = "答案4";
        idioms[2][5] = "以前的";
        idioms[2][6] = "隨機的";
        idioms[2][7] = "下一個";

        idioms[3][0] = "هنا سيظهر السؤال";
        idioms[3][1] = "الجواب 1";
        idioms[3][2] = "الجواب 2";
        idioms[3][3] = "الجواب 3";
        idioms[3][4] = "الجواب 4";
        idioms[3][5] = "سابق";
        idioms[3][6] = "عشوائي";
        idioms[3][7] = "سابق";

    }
    public void changeIdiom(int num){

        txtPregunta.setText(idioms[num][0]);
        btnR1.setText(idioms[num][1]);
        btnR2.setText(idioms[num][2]);
        btnR3.setText(idioms[num][3]);
        btnR4.setText(idioms[num][4]);
        btnAnterior.setText(idioms[num][5]);
        btnAleatiora.setText(idioms[num][6]);
        btnSiguiente.setText(idioms[num][7]);

    }

    @FXML
    void aleatorioAction(ActionEvent event) {

        if(Principal.questions.size() > 0) {
            numQuestion = random.nextInt(Principal.questions.size() - 1);
            askQuestion(numQuestion);
        }

    }

    @FXML
    void anteriorAction(ActionEvent event) {

        if(Principal.questions.size() > 0 && numQuestion > 0) {
            numQuestion--;
            askQuestion(numQuestion);
        }
        else{
            sendMessage("Limit out");
        }

    }

    @FXML
    void siguienteAction(ActionEvent event) {

        if(Principal.questions.size() > 0 && Principal.questions.size()-1 > numQuestion) {
            numQuestion++;
            askQuestion(numQuestion);
        }
        else{
            sendMessage("Limit out");
        }

    }

    public void askQuestion(int numQuestion){

        numButton = random.nextInt(3);

        System.out.println(numQuestion + "  " + numButton);

        txtPregunta.setText(Principal.questions.get(numQuestion).getQuestion());

        allButton0();
        restart();

        answer[numButton] = 1;

        switch (numButton){

            case 0:
                btnR1.setText(Principal.questions.get(numQuestion).getCorrectAnswer());
                btnR2.setText(Principal.questions.get(numQuestion).getAnswer2());
                btnR3.setText(Principal.questions.get(numQuestion).getAnswer3());
                btnR4.setText(Principal.questions.get(numQuestion).getAnswer4());
                break;
            case 1:
                btnR2.setText(Principal.questions.get(numQuestion).getCorrectAnswer());
                btnR3.setText(Principal.questions.get(numQuestion).getAnswer2());
                btnR4.setText(Principal.questions.get(numQuestion).getAnswer3());
                btnR1.setText(Principal.questions.get(numQuestion).getAnswer4());
                break;
            case 2:
                btnR3.setText(Principal.questions.get(numQuestion).getCorrectAnswer());
                btnR4.setText(Principal.questions.get(numQuestion).getAnswer2());
                btnR1.setText(Principal.questions.get(numQuestion).getAnswer3());
                btnR2.setText(Principal.questions.get(numQuestion).getAnswer4());
                break;
            case 3:
                btnR4.setText(Principal.questions.get(numQuestion).getCorrectAnswer());
                btnR1.setText(Principal.questions.get(numQuestion).getAnswer2());
                btnR2.setText(Principal.questions.get(numQuestion).getAnswer3());
                btnR3.setText(Principal.questions.get(numQuestion).getAnswer4());
                break;

        }

    }
    public void sendMessage(String message){

        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();

    }
    public void allButton0(){
        for(int x=0 ; x<4 ; x++) answer[x] = 0;
    }

    @FXML void r1Action(ActionEvent event) {
            if(answer[0] == 1){
                btnR1.setTextFill(Color.GREEN);
                addQuestionPlayer(btnR1.getText());
                btnR1.setText(btnR1.getText() + " ✓");
            }
            else{
                btnR1.setTextFill(Color.RED);
                btnR1.setText(btnR1.getText() + " ✗");
            }
    }

    @FXML void r2Action(ActionEvent event) {
        if(answer[1] == 1){
            btnR2.setTextFill(Color.GREEN);
            addQuestionPlayer(btnR2.getText());
            btnR2.setText(btnR2.getText() + " ✓");
        }
        else{
            btnR2.setTextFill(Color.RED);
            btnR2.setText(btnR2.getText() + " ✗");
        }
    }

    @FXML void r3Action(ActionEvent event) {
        if(answer[2] == 1){
            btnR3.setTextFill(Color.GREEN);
            addQuestionPlayer(btnR3.getText());
            btnR3.setText(btnR3.getText() + " ✓");
        }
        else{
            btnR3.setTextFill(Color.RED);
            btnR3.setText(btnR3.getText() + " ✗");
        }
    }

    @FXML void r4Action(ActionEvent event) {
        if(answer[3] == 1){
            btnR4.setTextFill(Color.GREEN);
            addQuestionPlayer(btnR4.getText());
            btnR4.setText(btnR4.getText() + " ✓");
        }
        else {
            btnR4.setTextFill(Color.RED);
            btnR4.setText(btnR4.getText() + " ✗");
        }
    }

    public void addQuestionPlayer(String answer){

        for(int x=0; x< Login.users.size(); x++){

            if(Login.users.get(x).getUser().equals(Principal.player)){

                Login.users.get(x).getQuestionAnswered().add(new QuestionAnswered(Principal.questions.get(numQuestion).getQuestion(),Principal.questions.get(numQuestion).getCorrectAnswer()));

            }

        }

    }

    public void restart(){

        btnR1.setTextFill(Color.web("#85CB33"));
        btnR2.setTextFill(Color.web("#85CB33"));
        btnR3.setTextFill(Color.web("#85CB33"));
        btnR4.setTextFill(Color.web("#85CB33"));

    }

}
