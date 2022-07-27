package com.howtodoinjava.demo.service;

import com.howtodoinjava.demo.dao.EmployeeRepository;
import com.howtodoinjava.demo.documents.Employee;
import com.howtodoinjava.demo.dto.EmployeeDTO;
import com.howtodoinjava.demo.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public Mono<Employee> create(Employee e) {

        return employeeRepo.save(e);
    }

    public Mono<Employee> findById(String id) {
        return employeeRepo.findById(id);
    }

    public Flux<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public Mono<PageDTO> getEmployees(PageRequest pageRequest) {

        Mono<PageImpl<Employee>> page = this.employeeRepo.findAllBy(pageRequest)
                .collectList()
                .zipWith(this.employeeRepo.count())
                .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()));

        return page
                .flatMap(p ->
                        Mono.just(PageDTO
                                .builder()
                                .employees(
                                        p.getContent()
                                                .stream()
                                                .map(em -> EmployeeDTO
                                                        .builder()
                                                        .id(em.getId().toHexString())
                                                        .name(em.getName())
                                                        .salary(em.getSalary())
                                                        .build())
                                                .collect(Collectors.toList())
                                )
                                .size(p.getSize())
                                .totalElements(p.getTotalElements())
                                .last(p.isLast())
                                .totalPages(p.getTotalPages())
                                .numberOfElements(p.getNumberOfElements())
                                .number(p.getNumber())
                                .first(p.isFirst())
                                .build()
                        )
                );
    }


    public Mono<Employee> update(Employee e) {
        return employeeRepo.save(e);
    }

    public Mono<Void> delete(String id) {
        return employeeRepo.deleteById(id);
    }

}