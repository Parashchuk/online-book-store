package bookstore.exception;

import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .toList();
        body.put("time", LocalDateTime.now());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handleUsernameNotFoundException(
            UsernameNotFoundException ex
    ) {
        return getCustomExceptionHandler(ex, "entity-not-found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(
            EntityNotFoundException ex
    ) {
        return getCustomExceptionHandler(ex, "entity-not-found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistrationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Map<String, Object>> handleRegistrationException(
            RegistrationException ex
    ) {
        return getCustomExceptionHandler(ex, "conflict", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleLoginException(
            BadCredentialsException ex
    ) {
        return getCustomExceptionHandler(ex, "bad-request",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Map<String, Object>> handleJwtException(
            JwtException ex
    ) {
        return getCustomExceptionHandler(ex, "invalid-or-expired-token",
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(OrderException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Map<String, Object>> handleOrderException(
            OrderException ex
    ) {
        return getCustomExceptionHandler(ex, "empty-order",
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleDuplicateValueException(
            DuplicateValueException ex
    ) {
        return getCustomExceptionHandler(ex, "duplicate-property",
                HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> getCustomExceptionHandler(
            Exception ex,
            String error,
            HttpStatus status
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("time", LocalDateTime.now());
        body.put("error", error);
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, new HttpHeaders(), status);
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            return field + " " + message;
        }

        return error.getDefaultMessage();
    }
}
