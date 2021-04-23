package br.com.lambda;


import br.com.lambda.annotations.Role;
import br.com.lambda.controller.RelatorioController;
import br.com.lambda.dto.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Capitulo12 {

	private static void execute(Supplier<String> supplier) {
		System.out.println("1");
		System.out.println(supplier.get());
		System.out.println("2");
	}

	public static void main(String[] args) {

		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (int i = 0; i < 100; i++) {
			usuarios.add(new Usuario("nome"+i, 90+i, i%2==0));
		}

		/*
		Apêndice: mais Java 8 com
		reflection, JVM, APIs e limitações
		*/

		/*
		12.1
		Novos detalhes na linguagem
		Operador diamante melhorado
		O operador diamante (diamond operator) é aquele que evita digitarmos desnecessa-
		riamente em algumas situações óbvias. Por exemplo, em vez de fazer:
		List<Usuario> lista = new ArrayList<Usuario>();
		Podemos fazer, a partir do agora antigo Java 7:
		List<Usuario> lista = new ArrayList<>();
		Porém o recurso era bastante limitado: basicamente apenas podia ser usado
		junto com a declaração da variável referência. Considerando a existência de um re-
		positório que tem o método adiciona(List<Usuario>) , o código a seguir falha
		no Java 7:
		repositorio.adiciona(new ArrayList<>());
		Para compilar no Java 7, você precisaria fazer repositorio.adiciona(new
		ArrayList<Usuario>()) , pois o compilador não conseguia inferir o tipo decla-
		rado na assinatura do método, que neste caso é Usuario .
		Se você conhece a API de Collections, sabe que ela possui o método
		Collections.emptyList() . Podemos utilizá-lo neste exemplo para resolver o
		problema sem ter que explicitamente inferir o tipo Usuario , visto que o compila-
		dor não fará essa inferência quando utilizamos o operador diamante.
		Nosso código ficará assim:
		repositorio.adiciona(Collections.emptyList());
		Porém o seguinte erro será exibido ao tentar compilar esse código:
		The method adiciona(List<Usuario>) in the type Capitulo12 is not
		applicable for the arguments (List<Object>)
		Isso acontece pois o compilador inferiu o tipo genérico como sendo um Object .
		Para que esse código compile precisaríamos fazer:
		repositorio.adiciona(Collections.<Usuario>emptyList());
		Já no Java 8, os dois casos funcionam perfeitamente, dadas as melhorias na infe-
		rência.
		Agora eu posso escrever e compilar o seguinte código sem nenhuma preocupa-
		ção, afinal o compilador vai extrair essa informação genérica pelo parâmetro espe-
		rado no método.
		repositorio.adiciona(new ArrayList<>());
		repositorio.adiciona(Collections.emptyList());
		Você pode ler mais a respeito da proposta de melhorias na inferência dos tipos
		aqui:
		http://openjdk.java.net/jeps/101
		*/

		/*
		Situações de ambiguidade
		Ainda que a inferência dos tipos tenha sido bastante melhorada, existem situa-
		ções de ambiguidade em que precisaremos ajudar o compilador. Para entender esse
		problema de forma prática considere a seguinte expressão lambda:
		Supplier<String> supplier = () -> "retorna uma String";
		Como a interface funcional Supplier<T> tem apenas o método get que não
		recebe nenhum parâmetro e retorna o tipo genérico, a expressão () -> "retorna
		uma String" pode ter seu tipo inferido sem nenhum problema.
		Mas essa não é a única interface funcional que atende bem a essa expressão, afinal
		qualquer interface pode ter um método que retorna um tipo genérico. Nós mesmos
		podemos criar interfaces com uma estrutura parecida.
		Para esse caso nem será preciso criar um caso de ambiguidade, pois dentro da
		própria API do Java temos a interface PrivilegedAction<T> que possui essa
		estrutura, repare:
		public interface PrivilegedAction<T> {
			T run();
		}
		Sabendo disso, podemos declarar a mesma expressão () -> "retorna uma
		String" com esses dois tipos!
		Supplier<String> supplier = () -> "retorna uma String";
		PrivilegedAction<String> p = () -> "retorna uma String";
		Mas isso não será um problema para o compilador, claro, afinal essa expressão
		tem seu tipo bem determinado em sua declaração.
		Isso nem mesmo será um problema quando passarmos a mesma expressão como
		parâmetro para um método como esse:
		private void metodo(Supplier<String> supplier) {
			// faz alguma lógica e invoca supplier.get()
		}
		Já vimos que, ao executar o seguinte código, o compilador agora vai inferir o
		tipo da lambda de acordo com o valor esperado pelo método, que neste caso é um
		Supplier .
		metodo(() -> "retorna uma String");
		Mas o que acontece se esse método tiver uma sobrecarga, que recebe uma inter-
		face funcional com a mesma estrutura? Poderíamos ter uma opção desse método
		recebendo uma PrivilegedAction :
		private void metodo(Supplier<String> supplier) {
			// faz alguma lógica e invoca supplier.get()
		}
		private void metodo(PrivilegedAction<String> action) {
			// faz alguma lógica e invoca action.run()
		}
		Tudo bem, esse pode não ser um caso tão comum, mas é interessante saber que
		esse tipo de ambiguidade pode acontecer. Ao adicionar esse segundo método, o com-
		pilador não vai conseguir mais inferir o tipo dessa expressão:
		metodo(() -> "retorna uma String");
		Como esperado a mensagem de erro será:
		The method metodo(Supplier<String>) is ambiguous for the type Capitulo12
		Neste caso será necessário recorrer ao casting para ajudar nessa inferência. Algo
		como:
		metodo((Supplier<String>) () -> "retorna uma String");
		*/

		/*
		Conversões entre interfaces funcionais
		Outro detalhe interessante sobre a inferência de tipos é que não existe uma con-
		versão automática entre interfaces funcionais equivalentes. Por exemplo, considere
		que temos o método execute que recebe um Supplier<String> como parâ-
		metro e apenas exibe o retorno de seu método get :
		private void execute(Supplier<String> supplier) {
		System.out.println(supplier.get());
		}
		Um exemplo de seu uso seria algo como:
		 */

//		Supplier<String> supplier = () -> "executando um supplier";
//		execute(supplier);

		/*
		Mas e se eu definir o tipo dessa expressão como um
		PrivilegedAction<String> ? Para todos os efeitos vimos que o resultado
		final deverá ser o mesmo, porém, ainda que estas interfaces funcionais sejam
		equivalentes, não existirá uma conversão automática. O seguinte código não
		compila:
		PrivilegedAction<String> action = () -> "executando uma ação";
		execute(action);
		Isso não deve funcionar, afinal o método execute(Supplier<String>)
		não é aplicável para o argumento PrivilegedAction<String> . Para que
		essa conversão seja possível podemos utilizar method reference nessa instância de
		PrivilegedAction , assim estamos explicitamente indicando ao compilador que
		desejamos essa conversão:
		 */

//		PrivilegedAction<String> action = () -> "executando um PrivilegedAction";
//		execute(action::run);


		/*
		12.2
		Qual é o tipo de uma expressão Lambda?
		Toda expressão lambda tem e precisa ter um tipo. Como vimos rapidamente, o
		seguinte trecho de código não funciona:
		Object o = () -> {
		System.out.println("eu sou um runnable!");
		};
		new Thread(o).start();
		Pois Object não é uma interface funcional. Sempre precisamos ter um tipo
		que seja uma interface funcional, envolvido na atribuição. Por exemplo, quando
		definimos esse Runnable :
		 */

//		Runnable r = () -> {
//			System.out.println("eu sou um runnable!");
//		};
//		new Thread(r).start();

		/*
		Fica explicito que essa expressão lambda representa a interface funcional
		Runnable , pois este é o tipo que demos para sua variável r . Mas e quando fazemos
		sua declaração de forma direta, em um único statement?
		*/

//		new Thread(() -> {
//			System.out.println("eu sou um runnable?");
//		}).start();


		/*
		Já não fica assim tão claro, não é? Mas tudo bem, sabemos que o método cons-
		trutor da classe Thread espera receber um Runnable , e da mesma forma o com-
		pilador também sabe que deve traduzir esse código lambda para um Runnable .
		O compilador é o responsável por inferir qual o tipo de uma expressão lambda, e
		para conseguir fazer esse trabalho ele agora leva em consideração o contexto em que
		essa expressão foi aplicada, ou seja, infere o tipo de acordo com o tipo que é esperado
		pelo método ou construtor. Esse “tipo esperado” é conhecido como Target Type.
		Conhecer o contexto foi um passo muito importante para o compilador poder
		inferir o tipo de uma expressão lambda, pois como já vimos, uma mesma expressão
		pode ter tipos diferentes:
		Callable<String> c = () -> "retorna uma String";
		PrivilegedAction<String> p = () -> "retorna uma String";
		Na primeira linha desse código, a expressão () -> "retorna uma String"
		representa um Callable<String> , e na segunda linha essa mesma expressão
		representa a interface funcional PrivilegedAction<String> . Foi o Target
		Type quem ajudou o compilador a decidir qual tipo essa mesma expressão repre-
		sentava em cada momento.
		 */

		/*
		O mesmo vale para method references
		Essa situação também acontece com o recurso de method reference. A mesma
		referência pode representar tipos diferentes, contanto que sejam equivalentes:
		Callable<String> c = callable::call;
		PrivilegedAction<String> action = callable::call;
		Como vimos, estes dois tipos possuem estrutura bem parecida, por-
		tanto a referência
		callable::call pode ser representada como um
		PrivilegedAction<String> .
		A grande diferença é que quando utilizamos method reference a inferência de
		tipo é mais forte, afinal o tipo está explícito em sua declaração, diferente de quando
		estamos trabalhando com uma expressão lambda. Outro ponto é que, como já vimos,
		existe a conversão entre interfaces funcionais.
		*/

		/*
		12.3
		Limitações da inferência no lambda
		Talvez você vá se deparar com esses problemas bem mais pra frente, mas é impor-
		tante ter conhecimento de que eles existem: algumas vezes o compilador não conse-
		gue inferir os tipos com total facilidade, em especial quando encadeamos métodos
		que utilizam lambda.
		Veja que com method references já vimos que isso compila:
		*/

//	 	usuarios.sort(
//				Comparator.comparingInt(Usuario::getPontos)
//												.thenComparing(Usuario::getNome)
//		);

		/*Porém, usando os lambdas que parecem equivalentes, não compila:*/

//		usuarios.sort(Comparator.comparingInt(u -> u.getPontos())
//												.thenComparing(u -> u.getNome()));

		/*
		Se avisarmos que o u é um Usuario , o retorno do comparingInt fica mais
		óbvio para o compilador, e com o código a seguir conseguimos uma compilação sem
		erros:
		*/

//		usuarios.sort(Comparator.comparingInt((Usuario u) -> u.getPontos())
//												.thenComparing(u -> u.getNome()));

		/*
		Outra forma seria quebrar a declaração do Comparator , perdendo a interface
		fluente:
		*/

//		Comparator<Usuario> comparator = Comparator.comparing(u -> u.getPontos());
//		usuarios.sort(comparator.thenComparing(u -> u.getNome()));

		/*
		Você também poderia forçar o tipo genérico do método estático, fazendo
		Comparator.<Usuario>comparingInt , que é uma sintaxe pouco vista no dia
		a dia.
		Repare que tudo funcionou perfeitamente quando utilizamos o method refe-
		rence, já que com eles o compilador pode facilmente dizer quais são os tipos en-
		volvidos.
		O mesmo acontece para casos com encadeamentos de interfaces fluentes mais
		simples. Você pode fazer, como visto:
		*/

//		usuarios.sort(Comparator.comparingInt(Usuario::getPontos).reversed());

		/*Mas se usar o lambda em vez do method reference, não compila:*/

//		usuarios.sort(Comparator.comparingInt(u -> u.getPontos()).reversed());

		/*
		Precisaria explicitar os tipos genéricos ou o tipo no lambda. Ou ainda declarar o
		Comparator antes e só depois invocar o reversed . Como utilizamos com mais
		frequência o method reference nesses encadeamentos, esses serão casos raros.
		Essas limitações são muito bem detalhadas na nova versão da Java Language
		Specification:
		http://cr.openjdk.java.net/\char126dlsmith/jsr335-0.8.0/D.html
		 */

		/*
		Suporte a múltiplas anotações iguais
		Um detalhe que pode ser bastante util em nosso dia a dia é a capacidade de aplicar
		a mesma anotação repetidas vezes em um mesmo tipo. Essa possibilidade, conhecida
		como repeating annotations, foi adicionada na nova versão da linguagem!
		Considere que temos a anotação @Role para determinar o tipo de acesso per-
		mitido para uma classe:
		@Documented
		@Retention(RetentionPolicy.RUNTIME)
		@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
		public @interface Role {
		String value();
		}
		Para adicionar duas roles em uma mesma classe nas versões pré-Java 8 teríamos
		que modificar sua estrutura pra receber um array de Strings , ou qualquer outro
		tipo que represente uma regra de acesso de seu sistema.
		Ao tentar declarar duas vezes a anotação, para talvez melhorar um pouco a legi-
		bilidade, receberíamos o erro de compilação: Duplicate annotation @Role.
		@Role("presidente")
		@Role("diretor")
		public class RelatorioController {
		}
		Para tornar isso possível agora podemos marcar a nossa anotação com
		@Repeatable , passando como argumento uma outra anotação que servirá para
		armazenar as anotações repetidas. Neste caso:
		@Repeatable(Roles.class)
		@Documented
		@Retention(RetentionPolicy.RUNTIME)
		@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
		public @interface Role {
		String value();
		}
		Onde @Roles conterá um array da anotação que ela armazenará:
		@Documented
		@Retention(RetentionPolicy.RUNTIME)
		@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
		public @interface Roles {
		Role[] value();
		}
		Tudo pronto! Agora o seguinte código compila:
		@Role("presidente")
		@Role("diretor")
		public class RelatorioController {
		}
		Aplicamos as anotações repetidas em uma classe neste exemplo, porém o mesmo
		pode ser feito em qualquer lugar passível de utilizar uma anotação.
		Diversos métodos foram adicionados na API de reflection para recuperar essas
		anotações. Um exemplo é o método getAnnotationsByType que recebe a classe
		da anotação procurada como parâmetro:
		 */

		RelatorioController controller = new RelatorioController();
		Role[] annotationsByType = controller
				.getClass()
				.getAnnotationsByType(Role.class);
		Arrays.asList(annotationsByType)
				.forEach(a -> System.out.println(a.value()));


	}

}
