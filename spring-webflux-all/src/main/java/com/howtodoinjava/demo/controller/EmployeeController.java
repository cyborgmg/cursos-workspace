package com.howtodoinjava.demo.controller;

import com.howtodoinjava.demo.documents.Employee;
import com.howtodoinjava.demo.dto.EmployeeDTO;
import com.howtodoinjava.demo.dto.PageDTO;
import com.howtodoinjava.demo.exceptions.ExceptionResponse;
import com.howtodoinjava.demo.formats.Formats;
import com.howtodoinjava.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Employee", description = "Employee operations")
@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
@ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
@ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final DateTimeFormatter DATE_DATETIMEFORMATTER = DateTimeFormatter.ofPattern(Formats.DATE_FORMAT);

    private static final DateTimeFormatter DATE_TIME_DATETIMEFORMATTER = DateTimeFormatter.ofPattern(Formats.DATE_TIME_FORMAT);

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Request Employees", description = "This operation allows the API consumer to Employee",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json"))})
    public ResponseEntity<Mono<EmployeeDTO>> create(@Valid @RequestBody EmployeeDTO e) {

        Employee em = Employee
                .builder()
                .id(new ObjectId())
                .name(e.getName())
                .salary(e.getSalary())
                .date(LocalDate.parse(e.getDate(), DATE_DATETIMEFORMATTER))
                .dateTime(LocalDateTime.parse(e.getDateTime(), DATE_TIME_DATETIMEFORMATTER))
                .build();

        Mono<EmployeeDTO> create = employeeService.create(em)
                .flatMap(r ->
                        Mono.just(EmployeeDTO
                                .builder()
                                .id(r.getId().toHexString())
                                .name(r.getName())
                                .salary(r.getSalary())
                                .date(r.getDate().toString())
                                .dateTime(r.getDateTime().toString())
                                .build())
                );

        return new ResponseEntity<Mono<EmployeeDTO>>(create, HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @Operation(summary = "Request Employee", description = "This operation allows the API consumer to Employee",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class)))})
    public ResponseEntity<Mono<EmployeeDTO>> findById(@PathVariable("id") String id) {
        Mono<EmployeeDTO> e = employeeService.findById(id).map(em ->
                EmployeeDTO
                        .builder()
                        .id(em.getId().toHexString())
                        .name(em.getName())
                        .salary(em.getSalary())
                        .date(em.getDate().toString())
                        .dateTime(em.getDateTime().toString())
                        .build()
        );
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<EmployeeDTO>>(e, status);
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request get Employee by id", description = "This operation allows the API get to Employees",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))})
    public Mono<List<EmployeeDTO>> findAll() {

        return employeeService
                .findAll()
                .map(e ->
                        EmployeeDTO
                                .builder()
                                .id(e.getId().toHexString())
                                .name(e.getName())
                                .salary(e.getSalary())
                                .date(e.getDate().toString())
                                .dateTime(e.getDateTime().toString())
                                .build())
                .collectList();

    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request Employee", description = "This operation allows the API get to Employee by pages",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageDTO.class)))})
    public Mono<PageDTO> getPages(
            @Min(0)
            @RequestParam("page") int page,
            @Min(1)
            @RequestParam("size") int size) {

        return this.employeeService.getEmployees(PageRequest.of(page, size));

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request Employee", description = "This operation allows the API update Employee",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class)))})
    public Mono<EmployeeDTO> update(@RequestBody EmployeeDTO e) {
        Mono<Employee> employee = employeeService.update(
                Employee
                        .builder()
                        .id(new ObjectId(e.getId()))
                        .name(e.getName())
                        .salary(e.getSalary())
                        .date(LocalDate.parse(e.getDate(), DATE_DATETIMEFORMATTER))
                        .dateTime(LocalDateTime.parse(e.getDateTime(), DATE_TIME_DATETIMEFORMATTER))
                        .build()
        );

        return employee
                .flatMap(em ->
                        Mono.just(EmployeeDTO
                                .builder()
                                .id(em.getId().toHexString())
                                .name(em.getName())
                                .salary(em.getSalary())
                                .date(em.getDate().toString())
                                .dateTime(em.getDateTime().toString())
                                .build())
                );
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Request transaction refund", description = "This operation allows the API consumer to refund",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json"))})
    public void delete(@PathVariable("id") String id) {
        employeeService.delete(id).subscribe();
    }

}
