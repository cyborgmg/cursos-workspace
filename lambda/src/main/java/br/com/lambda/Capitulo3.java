package br.com.lambda;

import br.com.lambda.interfaces.funcional.Validador;

public class Capitulo3 {

	public static void main(String[] args) {
		
		/*
		Validador<String> validadorCEP = valor ->  valor.matches("[0-9]{5}-[0-9]{3}");
		
		System.out.println(validadorCEP.valida("12345-000"));
		*/
		
		/*
		Runnable o = () -> {
			System.out.println("O que sou eu? Que lambda?");
		};
		
		System.out.println(o);
		System.out.println(o.getClass());
		o.run();
		*/
		
		/*
		final int numero = 5;
		new Thread(() -> {
			System.out.println(numero);
		}).start();
		*/
		
		int numero = 5;
		new Thread(() -> {
			System.out.println(numero);
		}).start();
	}

}
