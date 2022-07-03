package com.devsuperior.hruserfeinggatway.services;

import com.devsuperior.hruserfeinggatway.entities.Worker;
import com.devsuperior.hruserfeinggatway.feignclientes.WorkerFeignCliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WorkerService {

    @Autowired
    private WorkerFeignCliente workerFeignCliente;

    public List<Worker> findAll(){

        List<Worker> workers = workerFeignCliente.findAll().getBody();

        return workers;
    }

}
