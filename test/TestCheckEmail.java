import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestCheckEmail {

    @Test
    public void testCheckEmail() {
        String email = "";
        boolean falseExpected = false;
        boolean trueExpected = true;
        assertEquals(falseExpected, utils.Check.checkEmail(email));
        assertEquals(trueExpected, utils.Check.checkEmail("tienhuu12@gmail.com"));
        assertEquals(falseExpected, utils.Check.checkEmail("tien123@abc"));
        assertEquals(falseExpected, utils.Check.checkEmail("tien123@abc."));
    }

    @Test
    public void testValidEmail() {
        String validEmail = "example123@example.com";
        Assertions.assertTrue(utils.Check.checkEmail(validEmail));
    }

    @Test
    public void testInvalidEmailMissingAtSymbol() {
        String invalidEmail = "example123example.com";
        Assertions.assertFalse(utils.Check.checkEmail(invalidEmail));
    }

    @Test
    public void testInvalidEmailMissingDomain() {
        String invalidEmail = "example123@";
        Assertions.assertFalse(utils.Check.checkEmail(invalidEmail));
    }

    @Test
    public void testInvalidEmailMissingTopLevelDomain() {
        String invalidEmail = "example123@example";
        Assertions.assertFalse(utils.Check.checkEmail(invalidEmail));
    }

    @Test
    public void testInvalidEmailInvalidCharacters() {
        String invalidEmail = "example@#$@example.com";
        Assertions.assertFalse(utils.Check.checkEmail(invalidEmail));
    }

    @Test
    public void testInvalidEmailTooShortLocalPart() {
        String invalidEmail = "a@example.com";
        Assertions.assertFalse(utils.Check.checkEmail(invalidEmail));
    }

    @Test
    public void testInvalidEmailStartsWithNumber() {
        String invalidEmail = "1example@example.com";
        Assertions.assertFalse(utils.Check.checkEmail(invalidEmail));
    }

    @Test
    public void testInvalidEmailNoDotAfterAtSymbol() {
        String invalidEmail = "example123@examplecom";
        Assertions.assertFalse(utils.Check.checkEmail(invalidEmail));
    }

    @Test
    public void testInvalidEmailNoCharactersAfterDot() {
        String invalidEmail = "example123@example.";
        Assertions.assertFalse(utils.Check.checkEmail(invalidEmail));
    }
}
