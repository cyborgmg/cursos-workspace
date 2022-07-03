package com.bezkoder.spring.springpageable.service;

import com.bezkoder.spring.springpageable.entity.Tabela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TableService2 {

    @Autowired
    private TableService tableService;

    public Page<Tabela> findAll(Pageable paging, String methodName){

        return tableService.findAll(paging, methodName);
    }

    public Page<Tabela> findByNomeContaining(String nome,Pageable paging, String methodName){

        return tableService.findByNomeContaining(nome, paging, methodName);
    }

}
