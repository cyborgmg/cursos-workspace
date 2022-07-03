package br.com.lambda;

import br.com.lambda.dto.Usuario;

import java.util.ArrayList;
import java.util.List;



public class Capitulo7 {

	public static void main(String[] args) {
  		
			
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (int i = 0; i < 100; i++) {
			usuarios.add(new Usuario("nome"+i, 90+i, i%2==0));
		}
		
		/*
		 * Para filtrar os 10 usuários com mais pontos e torná-los moderadores, podemos
		 * agora fazer o seguinte código:
		 */
		
		/*
		usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());
		usuarios.subList(0,10).forEach(Usuario::tornaModerador);
		
		usuarios.forEach(u-> System.out.println(u.toString()) );
		*/
		
		/*
		 * Streams: tornando moderadores os usuários com mais de 100 pontos
		 */
		/*
		Stream<Usuario> stream = usuarios.stream().filter(u -> u.getPontos() > 100);
		stream.forEach(System.out::println);
		*/
		
		//usuarios.stream().filter(u -> u.getPontos() > 100).forEach(System.out::println);
		
		
		/*
		 * Como obter de volta uma Lista?
		 */
		
		//List<Usuario> maisQue100 = new ArrayList<>();
		
		/*
		usuarios.stream().filter(u -> u.getPontos() > 100).forEach(u -> maisQue100.add(u));
		maisQue100.forEach(System.out::println);
		*/
		/*
		usuarios.stream().filter(u -> u.getPontos() > 100).forEach(maisQue100::add);
		maisQue100.forEach(System.out::println);
		*/
		
		/*
		 * Collectors
		 */
		/*
		Supplier<ArrayList<Usuario>> supplier = ArrayList::new;
		BiConsumer<ArrayList<Usuario>, Usuario> accumulator =ArrayList::add;
		BiConsumer<ArrayList<Usuario>, ArrayList<Usuario>> combiner =ArrayList::addAll;
		List<Usuario> maisQue100 = usuarios.stream().filter(u -> u.getPontos() > 100).collect(supplier, accumulator, combiner);
		maisQue100.forEach(System.out::println);
		*/
		
		/*
		List<Usuario> maisQue100 = usuarios.stream().filter(u -> u.getPontos() > 100).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		maisQue100.forEach(System.out::println);
		*/
		
		//usuarios.stream().filter(u -> u.getPontos() > 100).collect(Collectors.toList()).forEach(System.out::println);
		
		//usuarios.stream().filter(u -> u.getPontos() > 100).collect(toList()).forEach(System.out::println);
		
		
		/*
		 * Avançado: por que não há um toList em Stream?
		 */
		
		/*
		Set<Usuario> maisQue100 = usuarios.stream().filter(u -> u.getPontos() > 100).collect(toSet());
		maisQue100.forEach(System.out::println);
		*/
		
		/*
		 * Liste apenas os pontos de todos os usuários com o map
		 */
		
		/*
		List<Integer> pontos = usuarios.stream().map(u -> u.getPontos()).collect(toList());
		pontos.forEach(System.out::println);
		*/
		/*
		List<Integer> pontos = usuarios.stream().map(Usuario::getPontos).collect(toList());
		pontos.forEach(System.out::println);
		*/
		
		/*
		 * IntStream e a família de Streams
		 */
		
		/*
		IntStream stream = usuarios.stream().mapToInt(Usuario::getPontos);
		stream.forEach(System.out::println);
		*/
		
		/*
		double pontuacaoMedia = usuarios.stream().mapToInt(Usuario::getPontos).average().getAsDouble();
		System.out.println(pontuacaoMedia);
		*/
		
		/*
		 * O Optional em java.util
		 */
		/*
		OptionalDouble media = usuarios.stream().mapToInt(Usuario::getPontos).average();
		System.out.println(media.getAsDouble());
		*/
		
		/*
		double pontuacaoMedia = usuarios.stream().mapToInt(Usuario::getPontos).average().orElse(0.0);
		System.out.println(pontuacaoMedia);
		*/
		
		/*
		double pontuacaoMedia = usuarios.stream().mapToInt(Usuario::getPontos).average().orElseThrow(IllegalStateException::new);
		System.out.println(pontuacaoMedia);
		*/
		
		/*
		Optional<Usuario> max = usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos));
		System.out.println(max.get());
		*/
		
		/*
		Optional<String> maxNome = usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos)).map(u -> u.getNome());
		System.out.println(maxNome.get());
		*/



	}

}
