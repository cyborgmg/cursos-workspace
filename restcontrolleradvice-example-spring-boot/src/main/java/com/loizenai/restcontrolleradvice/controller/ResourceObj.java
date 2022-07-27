package com.loizenai.restcontrolleradvice.controller;


import com.loizenai.restcontrolleradvice.errors.Errors;
import com.loizenai.restcontrolleradvice.model.ExceptionResponse;
import com.loizenai.restcontrolleradvice.model.Obj;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.Objects;

@RestController
@RequestMapping
@Validated
public class ResourceObj {

    @GetMapping(value = "/test")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request transaction refund", description = "This operation allows the API consumer to refund",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Obj.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))})
    public Obj getObj(@Parameter(description = "id") Integer id ){

        if(Objects.isNull(id)||id>10){
            throw new ValidationException(Errors.INVALID_ID);
        }

        return Obj
                .builder()
                .id(1)
                .nome("xxxxxxxxx")
                .build();
    }

}
