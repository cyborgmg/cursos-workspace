package br.com.lambda;

import br.com.lambda.dto.Customer;
import br.com.lambda.dto.Payment;
import br.com.lambda.dto.Product;
import br.com.lambda.dto.Subscription;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class Capitulo11 {

	public static void main(String[] args) {

		/*
		Um modelo de pagamentos com
		Java 8
		Vamos a mais um exemplo sucinto para praticar os conceitos e principais pontos da
		API vistos até aqui. Relembrando que você pode acessar todo o código-fonte do livro
		aqui:
		https://github.com/peas/java8
		Para fixar a nova API e sintaxe, por mais que você já domine o Java, é importante
		praticar. Se possível, recrie o código você mesmo, sem copiar e colar.
		11.1
		Uma loja de digital goodies
		Vender bens digitais é o dia a dia da nossa loja.
		Vendemos download de músicas, vídeos e imagens para serem utilizados em
		campanhas publicitárias. Cada um desses produtos possuem um nome, um preço e
		o caminho do arquivo:
		*/

		/*
		Utilizamos algumas das novidades do Java 8, como o java.time para armaze-
		nar a data e formatá-la.
		Sim, poderíamos ter modelado as classes de mil outras maneiras. Em vez de
		Path poderíamos ter URL se considerarmos que os arquivos estão no cloud. Po-
		deríamos ter deixado o relacionamento bidirecional, com o Customer possuindo
		também um List<Payment>. A escolha aqui é o suficiente para exercitarmos o
		nosso aprendizado e uma modelagem melhor fica sem dúvida a cargo do uso do
		TDD e de conhecer bem o domínio.
		Vamos popular um pouco a nossa base de testes, criando quatro clientes:
		*/

		Customer paulo = new Customer("Paulo Silveira");
		Customer rodrigo = new Customer("Rodrigo Turini");
		Customer guilherme = new Customer("Guilherme Silveira");
		Customer adriano = new Customer("Adriano Almeida");

		/*E fazemos o mesmo com os nossos produtos, criando seis deles:*/

		Product bach = new Product("Bach Completo",
				Paths.get("/music/bach.mp3"), new BigDecimal(100));
		Product poderosas = new Product("Poderosas Anita",
				Paths.get("/music/poderosas.mp3"), new BigDecimal(90));
		Product bandeira = new Product("Bandeira Brasil",
				Paths.get("/images/brasil.jpg"), new BigDecimal(50));
		Product beauty = new Product("Beleza Americana",
				Paths.get("beauty.mov"), new BigDecimal(150));
		Product vingadores = new Product("Os Vingadores",
				Paths.get("/movies/vingadores.mov"), new BigDecimal(200));
		Product amelie = new Product("Amelie Poulain",
				Paths.get("/movies/amelie.mov"), new BigDecimal(100));

		/*
		Agora precisamos de alguns pagamentos, relacionando clientes com produtos.
		Repare que Paulo fez pagamentos duas vezes. Por uma questão de legibilidade, uti-
		lizamos o import estático de Arrays.asList :
		*/

		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);

		Payment payment1 =
				new Payment(Arrays.asList(bach, poderosas), today, paulo);
		Payment payment2 =
				new Payment(Arrays.asList(bach, bandeira, amelie), yesterday, rodrigo);
		Payment payment3 =
				new Payment(Arrays.asList(beauty, vingadores, bach), today, adriano);
		Payment payment4 =
				new Payment(Arrays.asList(bach, poderosas, amelie), lastMonth, guilherme);
		Payment payment5 =
				new Payment(Arrays.asList(beauty, amelie), yesterday, paulo);

		List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4, payment5);

		/*
		11.2
		Ordenando nossos pagamentos
		O primeiro desafio é fácil. Ordenar os pagamentos por data e imprimi-los, para
		que fique clara a nossa base de dados. Para isso, podemos encadear o sorted e o
		forEach do stream dessa coleção:
		*/

