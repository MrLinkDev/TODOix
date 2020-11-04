package ru.link.todoix.Exceptions.TaskListExceptions;

import org.springframework.http.HttpStatus;
import ru.link.todoix.ErrorPostModel;

/**
 * Класс исключения пустого имени создаваемого списка дел
 */
public class TaskListEmptyNameException extends Exception{
    private static final String ERROR_MESSAGE = "Tasklist name cannot be empty!";

    /**
     * Метод для получения ErrorModel исключения
     *
     * @return ErrorModel
     */
    public static ErrorPostModel getErrorModel(){
        return new ErrorPostModel(HttpStatus.BAD_REQUEST, ERROR_MESSAGE);
    }
}
