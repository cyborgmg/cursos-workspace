package br.com.lambda;

import br.com.lambda.dto.Grupo;
import br.com.lambda.dto.Usuario;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class Capitulo8 {

    static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch(IOException e) {throw new UncheckedIOException(e);
        }
    }


    public static void main(String[] args) throws IOException {

        List<Usuario> usuarios = new ArrayList<Usuario>();
        for (int i = 0; i < 100; i++) {
            usuarios.add(new Usuario("nome"+i, 90+i, i%2==0));
        }

/*        Dada uma List<Usuario> usuarios , sabemos como podemos ordená-la por
        nome:*/
//       usuarios.sort(Comparator.comparing(Usuario::getNome));
//       usuarios.forEach(System.out::println);

/*        E um stream? Imagine que queremos filtrar os usuários com mais de 100 pontos
        e aí ordená-los:*/
//        usuarios.stream().filter(u -> u.getPontos() > 100).sorted(Comparator.comparing(Usuario::getNome)).forEach(System.out::println);

/*        No stream, o método de ordernação é o sorted . A diferença entre ordenar
        uma lista com sort e um stream com sorted você já deve imaginar: um mé-
                todo invocado em Stream não altera quem o gerou. No caso, ele não altera a
        List<Usuario> usuarios . Se quisermos o resultado em uma List , precisa-
                mos usar um coletor, como visto:*/

//        List<Usuario> filtradosOrdenados = usuarios.stream()
//                .filter(u -> u.getPontos() > 100)
//                .sorted(Comparator.comparing(Usuario::getNome))
//                .collect(Collectors.toList());
//
//        filtradosOrdenados.forEach(System.out::println);

        /*
        Quando manipulamos um Stream , normalmente encadeamos diversas operações
        computacionais. Esse conjunto de operações realizado em um Stream é conhecido
        como pipeline. O Java pode tirar proveito dessa estrutura para otimizar as operações.
        Como ele faz isso? Evitando executar as operações o máximo possível: grande parte
        delas são lazy e executam realmente só quando necessário para obter o resultado
        final.

        Um exemplo? Pense no código anterior, antes do collect :
        usuarios.stream()
        .filter(u -> u.getPontos() > 100)
        .sorted(Comparator.comparing(Usuario::getNome));
        Os métodos filter e sorted devolvem um Stream . No momento dessas
        invocações, esses métodos nem filtram, nem ordenam: eles apenas devolvem novos
        streams em que essa informação é marcada. Esses métodos são chamados de ope-
        rações intermediárias. Os novos streams retornados sabem que devem ser filtrados
        e ordenados (ou o equivalente) no momento em que uma operação terminal for
        invocada.
        O collect é um exemplo de operação terminal e só nesse momento o stream
        realmente vai começar a executar o pipeline de operações pedido.

        */

        /*8.3
        Qual é a vantagem dos métodos serem lazy?
        Imagine que queremos encontrar um usuário com mais de 100 pontos. Basta um e
        serve qualquer um, desde que cumpra o predicado de ter mais de 100 pontos.
                Podemos filtrar o stream, coletá-lo em uma lista e pegar o primeiro elemento:
        Usuario maisDe100 = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .collect(Collectors.toList())
                .get(0);
        É muito trabalho para algo simples: aqui filtramos todos os usuários e criamos
        uma nova coleção com todos eles apenas para pegar o primeiro elemento. Além
        disso, no caso de não haver nenhum usuário com mais de 100 pontos, receberemos
        uma exception.
        O Stream possui o método findAny que devolve qualquer um dos elementos:*/

//        Optional<Usuario> usuarioOptional = usuarios.stream().filter(u -> u.getPontos() > 100).findAny();
//        System.out.println(usuarioOptional.get());

        /*
        8.4
        Enxergando a execução do pipeline com peek
        Podemos pedir para que o stream execute um tarefa toda vez que processar um ele-
        mento. Fazemos isso através do peek :
        */

//        usuarios.stream().filter(u -> u.getPontos() > 100).peek(System.out::println).findAny();

        /*

        Podemos ver que só serão mostrados os elementos até que seja encontrado algum
        elemento que cumpra o predicado u.getPontos() > 100 . Assim, fica claro o
        poder que o lazyness tem na API de Stream .
                Bem diferente de um forEach , que devolve void e é uma operação terminal,
        o peek devolve um novo Stream e é uma operação intermediária. Ele não forçará
        a execução do pipeline. O seguinte código simplesmente não imprime nada:
        */

//        usuarios.stream().filter(u -> u.getPontos() > 100).peek(System.out::println);

        /*para imprimir*/
//        usuarios.stream().filter(u -> u.getPontos() > 100).peek(System.out::println).collect(Collectors.toList());

        /*
        Por quê? Pois o peek devolve um novo Stream , onde está marcado para im-
                primir todos os elementos processados. Ele só vai processar elementos quando en-
                contrar uma operação terminal, como o findAny , o collect ou o forEach .
        Com o peek , podemos ver se outras operações conseguem tirar vantagem do
            lazyness. Experimente fazer o mesmo truque com o sorted :

        Aqui o peek imprime todos os usuários, mesmo se só queremos fazer findAny .
                Dizemos que o sorted é um método intermediário stateful. Operações stateful
        podem precisar processar todo o stream mesmo que sua operação terminal não de-
                mande isso.
        */

//        usuarios.stream().sorted(Comparator.comparing(Usuario::getNome)).peek(System.out::println).findAny();

        /*
        8.5 Operações de redução
        Operações que utilizam os elementos da stream para retornar um valor final são
        frequentemente chamadas de operações de redução (reduction). Um exemplo é o
        average , que já havíamos visto:
        */
//        double pontuacaoMedia = usuarios.stream().mapToInt(Usuario::getPontos).average().getAsDouble();
//        System.out.println(pontuacaoMedia);

        /*
        Há outros métodos úteis como o average : o count , o min , o max e o sum .
        Esse último, como o average , encontra-se apenas nos streams primitivos. O min
        e o max pedem um Comparator como argumento. Todos, com exceção do sum e
        count , trabalham com Optional . Por exemplo:
        */

//        Optional<Usuario> max = usuarios.stream().max(Comparator.comparing(Usuario::getPontos));
//        Usuario maximaPontuacao = max.get();
//        System.out.println(maximaPontuacao);


        /*Se desejarmos somar todos os pontos dos usuários, fazemos:
        Essa soma é executada através de uma operação de redução que podemos deixar
        bem explícita. Como ela funciona? Ela pega o primeiro elemento, que é a pontuação
        do primeiro usuário do stream, e guarda o valor acumulado até então, com uma
        operação de soma. Também precisamos ter um valor inicial que, para o caso da
        soma, é zero.
        */

//        int total = usuarios.stream().mapToInt(Usuario::getPontos).sum();
//        System.out.println(total);

        /*
        Podemos quebrar essa operação de soma para enxergar melhor o que é uma ope-ração
        de redução. Repare nas definições:
        int valorInicial = 0;
        IntBinaryOperator operacao = (a, b) -> a + b;
        IntBinaryOperator é uma interface funcional que define o método
                applyAsInt , que recebe dois inteiros e devolve um inteiro. A soma é assim, não é
        mesmo?
                Com essas definições, podemos pedir para que o stream processe a redução,
        passo a passo:
        */

//        int valorInicial = 0;
//        IntBinaryOperator operacao = (a, b) -> a + b;
//        int total = usuarios.stream()
//                .mapToInt(Usuario::getPontos)
//                .reduce(valorInicial, operacao);
//        System.out.println(total);

        /*
        Pronto. Temos um código equivalente ao sum . Poderíamos ter escrito tudo mais
        sucintamente, sem a declaração de variáveis locais:
        */

//        int total = usuarios.stream()
//                .mapToInt(Usuario::getPontos)
//                .reduce(0, (a, b) -> a + b);
//        System.out.println(total);

        /*
        Podemos ir além. Na classe Integer , há agora o método estático
        Integer.sum , que soma dois inteiros. Em vez do lambda, podemos usar um
        method reference:
        */

//        int total = usuarios.stream()
//                .mapToInt(Usuario::getPontos)
//                .reduce(0, Integer::sum);
//        System.out.println(total);

        /*
        Qual é a vantagem de usarmos a redução em vez do sum ? Nenhuma. O impor-
                tante é conhecê-lo para poder realizar operações que não se encontram no Stream .
        Por exemplo? Multiplicar todos os pontos:
        */

//        double multiplicacao = usuarios.stream()
//                .mapToDouble(Usuario::getPontos)
//                .reduce(1, (a,b) -> a * b);
//        System.out.println(multiplicacao);

        /*
        Há também alguns casos especiais em que invocar o map pode ser custoso, e o
        melhor seria fazer a operação de soma diretamente. Esse não é o nosso caso, mas só
        para enxergarmos o exemplo, a soma sem o map ficaria assim:
        */

//        int total = usuarios.stream()
//                .reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);
//        System.out.println(total);

        /*
        8.6
        Conhecendo mais métodos do Stream
        São muitas as funcionalidades do Stream . O livro não tem intenção de cobrir todos
        os métodos da API, mas podemos passar rapidamente por alguns dos mais interes-
                santes, além dos que já vimos por aqui.
        Além disso, conheceremos mais alguns detalhes sobre o bom uso dos streams.
                Trabalhando com iterators
        Vimos que podemos coletar o resultado de um pipeline de operações de um
        Stream em uma coleção com o collect . Algumas vezes nem precisamos de uma
        coleção: bastaria iterarmos pelos elementos de um Stream .
                O que será que acontece se tentarmos percorrer o Stream da maneira antiga?
        for (Usuario u : usuarios.stream()){
            //...
        }
        Ocorre um erro de compilação. O enhanced for espera ou uma array ou um
        Iterable .
                Mas por que decidiram fazer com que Stream não seja um Iterable ? Pois
        as diversas operações terminais de um Stream o marcam como já utilizado. Sua
        segunda invocação lança um IllegalStateException . Vale lembrar que se você
        invocar duas vezes usuarios.stream() não haverá problema, pois abrimos dois
        streams diferentes a cada invocação!
                Porém, podemos percorrer os elementos de um Stream através de um
        Iterator . Para isso, podemos invocar o método iterator :

        */

//        Iterator<Usuario> i = usuarios.stream().iterator();
//        i.forEachRemaining(System.out::println);

        /*
        A interface Iterator já existe há bastante tempo no Java e define os métodos
                hasNext , next e remove . Com o Java8, também podemos percorrer um iterator
        utilizando o método forEachRemaining que recebe um Consumer como parâ-
                metro:
        */

//        usuarios.stream().iterator().forEachRemaining(System.out::println);

        /*
        Mas quando devemos utilizar um iterator de um Stream , se há um forEach
        tanto em Collection quanto no próprio Stream ?
                Um motivo para usar um Iterator é quando queremos modificar os objetos
        de um Stream . Quando utilizarmos streams paralelos, veremos que não devemos
        mudar o estado dos objetos que estão nele, correndo o risco de ter resultados não de-
                terminísticos. Outro motivo é a compatibilidade de APIs. Pode ser que você precise
        invocar um método que recebe Iterator .
        Testando Predicates
        Vimos bastante o uso do filter . Ele recebe um lambda como argumento, que
        é da interface Predicate . Há outras situações em que queremos testar predicados
        mas não precisamos da lista filtrada. Por exemplo, se quisermos saber se há algum
        elemento daquela lista de usuários que é moderador:
        */

//        boolean hasModerator = usuarios.stream().anyMatch(Usuario::isModerador);
//        System.out.println(hasModerator);

        /*
        sModerador() , gerando um predicado que testa se um usuário é mode-
                Aqui o
        rador e devolve um booleano. O processamento dessa operação vai parar assim que
        o stream encontrar algum usuário que é moderador.
        Assim como o anyMatch , podemos descobrir se todos os usuários são mode-
                radores com allMatch ou se nenhum deles é, com o noneMatch .
        Há muitos outros métodos e detalhes!
                Você pode utilizar o count para saber quantos elementos há no Stream , skip
        para pular os n próximos elementos e limit para cortar o número de elementos.
        Também há formas de você criar um Stream sem a existência de uma coleção.
        Na própria interface Stream há os métodos estáticos empty e of . O primeiro
        claramente cria um Stream vazio, e o of depende do que você passar como ar-
                gumento, como por exemplo Stream.of(user1, user2, user3) retorna um
        Stream<Usuario> . Você também pode concatená-los com Stream.concat .
                Fora da própria interface também podemos produzir Stream s. Por exemplo,
        você pode usar a API de regex para devolver um Stream<String> através do
            Pattern.splitAsStream , ou ainda pegar um Stream das linhas de um arquivo
        com o Files.lines . Se estiver trabalhando diretamente com um array, pode usar
        o Arrays.stream .
                Não deixe de conhecer a API e investigar bastante essa importante interface sem-
                pre que tiver novas ideias e sentir falta de algum método: ele provavelmente existe.
                Outro ponto relevante: como alguns Streams podem ser originados de recur-
                sos de IO, ele implementa a interface AutoCloseable e possui o close . Um
        exemplo é usar os novos métodos da classe java.nio.file.Files , incluída no
        Java 7 e com novidades no Java 8, para gerar um Stream . Nesse caso, é fundamen-
                tal tratar as exceções e o finally , ou então usar o try with resources. Em outras
        situações, quando sabemos que estamos lidando com streams gerados por coleções,
                essa preocupação não é necessária.
         */

        /*
        8.7
        Streams primitivos e infinitos
        Assim como vimos com o comparingInt , que devolve um Comparator que não
        tem a necessidade de fazer o unboxing, vimos também o caso do mapToInt , que
        devolve um IntStream , também para evitar operações desnecessárias.
        O IntStream , o LongStream e o DoubleStream possuem operações espe-
                ciais e que são importantes. Até mesmo o iterator deles devolvem Iterators
        diferentes. No caso do IntStream , é o PrimitiveIterator.OfInt , que im-
                plementa Iterator<Integer> mas que, além de um next que devolve um
        Integer fazendo o boxing, também possui o nextInt .
                Tem ainda métodos de factory, como IntStream.range(inicio, fim) .
                Streams infinitos
        Um outro recurso poderoso do Java 8: através da interface de factory Supplier ,
                podemos definir um Stream infinito, bastando dizer qual é a regra para a criação
                de objetos pertencentes a esse Stream .
        Por exemplo, se quisermos gerar uma lista “infinita” de números aleatórios, po-
                demos fazer assim:
        */

//        Random random = new Random(0);
//        Supplier<Integer> supplier = () -> random.nextInt();
//        Stream<Integer> stream = Stream.generate(supplier);
//        stream.forEach(System.out::println);

        /*
        O Stream gerado por generate é lazy. Certamente ele não vai gerar infinitos
        números aleatórios. Eles só serão gerados à medida que forem necessários.
                Aqui estamos gerando o boxing o tempo todo. Podemos usar o IntSupplier
        e o IntStream . Além disso, removeremos a variável temporária supplier :
        */

//        Random random = new Random(0);
//        IntStream stream = IntStream.generate(() -> random.nextInt());
//        stream.forEach(System.out::println);

        /*
        Agora precisamos de cuidado. Qualquer operação que necessite passar por todos
        os elementos do Stream nunca terminará de executar. Por exemplo:
        int valor = stream.sum();
        Você pode apenas utilizar operações de curto-circuito em Streams infinitos.
        */

        /*
        Operações de curto circuito
        São operações que não precisam processar todos os elementos. Um exemplo
        seria pegar apenas os 100 primeiros elementos com limit :
        */

//        Random random = new Random(0);
//        IntStream stream = IntStream.generate(() -> random.nextInt());
//        List<Integer> list = stream
//                .limit(100)
//                .boxed()
//                .collect(Collectors.toList());
//
//        list.forEach(System.out::println);

        /*
        Repare a invocação de boxed . Ele retorna um Stream<Integer> em vez
        do IntStream , possibilitando a invocação a collect da forma que já vimos.
                Sem isso, teríamos apenas a opção de fazer IntStream.toArray , ou então de
        chamar o collect que recebe três argumentos, mas não teríamos onde guardar
        os números. Não foi criado no Java um IntList que seria o análogo primitivo
        a List<Integer> , e também não entraram no Java 8 os tais dos value objects ou
        value types, que possibilitariam algo como List<int> .
        Vamos rever o mesmo código com a interface fluente:
        */

//        Random random = new Random(0);
//        List<Integer> list = IntStream.generate(() -> random.nextInt())
//                .limit(100)
//                .boxed()
//                .collect(Collectors.toList());
//
//        list.forEach(System.out::println);


        /*
        O Supplier passado ao generate pode servir para gerar um Stream
        infinito de constantes, por exemplo IntStream.generate(() -> 1) e
        Stream.generate(() -> new Object()) .
                Pode ser útil para um Supplier manter estado. Nesse caso, precisamos usar
        uma classe ou classe anônima, pois dentro de um lambda não podemos declarar
        atributos. Vamos gerar a sequência infinita de números de Fibonacci de maneira
        lazy e imprimir seus 10 primeiros elementos:
        */

//        IntStream.generate(new Fibonacci())
//                .limit(10)
//                .forEach(System.out::println);

        /*
        Veremos que manter o estado em uma interface funcional pode limitar os recur-
                sos de paralelização que um Stream fornece.
        Além do limit , há outras operações que são de curto-circuito. O findFirst
        é uma delas. Mas não queremos pegar o primeiro elemento Fibonacci. Quero pegar
        o primeiro elemento maior que 100! Como fazer? Podemos filtrar antes de invocar
        o findFirst :
        */

//        int maiorQue100 = IntStream
//                .generate(new Fibonacci())
//                .filter(f -> f > 100)
//                .findFirst()
//                .getAsInt();
//        System.out.println(maiorQue100);

        /*
        O filter não é de curto-circuito: ele não produz um Stream finito dado
        um Stream infinito. Basta apenas que você tenha uma operação de curto-circuito
        no pipeline (seja a operação intermediária ou final), que você terá chances de que
        a execução do pipeline não tome tempo infinito. Por que digo chances? Pois, por
        exemplo, se não houvesse um elemento de Fibonacci maior que 100, isso também
        rodaria indefinidamente.
        Os matchers também são de curto-circuito. Podemos tentar descobrir se todos os
        elementos de Fibonacci são pares com allMatch(f -> f % 2 ==0) . Se houver
        algum impar, ele retornará falso. Mas se houvesse apenas pares, ele rodaria indefini-
                damente! Lembre-se: trabalhar com Streams infinitos pode ser perigoso, mesmo
        que você utilize operações de curto-circuito.
                Quando for necessário manter o estado de apenas uma variável, podemos usar
        o iterate em vez do generate , que recebe um UnaryOperator . Para gerar os
        números naturais:
        */

//        IntStream.iterate(0, x -> x + 1)
//                .limit(10)
//                .forEach(System.out::println);

        /*
        Há modificações também na API antiga para trabalhar com Streams infinitos.
                Você pode ver que a classe java.util.Random já devolve Stream s infinitos,
        através de métodos como Random.ints() .
        */

        /*
        8.8
        Praticando
                o
        java.nio.file.Files
        Capítulo 8. Mais operações com Streams
        que
                aprendemos
        com
        A classe java.nio.file.Files entrou no Java 7 para facilitar a manipulação de
        arquivos e diretórios, trabalhando com a interface Path . É uma das classes que
        agora possuem métodos para trabalhar com Stream . Excelente oportunidade para
        praticarmos boa parte do que aprendemos.
        Se quisermos listar todos os arquivos de um diretório, basta pegar o
        Stream<Path> e depois um forEach :
        */

//        Files.list(Paths.get("./src/main/java/br/com/lambda")).forEach(System.out::println);

        /*Quer apenas os arquivos java ? Pode usar um filter :*/

//        Files.list(Paths.get("./src/main/java/br/com/lambda"))
//                .filter(p -> p.toString().endsWith(".java"))
//                .forEach(System.out::println);

        /*
        E se quisermos todo o conteúdo dos arquivos?
        Files.lines para ler todas as linhas de cada arquivo.
                Vamos tentar usar o
        */

//        Files.list(Paths.get("./src/main/java/br/com/lambda"))
//                .filter(p -> p.toString().endsWith(".java"))
//                .map(p -> {
//                    try {
//                        return Files.lines(p);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                })
//                .forEach(System.out::println);

        /*
        Infelizmente esse código não compila. O problema é que Files.lines lança
        IOException . Mesmo que o método que invoca o map lance essa exception,
                não compilará, pois nesse caso é a implementação do lambda que estará lançando
        IOException . O map recebe uma Function , que tem o método apply e que
        não lança exception alguma na assinatura.
        Uma solução seria escrever uma classe anônima ou um lambda definido com as
        chaves e com try/catch por dentro. Outra seria fazer um método estático simples,
                que faz o wrap da chamada para evitar a checked exception:
        static Stream<String> lines(Path p) {
            try {
                return Files.lines(p);
            } catch(IOException e) {throw new UncheckedIOException(e);
            }
        }

        Em vez de invocarmos map(p -> Files.lines(p)) , invocamos o nosso
        próprio lines , que não lança checked exception:
        */

//        Files.list(Paths.get("./src/main/java/br/com/lambda"))
//                .filter(p -> p.toString().endsWith(".java"))
//                .map(p -> lines(p))
//                .forEach(System.out::println);

        /*
        O problema é que, com esse map , teremos um Stream<Stream<String>> ,
        pois a invocação de lines(p) devolve um Stream<String> para cada Path do
            nosso Stream<Path> original! Isso fica mais claro de observar se não usarmos o
        forEach e atribuirmos o resultado a uma variável:
        */

//        Stream<Stream<String>> strings =
//                Files.list(Paths.get("./src/main/java/br/com/lambda"))
//                        .filter(p -> p.toString().endsWith(".java"))
//                        .map(p -> lines(p));
//
//        strings.forEach(f -> f.forEach(System.out::println));

        /*
        8.9
        FlatMap
        Podemos achatar um Stream de Streams com o flatMap . Basta trocar a invo-
                cação, que teremos no final um Stream<String> :
        */

//        Stream<String> strings =
//                Files.list(Paths.get("./src/main/java/br/com/lambda"))
//                        .filter(p -> p.toString().endsWith(".java"))
//                        .flatMap(p -> lines(p));
//        strings.forEach(System.out::println);

        /*
        Isso pode ser encadeado em vários níveis. Para cada String podemos invo-
                car String.chars() e obter um IntStream (definiram assim para evitar o bo-
                xing para Stream<Character> ). Se fizermos map(s -> s.chars()) , obtere-
                mos um indesejado Stream<IntStream> . Precisamos passar esse lambda para o
        flatMaptoInt :
        */

//        IntStream chars =
//                Files.list(Paths.get("./src/main/java/br/com/lambda"))
//                        .filter(p -> p.toString().endsWith(".java"))
//                        .flatMap(p -> lines(p))
//                        .flatMapToInt((String s) -> s.chars());
//
//        chars.forEach(System.out::println);

        /*
        O IntStream resultante possui todos os caracteres de todos os arquivos java
        do nosso diretório.
        */


        /*
        Mais um exemplo de flatMap
        Quando trabalhamos com coleções de coleções, usamos o flatMap quando
        queremos que o resultado da nossa transformação seja reduzido a um Stream ‘sim-
                ples’, sem composição.
        Imagine que temos grupos de usuários:
        */

        /*E que tenhamos alguns grupos, separando quem fala inglês e quem fala espanhol:*/

        Grupo englishSpeakers = new Grupo();
        englishSpeakers.add(usuarios.get(0));
        englishSpeakers.add(usuarios.get(1));

        Grupo spanishSpeakers = new Grupo();
        spanishSpeakers.add(usuarios.get(1));
        spanishSpeakers.add(usuarios.get(2));

        /*Se temos esses grupos dentro de uma coleção:*/

        List<Grupo> groups = Arrays.asList(englishSpeakers, spanishSpeakers);

        /*
        Pode ser que queiramos todos os usuários desses grupos. Se fizermos um sim-
                ples groups.stream().map(g -> g.getUsuarios().stream()) , teremos
        um Stream<Stream<Usuario>> , que não desejamos. O flatMap vai desem-
                brulhar esses Streams , achatando-os.
        */

//        groups.stream()
//        .flatMap(g -> g.getUsuarios().stream())
//        .distinct()
//        .forEach(System.out::println);

        /*
        Temos como resultado todos os usuários de ambos os grupos, sem repetição.
                Se tivéssemos coletado o resultado do pipeline em um Set , não precisaríamos do
            distinct .
                    Um outro exemplo de uso de flatMap ? Se nossos Usuarios pos-
                suíssem
        List<Pedido> pedidos , chamar o
        usuarios.map(u ->
                u.getPedidos()) geraria um Stream<List<Pedido>> . Se você tentar
                fazer
        usuarios.map(u -> u.getPedidos().stream()) , vai cair no
        Stream<Stream<Pedido>> . A resposta para obter um Stream<Pedido> com
        os pedidos de todos os usuários da nossa lista é fazer usuarios.flatMap(u ->
                u.getPedidos().stream()) .
        */

    }

}
