package sample.Config;


        import com.jfoenix.controls.JFXButton;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.text.Text;
        import sample.Main;

        import java.io.IOException;

public class Config {

    @FXML private Text textIdiom;

    @FXML private JFXButton buttonSpanish;

    @FXML private JFXButton buttonArabic;

    @FXML private JFXButton buttonChinesse;

    @FXML private JFXButton buttonEnglish;

    public static int idiom = 0;

    @FXML void initialize(){



    }

    @FXML void arabicAction(ActionEvent event) throws IOException {

        idiom = 3;
        changeScreen();

    }

    @FXML void chinesseAction(ActionEvent event) throws IOException {

        idiom = 2;
        changeScreen();

    }

    @FXML void englishAction(ActionEvent event) throws IOException {

        idiom = 0;
        changeScreen();

    }

    @FXML void spanishAction(ActionEvent event) throws IOException {

        idiom = 1;
        changeScreen();

    }

    public void changeScreen() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../Principal/Principal.fxml"));
        Main.stage.setScene(new Scene(root));

    }

}
