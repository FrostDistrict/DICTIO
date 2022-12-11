package exception;

/**
 * Exception retourner si un mot existe deja
 */
public class WordAlreadyExistsException extends Exception{

    private String message;

    /**
     * constructeur de la classe
     * @param word mot qui existe deja
     */
    public WordAlreadyExistsException(String word) {
        message = word + " existe deja dans ce dictionnaire!";
    }

    /**
     * méthode récupérant le message d'erreur
     * @return le message
     */
    public String getMessage() {
        return message;
    }
}
