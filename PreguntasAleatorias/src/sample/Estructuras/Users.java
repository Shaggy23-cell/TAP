package sample.Estructuras;

import java.util.LinkedList;

public class Users {

    public String user;
    public String password;

    public LinkedList<QuestionAnswered> questionAnswered = new LinkedList<>();

    public Users(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LinkedList<QuestionAnswered> getQuestionAnswered() {
        return questionAnswered;
    }

    public void setQuestionAnswered(LinkedList<QuestionAnswered> questionAnswered) {
        this.questionAnswered = questionAnswered;
    }
}
