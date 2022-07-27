package com.howtodoinjava.demo.dto;

import com.howtodoinjava.demo.descripions.Descriptions;
import com.howtodoinjava.demo.errors.Errors;
import com.howtodoinjava.demo.formats.Formats;
import com.howtodoinjava.demo.validations.DateType;
import com.howtodoinjava.demo.validations.DateValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @Schema(description = Descriptions.DESCRIPTION_ID_EMPLOYEE)
    @NotNull(message = Errors.REQUERED_ID_EMPLOYEE)
    private String id;
    @Schema(description = Descriptions.DESCRIPTION_NAME_EMPLOYEE)
    @NotNull(message = Errors.REQUERED_NAME_EMPLOYEE)
    private String name;
    @Schema(description = Descriptions.DESCRIPTION_SALARY_EMPLOYEE)
    @NotNull(message = Errors.REQUERED_SALARY_EMPLOYEE)
    private long salary;

    @DateValidation(value = String.class, format = Formats.DATE_FORMAT, type = DateType.DATE ,message = "campo date inválida")
    private String date;

    @DateValidation(value = String.class, format = Formats.DATE_TIME_FORMAT, type = DateType.DATE_TIME ,message = "campo dateTime inválida")
    private String dateTime;

}
