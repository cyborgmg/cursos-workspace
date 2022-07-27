package com.howtodoinjava.demo.dto;

import com.howtodoinjava.demo.descripions.Descriptions;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @Schema(description = Descriptions.DESCRIPTION_ID_EMPLOYEE)
    private String id;
    @Schema(description = Descriptions.DESCRIPTION_NAME_EMPLOYEE)
    private String name;
    @Schema(description = Descriptions.DESCRIPTION_SALARY_EMPLOYEE)
    private long salary;
}
