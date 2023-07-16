import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCheckPassword {
    @Test
    public void testValidPassword() {
        String validPassword = "example123";
        Assertions.assertTrue(utils.Check.checkPassword(validPassword));
    }

    @Test
    public void testInvalidPasswordTooShort() {
        String invalidPassword = "a";
        Assertions.assertFalse(utils.Check.checkPassword(invalidPassword));
    }

    @Test
    public void testInvalidPasswordTooLong() {
        String invalidPassword = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" ;
        Assertions.assertFalse(utils.Check.checkPassword(invalidPassword));
    }

    @Test
    public void testInvalidPasswordContainsSpace() {
        String invalidPassword = "example 123";
        Assertions.assertFalse(utils.Check.checkPassword(invalidPassword));
    }

    @Test
    public void testInvalidPasswordContainsSpecialCharacters() {
        String invalidPassword = "example@#$";
        Assertions.assertTrue(utils.Check.checkPassword(invalidPassword));
    }

}
