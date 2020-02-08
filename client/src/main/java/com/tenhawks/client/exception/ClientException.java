package com.tenhawks.client.exception;

import com.tenhawks.exception.BusinessException;

/**
 * @author Mukhtiar Ahmed
 */
public class ClientException extends BusinessException {

  private static final long serialVersionUID = 8087867092781813380L;


  /**
   * <p>
   * This is the constructor of <code>ClientException</code> class with message argument.
   * </p>
   *
   * @param message the error message.
   */
  public ClientException(String message) {
    super(message);
  }

  /**
   * <p>
   * This is the constructor of <code>ClientException</code> class with message and cause arguments.
   * </p>
   *
   * @param message the error message.
   * @param cause the cause of the exception.
   */
  public ClientException(String message, Throwable cause) {
    super(message, cause);
  }
}
