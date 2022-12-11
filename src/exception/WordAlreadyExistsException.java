package exception;

/**
 * Classe retournant une exception si le mot est déja connu
 */
public class WordAlreadyExistsException extends Exception{

    private String message;

    /**
     * méthode retournant une exception
     * @param word mot à comparer
     */
    public WordAlreadyExistsException(String word) {
        message = word + " existe deja dans ce dictionnaire!";
    }

    /**
     * méthode récupérant un mot
     * @return un mot pour le comparer
     */
    public String getMessage() {
        return message;
    }
}
