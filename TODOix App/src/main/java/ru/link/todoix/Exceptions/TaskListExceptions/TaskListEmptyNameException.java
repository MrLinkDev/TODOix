package ru.link.todoix.Exceptions.TaskListExceptions;

import org.springframework.http.HttpStatus;
import ru.link.todoix.PostModels.ErrorModel;

/**
 * Класс исключения пустого имени создаваемого списка дел
 */
public class TaskListEmptyNameException extends Exception{
    private static final String ERROR_MESSAGE = "Tasklist name cannot be empty!";

    /**
     * Метод для получения ErrorModel исключения
     * @return ErrorModel
     */
    public static ErrorModel getErrorModel(){
        return new ErrorModel(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
    }
}
