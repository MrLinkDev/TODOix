package ru.link.todoix;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.link.todoix.PostModels.ErrorModel;

/**
 * Обработчик исключений
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorModel errorModel = new ErrorModel(status, "Required parameter 'name' is not present!");
        return new ResponseEntity(errorModel, status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        ErrorModel errorModel = new ErrorModel(HttpStatus.BAD_REQUEST, "Wrong type of argument!");
        return new ResponseEntity(errorModel, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex){
        ErrorModel errorModel = new ErrorModel(HttpStatus.NOT_FOUND, "Item with this id doesn't exist!");
        return new ResponseEntity(errorModel, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PropertyReferenceException.class)
    protected ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException ex){
        ErrorModel errorModel = new ErrorModel(HttpStatus.BAD_REQUEST, "No property with this name!");
        return new ResponseEntity(errorModel, HttpStatus.BAD_REQUEST);
    }



}
