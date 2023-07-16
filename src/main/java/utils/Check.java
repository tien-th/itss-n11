package utils;

public class Check {
    public static boolean checkPassword(String password) {
        String regex = "^[^\\s]{6,30}$";
        return password.matches(regex);
    }

    public static boolean checkUsername(String username) {
        String regex = "^[a-zA-Z][a-zA-Z0-9]{4,28}$";
        return username.matches(regex);
    }

    public static boolean checkEmail(String email) {
        String regex = "^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$";
        return email.matches(regex);
    }
}
