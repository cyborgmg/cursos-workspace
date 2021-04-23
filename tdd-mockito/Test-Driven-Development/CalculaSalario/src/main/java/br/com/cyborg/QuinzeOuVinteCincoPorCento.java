package br.com.cyborg;

public class QuinzeOuVinteCincoPorCento implements RegraDeCalculo {
    public double calcula(Funcionario funcionario) {
        if(funcionario.getSalario() < 2500) {
            return funcionario.getSalario() * 0.85;
        }
        return funcionario.getSalario() * 0.75;
    }
}
