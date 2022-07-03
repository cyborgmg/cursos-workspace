package br.com.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import br.com.lambda.dto.Usuario;

public class Capitulo5 {

	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);
		
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
		

		/*
		Comparator<Usuario> comparator = new Comparator<Usuario>() {
			public int compare(Usuario u1, Usuario u2) {
				return u1.getNome().compareTo(u2.getNome());
			}
		};
		*/
		
		//Comparator<Usuario> comparator = (u1, u2) -> u1.getNome().compareTo(u2.getNome());
		
		//Collections.sort(usuarios, comparator);
		
		/*
		System.out.println("*************************Ascendente");
		Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		usuarios.forEach( u -> System.out.println(u.toString()) );
		
		System.out.println("*************************Descendente");
		Collections.sort(usuarios, (u1, u2) -> u2.getNome().compareTo(u1.getNome()));
		usuarios.forEach( u -> System.out.println(u.toString()) );
		*/
		
		/*
		usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));
		usuarios.forEach( u -> System.out.println(u.toString()) );
		*/
		
		//Comparator<Usuario> comparator = Comparator.comparing(u -> u.getNome());
		//usuarios.sort(comparator);
		
//		usuarios.sort(Comparator.comparing(u -> u.getNome()/*, Collections.reverseOrder() <- descendente */));
//		usuarios.forEach( u -> System.out.println(u.toString()) );
		
		/*
		List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");
		
		System.out.println("*************************Ascendente");
		palavras.sort(Comparator.naturalOrder());
		palavras.forEach(p->System.out.println(p));
		
		System.out.println("*************************Descendente");
		palavras.sort(Comparator.reverseOrder());
		palavras.forEach(p->System.out.println(p));
		*/
		
		/*
		Function<Usuario, String> extraiNome = u -> u.getNome();
		Comparator<Usuario> comparator = Comparator.comparing(extraiNome);
		usuarios.sort(comparator);
		usuarios.forEach( u -> System.out.println(u.toString()) );
		*/
		
		//Comparando log, double, int
		/*
		ToIntFunction<Usuario> extraiPontos = u -> u.getPontos();
		Comparator<Usuario> comparator = Comparator.comparingInt(extraiPontos);
		usuarios.sort(comparator);
		usuarios.forEach( u -> System.out.println(u.toString()) );
		*/
		usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));
		usuarios.forEach( u -> System.out.println(u.toString()) );
		
		
	}

}
