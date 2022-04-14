package cosc202.andie;

/**
 * <p>A custom exception class for creating exceptions specific to the operations of ANDIE. </p>
 * 
 * <p>Custom exceptions can be created using this class which extends Exception. Custom exceptions are thrown the same way any other exceptions are thrown.
 * There is the added advantage of the {@code CustomException} being able to carry a {@code CustomCode code}. This code is an enumeration of the different types of custom exceptions
 * that can be thrown such as the type {@link #CustomCode.FILE_SAVE_NULL_EXCEPTION} for when the user tries to save a file without one being opened beforehand. </p>
 */


public class CustomException extends Exception {

    /**
     * <p>The type of custom exception that has been thrown.</p>
     */
    public CustomCode code;

    /** <p> Enumeration of the types of exception. */
    public static enum CustomCode {
        /**Used when the user is trying to save an image file that is null.*/
        FILE_SAVE_NULL_EXCEPTION
    }

    /** <p>Constructs a new CustomException</p> */
    public CustomException() {
        super();
    }

    /**<p>Constructs a new CustomException with a code and message attached. </p>
     * 
     *  @param c The code of this CustomException.
     *  @param message A descriptive message on what caused the exception.
    */
    public CustomException(CustomCode c, String message) {
        super(message);
        code = c;
    }

    /**<p> Constructs a new CustomException with a code, message and cause attached.
     * 
     * @param c The code of this CustomException.
     * @param message A descriptive message on what caused the exception.
     * @param cause A Throwable object that played a part in causing the exception.
     */
    public CustomException(CustomCode c, String message, Throwable cause) {
        super(message, cause);
    }


}