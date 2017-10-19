package library.util.exceptoins;

/**
 * не найдено значение
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
