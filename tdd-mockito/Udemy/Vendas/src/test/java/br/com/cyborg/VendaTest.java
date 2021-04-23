package br.com.cyborg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
class VendaTest {

    @Mock
    CreditoService creditoService;

    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void checkout_com_limite_de_credito() {

        Cliente cliente = new Cliente("123","João");

        when(creditoService.getLimit(any(String.class))).thenReturn(2500d);

        Venda venda = new Venda(2000,false,cliente,creditoService);

        assertTrue(venda.checkout());

        verify(creditoService).getLimit(any(String.class));
    }

    @Test
    void checkout_sem_limite_de_credito() {

        Cliente cliente = new Cliente("123","João");

        when(creditoService.getLimit(any(String.class))).thenReturn(1000d);

        Venda venda = new Venda(2000,false,cliente,creditoService);

        assertFalse(venda.checkout());

        verify(creditoService).getLimit(any(String.class));
    }

    @Test
    void checkout_pagamento_a_vista() {

        Cliente cliente = new Cliente("123","João");

        when(creditoService.getLimit(any(String.class))).thenReturn(2500d);

        Venda venda = new Venda(2000,true,cliente,creditoService);

        assertTrue(venda.checkout());

        verify(creditoService, never()).getLimit(any(String.class));
    }

    @Test
    void testa_excessao() {

        Cliente cliente = new Cliente("123","João");

        when(creditoService.getLimit(any(String.class))).thenThrow(UnknownHostException.class);

        Venda venda = new Venda(2000,false,cliente,creditoService);

        assertThrows(UnknownHostException.class, () -> {
            venda.checkout();
        });
    }

}