package br.com.cyborg;

public class CalculadoraDeSalario {
    public double calculaSalario(Funcionario funcionario) {
        return funcionario.getCargo().getRegra().calcula(funcionario);
    }
}
