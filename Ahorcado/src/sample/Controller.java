package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.Random;

public class Controller {
    @FXML HBox contenedor;
    @FXML AnchorPane padre;
    @FXML Button salir;
    @FXML Button salir2;

    String[] palabras={"Pelota","Microfono","Libro", "Cuchara","Salsa","Chicharron","Mouse"};
    TextField[]arrayTxt=null;
    int contador=0;
    int contV;
    @FXML protected void initialize(){
        salir.setVisible(false);
        salir2.setVisible(false);
        Random random =new Random();
        int aleatorio= random.nextInt(6);
        String palabra=palabras[aleatorio].toLowerCase();
        System.out.println(palabra);
        arrayTxt=new TextField[palabra.length()];
       contV= palabra.length();
        int ayuda=1;// son las letras de ayuda



    for (int x=0;x<palabra.length();x++){
        arrayTxt[x]=new TextField();
        arrayTxt[x].setPrefWidth(50);
        arrayTxt[x].setPrefHeight(50);
        arrayTxt[x].setId("txt-"+x+"-"+palabra.charAt(x));
        arrayTxt[x].setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                TextField textField=(TextField) keyEvent.getTarget(); // obtener lo que dispara el evento

             String[] nombre= textField.getId().split("-");

                if(nombre[2].equals(textField.getText().toLowerCase())){
                    System.out.println("Correcto"+textField.getId());
                    textField.setDisable(true);
                    contV--;
                    if(contV==0){
                        Alert alerta= new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Victoria");
                        alerta.setHeaderText("Felicidades!!!");
                        alerta.setContentText("Buen trabajo");
                        alerta.show();
                        salir.setVisible(true);
                    }

                }else{

                    System.out.println("Incorrecto"+textField.getId());
                    textField.setText("");
                    if(contador==0){
                        pintarCabeza();
                        contador++;
                    }else if(contador==1){
                        pintarCuerpo();
                        contador++;
                    }else if(contador==2){
                        pintarBrazoI();
                        contador++;
                    }else if(contador==3){
                        pintarBrazoD();
                        contador++;
                    }else if(contador==4){
                        pintarPieI();
                        contador++;
                    }else if(contador==5){
                        pintarPieD();
                        Alert alerta= new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Game over");
                        alerta.setHeaderText("LOSER");
                        alerta.setContentText("Mejor suerte la proxima");
                        alerta.show();
                        contador++;
                        salir2.setVisible(true);
                    }else{


                    }
                }

            }
        });
        contenedor.getChildren().add( arrayTxt[x]);
    }
    }
public void pintarCabeza(){
    ImageView cabeza=new ImageView(new Image("sample/img/cabeza.png"));
    cabeza.setFitWidth(70);
    cabeza.setFitHeight(70);
    cabeza.setLayoutX(200);
    cabeza.setLayoutY(205);

    padre.getChildren().addAll(cabeza);
}
    public void pintarCuerpo(){
        ImageView cuerpo=new ImageView(new Image("sample/img/cuerpo.png"));
        cuerpo.setFitWidth(70);
        cuerpo.setFitHeight(70);
        cuerpo.setLayoutX(200);
        cuerpo.setLayoutY(270);
        padre.getChildren().addAll(cuerpo);
    }
    public void pintarBrazoI(){
        ImageView brazoIzquierdo=new ImageView(new Image("sample/img/brazoIzquierdo.png"));
        brazoIzquierdo.setFitWidth(70);
        brazoIzquierdo.setFitHeight(70);
        brazoIzquierdo.setLayoutX(150);
        brazoIzquierdo.setLayoutY(270);
        padre.getChildren().addAll(brazoIzquierdo);
    }
    public void pintarBrazoD(){
        ImageView brazoDerechoo=new ImageView(new Image("sample/img/brazoDerecho.png"));
        brazoDerechoo.setFitWidth(70);
        brazoDerechoo.setFitHeight(70);
        brazoDerechoo.setLayoutX(250);
        brazoDerechoo.setLayoutY(270);
        padre.getChildren().addAll(brazoDerechoo);
    }
    public void pintarPieI(){
        ImageView pieIzquierdo=new ImageView(new Image("sample/img/pieIzquierdo.png"));
        pieIzquierdo.setFitWidth(70);
        pieIzquierdo.setFitHeight(70);
        pieIzquierdo.setLayoutX(160);
        pieIzquierdo.setLayoutY(330);
        padre.getChildren().addAll(pieIzquierdo);
    }
    public void pintarPieD(){
        ImageView pieDerecho=new ImageView(new Image("sample/img/pieDerecho.png"));
        pieDerecho.setFitWidth(70);
        pieDerecho.setFitHeight(70);
        pieDerecho.setLayoutX(240);
        pieDerecho.setLayoutY(330);
        padre.getChildren().addAll(pieDerecho);
    }

    public void salirr(ActionEvent event){
        System.exit(0);
    }
    public void salirr2(ActionEvent event){
        System.exit(0);
    }
}
