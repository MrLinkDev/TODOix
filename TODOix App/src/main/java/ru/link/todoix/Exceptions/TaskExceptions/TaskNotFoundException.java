package ru.link.todoix.Exceptions.TaskExceptions;

import org.springframework.http.HttpStatus;
import ru.link.todoix.PostModels.ErrorModel;

/**
 * Класс исключения неверного ID дела
 */
public class TaskNotFoundException extends Exception {
    private static final String ERROR_MESSAGE = "Task with this ID not found!";

    /**
     * Метод для получения ErrorModel исключения
     * @return ErrorModel
     */
    public static ErrorModel getErrorModel(){
        return new ErrorModel(HttpStatus.NOT_FOUND, ERROR_MESSAGE);
    }
}
