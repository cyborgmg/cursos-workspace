package com.howtodoinjava.demo.dto;

import com.howtodoinjava.demo.descripions.Descriptions;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    @Schema(description = Descriptions.DESCRIPTION_EMPLOYEES)
    private List<EmployeeDTO> employees;
    @Schema(description = Descriptions.DESCRIPTION_TOTALELEMENTS)
    private Long totalElements;
    @Schema(description = Descriptions.DESCRIPTION_LAST)
    private boolean last;
    @Schema(description = Descriptions.DESCRIPTION_TOTALPAGES)
    private int totalPages;
    @Schema(description = Descriptions.DESCRIPTION_NUMBER)
    private int number;
    @Schema(description = Descriptions.DESCRIPTION_SIZE)
    private int size;
    @Schema(description = Descriptions.DESCRIPTION_NUMBEROFELEMENTS)
    private int numberOfElements;
    @Schema(description = Descriptions.DESCRIPTION_FIRST)
    private boolean first;
}
