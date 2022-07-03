package br.com.lambda.consumer;

import java.util.function.Consumer;

import br.com.lambda.dto.Usuario;

public class Mostrador implements Consumer<Usuario> {
	
	public void accept(Usuario u) {
		
		System.out.println(u.getNome());
	}
}
