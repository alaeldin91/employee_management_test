package com.alaeldin.employee_anagement_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Util
{
    public void validatePageable(Pageable pageable) {

        if (pageable == null) {
            log.debug("Pageable is null");
            throw new IllegalArgumentException("Pageable cannot be null");
        }

        if (pageable.getPageNumber() < 0) {
            log.debug("Invalid page number: {}", pageable.getPageNumber());
            throw new IllegalArgumentException("Page number cannot be negative:" + pageable.getPageNumber());
        }

        if (pageable.getPageSize() <= 0) {
            log.debug("Invalid page size: {}", pageable.getPageSize());
            throw new IllegalArgumentException("Page size must be greater than zero: " + pageable.getPageSize());
        }

        if (pageable.getPageSize() >= 100) {
            log.debug("Page size too large: {}", pageable.getPageSize());
            throw new IllegalArgumentException("Page size cannot exceed 100: " + pageable.getPageSize());
        }
    }
}
