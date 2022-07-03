package br.com.cyborg;

public class CreditoServiceImpl implements CreditoService {

    @Override
    public double getLimit(String cpf) {

        System.out.println("CreditoService.getLimit");

        return 1500;

    }

}
