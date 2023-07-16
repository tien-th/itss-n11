package model.entity;

public class Pet {
    private int id ;
    private String username ;
    private String name ;
    private String color ;
    private String category ;
    private int age ;
    private String gender ;


    public Pet(int id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
    public Pet(int id, String username, String name, String category) {
       this(id, username, name);
        this.category = category;
    }
    public Pet(int id, String username, String name, String color, String category, int age, String gender) {
this(id, username, name, category);
        this.color = color;

        this.age = age;
        this.gender = gender;
    }

    public Pet() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
