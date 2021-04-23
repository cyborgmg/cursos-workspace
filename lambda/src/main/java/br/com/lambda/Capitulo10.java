package br.com.lambda;

import java.time.Duration;
import java.time.LocalDateTime;


public class Capitulo10 {

	public static void main(String[] args) {

		/*
		Chega de Calendar ! Nova API de datas

		Trabalhar com datas em Java sempre foi um tanto complicado. Existem muitas críti-
		cas às classes Date e Calendar : são mutáveis, possuem várias limitações, decisões
		de design estranhas, alguns bugs conhecidos e dificuldade de se trabalhar com elas.
		Uma grande introdução do Java 8 foi o pacote java.time , que nos trouxe uma
		nova API de datas!
		*/

		/*
		10.1
		A java.time vem do Joda Time
		Há muito tempo existe o desejo de uma API de datas melhor, baseada no projeto
		Joda- Time. Essa afirmação foi uma das motivações usadas na proposta dessa nova
		feature.
		O Joda-Time é uma poderosa biblioteca open source bastante conhecida e utili-
		zada no mercado, que trabalha com tempo, datas e cronologia:
		http://www.joda.org/joda-time/
		Baseada nesse projeto, a nova API de datas possui métodos mais intuitivos, seu
		código ficou muito mais interessante e fluente. Você pode conhecer mais da JSR 310,
		que criou essa API, aqui:
		https://jcp.org/en/jsr/detail?id=310.
		Ela foi especificada por Steven Colebourne e Michael Nascimento, dois nomes
		bem conhecidos na comunidade, sendo este último um brasileiro.
		*/

		/*
		10.2
		Trabalhando com datas de forma fluente
		Aplicar uma transformação em um Calendar é um processo muito verboso, como
		por exemplo para criar uma data com um mês a partir da data atual. Repare:
		java.util.Calendar mesQueVem = Calendar.getInstance();
		mesQueVem.add(Calendar.MONTH, 1);
		*/

//		Calendar mesQueVem = Calendar.getInstance();
//		mesQueVem.add(Calendar.MONTH, 1);
//		System.out.println(mesQueVem);

		/*
		Com a nova API de datas podemos fazer essa mesma operação de uma forma
		mais moderna, utilizando sua interface fluente:
		*/

//		LocalDate mesQueVem = LocalDate.now().plusMonths(1);
//		System.out.println(mesQueVem);

		/*
		Como a maior parte de seus métodos não possibilita o retorno de parâmetros
		nulos, podemos encadear suas chamadas de forma fluente sem a preocupação de
		receber um NullPointerException !
				Da mesma forma que fizemos para adicionar o mês, podemos utilizar os mé-
				todos plusDays , plusYears em diante de acordo com nossa necessidade. E de
		forma igualmente simples, conseguimos decrementar os valores utilizando os mé-
				todos minus presentes nesses novos modelos. Para subtrair um ano, por exemplo,
				faríamos:
		*/

//		LocalDate anoPassado = LocalDate.now().minusYears(1);
//		System.out.println(anoPassado);

		/*
		Um ponto importante é que a classe LocalDate representa uma data sem horá-
				rio nem timezone, por exemplo 25-01-2014 . Se as informações de horário forem
		importantes, usamos a classe LocalDateTime de forma bem parecida:
		*/

//		LocalDateTime agora = LocalDateTime.now();
//		System.out.println(agora);

		/*
		Um exemplo de saída desse código seria 2014-02-19 T17:49:19.587 .
		Há ainda o LocalTime para representar apenas as horas:
		*/

//		LocalTime agora = LocalTime.now();
//		System.out.println(agora);

		/*
		Neste caso, a saída seria algo como 17:49:19.587 .
		Uma outra forma de criar uma LocalDateTime com horário específico é uti-
		lizando o método atTime da classe LocalDate , repare:
		*/

//		java.time.LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12,0);
//		System.out.println(hojeAoMeioDia);

		/*
		Assim como fizemos com esse método atTime , sempre podemos utilizar os
		método at para combinar os diferentes modelos. Observe como fica simples unir
		uma classe LocalDate com um LocalTime :
		*/

//		java.time.LocalTime agora = LocalTime.now();
//		LocalDate hoje = LocalDate.now();
//		LocalDateTime dataEhora = hoje.atTime(agora);
//		System.out.println(dataEhora);


		/*
		Também podemos, a partir desse LocalDateTime , chamar o método atZone
		para construir um ZonedDateTime , que é o modelo utilizado para representar uma
		data com hora e timezone.
		*/

//		java.time.LocalTime agora = LocalTime.now();
//		LocalDate hoje = LocalDate.now();
//		LocalDateTime dataEhora = hoje.atTime(agora);
//		ZonedDateTime dataComHoraETimezone = dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));
//		System.out.println(dataComHoraETimezone);

		/*
		Em alguns momentos é importante trabalhar com o timezone, mas o ideal é
		utilizá-lo apenas quando realmente for necessário. A própria documentação pede
		cuidado com o uso dessa informação, pois muitas vezes não será necessário e usá-la
		pode causar problemas, como para guardar o aniversário de um usuário.
		Para converter esses objetos para outras medidas de tempo podemos utili-
		zar os métodos to , como é o caso do toLocalDateTime presente na classe
		ZonedDateTime :
		*/

//		java.time.LocalTime agora = LocalTime.now();
//		LocalDate hoje = LocalDate.now();
//		LocalDateTime dataEhora = hoje.atTime(agora);
//		ZonedDateTime dataComHoraETimezone = dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));
//		LocalDateTime semTimeZone = dataComHoraETimezone.toLocalDateTime();
//		System.out.println(semTimeZone);

		/*
		O mesmo pode ser feito com o método
		toLocalDate da classe
		LocalDateTime , entre diversos outros métodos para conversão.
		Além disso, as classes dessa nova API contam com o método estático of , que é
		um factory method para construção de suas novas instâncias:
		*/

//		LocalDate date = LocalDate.of(2014, 12, 25);
//		LocalDateTime dateTime = LocalDateTime.of(2014, 12, 25, 10, 30);
//		System.out.println(date);
//		System.out.println(dateTime);

		/*
		E claro, é muito comum precisarmos instanciar uma data a partir de uma
		String . Para isso, podemos utilizar o método parse que será melhor detalhado
		adiante.
		Repare que todas essas invocações não só podem como devem ser encadeadas
		por um bom motivo: o modelo do java.time é imutável! Cada operação devolve
		um novo valor, nunca alterando o valor interno dos horários, datas e intervalos uti-
		lizados na operação. Isso simplifica muita coisa, não apenas para trabalhar concor-
		rentemente.
		De forma similar aos setter s, os novos modelos imutáveis possuem os méto-
		dos with s para facilitar a inserção de suas informações. Para modificar o ano de
		um LocalDate , por exemplo, poderíamos utilizar o método withYear . Repare:
		*/

//		java.time.LocalDate dataDoPassado = LocalDate.now().withYear(1988);
//		System.out.println(dataDoPassado);

		/*
		Mas e para recuperar essas informações? Podemos utilizar seus métodos get s,
		de acordo com o valor que estamos procurando. Por exemplo, o getYear para
		saber o ano, ou getMonth para o mês, assim por diante.
		*/

//		LocalDate dataDoPassado = LocalDate.now().withYear(1988);
//		System.out.println(dataDoPassado.getYear());

		/*
		Neste caso, a saída será 1988, que foi o valor adicionado pelo método withYear .
		Isso simplifica um pouco nosso código. Em um Calendar , por exemplo, te-
		ríamos que utilizar o método get passando a constante Calendar.YEAR como
		argumento.
		Existem também outros comportamentos essencias, como saber se alguma me-
		dida de tempo acontece antes, depois ou ao mesmo tempo que outra. Para esses
		casos, utilizamos os métodos is :
		*/

//		LocalDate hoje = LocalDate.now();
//		LocalDate amanha = LocalDate.now().plusDays(1);
//		System.out.println(hoje.isBefore(amanha));
//		System.out.println(hoje.isAfter(amanha));
//		System.out.println(hoje.isEqual(amanha));

		/*
		Neste exemplo, apenas o isBefore vai retornar true .
		Há ainda os casos em que queremos comparar datas iguais, porém em
		timezones diferentes. Utilizar o método equals , nesse caso, não causaria o efeito
		esperado — claro, afinal a sobrescrita desse método na classe ZonedDateTime es-
		pera que o offset entre as datas seja o mesmo:
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj instanceof ZonedDateTime) {
				ZonedDateTime other = (ZonedDateTime) obj;
				return dateTime.equals(other.dateTime) &&
						offset.equals(other.offset) &&
						zone.equals(other.zone);
			}
			return false;
		}
		Para estes casos podemos e devemos utilizar o método isEqual exatamente
		como fizemos com o LocalDate. Repare:
		*/

//		ZonedDateTime tokyo = ZonedDateTime
//				.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
//		ZonedDateTime saoPaulo = ZonedDateTime
//				.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
//		System.out.println(tokyo.isEqual(saoPaulo));

		/*
		Não muito diferente do que podemos esperar, o resultado ainda será false .
		Apesar da semelhança entre as datas, elas estão em timezones diferentes, portanto
		não são iguais. Para que o resultado do método isEqual de um ZonedDateTime
		seja true , precisamos acertar a diferença de tempo entre as duas datas. Uma forma
		de fazer isso seria adicionar as 12 horas de diferença na instância de tokyo , repare:
		*/

//		ZonedDateTime tokyo = ZonedDateTime
//				.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
//		ZonedDateTime saoPaulo = ZonedDateTime
//				.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
//		tokyo = tokyo.plusHours(12);
//		System.out.println(tokyo.isEqual(saoPaulo));

		/*
		Agora sim, ao executar o método a saída será true .
		A nova API possui diversos outros modelos que facilitam bastante nosso tra-
		balho com data e tempo, como por exemplo as classes MonthDay , Year e
		YearMonth.
		Para obter o dia do mês atual, por exemplo, poderíamos utilizar o método
		getDayOfMonth de uma instância da classe MonthDay. Repare:
		*/

//		System.out.println("hoje é dia: "+ MonthDay.now().getDayOfMonth());


		/*Você pode pegar o YearMonth de uma determinada data.*/

//		YearMonth ym = YearMonth.from(LocalDate.now());
//		System.out.println(ym.getMonth() + " " + ym.getYear());

		/*
		A vantagem de trabalhar apenas com ano e mês, por exemplo, é poder agrupar
		dados de uma forma mais direta. Com o Calendar , precisaríamos utilizar uma
		data completa e ignorar dia e hora, por exemplo. Soluções incompletas.
		*/

		/*
		10.3
		Enums no lugar de constantes
		*/

		/*
		Essa nova API de datas favorece o uso de Enums no lugar das famosas constantes
		do Calendar. Para representar um mês, por exemplo, podemos utilizar o enum
		Month . Cada valor desse enum tem um valor inteiro que o representa seguindo o
				intervalo de 1 (Janeiro) a 12 (Dezembro). Você não precisa, mas trabalhar com esses
		enums é uma boa prática, afinal seu código fica muito mais legível:
		*/

//		System.out.println(LocalDate.of(2014, 12, 25));
//		System.out.println(LocalDate.of(2014, Month.DECEMBER, 25));

		/*
		Nos dois casos, o valor de saída é 25/12/2014.
		Outra vantagem de utilizar os enums são seus diversos métodos auxiliares. Note
		como é simples consultar o primeiro dia do trimestre de determinado mês, ou então
		incrementar/decrementar meses:
		*/

//		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
//		System.out.println(Month.DECEMBER.plus(2));
//		System.out.println(Month.DECEMBER.minus(1));

		/*
		Para imprimir o nome de um mês formatado, podemos utilizar o método
		getDisplayName fornecendo o estilo de formatação (completo, resumido, entre
		outros) e também o Locale :
		*/

//		java.util.Locale pt = new Locale("pt");
//		System.out.println(Month.DECEMBER
//				.getDisplayName(TextStyle.FULL, pt));
//		System.out.println(Month.DECEMBER
//				.getDisplayName(TextStyle.SHORT, pt));

		/*
		Repare que, como estamos utilizando TextStyle.FULL no primeiro exemplo
		e TextStyle.SHORT no seguinte, teremos como resultado:
		Dezembro Dez
		Outro enum introduzido no java.time foi o DayOfWeek . Com ele, pode-
		mos representar facilmente um dia da semana, sem utilizar constantes ou números
		mágicos!
		*/

		/*
		10.4
		Formatando com a nova API de datas
		A formatação de datas também recebeu melhorias. Formatar um LocalDateTime ,
				por exemplo, é um processo bem simples! Tudo que você precisa fazer é chamar o
		método format passando um DateTimeFormatter como parâmetro.
				Para formatar em horas, por exemplo, podemos fazer algo como:
		*/

//		LocalDateTime agora = LocalDateTime.now();
//		String resultado = agora.format(DateTimeFormatter.ISO_LOCAL_TIME);
//		System.out.println(resultado);

		/*
		Um exemplo de resultado seria 01:15:45 , ou seja, o pattern é hh:mm:ss .
		Note que usamos um
		DateTimeFormatter predefinido, o
		ISO_LOCAL_TIME . Assim como ele existem diversos outros que você pode
		ver no javadoc do DateTimeFormatter .
		Mas como criar um DateTimeFormatter com um novo padrão? Uma das
		formas é usando o método ofPattern , que recebe uma String como parâmetro:
		*/

//		LocalDateTime agora = LocalDateTime.now();
//		String resultado = agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//		System.out.println(resultado);

		/*
		O resultado do método format seria, por exemplo, 06/02/2014. Tam-
		bém existe uma sobrecarga desse método que, além do pattern, recebe um
		java.util.Locale .
		De forma parecida, podemos transformar uma String em alguma represen-
		tação de data ou tempo válida, e para isso utilizamos o método parse ! Podemos,
		por exemplo, retornar o nosso resultado anterior em um LocalDate utilizando o
		mesmo formatador:
		*/

//		LocalDateTime agora = LocalDateTime.now();
//		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		String resultado = agora.format(formatador);
//		LocalDate agoraEmData = LocalDate.parse(resultado, formatador);
//		System.out.println(agoraEmData);

		/*
		Repare que fizemos o parse desse resultado em um LocalDate , e não em
		um LocalDateTime , que é o tipo da data inicial. Não poderíamos retor-
		nar um LocalDateTime , afinal quando formatamos em data (com a padrão
		dd/MM/yyyy ), perdemos as informações de tempo! Essa tentativa resultaria em
		uma DateTimeParseException !
		*/

		/*
		10.5
		Datas inválidas
		Ao trabalhar com Calendar , alguma vez você já pode ter se surpreendido ao exe-
		cutar um código como este:
		*/

//		Calendar instante = Calendar.getInstance();
//		instante.set(2014, Calendar.FEBRUARY, 30);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		System.out.println(dateFormat.format(instante.getTime()));


		/*
		Levando em consideração que fevereiro deste ano só vai até o dia 28, qual será
		o resultado? Uma exception? Esse pode ser o resultado esperado, afinal estamos
		passando um argumento inválido!
		Mas, na verdade, o resultado será: 02/03/14. Isso mesmo, o Calendar ajus-
		tou o mês e dia, sem dar nenhum feedback desse erro que provavelmente passaria
		despercebido.
		Nem sempre isso é o que esperamos ao tentar criar uma data com valor inválido.
		Muito diferente desse comportamento, a nova API de datas vai lançar uma
		DateTimeException em casos como esse. Repare:
		*/

//		LocalDate.of(2014, Month.FEBRUARY, 30);

		/*
		O resultado será:
		java.time.DateTimeException: Invalid date
		'FEBRUARY 30' .
		O mesmo acontecerá se eu tentar criar um LocalDateTime com um horário
		inválido:
		*/

//		java.time.LocalDateTime horaInvalida = LocalDate.now().atTime(25, 0);


		/*
		Neste caso, a exceptions será: java.time.DateTimeException:Invalid
		value for HourOfDay (valid values 0 - 23): 25 .
		A própria API do Joda-time deixou passar um problema parecido ao criar a
		classe DateMidnight . Na documentação do método toDateMidnight da classe
		LocalDate , você vai encontrar a seguinte nota:
		“Desde a versão 1.5, recomendamos que você evite o DateMidnight e passe a
		utilizar o método toDateTimeAtStartOfDay por causa da exceção que será de-
		talhada a seguir. Esse método vai lançar uma exceção se o horário de verão padrão
		começar à meia noite e esse LocalDate represente esse horário da troca. O problema
		é que não existe meia noite nessa data e, assim, uma exceção é lançada”.
		Ou seja, esse método é um tanto perigoso, pois sempre lança uma exceção no
		dia em que começa o horário de verão. O próprio Stephen Colebourne disse em seu
		blog que não existe uma boa razão para algum dia utilizar a classe DateMidnight .
		E claro, essa classe não está presente na nova API de datas.
		*/

		/*
		10.6
		Duração e Período
		Sempre foi problemático trabalhar com a diferença de alguma medida de tempo em
		Java. Por exemplo, como calcular a diferença de dias entre dois Calendar s? Você
		possivelmente já passou por isso:
		*/

//		Calendar agora = Calendar.getInstance();
//		Calendar outraData = Calendar.getInstance();
//		outraData.set(1988, Calendar.JANUARY, 25);
//		long diferenca = agora.getTimeInMillis() - outraData.getTimeInMillis();
//		long milissegundosDeUmDia = 1000 * 60 * 60 * 24;
//		long dias = diferenca / milissegundosDeUmDia;
//		System.out.println(dias);

		/*
		Resolvemos o problema, claro, mas sabemos que trabalhar com a diferença de
		milissegundos pode não ser o ideal. Há também alguns casos particulares que não
		são cobertos nesse caso. E a cada nova necessidade, precisaríamos criar um código
		igualmente verboso e difícil de manter.
		Agora podemos fazer essa mesma operação de forma muito mais simples, utili-
		zando o enum ChronoUnit da nova api:
		*/

//		LocalDate agora = LocalDate.now();
//		LocalDate outraData = LocalDate.of(1989, Month.JANUARY, 25);
//		long dias = ChronoUnit.DAYS.between(outraData, agora);
//		System.out.println(dias);

		/*
		Esse enum está presente no pacote java.time.temporal e possui uma re-
		presentação para cada uma das diferentes medidas de tempo e data. Além disso,
		possui vários métodos para facilitar o calculo de diferença entre seus valores e que
		nos auxiliam a extrair informações úteis, como é o caso do between .
		Mas e se também quisermos saber a diferença de anos e meses entre essas duas
		datas? Poderíamos utilizar o ChronoUnit.YEARS e ChronoUnit.MONTHS para
		obter essas informações, mas ele vai calcular cada uma das medidas de forma sepa-
		rada. Repare:
		*/

//		LocalDate agora = LocalDate.now();
//		LocalDate outraData = LocalDate.of(2015, Month.JANUARY, 25);
//		long dias = ChronoUnit.DAYS.between(outraData, agora);
//		long meses = ChronoUnit.MONTHS.between(outraData, agora);
//		long anos = ChronoUnit.YEARS.between(outraData, agora);
//		System.out.printf("%s dias, %s meses e %s anos", dias, meses, anos);

		/*
		Neste caso, o resultado será algo como: 9147 dias, 300 meses e 25 anos.
		Uma forma de conseguir o resultado que esperamos: os dias, meses e anos entre
		duas datas, é utilizando o modelo Period . Essa classe da API também possui o
		método between , que recebe duas instâncias de LocalDate :
		*/

//		LocalDate agora = LocalDate.now();
//		LocalDate outraData = LocalDate.of(2015, Month.JANUARY, 25);
//		Period periodo = Period.between(outraData, agora);
//		System.out.printf("%s dias, %s meses e %s anos", periodo.getDays(), periodo.getMonths(), periodo.getYears());

		/*
		Note que agora o resultado será: 16 dias, 0 meses e 25 anos.
		A classe Period tem uma série de métodos que auxiliam nas diversas situações
		que enfrentamos ao trabalhar com datas. Por exemplo, ao calcular uma diferença
		entre datas, é comum a necessidade de lidarmos com valores negativos. Observe o
		que acontece se alterarmos o ano da outraData para 2015:
		*/

//		LocalDate agora = LocalDate.now();
//		LocalDate outraData = LocalDate.of(2015, Month.JANUARY, 25);
//		Period periodo = Period.between(outraData, agora);
//		System.out.printf("%s dias, %s meses e %s anos", periodo.getDays(), periodo.getMonths(), periodo.getYears());

		/*
		A saída será algo como: -15 dias, -11 meses e 0 anos.
		Essa pode ser a saída esperada, mas caso não seja, podemos facilmente per-
		guntar ao Period se ele é um período de valores negativos invocando o método
		isNegative . Caso seja, poderíamos negar seus valores com o método negated ,
		repare:
		*/

//		LocalDate agora = LocalDate.now();
//		LocalDate outraData = LocalDate.of(2015, Month.JANUARY, 25);
//		Period periodo = Period.between(outraData, agora);
//		System.out.println(periodo.isNegative());
//		System.out.printf("%s dias, %s meses e %s anos", periodo.getDays(), periodo.getMonths(), periodo.getYears());

		/*
		Agora a saída terá seus valores invertidos: 15 dias, 11 meses e 0 anos.
		Existem diversas outras formas de se criar um Period , além do método
		between . Uma delas é utilizando o método of(years, months, days) de
		forma fluente:
		Period periodo = Period.of(2, 10, 5);
		*/


		/*
		Também podemos criar um período com apenas dias, meses ou anos utilizando
		os métodos: ofDays , ofMonths ou ofYears .
		Mas como criar um período de horas, minutos ou segundos? A resposta é:
		não criamos. Neste caso, estamos interessados em outra medida de tempo, que é
		a Duration . Enquanto um Period considera as medidas de data (dias, meses e
		anos), a Duration considera as medidas de tempo (horas, minutos, segundos etc.).
		Sua API é muito parecida, observe:
		*/


		LocalDateTime agora = LocalDateTime.now();
		LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1);
		Duration duration = Duration.between(agora, daquiAUmaHora);
		if (duration.isNegative()) {
			duration = duration.negated();
		}
		System.out.printf("%s horas, %s minutos e %s segundos", duration.toHours(), duration.toMinutes(), duration.getSeconds());

