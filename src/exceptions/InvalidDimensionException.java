package exceptions;

public class InvalidDimensionException extends IllegalArgumentException{
    public InvalidDimensionException(String message) {
        super(message);
    }
}
