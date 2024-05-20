package blog.flatform.message;

import org.springframework.http.HttpStatus;

public record ErrorMessage(
        HttpStatus status, String errorMessage) {
}
