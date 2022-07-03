package com.bezkoder.spring.springpageable.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginaDTO {

    private List<?> objects;
    private Integer currentPage;
    private Long totalItems;
    private Integer totalPages;
}
