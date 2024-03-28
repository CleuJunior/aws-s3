package backendapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorInfoResponse> handleException(ResourceNotFoundException e,
                                                             HttpServletRequest request) {
        var apiError = new ErrorInfoResponse(
                request.getRequestURI(),
                e.getMessage(),
                NOT_FOUND.value(),
                now()
        );

        return new ResponseEntity<>(apiError, NOT_FOUND);
    }
}
