package com.alaeldin.employee_anagement_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException
{

    /**
     * Resource Name Like Employee
     */
    private String resourceName;
    /**
     * Field Name Like email
     */
    private String fieldName;
    /**
     * Field value like alaeldinmusa91@gmail.com
     */
    private Object fieldValue;

    public ResourceNotFound(String resourceName, String fieldName, Object fieldValue) {

      super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
      this.resourceName = resourceName;
      this.fieldName = fieldName;
      this.fieldValue = fieldValue;
     }
}
