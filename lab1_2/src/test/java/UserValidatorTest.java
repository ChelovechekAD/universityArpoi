import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    @ParameterizedTest
    @MethodSource("listOfInvalidLogins")
    public void testRegInvalidLogin(String invalidLogin) {
        Exception exception = assertThrows(WrongLoginException.class, () -> UserRegistration.validateLogin(invalidLogin));
        assertEquals(Constants.LOGIN_IS_INCORRECT_MESSAGE, exception.getMessage());

    }


    @ParameterizedTest
    @MethodSource("listOfInvalidPasswords")
    public void testRegInvalidPassword(String password, String passwordConf) {
        Exception exception = assertThrows(WrongPasswordException.class, () -> UserRegistration.validatePassword(password, passwordConf));
        assertEquals(Constants.PASSWORD_IS_INCORRECT_MESSAGE, exception.getMessage());
    }


    @Test
    public void testRegPositive() {
        assertTrue(UserRegistration.registerUser("testLogin", "testPass", "testPass"));
    }


    private static List<String> listOfInvalidLogins() {
        return Arrays.asList(
                "space error!",
                "lengthOfThisStringMoreThan20Char"
        );
    }

    private static List<Arguments> listOfInvalidPasswords() {
        return Arrays.asList(
                Arguments.of("space error!", "space error!"),
                Arguments.of("lengthOfThisStringMoreThan20Char", "lengthOfThisStringMoreThan20Chars"),
                Arguments.of("password", "passwordConf")
        );
    }

}
