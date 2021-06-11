package sample.Record;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Login.Login;
import sample.Principal.Principal;


public class Record {

    @FXML
    private VBox vBoxRecord;

    @FXML void initialize(){

        for(int x=0; x< Login.users.size(); x++){

            if(Login.users.get(x).getUser().equals(Principal.player)){

                Text[] texts = new Text[Login.users.get(x).getQuestionAnswered().size()];

                for(int y=0; y<texts.length;y++){

                    int finalY = y;
                    int finalX = x;

                    texts[y] = new Text(Login.users.get(x).getQuestionAnswered().get(y).getQuestion());
                    texts[y].setFont(new Font("GERUDA",30));
                    texts[y].setFill(Color.web("#85CB33"));

                    texts[y].setOnMouseClicked(event -> {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText(Login.users.get(finalX).getQuestionAnswered().get(finalY).getAnswer());
                        alert.setHeaderText("Answer");
                        alert.show();

                    });

                    vBoxRecord.getChildren().add(texts[y]);

                }

                break;
            }//key if

        }//key for


    }

}
