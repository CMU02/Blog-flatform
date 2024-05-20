package blog.flatform.message;

import org.springframework.http.HttpStatus;

public record SuccessfulMessage(
        HttpStatus status, String successfulMessage) {
}
