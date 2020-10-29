package ru.link.todoix.PostModels;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data @RequiredArgsConstructor
public class ErrorModel {
    @NonNull private HttpStatus httpStatus;
    @NonNull private String message;
}
