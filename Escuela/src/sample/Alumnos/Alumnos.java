package sample.Alumnos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.Fila;
import sample.Main;
import sample.models.Conexion;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Alumnos {
@FXML Button btnCancelar,btnInsertar;
@FXML TextField txtName,txtLastName,txtPhone,txtCareer;

    Conexion conexion;
    TableColumn colId=new TableColumn("ID");
    TableColumn colName=new TableColumn("Nombres");
    TableColumn colLast=new TableColumn("Apellidos");
    TableColumn colCell=new TableColumn("Numero celular");
    TableColumn colCareer=new TableColumn("Carrera");
    TableColumn colEditar=new TableColumn("     ");
    TableColumn colEliminar=new TableColumn("     ");
    Fila filaEdit;
    @FXML
    TableView table;
    ObservableList<Fila> datosTabla= FXCollections.observableArrayList();
Callback<TableColumn<Fila,String>, TableCell<Fila,String>> celdaEditar=new Callback<TableColumn<Fila, String>, TableCell<Fila, String>>() {
    @Override
    public TableCell<Fila, String> call(TableColumn<Fila, String> filaStringTableColumn) {
        TableCell<Fila,String> cell = new TableCell<Fila,String>(){
            Button btnEditar=new Button("Editar");

            @Override
            protected void updateItem(String item, boolean empty) {
                if(empty){
                    setGraphic(null);
                    setText(null);
                }else{
                    btnEditar.getStyleClass().add("btnEditar");
                    btnEditar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            filaEdit=getTableView().getItems().get(getIndex());
                            txtName.setText((filaEdit.getName()));
                            txtLastName.setText((filaEdit.getLast_name()));
                            txtPhone.setText((filaEdit.getCellphone()));
                            txtCareer.setText((filaEdit.getCareer()));
                            btnCancelar.setVisible(true);
                            btnInsertar.setText("ACTUALIZAR");
                        }
                    });
                    setGraphic(btnEditar);
                    setText(null);
                }
            }
        };
      return cell;
    }
};
    Callback<TableColumn<Fila,String>, TableCell<Fila,String>> celdaEliminar=new Callback<TableColumn<Fila, String>, TableCell<Fila, String>>() {
        @Override
        public TableCell<Fila, String> call(TableColumn<Fila, String> filaStringTableColumn) {
            TableCell<Fila,String> cell = new TableCell<Fila,String>(){
                Button btnEliminar=new Button("Eliminar");

                @Override
                protected void updateItem(String item, boolean empty) {
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        btnEliminar.getStyleClass().add("btnEliminar");
                        btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Eliminar Registro");
                                alert.setContentText("¿Desea eliminar el registro?");
                                Optional<ButtonType> resultado=alert.showAndWait();
                                if(resultado.get()== ButtonType.OK){
                                    Fila fila=getTableView().getItems().get(getIndex());
                                    conexion.insmodel("delete from alumnos where id= "+fila.getId());
                                    try {
                                        recargar();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });



                        setGraphic(btnEliminar);
                        setText(null);
                    }
                }
            };
            return cell;
        }
    };
    @FXML void initialize() throws SQLException {

        conexion = new Conexion();
        colId.setMinWidth(123);
        colName.setMinWidth(129.8);
        colLast.setMinWidth(129.8);
        colCell.setMinWidth(129.8);
        colCareer.setMinWidth(129.8);

        //LIGAR COLUNA CON FILA
        colId.setCellValueFactory(new PropertyValueFactory<Fila,String>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Fila,String>("name"));
        colLast.setCellValueFactory(new PropertyValueFactory<Fila,String>("last_name"));
        colCell.setCellValueFactory(new PropertyValueFactory<Fila,String>("cellphone"));
        colCareer.setCellValueFactory(new PropertyValueFactory<Fila,String>("career"));
        colEditar.setCellFactory(celdaEditar);
        colEliminar.setCellFactory(celdaEliminar);
        table.getColumns().addAll(colId,colName,colLast,colCell, colCareer,colEditar,colEliminar);
        recargar();
        table.setItems(datosTabla);
    }

    public void recargar() throws SQLException {
        ResultSet res=conexion.consultar("SELECT * FROM `alumnos` order by id DESC ");
        datosTabla.clear();
        if (res!=null){
            while(res.next()){
                datosTabla.addAll(new Fila(res.getObject("id").toString(),
                        res.getObject("name").toString(),
                        res.getObject("last_name").toString(),
                        res.getObject("cellphone").toString(),
                        res.getObject("career").toString()));
            }
        }

    }

    public void insertar() throws SQLException {
        if(btnInsertar.getText().equals("ACTUALIZAR")){
            String nom= txtName.getText();
            String lastnom=txtLastName.getText();
            int phone=Integer.parseInt(txtPhone.getText());
            String career=txtCareer.getText();

            conexion.insmodel( "UPDATE `alumnos` SET `name`='"+nom+"',`last_name`='"+lastnom+"',`cellphone`="+phone+",`career`='"+career+"' WHERE id="+filaEdit.getId());



            btnInsertar.setText("INSERTAR");
            txtName.setText(""); txtLastName.setText("");  txtPhone.setText("");  txtCareer.setText("");
            btnCancelar.setVisible(false);
            recargar();
        }else{
            if(!txtName.getText().trim().equals("")&&!txtLastName.getText().trim().equals("")&&!txtPhone.getText().trim().equals("")&&
                    !txtCareer.getText().trim().equals("")){


                String nom= txtName.getText();
                String lastnom=txtLastName.getText();
                String phone=txtPhone.getText();
                String career=txtCareer.getText();

                conexion.insmodel("INSERT INTO `alumnos` (`name`, `last_name`, `cellphone`, `career`) VALUES ('"+nom+"','"+lastnom+"','"+phone+"','"+career+"')");
               
                txtName.setText(""); txtLastName.setText("");  txtPhone.setText("");  txtCareer.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setContentText("Registro insertado correctamente");
                alert.show();
                recargar();

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Favor de llenar todos los campos");
                alert.show();
            }
        }


    }
    public void cancelar(ActionEvent event){
    if(filaEdit!=null){
        btnInsertar.setText("INSERTAR");
        txtName.setText(""); txtLastName.setText("");  txtPhone.setText("");  txtCareer.setText("");
        btnCancelar.setVisible(false);
    }
    }
    public void regresar() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));
        Main.stage.setScene(new Scene(root));
}
}
