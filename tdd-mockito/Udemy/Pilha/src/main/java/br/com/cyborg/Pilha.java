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
public class Pilha {

    private int limit = 5;

    private List<Livro> pilha = new ArrayList<>();

    private PilhaDao pilhaDao;


    public Pilha(PilhaDao pilhaDao) {
        this.pilhaDao = pilhaDao;
    }


    public void push(Livro livro){

        if(pilha.size()>=limit){
            return;
        }

        if( !livro.getTitulo().startsWith("A") && !livro.getTitulo().startsWith("O") ){
            return;
        }

        pilha.add(livro);

        pilhaDao.save(this);
    }

    public Livro pop(){
        return pilha.get(pilha.size()==0?0:(pilha.size()-1));
    }

    public int count(){
        return pilha.size();
    }

}
