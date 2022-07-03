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

    private List<Produto> produtos = new ArrayList<Produto>();

    public void adiciona(Produto produto){
        produtos.add(produto);
    }

}
