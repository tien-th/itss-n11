package view_pet;

public class UpdatedPetInfo {
    private String name;
    private String category;
    private int age;
    private String color;

    public UpdatedPetInfo(String name, String category, int age, String color) {
        this.name = name;
        this.category = category;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }
}
