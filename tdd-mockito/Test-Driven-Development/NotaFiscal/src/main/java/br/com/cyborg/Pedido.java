package br.com.cyborg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private String cliente;
    private double valorTotal;
    private int quantidadeItens;

}
