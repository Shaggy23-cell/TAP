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
import sample.Estructuras.Questions;

import java.io.IOException;
import java.util.LinkedList;

public class Principal {

    @FXML private JFXButton buttonPlay;

    @FXML private JFXButton buttonAddQuestion;

    @FXML private JFXButton buttonSetting;

    @FXML private JFXButton buttonExit;

    @FXML private AnchorPane anchorPane;

    @FXML private VBox vBoxHijo;

    public static LinkedList<Questions> questions = new LinkedList<>();

    Parent parent;

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