//		payments.stream()
//				.sorted(Comparator.comparing(Payment::getDate))
//				.forEach(System.out::println);

		/*
		11.3
		Reduzindo BigDecimal em somas
		Vamos calcular o valor total do pagamento payment1 utilizando a API de Stream
		e lambdas. Há um problema. Se preço fosse um int , poderíamos usar o
		mapToDouble e invocar o sum do DoubleStream resultante. Não é o caso. Tere-
		mos um Stream<BigDecimal> e ele não possui um sum .
		Nesse caso precisaremos fazer a redução na mão, realizando a soma de
		BigDecimal . Podemos usar o (total, price) -> total.add(price) , mas
		fica ainda mais fácil usando um method reference:
		*/

//		payment1.getProducts().stream()
//				.map(Product::getPrice)
//				.reduce(BigDecimal::add)
//				//.reduce((total, price) -> total.add(price))
//				.ifPresent(System.out::println);

		/*
		O ifPresent é do Optional<BigDecimal> retornado pelo reduce . Se
		invocarmos o reduce que recebe o argumento de inicialização, teríamos como re-
		torno um BigDecimal diretamente:
		*/

//		BigDecimal total =
//				payment1.getProducts().stream()
//						.map(Product::getPrice)
//						.reduce(BigDecimal.ZERO, BigDecimal::add);
//		System.out.println(total);

		/*
		Uma simples operação como a soma de BigDecimal apresenta várias soluções.
		Na maioria das vezes, essas soluções são equivalentes, pois a estratégia de execução
		do pipeline consegue ser bastante eficiente para evitar qualquer operação repetida.
		Certamente poderíamos ter um método getTotalAmount em Product , caso
		julgássemos necessário.
		E se precisarmos somar todos os valores de todos os pagamentos da lista
		payments ? Novamente nos deparamos com diversas opções. Podemos usar o
		mesmo código anterior, usando o map de payments :
		*/

//		Stream<BigDecimal> pricesStream =
//				payments.stream()
//						.map(p -> p.getProducts().stream()
//								.map(Product::getPrice)
//								.reduce(BigDecimal.ZERO, BigDecimal::add)
//						);
//		pricesStream.forEach(System.out::println);

		/*
		Repare que o código dentro do primeiro map é o mesmo que o do código que
		usamos para calcular a soma dos valores do payment1 . Com esse map , temos como
		resultado um Stream<BigDecimal> . Precisamos repetir a operação de reduce
		para somar esses valores intermediários. Isto é, realizamos a soma de preços dos
		produtos de cada pagamento, agora vamos somar cada um desses subtotais:
		*/

//		BigDecimal total =
//				payments.stream()
//						.map(p -> p.getProducts().stream()
//								.map(Product::getPrice)
//								.reduce(BigDecimal.ZERO, BigDecimal::add)
//						)
//						.reduce(BigDecimal.ZERO, BigDecimal::add);
//		System.out.println(total);

		/*
		O código está um pouco repetitivo. Em vez de realizarmos operações de soma em
		momentos distintos, podemos criar um único Stream<BigDecimal> com os va-
		lores de todos os produtos de todos pagamentos. Conseguimos esse java.util.stream.Stream usando
		o flatMap :
		*/

//		Stream<BigDecimal> priceOfEachProduct =
//				payments.stream()
//						.flatMap(p -> p.getProducts().stream().map(Product::getPrice));
//		priceOfEachProduct.forEach(System.out::println);

		/*
		Se está difícil ler este código, leia-o passo a passo. O importante é enxergar essa
		função:
		*/

//		Function<Payment, Stream<BigDecimal>> mapper = p -> p.getProducts().stream().map(Product::getPrice);
//		mapper.

		/*
		Essa função mapeia um Payment para o Stream que passeia por todos os seus
		produtos. E é por esse exato motivo que precisamos invocar depois o flatMap e
		não o map , caso contrário obteríamos um Stream<java.util.stream.Stream<BigDecimal>> .
		Para somar todo esse Stream<BigDecimal> , basta realizarmos a operação de
		reduce que conhecemos:
		*/

//		BigDecimal totalFlat =
//				payments.stream()
//						.flatMap(p -> p.getProducts().stream().map(Product::getPrice))
//						.reduce(BigDecimal.ZERO, BigDecimal::add);
//		System.out.println(totalFlat);

		/*
		11.4
		Produtos mais vendidos
		Queremos saber nossos campeões de vendas. Há, mais uma vez, diversas maneiras
		de realizar tal tarefa. Mapearemos nossos produtos para a quantidade que eles apa-
		recem. Para tal, criamos um Stream com todos os Product vendidos. Mais uma
		vez entra o flatMap :
		*/

