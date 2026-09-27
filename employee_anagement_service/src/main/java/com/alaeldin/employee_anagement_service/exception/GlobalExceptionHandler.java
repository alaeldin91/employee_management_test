package com.alaeldin.employee_anagement_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        Map<String, String> errorsField = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            errorsField.put(fieldName, message);
        });

        String errorMessage = String.format(" Validation failed For fields %s", errorsField);
        log.warn("Validation failed for fields {}", errorsField);

        ApiErrorResponse apiErrorResponse = buildApiResponse(errorMessage, request
                , HttpStatus.BAD_REQUEST.value(), "VALIDATION_ERROR");

        apiErrorResponse.setDetails(errorsField);

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        Map<String, String> errorsField = e.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        err-> err.getPropertyPath().toString(),
                        ConstraintViolation::getMessage,
                        (oldData,newData) -> newData

                ));
        String errorMessage = String.format(" Validation failed For fields %s", errorsField);
        log.warn("Validation failed for fields {}", errorsField);
        ApiErrorResponse apiErrorResponse = buildApiResponse(errorMessage
                , request, HttpStatus.BAD_REQUEST.value(), "VALIDATION_ERROR");

      apiErrorResponse.setDetails(errorsField);

      return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
   public ResponseEntity<?> handleResourceNotFound(ResourceNotFound e, HttpServletRequest request) {

        String message = e.getMessage();
        log.warn("Resource not found {}", message);
        ApiErrorResponse apiErrorResponse = buildApiResponse(message, request
                , HttpStatus.NOT_FOUND.value(), "RESOURCE_NOT_FOUND");

      return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExist.class)
    public ResponseEntity<?> handleResourceAlreadyExist(ResourceAlreadyExist e, HttpServletRequest request) {

        String message = e.getMessage();
        log.warn("Resource already exist {}", message);
        ApiErrorResponse apiErrorResponse = buildApiResponse(
                message, request, HttpStatus.CONFLICT.value(), "RESOURCE_ALREADY_EXIST"
        );
        return new ResponseEntity<>(apiErrorResponse, HttpStatus.CONFLICT);
    }
    /**
     *
     * @param message String
     * @param request HttpServlet
     * @param status Integer
     * @param errorCode String
     * @return apiErrorResponse
     */
    private ApiErrorResponse buildApiResponse(String message, HttpServletRequest request, Integer status, String errorCode) {
        LocalDateTime timestamp = LocalDateTime.now();

        return ApiErrorResponse.builder().timestamp(timestamp)
                .message(message).path(request.getRequestURI())
                .status(status).errorCode(errorCode).build();
    }

}