		/*
		Repare que, como agora estamos trabalhando com tempo, utilizamos a classe
		LocalDateTime .
		*/

		/*
		10.7
		Diferenças para o Joda Time
		É importante lembrar que a nova API de datas (JSR-310) é baseada no Joda-Time,
		mas que não é uma cópia. Existem algumas diferenças de design que foram cuida-
		dosamente apontadas pelo Stephen Colebourne em seu blog, no artigo Why JSR-310
		isn’t Joda-Time:
		http://blog.joda.org/2009/11/why-jsr-310-isn-joda-time_4941.html.
		As principais mudanças foram inspiradas pelas falhas de design do Joda-Time,
		como é o caso das referências nulas. No Joda-Time não só era possível fornecer
		valores nulos para grande maioria de seus métodos, como isso tinha um significado
		pra cada modelo. Por exemplo, para as representações de data e tempo o valor null
		significava 1970-01-01T00:00Z . Para as classes Duration e Period , null
		significava zero.
		Essa é uma estratégia de design um tanto inesperada, e que pode facilmente cau-
		sar bugs em nossas aplicações.
		Há ainda o caso da classe DateTime que implementa ReadableInstant ,
		sendo que como uma interpretação humana de tempo não deveria. No fim, ela acaba
		sendo uma projeção de uma linha do tempo de máquina em uma linha do tempo
		humana, ficando limitada à precisão de milissegundos.
		 */

	}

}
