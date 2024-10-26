package ma.crm.carental.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice  
public class GlobalExceptionHandler {
    

    @ExceptionHandler(UnableToProccessIteamException.class)
    public ResponseEntity<Map<String, Map<String , Object>>> handleUnableToProccessIteamException(UnableToProccessIteamException ex) {
        log.error("UnableToProccessIteamException: {}", ex.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", ex.getMessage());

        Map<String, Map<String , Object>> error = new HashMap<>();
        error.put("error", response) ;

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Map<String, Object>>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException: {}", ex.getMessage());
        
        // Extract detailed information about the JSON parse error
        String errorMessage = "Invalid JSON format";
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", errorMessage);

        Map<String, Map<String, Object>> error = new HashMap<>();
        error.put("error", response);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("message", "Validation failed");

        // Format the list of validation errors
        List<Map<String, String>> validationErrors = ex.getErrors().stream()
            .map(error -> {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("field", error.getField());
                errorMap.put("message", error.getMessage());
                return errorMap;
            })
            .collect(Collectors.toList());

        response.put("errors", validationErrors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Map<String, Object>> handelExption(Exception ex) {
    //     log.error("UnableToProccessIteamException: {}", ex.getMessage());
    //     Map<String, Object> response = new HashMap<>();
    //     response.put("status", false);
    //     response.put("message", "hello");
    //     return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    // }
}
