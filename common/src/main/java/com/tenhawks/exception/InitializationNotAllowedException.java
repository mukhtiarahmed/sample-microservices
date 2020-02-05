package com.tenhawks.exception;



/**
 *  This exception is thrown when try to create instance of restrict class.
 * @author Mukhtiar
 */
public class InitializationNotAllowedException extends SystemException  {


    /**
     * <p>
     * This is the constructor of <code>InitializationNotAllowedException</code> class with message and cause arguments.
     * </p>
     *
     * @param message the error message.
     * @param exception the cause of the exception.
     */
    public InitializationNotAllowedException(String message, Throwable exception) {
        super(message, exception);

    }

    /**
     * <p>
     * This is the constructor of <code>InitializationNotAllowedException</code> class with exception and cause arguments.
     * </p>
     *
     * @param exception the error exception.
     */
    public InitializationNotAllowedException(String exception) {
        super(exception);
    }
}
