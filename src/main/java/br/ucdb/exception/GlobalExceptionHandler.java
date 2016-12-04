package br.ucdb.exception;

import br.ucdb.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CYNNER on 08/10/2016.
 */

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { BaseException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(BaseException ex) {
        return new ApiErrorResponse(500, 4041, ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        return e.getMessage();
    }



}




