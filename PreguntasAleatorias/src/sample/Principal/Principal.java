package sample.Principal;


import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sample.Config.Config;
import sample.Estructuras.Questions;

import java.io.IOException;
import java.util.LinkedList;

public class Principal {

    @FXML private JFXButton buttonPlay;

    @FXML private JFXButton buttonAddQuestion;

    @FXML private JFXButton buttonSetting;

    @FXML private JFXButton buttonExit;

    @FXML private JFXButton buttonRecord;

    @FXML private AnchorPane anchorPane;

    @FXML private VBox vBoxHijo;

    public static LinkedList<Questions> questions = new LinkedList<>();
    public static String player;
    String[][] idiom = new String[4][5];

    Parent parent;

    @FXML void initialize(){

        addIdioms();
        changeIdiom(Config.idiom);

        questions.clear();

        questions.add(new Questions("Cuantos minutos tiene una hora?","60 min","10 min","100 min","30 min"));
        questions.add(new Questions("Cuantas patas tiene un pato?","2","6","Ninguna","8"));
        questions.add(new Questions("4x5?","20","45","120","4"));
        questions.add(new Questions("De que color es el Sol?","Rojo-Naranja","Rosa","Verde","Blanco"));
        questions.add(new Questions("Que es opuesto a la libertad ","Exclavitud","Paz","Guerra","ninguno"));



    }

    public void addIdioms(){

        idiom[0][0] = "Play";
        idiom[0][1] = "AddQuestion";
        idiom[0][2] = "Settings";
        idiom[0][3] = "Record";
        idiom[0][4] = "Exit";

        idiom[1][0] = "Jugar";
        idiom[1][1] = "AgregarPregunta";
        idiom[1][2] = "Configuración";
        idiom[1][3] = "Puntaje";
        idiom[1][4] = "Salir";

        idiom[2][0] = "玩";
        idiom[2][1] = "添加問題";
        idiom[2][2] = "環境";
        idiom[2][3] = "分數";
        idiom[2][4] = "離開";

        idiom[3][0] = "لعب";
        idiom[3][1] = "إضافة سؤال";
        idiom[3][2] = "ضبط";
        idiom[3][3] = "نتيجة";
        idiom[3][4] = "غادر";


    }
    public void changeIdiom(int num){

        buttonPlay.setText(idiom[num][0]);
        buttonAddQuestion.setText(idiom[num][1]);
        buttonSetting.setText(idiom[num][2]);
        buttonRecord.setText(idiom[num][3]);
        buttonExit.setText(idiom[num][4]);

    }

    @FXML void addQuestionAction(ActionEvent event) throws IOException {

        changeScreen("../AddQuestion/AddQuestion.fxml");

    }

    @FXML void settingAction(ActionEvent event) throws IOException {

        changeScreen("../Config/Config.fxml");

    }

    @FXML void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML void playAction(ActionEvent event) throws IOException {

        changeScreen("../Question/Question.fxml");

    }

    @FXML void recordAction(ActionEvent event) throws IOException {

        changeScreen("../Record/Record.fxml");

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