package br.com.cyborg;

public class PilhaDaoImpl implements PilhaDao {

    @Override
    public Pilha save(Pilha pilha) {
        System.out.println("salvando dao");
        return pilha;
    }

}
