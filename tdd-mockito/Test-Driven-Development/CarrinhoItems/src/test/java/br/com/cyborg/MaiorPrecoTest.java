package br.com.cyborg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaiorPrecoTest {

    private CarrinhoDeCompras carrinho;

    @BeforeEach
    public void inicializa() {
        this.carrinho = new CarrinhoDeCompras();
    }

    @Test
    public void deveRetornarZeroSeCarrinhoVazio() {

        assertEquals(0.0, this.carrinho.maiorValor(), 0.0001);
    }

    @Test
    public void deveRetornarValorDoItemSeCarrinhoCom1Elemento() {

        this.carrinho.adiciona(new Item("Geladeira", 1, 900.0));
        assertEquals(900.0, carrinho.maiorValor(), 0.0001);
    }

    @Test
    public void deveRetornarMaiorValorSeCarrinhoContemMuitosElementos() {

        this.carrinho.adiciona(new Item("Geladeira", 1, 900.0));
        this.carrinho.adiciona(new Item("Fogão", 1, 1500.0));
        this.carrinho.adiciona(new Item("Máquina de Lavar", 1, 750.0));
        assertEquals(1500.0, carrinho.maiorValor(), 0.0001);
    }

}
