package ru.leska.sensorapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.leska.sensorapi.exception.SensorErrorResponse;
import ru.leska.sensorapi.exception.SensorNotCreatedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(RuntimeException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                "Sensor name already exists",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
