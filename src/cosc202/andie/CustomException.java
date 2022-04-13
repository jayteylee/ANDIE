package cosc202.andie;

public class CustomException extends Exception {

    public CustomCode code;

    public static enum CustomCode {
        FILE_SAVE_NULL_EXCEPTION
    }

    public CustomException() {
        super();
    }

    public CustomException(CustomCode c, String message) {
        super(message);
        code = c;
    }

    public CustomException(CustomCode c, String message, Throwable cause) {
        super(message, cause);
    }


}