//		Stream<Product> products = payments.stream()
//				.map(Payment::getProducts)
//				.flatMap(p -> p.stream());
//		products.forEach(System.out::println);

		/*
		Em vez de p -> p.stream() , há a possibilidade de passar o lambda como
		method reference: List::stream :
		*/

//		Stream<Product> products = payments.stream()
//				.map(Payment::getProducts)
//				.flatMap(List::stream);
//		products.forEach(System.out::println);

		/*
		Sempre podemos juntar dois maps (independente de um deles ser flat ) em
		um único map :
		*/

//		Stream<Product> products = payments.stream()
//				.flatMap(p -> p.getProducts().stream());
//		products.forEach(System.out::println);

		/*
		E também não há diferença de performance significativa. Fica a seu cargo utilizar
		o que considerar mais legível. Como a API e o Java 8 são muito recentes, boas prá-
		ticas ainda não surgiram para dizer qual das duas abordagens é mais adequada para
		facilitar a manutenção. Pessoalmente acreditamos que as duas são suficientemente
		claras.
		Precisamos gerar um Map de Product para Long . Esse Long indica quan-
		tas vezes o produto foi vendido. Usaremos o groupingBy , agrupando todos esses
		produtos pelo próprio produto, mapeando-o pela sua contagem:
		*/

//		Map<Product, Long> topProducts = payments.stream()
//				.flatMap(p -> p.getProducts().stream())
//				.collect(
//						Collectors.groupingBy(
//								Function.identity(),
//								Collectors.counting()
//						)
//				);
//
//		topProducts.forEach((p,c)->{
//			System.out.println(p);
//			System.out.println(c);
//		});

		/*
		Pode ser ruim ler o resultado do System.out.println , pois o toString
		do nosso Map gerará uma linha bem comprida. Podemos pegar o entrySet desse
		mapa e imprimir linha a linha:
		*/

//		Map<Product, Long> topProducts = payments.stream()
//				.flatMap(p -> p.getProducts().stream())
//				.collect(
//						Collectors.groupingBy(
//								Function.identity(),
//								Collectors.counting()
//						)
//				);
//		topProducts.entrySet().stream()
//				.forEach(System.out::println);

		/*
		Certamente poderíamos ter encadeado essa chamada ao entrySet logo após
		nosso collect , porém não teríamos mais acesso ao Map .
		Nosso resultado é parecido com o seguinte:

		Beleza Americana=2
		Amelie Poulain=3
		Bandeira Brasil=1
		Bach Completo=4
		Poderosas Anita=2
		Os Vingadores=1
		*/

		/*
		Pelo visto, Bach ainda é o mais popular. Mas como pegar apenas essa entrada do
		mapa? Basta pedirmos a maior entrada do mapa considerando um Comparator
		que compare o value de cada entrada. Vale lembrar que ela é representada pela
		interface interna Map.Entry :
		*/

//		Map<Product, Long> topProducts = payments.stream()
//				.flatMap(p -> p.getProducts().stream())
//				.collect(
//						Collectors.groupingBy(
//								Function.identity(),
//								Collectors.counting()
//						)
//				);
//
//		topProducts.entrySet().stream()
//				.max(Comparator.comparing(Map.Entry::getValue))
//				.ifPresent(System.out::println);


		/*
		11.5
		Valores gerados por produto
		Calculamos a quantidade de vendas por produtos. E a soma do valor por produto?
		O processo é muito parecido. Em vez de agruparmos com o valor de
		Collectors.counting , queremos fazer algo como Collectors.summing . Há
		diversos métodos como esse em Collectors , porém todos trabalham com tipos
		primitivos. Para realizar a soma em BigDecimal teremos de deixar o reduce
		explícito:
		*/

