package ru.link.todoix.Exceptions.TaskListExceptions;

import org.springframework.http.HttpStatus;
import ru.link.todoix.PostModels.ErrorModel;

/**
 * Класс исключения неверного ID списка дел
 */
public class TaskListNotFoundException extends Exception{
    private static final String ERROR_MESSAGE = "Tasklist with this ID not found!";

    /**
     * Метод для получения ErrorModel исключения
     * @return ErrorModel
     */
    public static ErrorModel getErrorModel(){
        return new ErrorModel(HttpStatus.NOT_FOUND, ERROR_MESSAGE);
    }
}
