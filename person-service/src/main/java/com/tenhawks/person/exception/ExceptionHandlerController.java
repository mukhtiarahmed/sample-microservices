package com.tenhawks.person.exception;

import com.tenhawks.bean.ApiResponse;
import com.tenhawks.bean.Meta;
import com.tenhawks.exception.AssignmentException;
import com.tenhawks.exception.EntityNotFoundException;
import com.tenhawks.exception.InvalidDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


/**
 * The exception handler.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * Handle EntityNotFoundException.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResponse<String> handleEntityNotFound(HttpServletRequest request, EntityNotFoundException exception) {
        log.info("EntityNotFoundException Occurred:: URL {} ", request.getRequestURL());
        return new ApiResponse<>(new Meta(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()),
                exception.getMessage(), null);

    }

    /**
     * Handle InvalidDataException.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidDataException.class})
    public ApiResponse<String> handleBadRequest(HttpServletRequest request, InvalidDataException exception) {
        log.info("InvalidDataException Occurred:: URL {} ", request.getRequestURL());
        return new ApiResponse<>(new Meta(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
                exception.getMessage(), null);
    }

    /**
     * HttpRequestMethodNotSupportedException
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ApiResponse<String> handleBadRequest(HttpServletRequest request,
                                        MethodArgumentTypeMismatchException exception) {
        log.info("MethodArgumentTypeMismatchException Occurred:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod());
        return new ApiResponse<>(new Meta(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
                exception.getMessage(), null);
    }

    /**
     * HttpRequestMethodNotSupportedException
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ApiResponse<String> handleBadRequest(HttpServletRequest request,
                                        HttpRequestMethodNotSupportedException exception) {
        log.info("HttpRequestMethodNotSupportedException Occurred:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod());
        return new ApiResponse<>(new Meta(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
                exception.getMessage(), null);
    }

    /**
     * HttpRequestMethodNotSupportedException
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ApiResponse<String> handleBadRequest(HttpServletRequest request, HttpMessageNotReadableException exception) {
        log.info("HttpMessageNotReadableException Occurred:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod());
        return new ApiResponse<>(new Meta(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
                exception.getMessage(), null);
    }

    /**
     * MethodArgumentNotValidException
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResponse<String> handleBadRequest(HttpServletRequest request, MethodArgumentNotValidException exception) {
        log.info("MethodArgumentNotValidException Occurred:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod());
        return new ApiResponse<>(new Meta(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
                exception.getMessage(), null);
    }

    /**
     * Handle AssignmentException.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AssignmentException.class)
    public ApiResponse<String> handleBadRequest(HttpServletRequest request, AssignmentException exception) {
        log.error("AssignmentException Occurred:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        return new ApiResponse<>(new Meta(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
                exception.getMessage(), null);
    }


    /**
     * Handle AssignmentException.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse<String> handleBadRequest(HttpServletRequest request, AccessDeniedException exception) {
        log.error("AccessDeniedException Occurred:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        return new ApiResponse<>(new Meta(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()),
                exception.getMessage(), null);
    }

    /**
     * Handle all other runtime exceptions.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<String> handleBadRequest(HttpServletRequest request, RuntimeException exception) {
        log.error("RuntimeException Occurred:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        return new ApiResponse<>(new Meta(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), exception.getMessage(), null);
    }

    /**
     * Handle all SQLException exceptions.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ApiResponse<String> handleBadRequest(HttpServletRequest request, SQLException exception) {
        log.error("SQLException Occurred:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        return new ApiResponse<>(new Meta(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), exception.getMessage(), null);
    }

    /**
     * Handle all other exceptions.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleBadRequest(HttpServletRequest request, Exception exception) {
        log.error("Exception Occurred:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        return new ApiResponse<>(new Meta(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), exception.getMessage(), null);
    }

}
