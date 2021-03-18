package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;



public class Controller {


    @FXML Canvas lienzo;
    GraphicsContext context;
    @FXML ColorPicker colorPicker;
    Color colorPincel=Color.BLACK;
    @FXML Slider slider;
@FXML ComboBox comboOpciones;
    @FXML protected void initialize(){
        context=lienzo.getGraphicsContext2D();
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                pintarDibujos(t1.intValue());
            }
        });
        comboOpciones.getItems().addAll("Cuadricula","Ajedrez","Estrella","Estrella doble");


        // traer pixeles para manipularlos

       /* context.setFill(Color.BLUE);//fill es relleno
        context.fillRect(10,20,100,50);
        context.setFill(Color.RED);
        context.strokeRect(200,150,200,100);
        context.strokeRect(400,250,200,100);
        context.setFill(Color.GREEN);
        context.fillOval(350,350,50,50);
        context.strokeLine(50,50,lienzo.getWidth(),lienzo.getHeight());*/




        
        
        
    }
    public void pintarDibujos(int valor){
        context.setFill(Color.WHITESMOKE);
        context.fillRect(0,0, lienzo.getWidth(),lienzo.getHeight());
        context.setFill(colorPincel);
        System.out.println(valor);
        int diviciones= (int) lienzo.getWidth()/valor;
if(comboOpciones.getSelectionModel().getSelectedIndex()==0){
//Cuadricula

    for(int x=1;x<valor+1;x++){

        context.strokeLine(0,diviciones*x,lienzo.getWidth(),diviciones*x);//linea horizontal
        context.strokeLine(diviciones*x,0,diviciones*x,lienzo.getWidth());//linea vertical
    }

}else if(comboOpciones.getSelectionModel().getSelectedIndex()==1){
//ajedres}

    context.setFill(Color.WHITE);
    context.fillRect(0,0, lienzo.getWidth(),lienzo.getHeight());
    int alternarColor=0;
    for(int x=0;x<valor;x++){

    for(int j=0;j<valor;j++){

        if(alternarColor==0){
            context.setFill(Color.WHITE);
            alternarColor=1;
        }else{
            context.setFill(Color.BLACK);
            alternarColor=0;
        }

        context.fillRect(j*diviciones,x*diviciones,diviciones,diviciones);
    }
    if(valor%2==0){
        if(alternarColor==0){
            alternarColor=1;
        }else{
            alternarColor=0;
        }
    }
    }

        }else if(comboOpciones.getSelectionModel().getSelectedIndex()==2){
//Estrella
    int mitadAncho=((int) lienzo.getWidth())/2;
    int mitadAlto=((int) lienzo.getHeight())/2;
    int alto= (int) lienzo.getHeight();
context.strokeLine(mitadAncho, 0,mitadAlto,lienzo.getHeight());
    context.strokeLine(0, mitadAlto,lienzo.getWidth(),mitadAlto);


    for(int j=1;j<valor +1;j++){
        context.strokeLine(mitadAncho,diviciones*j,mitadAncho+(diviciones*j),mitadAlto);//1
        context.strokeLine(mitadAncho,(diviciones*j),mitadAncho+(diviciones*-j),mitadAlto);//2
        context.strokeLine(diviciones*j,mitadAlto,mitadAncho,mitadAlto+(diviciones*j));//3

//context.strokeLine(mitadAncho,(lienzo.getWidth())-( diviciones*j),mitadAlto+(diviciones*j),mitadAlto);//4
        //context.strokeLine(mitadAncho+(diviciones*j),mitadAlto,mitadAncho,lienzo.getHeight()-(diviciones*j));//4
        context.strokeLine(mitadAncho,alto-(diviciones*j),mitadAncho+(diviciones*j),mitadAlto);//4
    }



        }else if(comboOpciones.getSelectionModel().getSelectedIndex()==3) {
//Estrella doble

    int mitadAncho = ((int) lienzo.getWidth()) / 2;
    int mitadAlto = ((int) lienzo.getHeight()) / 2;
    int alto = (int) lienzo.getHeight();
    int ancho = (int) lienzo.getWidth();
    context.strokeLine(ancho, 0, 0,alto);
    context.strokeLine(0, 0, ancho, alto);


    for (int j = 1; j < valor + 1; j++) {
        context.strokeLine(mitadAncho, diviciones * j, mitadAncho + (diviciones * j), mitadAlto);//1
        context.strokeLine(mitadAncho, (diviciones * j), mitadAncho + (diviciones * -j), mitadAlto);//2
        context.strokeLine(diviciones * j, mitadAlto, mitadAncho, mitadAlto + (diviciones * j));//3
//context.strokeLine(mitadAncho,(lienzo.getWidth())-( diviciones*j),mitadAlto+(diviciones*j),mitadAlto);//4
        //context.strokeLine(mitadAncho+(diviciones*j),mitadAlto,mitadAncho,lienzo.getHeight()-(diviciones*j));//4
        context.strokeLine(mitadAncho, alto - (diviciones * j), mitadAncho + (diviciones * j), mitadAlto);//4


    }

}

    }
    public void borrar2(ActionEvent event){
       context.setFill(Color.WHITESMOKE);
       context.fillRect(0,0, lienzo.getWidth(),lienzo.getHeight());
    } //Borrado total
    public void borrar(ActionEvent event){
        colorPincel=Color.WHITESMOKE;

    }
public void cambiarColor(ActionEvent event){
        colorPincel=colorPicker.getValue();
}
    public void  arrastrar(MouseEvent event){
        context.setFill(colorPincel);
        context.fillOval(event.getX(),event.getY(),10,10);
}




}