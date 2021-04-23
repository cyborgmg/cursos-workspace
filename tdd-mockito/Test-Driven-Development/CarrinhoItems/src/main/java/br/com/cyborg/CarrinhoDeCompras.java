package br.com.cyborg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDeCompras {

    private List<Item> itens = new ArrayList<>();

    public void adiciona(Item item) {
        this.itens.add(item);
    }

    public double maiorValor() {
        if(itens.size() == 0) return 0;
        double maior = itens.get(0).getValorTotal();
        for(Item item : itens) {
            if(maior < item.getValorTotal()) {
                maior = item.getValorTotal();
            }
        }
        return maior;
    }
}
