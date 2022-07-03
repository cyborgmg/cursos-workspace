package com.bezkoder.spring.springpageable.service;

import com.bezkoder.spring.springpageable.entity.Tabela;
import com.bezkoder.spring.springpageable.repository.TabelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    @Autowired
    private TabelaRepository tabelaRepository;

    public Page<Tabela> findAll(Pageable paging, String methodName){

        System.out.println("methodName="+methodName);

        return tabelaRepository.findAll(paging);
    }

    public Page<Tabela> findByNomeContaining(String nome,Pageable paging, String methodName){

        System.out.println("methodName="+methodName);

        return tabelaRepository.findByNomeContaining(nome, paging);
    }

}
