package com.tenhawks.client.exception;

import com.tenhawks.exception.InvalidDataException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.tenhawks.bean.CommonHelper.ERROR_MESSAGE;
import static com.tenhawks.bean.CommonHelper.ERROR_NOT_PROVIDED;

/**
 * The exception handler.
 *
 * @author  mukhtiar.ahmed
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {


    public static final String ERROR = "error";

    /**
     * Handle InvalidDataException.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidDataException.class})
    public ModelAndView handleBadRequest(HttpServletRequest request, InvalidDataException exception) {
        log.info("InvalidDataException Occured:: URL {} ", request.getRequestURL());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR, exception.getMessage());
        modelAndView.setViewName("error");
        return  modelAndView;
    }
    
    /**
     * HttpRequestMethodNotSupportedException
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public  ModelAndView handleBadRequest(HttpServletRequest request,
                                          MethodArgumentTypeMismatchException exception) {
        log.info("MethodArgumentTypeMismatchException Occured:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR, "Internal Server error. Please contact to admin");
        modelAndView.setViewName("error");
        return  modelAndView;
    }
    
    /**
     * HttpRequestMethodNotSupportedException
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ModelAndView handleBadRequest(HttpServletRequest request,
                                         HttpRequestMethodNotSupportedException exception) {
        log.info("HttpRequestMethodNotSupportedException Occured:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR, exception.getMessage());
        modelAndView.setViewName("error");
        return  modelAndView;
    }
    
    /**
     * HttpRequestMethodNotSupportedException
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ModelAndView handleBadRequest(HttpServletRequest request, HttpMessageNotReadableException exception) {
        log.info("HttpMessageNotReadableException Occured:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR, exception.getMessage());
        modelAndView.setViewName("error");
        return  modelAndView;
    }
    
    /**
     * Handle ClientException.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientException.class)
    public ModelAndView handleBadRequest(HttpServletRequest request, ClientException exception) {
        log.error("ClientException Occured:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR, exception.getMessage());
        modelAndView.setViewName("error");
        return  modelAndView;
    }


    @ExceptionHandler(FeignException.class)
    public ModelAndView handleFeignStatusException(HttpServletRequest request, FeignException exception) {
        log.error("FeignException Occured:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR,  ERROR_MESSAGE);
        modelAndView.setViewName("error");
        return  modelAndView;
    }

    /**
     * Handle all other runtime exceptions.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleBadRequest(HttpServletRequest request,  RuntimeException exception) {
        log.error("RuntimeException Occured:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR, ERROR_MESSAGE);
        modelAndView.setViewName("error");
        return  modelAndView;
    }
    
    /**
     * Handle all SQLException exceptions.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ModelAndView handleBadRequest(HttpServletRequest request,  SQLException exception) {
        log.error("SQLException Occured:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR, ERROR_MESSAGE);
        modelAndView.setViewName("error");
        return  modelAndView;
    }

    /**
     * Handle all other exceptions.
     *
     * @param exception the exception
     * @return the error response
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleBadRequest(HttpServletRequest request,  Exception exception) {
        log.error("Exception Occured:: URL {} , method {} ",
                request.getRequestURL(), request.getMethod(), exception);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR, ERROR_MESSAGE);
        modelAndView.setViewName("error");
        return  modelAndView;
    }

}
