package sample;

import javafx.beans.property.SimpleStringProperty;

public class Fila {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty last_name;
    private SimpleStringProperty cellphone;
    private SimpleStringProperty career;


    public Fila(String id, String name, String last_name, String cellphone, String career) {
        this.id =new  SimpleStringProperty(id);
        this.name = new  SimpleStringProperty(name);
        this.last_name = new  SimpleStringProperty(last_name);
        this.cellphone = new  SimpleStringProperty(cellphone);
        this.career = new  SimpleStringProperty(career);

    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLast_name() {
        return last_name.get();
    }

    public SimpleStringProperty last_nameProperty() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public String getCellphone() {
        return cellphone.get();
    }

    public SimpleStringProperty cellphoneProperty() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone.set(cellphone);
    }

    public String getCareer() {
        return career.get();
    }

    public SimpleStringProperty careerProperty() {
        return career;
    }

    public void setCareer(String career) {
        this.career.set(career);
    }
}
