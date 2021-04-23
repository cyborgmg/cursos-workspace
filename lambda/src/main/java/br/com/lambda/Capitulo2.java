package br.com.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import br.com.lambda.consumer.Mostrador;
import br.com.lambda.dto.Usuario;

public class Capitulo2 {

	public static void main(String ... args) {
	
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		/*
		for(Usuario u : usuarios) {
			System.out.println(u.getNome());
		
		}
		*/
		
		/*
		Mostrador mostrador = new Mostrador();
		usuarios.forEach(mostrador);
		*/
		
		/*
		Consumer<Usuario> mostrador = new Consumer<Usuario>() {
			public void accept(Usuario u) {
				System.out.println(u.getNome());
			}
		};
		usuarios.forEach(mostrador);
		*/
		
		/*
		usuarios.forEach( new Consumer<Usuario>() {
								public void accept(Usuario u) {
									System.out.println(u.getNome());
								}
							}
			);
		*/
		
		/*
		Consumer<Usuario> mostrador = u -> {System.out.println(u.getNome());};
		usuarios.forEach(mostrador);
		*/
		
		//usuarios.forEach((u)-> System.out.println(u.getNome()));
		
		usuarios.forEach( u-> u.tornaModerador() );
		
	}

}
