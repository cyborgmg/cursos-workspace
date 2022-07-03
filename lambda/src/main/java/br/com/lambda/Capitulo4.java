package br.com.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import br.com.lambda.dto.Usuario;

public class Capitulo4 {
	
	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);
		/*
		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
		
		
		Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");
		
		Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());
		
		Consumer<Usuario> imprimePontos = u -> System.out.println(u.getPontos());
		
		usuarios.forEach(mostraMensagem.andThen(imprimeNome).andThen(imprimePontos));
		*/
		
		
		
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
		
		/*
		Predicate<Usuario> predicado = new Predicate<Usuario>() {
			public boolean test(Usuario u) {
				return u.getPontos() > 160;
			}
		};
		*/
		
		usuarios.forEach(u -> System.out.println(u.toString()));
		
		System.out.println("**************************Predicate");
		
		
		
		//usuarios.removeIf(predicado);
		
		usuarios.removeIf(u -> u.getPontos() > 160);
		
		usuarios.forEach(u -> System.out.println(u.toString()));
		
	}

}
