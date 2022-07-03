package br.com.cyborg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String descricao;
    private int quantidade;
    private double valorUnitario;


    public double getValorTotal() {
        return this.valorUnitario * this.quantidade;
    }

}
