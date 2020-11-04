package ru.link.todoix.Exceptions.PageExceptions;

import org.springframework.http.HttpStatus;
import ru.link.todoix.ErrorPostModel;

/**
 * Класс исключения неверного размера страницы
 */
public class PageSizeException extends Exception{
    private static final String ERROR_MESSAGE = "Page size must not be less than one!";

    /**
     * Метод для получения ErrorModel исключения
     *
     * @return ErrorModel
     */
    public static ErrorPostModel getErrorModel(){
        return new ErrorPostModel(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
    }
}