//		Map<Product, BigDecimal> totalValuePerProduct = payments.stream()
//				.flatMap(p -> p.getProducts().stream())
//				.collect(
//						Collectors.groupingBy(
//							Function.identity(),
//							Collectors.reducing(BigDecimal.ZERO, Product::getPrice,BigDecimal::add)
//						)
//				);
//		totalValuePerProduct.forEach((p,sv)->{
//			System.out.println(p);
//			System.out.println(sv);
//		});

		/*
		Podemos usar a mesma estratégia do stream().forEach(System.out::println)
		para mostrar o resultado, mas vamos aproveitar e ordenar a saída por valor:
		*/

//		Map<Product, BigDecimal> totalValuePerProduct = payments.stream()
//				.flatMap(p -> p.getProducts().stream())
//				.collect(
//						Collectors.groupingBy(
//							Function.identity(),
//							Collectors.reducing(BigDecimal.ZERO, Product::getPrice,BigDecimal::add)
//						)
//				);
//		totalValuePerProduct.entrySet().stream()
//				.sorted(Comparator.comparing(Map.Entry::getValue))
//				.forEach(System.out::println);

		/*
		11.6 Quais são os produtos de cada cliente?
		Em um primeiro momento podemos ter para cada Custumoer,
		Sua List<Payment>, bastando agrupar os paymetes com
		groupingBy(Payment::getCustomer):
		*/

//		Map<Customer, List<Payment>> customerToPayments =
//				payments.stream()
//						.collect(Collectors.groupingBy(Payment::getCustomer));
//
//		customerToPayments.forEach((c,lp)->{
//			System.out.println(c);
//			lp.forEach(System.out::println);
//		});

		/*
		Não estamos interessados nos payments de um Customer , e sim nas listas de
		Product dentro de cada um desses Payments .
		Uma implementação inocente vai gerar uma List<List<Product>> dentro
		do valor do Map :
		*/

//		Map<Customer, List<List<Product>>> customerToProductsList =
//						payments.stream()
//								.collect(
//										Collectors.groupingBy(
//												Payment::getCustomer,
//												Collectors.mapping(
//														Payment::getProducts,
//														Collectors.toList()
//												)
//										)
//								);
//		customerToProductsList.entrySet().stream()
//				.sorted(Comparator.comparing(e -> e.getKey().getName()))
//				.forEach(System.out::println);

		/*
		Queremos esse mesmo resultado, porém com as listas achatadas em uma só.
		Há duas formas. Sim, uma envolve o flatMap do mapa resultante. Dado o
		customerToProductsList , queremos que o value de cada entry seja acha-
		tado. Fazemos:
		*/

//		Map<Customer, List<List<Product>>> customerToProductsList =
//						payments.stream()
//								.collect(
//										Collectors.groupingBy(
//												Payment::getCustomer,
//												Collectors.mapping(
//														Payment::getProducts,
//														Collectors.toList()
//												)
//										)
//								);
//		Map<Customer, List<Product>> customerToProducts2steps =
//				customerToProductsList.entrySet().stream()
//						.collect(Collectors.toMap(Map.Entry::getKey,
//								e -> e.getValue().stream()
//										.flatMap(List::stream)
//										.collect(Collectors.toList())));
//		customerToProducts2steps.entrySet().stream()
//				.sorted(Comparator.comparing(e -> e.getKey().getName()))
//				.forEach(System.out::println);

		/*
		Usamos o Collectors.toMap para criar um novo mapa no qual a chave
		continua a mesma ( Map.EntrygetKey) mas o valor é o resultado do
		flatMap dos Liststream de todas as listas.
		Podemos verificar o conteúdo desse mapa, obtendo o mesmo resultado anterior,
		porém sem as listas aninhadas:
		 */

		/*
		Poderíamos ter feito tudo com uma única chamada. Creio que nesse caso estou-
		ramos o limite da legibilidade do uso dessa API. Apenas para efeitos didáticos, veja
		como ficaria:
		*/

//		Map<Customer, List<Product>> customerToProducts1step = payments.stream()
//				.collect(
//						Collectors.groupingBy(
//								Payment::getCustomer,
//								Collectors.mapping(
//										Payment::getProducts,
//										Collectors.toList()
//								)
//						)
//				)
//				.entrySet().stream()
//				.collect(
//						Collectors.toMap(
//								Map.Entry::getKey,
//								e -> e.getValue().stream().flatMap(List::stream).collect(Collectors.toList())
//						)
//				);
//
//		customerToProducts1step.forEach((c,lp)->{
//			System.out.println(c);
//			lp.forEach(System.out::println);
//		});


		/*
		Difícil de seguir a sequência. Certamente quebrar em vários passos é o mais
		indicado.
		Como sempre, há outras formas de resolver o mesmo problema. Podemos usar o
		reducing mais uma vez, pois queremos acumular as listas de cada cliente agrupado.
		*/

