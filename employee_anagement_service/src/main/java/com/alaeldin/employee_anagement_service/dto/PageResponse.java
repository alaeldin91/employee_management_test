package com.alaeldin.employee_anagement_service.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageResponse<T> {

  private List<T> data;
  private Integer total;
  private Integer page;
  private Integer pageSize;
  private Integer totalPages;
  boolean last;
  boolean first;
}
