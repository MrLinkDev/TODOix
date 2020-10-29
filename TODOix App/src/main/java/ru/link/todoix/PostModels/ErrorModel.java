package ru.link.todoix.PostModels;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Модель возврата для ошибки
 */
@Data @RequiredArgsConstructor
public class ErrorModel {
    @NonNull private HttpStatus httpStatus;
    @NonNull private String message;
}
