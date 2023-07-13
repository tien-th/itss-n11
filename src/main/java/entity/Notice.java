package entity;

public class Notice {
    private String username;
    private String content;

    // constructor
    public Notice(String username, String content) {
        this.username = username;
        this.content = content;
    }
    //getter and setter
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }


}
