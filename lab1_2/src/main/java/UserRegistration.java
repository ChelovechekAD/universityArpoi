import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;

public final class UserRegistration {

    private UserRegistration(){}

    public static boolean registerUser(String login, String password, String confirmPassword) {
        try {
            validateLogin(login);
            validatePassword(password, confirmPassword);
            return true;
        } catch (WrongLoginException | WrongPasswordException e) {
            System.err.println(String.format(Constants.ERROR_MESSAGE_TEMPLATE, e.getMessage()));
            return false;
        }
    }

    public static void validateLogin(String login) throws WrongLoginException {
        if (!login.matches(Constants.LOGIN_REGEX) || login.length() >= 20) {
            throw new WrongLoginException(Constants.LOGIN_IS_INCORRECT_MESSAGE);
        }
    }

    public static void validatePassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!password.matches(Constants.PASSWORD_REGEX) || password.length() >= 20 || !password.equals(confirmPassword)) {
            throw new WrongPasswordException(Constants.PASSWORD_IS_INCORRECT_MESSAGE);
        }
    }

}
