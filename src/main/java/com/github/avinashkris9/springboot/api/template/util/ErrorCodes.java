package com.github.avinashkris9.springboot.api.template.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ErrorCodes
 * Utility class to decalre all constant error codes , error messages
 */
public class ErrorCodes {

    public enum ERROR_CODES{

        ERR_NOT_FOUND,
        ERR_VALIDATION_FAILURE,

        ERR_EXISTS

    };
    public enum Gender { M,F,T};

    public static final String  VALIDATION_FAILURE= "ERR";
    public static final String ENTITY_NOT_FOUND="Entity not found in system";
    public static final String EMPTY_MESSAGE_BODY="Invalid message body";

    public static final String MANDATORY_MISSING="Mandatory fields missing ";

}