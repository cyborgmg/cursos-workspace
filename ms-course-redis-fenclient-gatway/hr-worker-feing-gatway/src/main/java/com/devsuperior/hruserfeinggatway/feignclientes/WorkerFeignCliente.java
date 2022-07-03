package com.devsuperior.hruserfeinggatway.feignclientes;

import com.devsuperior.hruserfeinggatway.entities.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@FeignClient(name = "hr-worker", path = "/workers")
public interface WorkerFeignCliente {

    @GetMapping
    public ResponseEntity<List<Worker>> findAll();

}
