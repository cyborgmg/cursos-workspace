package br.com.lambda;

import br.com.lambda.dto.Usuario;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public class Capitulo9 {

	static Stream<String> lines(Path p) {
		try {
			return Files.lines(p);
		} catch(IOException e) {throw new UncheckedIOException(e);
		}
	}


	public static void main(String[] args) throws IOException {
  		
//
//		List<Usuario> usuarios = new ArrayList<Usuario>();
//		for (int i = 0; i < 100; i++) {
//			usuarios.add(new Usuario("nome"+i, 90+i, i%2==0));
//		}

		/*
		Mapeando, particionando,
				agrupando e paralelizando
		Podemos realizar tarefas mais complexas e interessantes com o uso de streams e
		coletores. Vamos ver códigos mais elaborados e necessidades que aparecem com
		frequência.
		9.1
		Coletores gerando mapas
		Vimos como gerar um Stream com todas as linhas dos arquivos de determinado
		diretório:
		*/

//		Stream<String> strings =
//				Files.list(Paths.get("./src/main/java/br/com/lambda"))
//						.filter(p -> p.toString().endsWith(".java"))
//						.flatMap(p -> lines(p));
//		strings.forEach(System.out::println);

		/*
		Poderíamos ter um Stream com a quantidade de linhas de cada arquivo. Para
		isso, em vez de fazer um flatMap para as linhas, fazemos um map para a quantidade
		de linhas, usando o count do Stream :
		*/

//		LongStream lines =
//				Files.list(Paths.get("./src/main/java/br/com/lambda"))
//						.filter(p -> p.toString().endsWith(".java"))
//						.mapToLong(p -> lines(p).count());
//
//		lines.forEach(System.out::println);

		/*
		Se quisermos uma List<Long> com os valores desse LongStream , fazemos
		um collect como já conhecemos.
		*/

//		List<Long> lines =
//		Files.list(Paths.get("./src/main/java/br/com/lambda"))
//				.filter(p -> p.toString().endsWith(".java"))
//				.map(p -> lines(p).count())
//				.collect(Collectors.toList());
//		lines.forEach(System.out::println);

		/*
		Um detalhe: repare que poderíamos usar o mapToLong e depois invocar o
		boxed para fazer o collect , mas usamos o map , que já retorna Stream<Long>
		em vez de LongStream .
		De qualquer maneira, o resultado não parece muito útil: um monte de longs .
		O que precisamos com mais frequência é saber quantas linhas tem cada arquivo, por
		exemplo. Podemos fazer um forEach e popular um Map<Path, Long> , no qual
		a chave é o arquivo e o valor é a quantidade de linhas daquele arquivo:
		*/

//		java.util.Map<Path, Long> linesPerFile = new HashMap<>();
//
//		Files.list(Paths.get("./src/main/java/br/com/lambda"))
//				.filter(p -> p.toString().endsWith(".java"))
//				.forEach(p -> linesPerFile.put(p, lines(p).count()));
//
//		linesPerFile.forEach((p,l)->{
//			System.out.println(p);
//			System.out.println(l);
//
//		});

		/*
		Essa abordagem está correta e já é muito mais concisa e expressiva do que se ti-
				véssemos usado BufferedReaders e loops para criar esse Map . Ao mesmo tempo,
				essa solução não é muito funcional: o lambda passado para o forEach utiliza uma
		variável declarada fora do seu escopo, mudando seu estado, o que chamamos de
		efeito colateral. Isso diminui a possibilidade de otimizações, em especial para a exe-
				cução em paralelo.
		Podemos criar esse mesmo mapa com um outro coletor mais específico para esse
		tipo de tarefa, o toMap :
		*/

//		java.util.Map<Path, Long> lines =
//				Files.list(Paths.get("./src/main/java/br/com/lambda"))
//						.filter(p -> p.toString().endsWith(".java"))
//						.collect(
//								Collectors.toMap(
//									p -> p,
//									p -> lines(p).count()
//								)
//						);
//
//		lines.forEach((p,l)->{
//			System.out.println(p);
//			System.out.println(l);
//
//		});

		/*
		O toMap recebe duas Functions. A primeira produzirá a chave (no nosso
				caso o próprio Path ) e a segunda produzirá o valor (a quantidade de linhas). Como
		é comum precisarmos de um lambda que retorna o próprio argumento (o nosso p
				-> p ), podemos utilizar Function.identity() para deixar mais claro.
		Se quisermos gerar um mapa de cada arquivo para toda a lista de linhas con-
				tidas nos arquivos, podemos utilizar um outro coletor e gerar um Map<Path,
				List<String>> :
		*/

//		java.util.Map<Path, List<String>> content =
//				Files.list(Paths.get("./src/main/java/br/com/lambda"))
//						.filter(p -> p.toString().endsWith(".java"))
//						.collect(
//								Collectors.toMap(
//									Function.identity(),
//									p -> lines(p).collect(Collectors.toList())
//								)
//						);
//
//		content.forEach((p,l)->{
//			System.out.println(p);
//			l.forEach(System.out::println);
//
//		});

		/*
		Certamente, o toMap vai aparecer bastante no seu código. São muitos os casos
		em que queremos gerar mapas temporários para processar dados e gerar estatísticas
		e relatórios.
		Mapear todos os usuários utilizando seu nome como chave fica fácil:
		*/

//		Map<String, Usuario> nameToUser = usuarios
//				.stream()
//				.collect(
//						Collectors.toMap(
//							Usuario::getNome,
//							Function.identity()
//						)
//				);
//		nameToUser.forEach((n,u)->{
//			System.out.println(n);
//			System.out.println(u);
//		});

		/*
		Se o Usuario fosse uma entidade JPA, poderíamos utilizar
		toMap(Usuario::getId, Function.identity) para gerar um Map<Long,
				Usuario> , no qual a chave é o id da entidade.
		*/

		/*
		9.2
		o
		groupingBy e partitioningBy
		Há muitos coletores já prontos que sabem gerar mapas importantes. Para os nossos
		exemplos ficarem mais interessantes, vamos popular nossa lista de usuários com mais
		objetos:
		*/

		Usuario user1 =new Usuario("Paulo Silveira", 150, true);
		Usuario user2 =new Usuario("Rodrigo Turini", 120, true);
		Usuario user3 =new Usuario("Guilherme Silveira", 90);
		Usuario user4 =new Usuario("Sergio Lopes", 120);
		Usuario user5 =new Usuario("Adriano Almeida", 100);

		List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);

		/*
		Considere que o boolean passado para a sobrecarga do construtor de Usuario
		é para definir se ele é um moderador ou não.
		Queremos um mapa em que a chave seja a pontuação do usuário e o valor seja
		uma lista de usuários que possuem aquela pontuação. Isto é, um Map<Integer,
						List<Usuario>>.
				Para fazer isso de maneira tradicional, precisamos passar por todos os usuários
		e ver se já existe uma lista para aquela pontuação. Caso não exista, criamos uma
		ArrayList. Se existe, adicionamos o usuário a lista. O código fica da seguinte
		forma:
		*/

//		Map<Integer, List<Usuario>> pontuacao = new HashMap<>();
//		for(Usuario u: usuarios) {
//			if(!pontuacao.containsKey(u.getPontos())) {
//				pontuacao.put(u.getPontos(), new ArrayList<>());
//			}
//			pontuacao.get(u.getPontos()).add(u);
//		}
//		pontuacao.forEach((p, lu)->{
//			System.out.println(p);
//			lu.forEach(System.out::println);
//		});

		/*
		No Java 8 poderíamos diminuir um pouco esse código com a ajuda de novos
		métodos default do Map :
		*/

//		Map<Integer, List<Usuario>> pontuacao = new HashMap<>();
//		for(Usuario u: usuarios) {
//			pontuacao
//					.computeIfAbsent(u.getPontos(), user -> new ArrayList<>())
//					.add(u);
//		}
//		pontuacao.forEach((p, lu)->{
//			System.out.println(p);
//			lu.forEach(System.out::println);
//		});

		/*
		O método computeIfAbsent vai chamar a Function do lambda no caso de
		não encontrar um valor para a chave u.getPontos() e associar o resultado (a nova
		ArrayList ) a essa mesma chave. Isto é, essa invocação do computeIfAbsent faz
		o papel do if que fizemos no código anterior.
				Mas o que realmente queremos é trabalhar com Streams . Poderíamos es-
				crever um Collector ou trabalhar manualmente com o reduce , mas há um
		java.util.stream.Collector que faz exatamente esse trabalho:
		*/

//		Map<Integer, List<Usuario>> pontuacao = usuarios
//				.stream()
//				.collect(Collectors.groupingBy(Usuario::getPontos));
//
//		pontuacao.forEach((p, lu)->{
//			System.out.println(p);
//			lu.forEach(System.out::println);
//		});


		/*
		A saída é a mesma! O segredo é o Collectors.groupingBy , que é uma
		factory de Collector s que fazem agrupamentos.
				Podemos fazer mais. Podemos particionar todos os usuários entre moderadores
		e não moderadores, usando o partitionBy :
		*/

//		Map<Boolean, List<Usuario>> moderadores = usuarios
//				.stream()
//				.collect(Collectors.partitioningBy(Usuario::isModerador));
//		moderadores.forEach((m, lu)->{
//			System.out.println(m);
//			lu.forEach(System.out::println);
//		});


		/*
		O partitioningBy nada mais é do que uma versão mais eficiente para ser
		usada ao agrupar booleans .
		O
		partitioningBy(Usuario::isModerador) nos devolve um
		Map<Boolean, List<Usuario>>. E se quiséssemos uma lista com os no-
				mes dos usuários? Se fizermos stream().map(Usuario::getNome) não
		poderemos particionar por Usuario::isModerador , pois o map nos retornaria
		um Stream<String> .
		Tanto o partitioningBy quanto o groupingBy possuem uma sobrecarga
		que permite passar um Collector como argumento. Há um Collector que sabe
		coletar os objetos ao mesmo tempo que realiza uma transformação de map :
		Em vez de guardar os objetos dos usuários, poderíamos guardar uma lista com
		apenas o nome de cada usuário, usando o mapping para coletar esses nomes em
		uma lista:
		*/

//		Map<Boolean, List<String>> nomesPorTipo = usuarios
//				.stream()
//				.collect(
//						Collectors.partitioningBy(
//									Usuario::isModerador,
//									Collectors.mapping(Usuario::getNome,Collectors.toList())
//						)
//				);
//		nomesPorTipo.forEach((m, lu)->{
//			System.out.println(m);
//			lu.forEach(System.out::println);
//		});

		/*
		Vamos a mais um desafio. Queremos particionar por moderação, mas ter
		como valor não os usuários, mas sim a soma de seus pontos. Também existe um
		coletor para realizar essas somatórias, que pode ser usado em conjunto com o
		partitioningBy e groupingBy :
		*/

//		Map<Boolean, Integer> pontuacaoPorTipo = usuarios
//				.stream()
//				.collect(
//						Collectors.partitioningBy(
//								Usuario::isModerador,
//								Collectors.summingInt(Usuario::getPontos)));
//		pontuacaoPorTipo.forEach((m, p)->{
//			System.out.println(m);
//			System.out.println(p);
//		});

		/*
		Conhecer bem toda a factory Collectors certamente vai ajudar suas manipu-
				lações de coleções. Perceba que não usamos mais loops para processar os elementos.
		Até mesmo para concatenar todos os nomes dos usuários há um coletor:
		*/

//		String nomes = usuarios
//				.stream()
//				.map(Usuario::getNome)
//				.collect(Collectors.joining(", "));
//		System.out.println(nomes);


		/*
		9.3
		Casa do Código
		Executando o pipeline em paralelo
		Vamos voltar a um exemplo simples de uso dos streams. Filtrar os usuários com mais
		de 100 pontos, ordená-los e coletar o resultado em uma lista:
		*/


//		List<Usuario> filtradosOrdenados = usuarios.stream()
//				.filter(u -> u.getPontos() > 100)
//				.sorted(Comparator.comparing(Usuario::getNome))
//				.collect(Collectors.toList());
//
//		filtradosOrdenados.forEach(System.out::println);

		/*
		Tudo acontece na própria thread, como é esperado. Se tivermos uma lista com
		milhões de usuários, o processo poderá levar mais que alguns segundos.
				E se precisarmos paralelizar esse processo? Até seu smartphone possui 4 pro-
				cessadores. Escrever um código que use Thread para filtrar, ordenar e coletar dá
		bastante trabalho. Uma opção seria tirar proveito da API de Fork/Join. Apesar de já
		ser um pouco mais simples, ainda assim não é uma tarefa fácil.
		As collections oferecem uma implementação de Stream diferente, o stream pa-
				ralelo. Ao usar um stream paralelo, ele vai decidir quantas threads deve utilizar, como
		deve quebrar o processamento dos dados e qual será a forma de unir o resultado fi-
				nal em um só. Tudo isso sem você ter de configurar nada. Basta apenas invocar
		parallelStream em vez de Stream :
		*/

//		List<Usuario> filtradosOrdenados = usuarios.parallelStream()
//				.filter(u -> u.getPontos() > 100)
//				.sorted(Comparator.comparing(Usuario::getNome))
//				.collect(Collectors.toList());
//
//		filtradosOrdenados.forEach(System.out::println);

		/*
		Com uma coleção pequena, não podemos enxergar as perdas e ganhos com fa-
				cilidade. Vamos gerar uma quantidade grande de números, filtrá-los e ordená-los,
				para poder ter uma base de comparação.
		Para gerar os números de 1 a um bilhão, utilizaremos o LongStream.range .
				Usaremos o parallel e o filter para filtrar:
		*/

//		long sum = LongStream.range(0, 1_000_000_000)
//						.parallel()
//						.filter(x -> x % 2 == 0)
//						.sum();
//		System.out.println(sum);

		/*
		Em um computador com 2 cores, executamos o código em 1.276s de tempo
		realmente gasto. As configurações da máquina não importam muito, o que queremos
		é fazer a comparação.
		Removendo a invocação do parallel() , o tempo é significativamente maior:
		1.842s . Não é o dobro, pois paralelizar a execução de um pipeline sempre tem seu
		preço.
		Essa diferença favorece o parallel pois temos um input grande de dados. Se
		diminuirmos a sequência gerada de 1 bilhão para 1 milhão, fica claro o problema. O
		paralelo roda em 0.239s e o sequencial em 0.201 . Isso mesmo: a versão paralela
		é mais lenta por causa do overhead: quebrar o problema em várias partes, juntar os
		dados resultantes etc.
		São dois fatores que podem ajudar sua decisão: o tamanho do input dos dados
		e a velocidade de execução da operação. Soma de números é uma operação muito
		barata, por isso é necessário um input grande. Se a operação fosse mais lenta, envol-
		vesse operações blocantes, o tamanho necessário para valer a pena seria menor.
		Micro benchmarks são sempre perigosos e podem enganar com facilidade, ainda
		mais sem saber quais serão as operações realizadas. Seja cético e teste seus próprios
		casos.
		*/

		/*
		9.4
		Operações não determinísticas e ordered streams
		Há diversas outras considerações que devem ser feitas. Algumas operações no pipe-
		line são chamadas de não-determinísticas. Elas podem devolver diferentes resulta-
		dos quando utilizadas em streams paralelos. Os principais exemplos são o forEach
		e o findAny .
		Ao invocar esses dois métodos em um stream paralelo, você não tem garantia da
		ordem de execução. Isso melhora sua performance em paralelo. Caso necessite ga-
		rantir a ordem da execução, você deve utilizar o forEachOrdered e o findFirst
		respectivamente. Na maioria das vezes eles não são necessários.
		Mesmo métodos como map podem ter sua execução em paralelo melhoradas.
		Quando criamos um stream de uma List (ou do LongStream.range ), ele é
		criado de maneira ordered . Isto é, ao coletarmos a lista resultante de um map
		da função x -> x + 1 nos elementos 1, 2, 3 , no final teremos 2, 3, 4 . Se
		gerarmos um stream com o mesmo conteúdo, só que vindo de um HashSet , o
		resultado final pode ser 3, 4, 2 . Ao executar em paralelo o map , a fase de fazer
		o join dos resultados será bem mais simples por não necessitar garantir a mesma
		ordem de entrada!
		Você pode relaxar a restrição de ordem de um Stream ordered invocando
		seu método unordered
		Um outro obstáculo para a performance do Stream paralelo é o coletor de
		agrupamento. O Collectors.groupingBy garante a ordem de aparição dos
		elementos ao agrupá-los, o que pode ser custoso na fase de fazer join. Utilizar
		Collectors.groupingByConcurrent não garante essa ordem, utilizando um
		único mapa concorrente como ConcurrentHashMap , mas a performance final
		será melhor.
		E se tivéssemos efeitos colaterais?
		Os streams paralelos são incrivelmente poderosos, mas não há mágica: continu-
		amos a ter problemas se houver operações com efeitos colaterais em estado compar-
		tilhado.
		Imagine uma outra forma de somar de 1 a 1 bilhão. Em vez de usar o sum
		do LongStream , vamos criar atributo total para armazenar o resultado. Pelo
		forEach realizamos a soma nesse atributo em comum. Repare:
		*/

		AtomicLong total = new AtomicLong();

		LongStream.range(0, 1_000_000_000)
				.parallel()
				.filter(x -> x % 2 == 0)
				.forEach(n -> total.addAndGet(n));
		System.out.println(total.get());

		/*
		Se você possuir mais de um core, cada vez que você rodar esse código obterá
		provavelmente um resultado diferente! O uso concorrente de uma variável compar-
				tilhada possibilita o interleaving de operações de forma indesejada. Claro, você pode
		utilizar o synchronized dentro do bloco do lambda para resolver isso, mas per-
				dendo muita performance. Estado compartilhado entre threads continua sendo um
		problema.
				Para saber mais:Spliterators
		A base do trabalho de todo Stream paralelo é o Spliterator . Ele é como
		um Iterator , só que muitas vezes pode ser facilmente quebrado em spliterators
		menores, para que cada thread disponível consuma um pedaço do seu stream.
		A interface
		Iterable agora também define um método default
		spliterator() . Tudo que vimos de paralelização são abstrações que utili-
				zam Spliterators por debaixo dos panos, junto com a API de Fork/Join. Caso
		você vá criar uma operação complexa paralela, é esse o caminho que deve seguir
		*/

	}

}
