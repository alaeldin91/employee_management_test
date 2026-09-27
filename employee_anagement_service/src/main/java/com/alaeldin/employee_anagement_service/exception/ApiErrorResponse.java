package com.alaeldin.employee_anagement_service.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private String path;
    private Integer status;
    private String errorCode;
    Map<String , String> details;

    /**
     *
     * @param timestamp LocalDateTime
     * @param message   String
     * @param path      String
     * @param status    Integer
     * @param errorCode Integer
     */
    public ApiErrorResponse(LocalDateTime timestamp, String message, String path, Integer status, String errorCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        this.status = status;
        this.errorCode = errorCode;
    }
}
