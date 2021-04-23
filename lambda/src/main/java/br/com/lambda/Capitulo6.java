package br.com.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import br.com.lambda.dto.Usuario;
import br.com.lambda.interfaces.funcional.TriFunction;

//Method References
public class Capitulo6 {
	
	public void callFunction(Supplier sp) {
		
		System.out.println("**************BLOCO INI");
		
		sp.get();
		
		System.out.println("**************BLOCO FIM");
	}
	

	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);
		
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
		
		//usuarios.forEach(Usuario::tornaModerador);
		
		//usuarios.sort(Comparator.comparing(Usuario::getNome));
		
		//Function<Usuario, String> byName = Usuario::getNome;
		//usuarios.sort(Comparator.comparing(byName));
		
		//usuarios.sort(Comparator.comparingInt(Usuario::getPontos));
		
		//Comparator<Usuario> c = Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome);
		//usuarios.sort(c);
		
		//usuarios.sort(Comparator.comparingInt(Usuario::getPontos).thenComparing(Usuario::getNome));
		
		//usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());
		//usuarios.forEach(System.out::println);
		
		
		//Usuario rodrigo = new Usuario("Rodrigo Turini", 50);
		
		//Runnable bloco = rodrigo::tornaModerador;
		//bloco.run();
		
		/*
		Consumer<Usuario> consumer = Usuario::tornaModerador;
		System.out.println(rodrigo);
		consumer.accept(rodrigo);
		System.out.println(rodrigo);
		*/
		
		//Referenciando construtores
		
		
		
		
		//Supplier<Usuario> criadorDeUsuarios = Usuario::new;
		//Usuario u = criadorDeUsuarios.get();
		
		
		//Function<String, Usuario> criadorDeUsuarios = Usuario::new;
		//Usuario u = criadorDeUsuarios.apply("Rodrigo Turini");
		
		
		//BiFunction<String, Integer, Usuario> criadorDeUsuarios =Usuario::new;
		//Usuario u = criadorDeUsuarios.apply("Rodrigo Turini", 50);
		
		TriFunction<String, Integer, Boolean, Usuario> criadorDeUsuarios =Usuario::new;
		Usuario u = criadorDeUsuarios.apply("Rodrigo Turini", 50, true);
		
		//Passando um methodo como parametro
		Supplier func = ()-> { System.out.println(u.toString()); return 0; };
		(new Capitulo6()).callFunction(func);

	}

}