//		Map<Customer, List<Product>> customerToProducts = payments.stream()
//				.collect(
//						Collectors.groupingBy(
//								Payment::getCustomer,
//								Collectors.reducing(
//										Collections.emptyList(),
//										Payment::getProducts,
//										(l1, l2) -> {
//											List<Product> l = new ArrayList<>();
//											l.addAll(l1);
//											l.addAll(l2);
//											return l;
//										}
//								)
//						)
//				);
//		customerToProducts.forEach((c,lp)->{
//			System.out.println(c);
//			lp.forEach(System.out::println);
//		});


		/*
		Tivemos de escrever algo muito parecido com o que o Collectors.toList
		devolve. Infelizmente não há um método auxiliar que una duas Collections , im-
		pedindo a simplificação do terceiro argumento, que é um BinaryOperator. Cri-
		amos um coletor que pega todos os Payment::getProducts e vai acumulando
		todo o resultando em uma nova ArrayList.
		O resultado é exatamente o mesmo que com o flatMap .
		*/

		/*
		11.7
		Qual é nosso cliente mais especial?
		Qual seria a estratégia para obter o desejado Map<Customer, BigDecimal> ?
		Será a mesma que a da redução anterior, apenas mudando a operação. Começare-
		mos com BigDecimal.ZERO e, para cada Payment , faremos BigDecimal::add
		da soma dos preços de seus produtos. Por esse motivo uma redução ainda aparece
		dentro do reducing !
		*/

//		Map<Customer, BigDecimal> totalValuePerCustomer = payments.stream()
//									.collect(
//											Collectors.groupingBy(
//													Payment::getCustomer,
//													Collectors.reducing(
//															BigDecimal.ZERO,
//															p -> p.getProducts().stream().map(Product::getPrice)
//																	.reduce(
//																		BigDecimal.ZERO, BigDecimal::add),
//																		BigDecimal::add
//																	)
//											)
//									);
//		totalValuePerCustomer.forEach((c,tt)->{
//			System.out.println(c);
//			System.out.println(tt);
//		});


		/*
		O código está no mínimo muito acumulado. Cremos já termos passado do li-
		mite da legibilidade. Vamos quebrar essa redução, criando uma variável temporária
		responsável por mapear um Payment para a soma de todos os preços de seus produtos:
		*/

//		Function<Payment, BigDecimal> paymentToTotal = p -> p.getProducts().stream()
//						.map(Product::getPrice)
//						.reduce(BigDecimal.ZERO, BigDecimal::add);
//
//		Map<Customer, BigDecimal> totalValuePerCustomer = payments.stream()
//				.collect(
//						Collectors.groupingBy(
//								Payment::getCustomer,
//								Collectors.reducing(BigDecimal.ZERO,paymentToTotal,BigDecimal::add)
//						)
//				);
//
//		totalValuePerCustomer.forEach((c,tt)->{
//			System.out.println(c);
//			System.out.println(tt);
//		});

		/*
		Novamente surge um forte indício de que deveria haver um método
		getTotalAmount em Payment , que calculasse todo esse valor.
		Nesse caso, poderíamos fazer um simples
		reducing(BigDecimal.ZERO,
		PaymentgetTotalAmount, BigDecimaladd) .
		Ao mesmo tempo, este
		está sendo um excelente exercício de manipulação de coleções e relacionamentos.
		Já podemos exibir o conteúdo desse mapa:
		*/

