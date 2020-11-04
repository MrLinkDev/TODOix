package ru.link.todoix;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Модель возврата для ошибки
 */
@Data
@RequiredArgsConstructor
public class ErrorPostModel {

    @NonNull
    private HttpStatus httpStatus;

    @NonNull
    private String message;
}
