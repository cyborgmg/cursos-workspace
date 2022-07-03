package com.marcosbarbero.lab.sec.oauth.jwt.ds.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/me")
@Api(value = "Teste")
public class UserController {

    @GetMapping
    @ApiOperation(value="Testa Principal", response=Principal.class, notes="Essa operação testa Principal")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="Sucesso", response=Principal.class),
            @ApiResponse(code=400, message="Requisição mau formada.", response=Principal.class)
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

}
