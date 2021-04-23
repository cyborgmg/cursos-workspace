package br.com.cyborg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaiorEMenorTest {

    @Test
    void ordemDecrescente() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adiciona(new Produto("Geladeira", 450.0));
        carrinho.adiciona(new Produto("Liquidificador", 250.0));
        carrinho.adiciona(new Produto("Jogo de pratos", 70.0));
        MaiorEMenor algoritmo = new MaiorEMenor();
        algoritmo.encontra(carrinho);
        assertEquals("Jogo de pratos", algoritmo.getMenor().getNome());
        assertEquals("Geladeira", algoritmo.getMaior().getNome());
    }

    @Test
    public void apenasUmProduto() {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.adiciona(new Produto("Geladeira", 450.0));
        MaiorEMenor algoritmo = new MaiorEMenor();
        algoritmo.encontra(carrinho);
        assertEquals("Geladeira",algoritmo.getMenor().getNome());
        assertEquals("Geladeira",algoritmo.getMaior().getNome());
    }

}