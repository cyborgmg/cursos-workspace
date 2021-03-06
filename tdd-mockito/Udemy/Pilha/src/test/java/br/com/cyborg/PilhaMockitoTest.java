package br.com.cyborg;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitPlatform.class)
class PilhaMockitoTest {

    Pilha pilha;

    @Mock
    PilhaDao pilhaDao;

    @BeforeEach
    private void beforeEach(){
        MockitoAnnotations.initMocks(this);
        pilha = new Pilha(pilhaDao);
    }

    @AfterEach
    public void afterEach(){
        pilha.setPilha(new ArrayList<>());
    }

    @Test
    void pop() {

        Livro livro1 = new Livro("A fortalesa");
        pilha.push(livro1);

        Livro livro2 = new Livro("A emboscada");
        pilha.push(livro2);

        Livro livro3 = new Livro("O naufrago");
        pilha.push(livro3);

        assertEquals(pilha.pop().getTitulo(),"O naufrago" );

    }

    @Test
    void push() {

        Livro livro1 = new Livro("A fortaleza");
        Livro livro2 = new Livro("A emboscada");
        Livro livro3 = new Livro("O palhaço");
        Livro livro4 = new Livro("A Terra");
        Livro livro5 = new Livro("O Sol");
        Livro livro6 = new Livro("A Lua");

        pilha.push(livro1);
        pilha.push(livro2);
        pilha.push(livro3);
        pilha.push(livro4);
        pilha.push(livro5);
        pilha.push(livro6);

        assertEquals(pilha.pop().getTitulo(),"O Sol" );

    }

    @Test
    void nao_adciona_livro_fora_padrao_nome() {

        Livro livro1 = new Livro("A fortaleza");
        Livro livro2 = new Livro("Home de ferro");

        pilha.push(livro1);
        pilha.push(livro2);

        assertEquals(pilha.pop().getTitulo(),"A fortaleza" );

    }

}