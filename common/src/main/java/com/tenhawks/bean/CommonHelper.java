package com.tenhawks.bean;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenhawks.exception.ConfigurationException;
import com.tenhawks.exception.EntityNotFoundException;
import com.tenhawks.exception.InvalidDataException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;


/**
 * This class has utilities related to application
 * @author Mukhtiar
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonHelper {
    /*
     * <p>
	 * Represents the entrance message.
     * </p>
     */
    public static final String MESSAGE_ENTRANCE = "Entering method %1$s.";

    /**
     * <p>
     * Represents the exit message.
     * </p>
     */
    public static final String MESSAGE_EXIT = "Exiting method %1$s.";

    /**
     * <p>
     * Represents the error message.
     * </p>
     */
    public static final String MESSAGE_ERROR = "Error in method %1$s. Details:";

    /**
     * The object mapper.
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static final String ERROR_MESSAGE = "Internal Server Error";

    public static final String INVALID_REQUEST_MESSAGE = "HTTP URL or Method is not valid";

    public static final String MISSING_REQUEST_JSON_MESSAGE = "Missing Request Input JSON";

    public static final String AUTH_FAILED_MESSAGE = "Authentication Failed";

    public static final String  ERROR_NOT_PROVIDED =  " %s is not provided";

    public static final int PAGE_SIZE = 10;

    public static final int STRENGTH = 12;


    public static String generateBCryptPassword(final String plainPassword) {
        return new BCryptPasswordEncoder(STRENGTH).encode(plainPassword);
    }

    public static boolean isPasswordMatched(final String plainPassword, final String bCryptHash) {
        return new BCryptPasswordEncoder(STRENGTH).matches(plainPassword, bCryptHash);
    }



    /**
     * It checks whether a given object is null.
     *
     * @param object the object to be checked
     * @param name the name of the object, used in the exception message
     */
    public static void checkNull(Object object, String name) {
        if (object == null) {
            throw new InvalidDataException(String.format(ERROR_NOT_PROVIDED, name));
        }
    }

    /**
     * It checks whether a given string is null or empty.
     *
     * @param str the string to be checked
     * @param name the name of the object, used in the exception message

     */
    public static void checkNullOrEmpty(String str, String name)  {
        if (StringUtils.isEmpty(str)) {
            throw new InvalidDataException(String.format(ERROR_NOT_PROVIDED, name));
        }
    }
    /**
     * It checks whether the given list is null or empty.
     *
     * @param list the list
     * @param name the name of the object, used in the exception message
     * @throws InvalidDataException  the exception thrown when the list is null or empty
     */
    public static void checkNullOrEmpty(Collection<?> list, String name)  {
        if (list == null || list.isEmpty()) {
            throw new InvalidDataException(String.format(ERROR_NOT_PROVIDED, name));
        }
    }

    /**
     * Check if the value is positive.
     *
     * @param value the value
     * @param name the name
     * @throws InvalidDataException if the value is not positive
     */
    public static void checkPositive(long value, String name)  {
        if (value <= 0) {
            throw new InvalidDataException(String.format(" %s should be a positive value.", name));
        }
    }

    /**
     * Check if the configuration state is true.
     *
     * @param state the state
     * @param message the error message if the state is not true
     * @throws ConfigurationException if the state is not true
     */
    public static void checkConfigState(boolean state, String message) {
        if (!state) {
            throw new ConfigurationException(message);
        }
    }

    /**
     * Check if the configuration is null or not.
     *
     * @param object the object
     * @param name the name
     * @throws ConfigurationException if the state is not true
     */
    public static void checkConfigNotNull(Object object, String name) {
        if (object == null) {
            throw new ConfigurationException(String.format("%s should be provided", name));
        }
    }

    /**
     * Check if an entity with a given ID exists.
     *
     * @param id the ID
     * @param entity the entity object
     */
    public static void checkEntityExist(Object entity, long id){
        if (entity == null) {
            throw new EntityNotFoundException(String.format("entity of ID=%d can not be found.", id));
        }
    }




}
