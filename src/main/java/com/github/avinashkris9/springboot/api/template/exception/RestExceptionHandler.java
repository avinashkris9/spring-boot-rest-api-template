package com.github.avinashkris9.springboot.api.template.exception;

import com.github.avinashkris9.springboot.api.template.model.APIError;
import com.github.avinashkris9.springboot.api.template.util.ErrorCodes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    /*
    Function to handle http message non readable spring error.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        APIError apiError=new APIError();
        apiError.setCode(ErrorCodes.ERROR_CODES.ERR_VALIDATION_FAILURE.name());
        apiError.setMessage(ErrorCodes.EMPTY_MESSAGE_BODY);
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }


/*

Data Validation Exception handler.
The exception message  will contain the first matching exception.
 */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        List<String> mandatoryFieldsMissing=
                ex.getBindingResult().getFieldErrors().stream().
                        filter(error -> error.getCode().equalsIgnoreCase("NotBlank") || error.getCode().equalsIgnoreCase("NotNull")
                        ).map(FieldError::getField).collect(Collectors.toList());


        APIError apiError=new APIError();


        if(mandatoryFieldsMissing.isEmpty())
        {

            apiError.setMessage((ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage()));
        }
        else
        {
            String errorMessage=mandatoryFieldsMissing.stream().collect(Collectors.joining(",","{","}"));
            errorMessage=ErrorCodes.MANDATORY_MISSING+errorMessage;
            apiError.setMessage(errorMessage);
        }



        apiError.setCode(ErrorCodes.ERROR_CODES.ERR_VALIDATION_FAILURE.name());
        return new ResponseEntity(apiError,HttpStatus.BAD_REQUEST);
    }

/*

Custom Exception when the entity is not found in database. The custom exception should be thrown from service.
 */
    @ExceptionHandler(value={EntityNotFoundException.class})
    public ResponseEntity<APIError> customHandleNotFound(Exception ex) {

        APIError errors = new APIError();
        errors.setMessage(ex.getMessage());
        errors.setCode(ErrorCodes.ENTITY_NOT_FOUND);


        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }
}