//		Function<Payment, BigDecimal> paymentToTotal = p -> p.getProducts().stream()
//						.map(Product::getPrice)
//						.reduce(BigDecimal.ZERO, BigDecimal::add);
//
//		Map<Customer, BigDecimal> totalValuePerCustomer = payments.stream()
//				.collect(
//						Collectors.groupingBy(
//								Payment::getCustomer,
//								Collectors.reducing(BigDecimal.ZERO,paymentToTotal,BigDecimal::add)
//						)
//				);
//
//		totalValuePerCustomer.entrySet().stream()
//				.sorted(Comparator.comparing(Map.Entry::getValue))
//				.forEach(System.out::println);

		/*
		11.8 Relatórios com datas
		É muito simples separarmos os pagamentos por data, usando um groupingBy(Payment::getDate)
		Há um perigo: o LocalDateTime
		vai agrupar os pagamentos até pelos milissegundos. Não é o que queremos.
		Podemos agrupar por LocalDate , usando um groupingBy(p ->
		p.getDate().toLocalDate()) , ou em um intervalo ainda maior, como por ano
		e mês. Para isso usamos o YearMonth :
		*/

//		Map<YearMonth, List<Payment>> paymentsPerMonth = payments.stream()
//										.collect(
//												Collectors.groupingBy(
//														p -> YearMonth.from(p.getDate())
//												)
//										);
//
//		paymentsPerMonth.entrySet().stream()
//				.forEach(System.out::println);


		/*
		E se quisermos saber, também por mês, quanto foi faturado na loja? Basta agru-
		par com o mesmo critério e usar a redução que conhecemos: somando todos os
		preços de todos os produtos de todos pagamentos.
		*/

//		Map<YearMonth, BigDecimal> paymentsValuePerMonth = payments.stream()
//				.collect(
//						Collectors.groupingBy(
//								p -> YearMonth.from(p.getDate()),
//								Collectors.reducing(
//										BigDecimal.ZERO,
//										p -> p.getProducts().stream()
//											.map(Product::getPrice)
//											.reduce(BigDecimal.ZERO,BigDecimal::add),
//										BigDecimal::add
//								)
//						)
//				);
//		paymentsValuePerMonth.forEach((y,f)->{
//			System.out.println(y);
//			System.out.println(f);
//		});

		/*
		Considere também os respectivos getters de cada atributo.
		Teremos três usuários com assinaturas de 99.90 . Dois deles encerraram suas
		assinaturas:
		*/

		BigDecimal monthlyFee = new BigDecimal("99.90");
		Subscription s1 = new Subscription(monthlyFee,
				yesterday.minusMonths(5), paulo);
		Subscription s2 = new Subscription(monthlyFee,
				yesterday.minusMonths(8), today.minusMonths(1), rodrigo);
		Subscription s3 = new Subscription(monthlyFee,
				yesterday.minusMonths(5), today.minusMonths(2), adriano);
		List<Subscription> subscriptions = Arrays.asList(s1, s2, s3);


		/*
		Como calcular quantos meses foram pagos através daquela assinatura? Basta
		usar o que conhecemos da API de java.time . Mas depende do caso. Se a assina-
		tura ainda estiver ativa, calculamos o intervalo de tempo entre begin e a data de hoje:
		*/

//		long meses = ChronoUnit.MONTHS
//				.between(s1.getBegin(), LocalDateTime.now());
//		System.out.printf("%s meses",meses);

		/*
		E se a assinatura terminou? Em vez de enchermos nosso código com ifs , tira-
		mos proveito do Optional :
		*/

//		long meses = ChronoUnit.MONTHS
//				.between(s1.getBegin(), s1.getEnd().orElse(LocalDateTime.now()));
//		System.out.printf("%s meses",meses);

		/*
		Para calcular o valor gerado por aquela assinatura, basta multiplicar esse número
		de meses pelo custo mensal:
		*/

//		BigDecimal total = s1.getMonthlyFee()
//				.multiply(
//						new BigDecimal(
//								ChronoUnit.MONTHS.between(
//										s1.getBegin(),
//										s1.getEnd().orElse(LocalDateTime.now())
//								)
//						)
//				);
//		System.out.printf("total %s",total);

		/*Dada uma lista de subscriptions , fica fácil somar todo o total pago:*/

//		BigDecimal totalPaid = subscriptions.stream()
//				.map(Subscription::getTotalPaid)
//				.reduce(BigDecimal.ZERO, BigDecimal::add);
//		System.out.printf("total %s",totalPaid);


	}

}
