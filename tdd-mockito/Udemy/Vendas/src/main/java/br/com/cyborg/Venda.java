package br.com.cyborg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    private double valor;

    private boolean pgtoVista;

    private Cliente cliente;

    private CreditoService creditoService;


    public boolean checkout(){

        return pgtoVista?true:(creditoService.getLimit(cliente.getCpf())>valor);
    }

}
