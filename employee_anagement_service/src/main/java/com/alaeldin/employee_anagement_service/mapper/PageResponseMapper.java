package com.alaeldin.employee_anagement_service.mapper;

import com.alaeldin.employee_anagement_service.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageResponseMapper {

   public <T> PageResponse<T> from(Page<T> page) {
      return PageResponse.<T>builder()
              .data(page.getContent())
              .page(page.getNumber())
              .pageSize(page.getSize())
              .totalPages(page.getTotalPages())
              .last(page.isLast())
              .build();
   }
}