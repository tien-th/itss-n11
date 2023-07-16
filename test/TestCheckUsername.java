import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCheckUsername {
    @Test
    public void testValidUsername() {
        String validUsername = "example123";
        Assertions.assertTrue(utils.Check.checkUsername(validUsername));
    }

    @Test
    public void testInvalidUsernameTooShort() {
        String invalidUsername = "a";
        Assertions.assertFalse(utils.Check.checkUsername(invalidUsername));
    }

    @Test
    public void testInvalidUsernameTooLong() {
        String invalidUsername = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" ;
        Assertions.assertFalse(utils.Check.checkUsername(invalidUsername));
    }

    @Test
    public void testInvalidUsernameStartsWithNumber() {
        String invalidUsername = "1example";
        Assertions.assertFalse(utils.Check.checkUsername(invalidUsername));
    }

    @Test
    public void testInvalidUsernameContainsSpecialCharacters() {
        String invalidUsername = "example@#$";
        Assertions.assertFalse(utils.Check.checkUsername(invalidUsername));
    }

    @Test
    public void testInvalidUsernameContainsSpace() {
        String invalidUsername = "example 123";
        Assertions.assertFalse(utils.Check.checkUsername(invalidUsername));
    }
}
