package entity;

public class Feedback {
    int id;
    String username;
    String feedback;
    String date;

    public Feedback(int id, String username, String feedback, String date) {
        this.id = id;
        this.username = username;
        this.feedback = feedback;
        this.date = date;
    }
    //setter and getter
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getFeedback() {
        return feedback;
    }
    public String getDate() {
        return date;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
