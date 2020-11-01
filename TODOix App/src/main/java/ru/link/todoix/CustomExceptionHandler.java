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
import ru.link.todoix.PostModels.ErrorModel;

/**
 * Обработчик исключений
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorModel errorModel = new ErrorModel(status, "Required parameter is not present!");
        return new ResponseEntity(errorModel, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleTypeMismatchException(MethodArgumentTypeMismatchException ex){
        ErrorModel errorModel = new ErrorModel(HttpStatus.BAD_REQUEST, "Wrong type of argument!");
        return new ResponseEntity(errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskListNotFoundException.class)
    protected ResponseEntity<Object> handleTaskListNotFoundException(){
        ErrorModel errorModel = TaskListNotFoundException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(TaskListEmptyNameException.class)
    protected ResponseEntity<Object> handleTaskListEmptyNameException(){
        ErrorModel errorModel = TaskListEmptyNameException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(PageIndexException.class)
    protected ResponseEntity<Object> handlePageIndexException(){
        ErrorModel errorModel = PageIndexException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(PageSizeException.class)
    protected ResponseEntity<Object> handlePageSizeException(){
        ErrorModel errorModel = PageSizeException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(PageSortException.class)
    protected ResponseEntity<Object> handlePageSortException(){
        ErrorModel errorModel = PageSortException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    protected ResponseEntity<Object> handleTaskNotFoundException(){
        ErrorModel errorModel = TaskNotFoundException.getErrorModel();
        return new ResponseEntity(errorModel, errorModel.getHttpStatus());
    }

}
