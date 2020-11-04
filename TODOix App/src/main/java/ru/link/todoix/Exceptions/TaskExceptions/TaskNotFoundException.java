package ru.link.todoix.Exceptions.TaskExceptions;

import org.springframework.http.HttpStatus;
import ru.link.todoix.ErrorPostModel;

/**
 * Класс исключения неверного ID дела
 */
public class TaskNotFoundException extends Exception {
    private static final String ERROR_MESSAGE = "Task with this ID not found!";

    /**
     * Метод для получения ErrorModel исключения
     *
     * @return ErrorModel
     */
    public static ErrorPostModel getErrorModel(){
        return new ErrorPostModel(HttpStatus.NOT_FOUND, ERROR_MESSAGE);
    }
}
