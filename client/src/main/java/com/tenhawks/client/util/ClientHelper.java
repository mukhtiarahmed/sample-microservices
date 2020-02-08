package com.tenhawks.client.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenhawks.bean.CommonHelper;
import com.tenhawks.client.dto.PersonDTO;
import com.tenhawks.exception.AssignmentException;
import com.tenhawks.exception.InitializationNotAllowedException;
import lombok.extern.slf4j.Slf4j;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.Period;

import static com.tenhawks.bean.CommonHelper.*;

/**
 * The Helper class.
 *
 * @author mukhtiar.ahmed
 */
@Slf4j
public final class ClientHelper {



    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";



    /**
     * The object mapper.
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();



    /**
     * private Constructor
     */
    private ClientHelper() throws InitializationNotAllowedException {
        throw new InitializationNotAllowedException("Initialization not allowed");
    }

    /**
     * <p>
     * Logs for entrance into public methods at <code>DEBUG</code> level.
     * </p>
     *
     * @param signature  the signature.
     * @param paramNames the names of parameters to log (not Null).
     * @param params     the values of parameters to log (not Null).
     */
    public static void logEntrance(String signature, String[] paramNames, Object[] params) {
        if (log.isDebugEnabled()) {
            String msg = String.format(MESSAGE_ENTRANCE, signature) + toString(paramNames, params);
            log.debug(msg);
        }
    }

    /**
     * <p>
     * Logs for exit from public methods at <code>DEBUG</code> level.
     * </p>
     *
     * @param signature the signature of the method to be logged.
     * @param value     the return value to log.
     */
    public static void logExit(String signature, Object value) {
        if (log.isDebugEnabled()) {
            StringBuilder msg = new StringBuilder(String.format(MESSAGE_EXIT, signature));
            if (value != null) {
                msg.append(" Output parameter : ").append(value);
            }
            log.debug(msg.toString());
        }
    }

    /**
     * <p>
     * Logs the given exception and message at <code>ERROR</code> level.
     * </p>
     *
     * @param <T>       the exception type.
     * @param signature the signature of the method to log.
     * @param e         the exception to log.
     */
    public static <T extends Throwable> void logException(String signature, T e) {
        StringBuilder sw = new StringBuilder();
        sw.append(String.format(MESSAGE_ERROR, signature))
                .append(e.getClass().getName())
                .append(": ")
                .append(e.getMessage());
        Throwable cause = e.getCause();
        final String lineSeparator = System.getProperty("line.separator");
        while (null != cause) {
            sw.append(lineSeparator)
                    .append("        Caused By: ")
                    .append(cause.getClass().getName())
                    .append(": ")
                    .append(cause.getMessage());
            cause = cause.getCause();
        }

        if (e instanceof AssignmentException) {
            log.error(sw.toString());
        } else {
            log.error(sw.toString(), e);
        }

    }

    /**
     * <p>
     * Converts the parameters to string.
     * </p>
     *
     * @param paramNames the names of parameters.
     * @param params the values of parameters.
     * @return the string
     */
    public static String toString(String[] paramNames, Object[] params) {
        StringBuilder sb = new StringBuilder(" Input parameters: {");
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(paramNames[i]).append(" -> ").append(toString(params[i]));
            }
        }
        sb.append("}.");
        return sb.toString();
    }

    /**
     * <p>
     * Converts the object to string.
     * </p>
     *
     * @param obj the object
     * @return the string representation of the object.
     */
    public static String toString(Object obj) {
        String result;
        try {
            if (obj instanceof HttpServletRequest) {
                result = "Spring provided servlet request";
            } else if (obj instanceof HttpServletResponse) {
                result = "Spring provided servlet response";
            } else if (obj instanceof ModelAndView) {
                result = "Spring provided model and view object";
            } else {
                result = MAPPER.writeValueAsString(obj);
            }

        } catch (IOException e) {
            result = "The object can not be serialized by Jackson JSON mapper, error: " + e.getMessage();
        }
        return result;
    }

    public static  String getAuthorizationHeader(String clientId, String clientSecret) {


        String creds = String.format("%s:%s", clientId, clientSecret);

        try {
            return "Basic " + new String(Base64.getEncoder().encode(creds.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException var5) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    /**
     * Validate Person DTO. return true when person data not valid.
     * @param personDTO
     * @param result
     * @return boolean
     */
    public static boolean validatePerson(PersonDTO personDTO, BindingResult result) {

        boolean hasErrors =  result.hasErrors();
        if(StringUtils.isEmpty(personDTO.getDob())) {
            result.rejectValue("dob",  "dob.required", "Date of Birth is mandatory");
            hasErrors =  true;
        }

        if(hasErrors) return hasErrors;

        try {
            LocalDate.parse(personDTO.getDob());
        } catch (Exception e) {
             result.rejectValue("dob",  "dob.required",
                        "Date of birth format should be like 2019-12-25");
            hasErrors =  true;
        }

        return hasErrors;


    }

}
