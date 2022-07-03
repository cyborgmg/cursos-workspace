package com.bezkoder.spring.springpageable.resource;

import com.bezkoder.spring.springpageable.dto.PaginaDTO;
import com.bezkoder.spring.springpageable.entity.Tabela;
import com.bezkoder.spring.springpageable.service.TableService2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TableResource {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TableService2 tableService2;

    @GetMapping("/tabelas")
    public ResponseEntity<PaginaDTO> getAllTutorials(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {

        try {

            Pageable paging = PageRequest.of(page, size);

            Page<Tabela> pageTuts;
            if (nome == null)
                pageTuts = tableService2.findAll(paging, Thread.currentThread().getStackTrace()[1].getMethodName());
            else
                pageTuts = tableService2.findByNomeContaining(nome, paging, Thread.currentThread().getStackTrace()[1].getMethodName());

            List<Tabela> tabelas = pageTuts.getContent();

            PaginaDTO paginaDTO = PaginaDTO
                    .builder()
                    .objects(tabelas)
                    .currentPage(pageTuts.getNumber())
                    .totalItems(pageTuts.getTotalElements())
                    .totalPages(pageTuts.getTotalPages())
                    .build();

            return ResponseEntity.ok().body(paginaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
