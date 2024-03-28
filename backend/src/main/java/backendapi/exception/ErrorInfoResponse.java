package backendapi.exception;

import java.time.LocalDateTime;

public record ErrorInfoResponse(String path, String message, int statusCode, LocalDateTime localDateTime) {
}