package library.util.exceptoins;

/**
 * Неправильный тип загружаемого файла
 */
public class ContentTypeException extends RuntimeException {
    public ContentTypeException(String message) {
        super(message);
    }
}