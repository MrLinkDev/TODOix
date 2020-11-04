package ru.link.todoix;

import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.link.todoix.Exceptions.PageExceptions.*;
import ru.link.todoix.Exceptions.TaskExceptions.TaskNotFoundException;
import ru.link.todoix.Exceptions.TaskListExceptions.*;

/**
 * Обработчик исключений
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorPostModel errorModel = new ErrorPostModel(status, "Required parameter is not present!");
        return new ResponseEntity(errorModel, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleTypeMismatchException(MethodArgumentTypeMismatchException ex){
        ErrorPostModel errorModel = new ErrorPostModel(HttpStatus.BAD_REQUEST, "Wrong type of argument!");
        return new ResponseEntity(errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskListNotFoundException.class)
    protected ResponseEntity<Object> handleTaskListNotFoundException(){
        ErrorPostModel errorModel = TaskListNotFoundException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(TaskListEmptyNameException.class)
    protected ResponseEntity<Object> handleTaskListEmptyNameException(){
        ErrorPostModel errorModel = TaskListEmptyNameException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(PageIndexException.class)
    protected ResponseEntity<Object> handlePageIndexException(){
        ErrorPostModel errorModel = PageIndexException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(PageSizeException.class)
    protected ResponseEntity<Object> handlePageSizeException(){
        ErrorPostModel errorModel = PageSizeException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(PageSortException.class)
    protected ResponseEntity<Object> handlePageSortException(){
        ErrorPostModel errorModel = PageSortException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    protected ResponseEntity<Object> handleTaskNotFoundException(){
        ErrorPostModel errorModel = TaskNotFoundException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

}
