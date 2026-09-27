package com.alaeldin.employee_anagement_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExist extends RuntimeException
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
     * Field Value Like alaeldinmusa91@gmail.com
     */
    private Object fieldValue;

    public  ResourceAlreadyExist(String resourceName, String fieldName, String fieldValue)
    {
        super(String.format("Resource %s already exists with %s = '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
