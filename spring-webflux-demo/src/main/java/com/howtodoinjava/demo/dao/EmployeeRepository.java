package com.howtodoinjava.demo.dao;

import com.howtodoinjava.demo.documents.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String>{
    Flux<Employee> findAllBy(Pageable pageable);
}