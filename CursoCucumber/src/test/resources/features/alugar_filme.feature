# language: pt
@unitários
Funcionalidade: Alugar Filme
	Como um usuário
	Eu quero cadastrar aluguéis de filmes
	Para controlar preços e datas de entrega
	
Cenário: Deve alugar um filme com sucesso
	Dado um filme
		| estoque | 2     |
		| preco   | 3     |
		| tipo    | comum |
	Quando alugar
	Então o preço do aluguel será R$ 3
	E a data de entrega será em 1 dia
	E o estoque do filme será 1 unidade	
	
Cenário: Não deve alugar filme sem estoque
	Dado um filme com estoque de 0 unidades
	Quando alugar
	Então não será possível por falta de estoque
	E o estoque do filme será 0 unidade
	
Esquema do Cenário: Deve dar condições conforme tipo de aluguel
	Dado um filme com estoque de 2 unidades
	E que o preço do aluguel seja R$ <preco>
	E que tipo do aluguel seja <tipo>
	Quando alugar
	Então o preço do aluguel será R$ <valor>
	E a data de entrega será em <qtdDias> dia
	E a pontuação será de <pontuacao> pontos
	
Exemplos:
	| preco |    tipo   | valor | qtdDias | pontuacao |
	|   4   |   COMUM   |   4   |   1     |     1     |
	|   4   |   COMUM   |   4   |   1     |     1     |
	|  10   | EXTENDIDO |  20   |   3     |     2     |
	|  5    |  SEMANAL  |  15   |   7     |     3     |