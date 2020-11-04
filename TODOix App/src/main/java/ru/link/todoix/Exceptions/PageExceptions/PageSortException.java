package ru.link.todoix.Exceptions.PageExceptions;

import org.springframework.http.HttpStatus;
import ru.link.todoix.ErrorPostModel;

/**
 * Класс исключения неверного параметра сортировки элементов страницы
 */
public class PageSortException extends Exception{
    private static final String ERROR_MESSAGE = "Illegal page sorting arg!";

    /**
     * Метод для получения ErrorModel исключения
     *
     * @return ErrorModel
     */
    public static ErrorPostModel getErrorModel(){
        return new ErrorPostModel(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
    }
}
