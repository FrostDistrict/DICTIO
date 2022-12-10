package exception;

public class WordAlreadyExistsException extends Exception{

    private String message;

    public WordAlreadyExistsException(String word) {
        message = word + " existe deja dans ce dictionnaire!";
    }

    public String getMessage() {
        return message;
    }
}
