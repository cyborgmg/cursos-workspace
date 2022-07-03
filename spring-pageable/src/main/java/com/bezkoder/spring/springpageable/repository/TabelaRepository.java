package com.bezkoder.spring.springpageable.repository;

import com.bezkoder.spring.springpageable.entity.Tabela;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaRepository extends JpaRepository<Tabela,Long> {

    Page<Tabela> findByNomeContaining(String nome, Pageable pageable);

}
