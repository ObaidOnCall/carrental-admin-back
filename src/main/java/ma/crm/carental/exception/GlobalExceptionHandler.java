package ma.crm.carental.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Map<String, Object>> handelExption(Exception ex) {
    //     log.error("UnableToProccessIteamException: {}", ex.getMessage());
    //     Map<String, Object> response = new HashMap<>();
    //     response.put("status", false);
    //     response.put("message", "hello");
    //     return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    // }
}
